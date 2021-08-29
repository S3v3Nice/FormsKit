package ru.s3v3nice.formskit.elements;

import com.google.gson.JsonObject;

public abstract class Element {
    public static final String BUTTON = "button";
    public static final String LABEL = "label";
    public static final String TOGGLE = "toggle";
    public static final String SLIDER = "slider";
    public static final String STEP_SLIDER = "step_slider";
    public static final String DROPDOWN = "dropdown";
    public static final String INPUT = "input";

    private final String type;
    private String text;

    public Element(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public JsonObject serialize() {
        JsonObject data = new JsonObject();
        data.addProperty("type", type);
        data.addProperty("text", text);

        return data;
    }
}
