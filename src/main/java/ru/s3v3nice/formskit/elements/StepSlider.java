package ru.s3v3nice.formskit.elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public final class StepSlider extends Element {
    private final List<String> steps;
    private int value;

    public StepSlider(String text, List<String> steps) {
        this(text, steps, 0);
    }

    public StepSlider(String text, List<String> steps, int defaultValue) {
        super(Element.STEP_SLIDER, text);

        this.steps = steps;
        this.value = Math.max(Math.min(defaultValue, steps.size()), 0);
    }

    public int getValue() {
        return value;
    }

    public String getValueStr() {
        return steps.get(value);
    }

    @Override
    public void setValue(String valueStr) {
        value = Integer.parseInt(valueStr);
    }

    @Override
    public JsonObject serialize() {
        JsonObject data = super.serialize();

        JsonArray stepsData = new JsonArray();
        for (String step : steps) {
            stepsData.add(step);
        }

        data.add("steps", stepsData);
        data.addProperty("default", value);
        return data;
    }
}
