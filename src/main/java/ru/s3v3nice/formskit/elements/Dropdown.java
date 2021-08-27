package ru.s3v3nice.formskit.elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public final class Dropdown extends Element {
    private final List<String> options;
    private int value;

    public Dropdown(String text, List<String> options) {
        this(text, options, 0);
    }

    public Dropdown(String text, List<String> options, int defaultValue) {
        super(Element.DROPDOWN, text);

        this.options = options;
        this.value = defaultValue;
    }

    public int getValue() {
        return value;
    }

    public String getValueStr() {
        return options.get(value);
    }

    @Override
    public void setValue(String valueStr) {
        value = Integer.parseInt(valueStr);
    }

    @Override
    public JsonObject serialize() {
        JsonObject data = super.serialize();

        JsonArray optionsData = new JsonArray();
        for (String option : options) {
            optionsData.add(option);
        }

        data.add("options", optionsData);
        data.addProperty("default", value);
        return data;
    }
}
