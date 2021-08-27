package ru.s3v3nice.formskit.elements;

import com.google.gson.JsonObject;

public final class Slider extends Element {
    private final float min;
    private final float max;
    private final float step;
    private float value;

    public Slider(String text, float min, float max) {
        this(text, min, max, 1, min);
    }

    public Slider(String text, float min, float max, float step) {
        this(text, min, max, step, min);
    }

    public Slider(String text, float min, float max, float step, float defaultValue) {
        super(Element.SLIDER, text);

        this.min = min;
        this.max = max;
        this.step = step;
        this.value = Math.max(Math.min(defaultValue, max), min);
    }

    public float getValue() {
        return value;
    }

    @Override
    public void setValue(String valueStr) {
        value = Float.parseFloat(valueStr);
    }

    @Override
    public JsonObject serialize() {
        JsonObject data = super.serialize();

        data.addProperty("min", min);
        data.addProperty("max", max);
        data.addProperty("step", step);
        data.addProperty("default", value);
        return data;
    }
}
