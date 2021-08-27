package ru.s3v3nice.formskit.events;

import cn.nukkit.Player;
import com.google.gson.JsonElement;
import ru.s3v3nice.formskit.forms.Form;

public final class FormResponseEvent extends FormEvent {
    private final JsonElement data;

    public FormResponseEvent(Form form, Player player, JsonElement data) {
        super(form, player);
        this.data = data;
    }

    public JsonElement getData() {
        return data;
    }
}
