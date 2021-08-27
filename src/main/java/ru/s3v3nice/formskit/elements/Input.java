package ru.s3v3nice.formskit.elements;

import com.google.gson.JsonObject;

public final class Input extends Element {
    private final String placeholder;
    private String value;

    public Input(String text) {
        this(text, "", "");
    }

    public Input(String text, String placeholder) {
        this(text, placeholder, "");
    }

    public Input(String text, String placeholder, String defaultValue) {
        super(Element.INPUT, text);

        this.placeholder = placeholder;
        this.value = defaultValue;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public JsonObject serialize() {
        JsonObject data = super.serialize();

        data.addProperty("placeholder", placeholder);
        data.addProperty("default", value);
        return data;
    }
}
