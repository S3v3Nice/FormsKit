package ru.s3v3nice.formskit;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.Event;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.ModalFormResponsePacket;
import com.google.gson.*;
import ru.s3v3nice.formskit.elements.CustomElement;
import ru.s3v3nice.formskit.elements.Label;
import ru.s3v3nice.formskit.elements.ValueElement;
import ru.s3v3nice.formskit.events.FormCloseEvent;
import ru.s3v3nice.formskit.events.FormResponseEvent;
import ru.s3v3nice.formskit.forms.CustomForm;
import ru.s3v3nice.formskit.forms.Form;
import ru.s3v3nice.formskit.forms.ModalForm;
import ru.s3v3nice.formskit.forms.SimpleForm;

import java.util.List;

public final class EventListener implements Listener {
    @EventHandler
    public void onFormPacket(DataPacketReceiveEvent event) {
        if (!(event.getPacket() instanceof ModalFormResponsePacket packet)) return;

        Player player = event.getPlayer();
        Form form = FormsManager.getForm(player);

        if (form == null || packet.formId != form.getId()) return;

        JsonElement data = JsonParser.parseString(packet.data);

        Event e = data instanceof JsonNull ? new FormCloseEvent(form, player) :
                new FormResponseEvent(form, player, data);
        Server.getInstance().getPluginManager().callEvent(e);
    }

    @EventHandler
    public void onFormResponse(FormResponseEvent event) {
        try {
            Form form = event.getForm();
            Player player = event.getPlayer();
            JsonElement data = event.getData();

            switch (form.getType()) {
                case Form.SIMPLE -> ((SimpleForm) form).setResponse(data.getAsInt());
                case Form.CUSTOM -> {
                    List<CustomElement> elements = ((CustomForm) form).getElements();
                    JsonArray dataArray = data.getAsJsonArray();

                    for (int i = 0; i < elements.size(); i++) {
                        if (elements.get(i) instanceof ValueElement valueElement) {
                            valueElement.setValue(dataArray.get(i).getAsString());
                        }
                    }
                }
                case Form.MODAL -> ((ModalForm) form).setResponse(data.getAsBoolean());
            }

            form.setClosed();
            form.onResponse();

            if (form instanceof CustomForm) {
                Server.getInstance().getPluginManager().callEvent(new FormCloseEvent(form, player));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @EventHandler
    public void onFormClose(FormCloseEvent event) {
        Player player = event.getPlayer();
        Form form = event.getForm();

        boolean isClosedByPlayer = !form.isClosed();

        if (isClosedByPlayer) {
            form.setClosed();
            form.onClose();
        }

        FormsManager.removeForm(player, form);

        if (form.isCancelled()) {
            form.setClosed(false);
            form.setCancelled(false);

            FormsKit.send(player, form);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        FormsManager.removeForm(player);
    }
}
