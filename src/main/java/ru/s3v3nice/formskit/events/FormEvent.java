package ru.s3v3nice.formskit.events;

import cn.nukkit.Player;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import ru.s3v3nice.formskit.forms.Form;

public abstract class FormEvent extends Event {
    private final Form form;
    private final Player player;
    private static final HandlerList handlers = new HandlerList();

    public FormEvent(Form form, Player player) {
        this.form = form;
        this.player = player;
    }

    public static HandlerList getHandlers() {
        return handlers;
    }

    public Form getForm() {
        return form;
    }

    public Player getPlayer() {
        return player;
    }
}
