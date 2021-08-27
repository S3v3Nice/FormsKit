package ru.s3v3nice.formskit;

import cn.nukkit.Player;
import cn.nukkit.network.protocol.ModalFormRequestPacket;
import cn.nukkit.plugin.PluginBase;

import ru.s3v3nice.formskit.forms.Form;

public final class FormsKit extends PluginBase {
    private static FormsKit instance;

    public static FormsKit getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    public static void send(Player player, Form form) {
        if (player == null || form == null) return;

        Form formCurrent = FormsManager.getForm(player);
        if (formCurrent != null && !formCurrent.isClosed()) return;

        form.setCancelled(false);
        form.setClosed(false);

        ModalFormRequestPacket packet = new ModalFormRequestPacket();
        packet.formId = form.getId();
        packet.data = form.serialize().toString();

        player.dataPacket(packet);
        FormsManager.addForm(player, form);
    }
}