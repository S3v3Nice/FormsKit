package ru.s3v3nice.formskit.events;

import cn.nukkit.Player;
import ru.s3v3nice.formskit.forms.Form;

public final class FormCloseEvent extends FormEvent {

    public FormCloseEvent(Form form, Player player) {
        super(form, player);
    }
}
