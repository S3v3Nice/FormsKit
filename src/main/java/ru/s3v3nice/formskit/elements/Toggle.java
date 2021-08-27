package ru.s3v3nice.formskit.elements;

import com.google.gson.JsonObject;

public final class Toggle extends Element {
    private boolean value;

    public Toggle(String text) {
        this(text, true);
    }

    public Toggle(String text, boolean defaultValue) {
        super(Element.TOGGLE, text);
        value = defaultValue;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public void setValue(String valueStr) {
        value = Boolean.parseBoolean(valueStr);
    }

    @Override
    public JsonObject serialize() {
        JsonObject data = super.serialize();

        data.addProperty("default", value);
        return data;
    }
}
