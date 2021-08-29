package ru.s3v3nice.formskit.elements;

import com.google.gson.JsonObject;

public final class Button extends Element {
    private final String imgType;
    private final String img;

    public Button(String text) {
        this(text, "", "");
    }

    public Button(String text, String imgType, String img) {
        super(BUTTON, text);
        this.imgType = imgType;
        this.img = img;
    }

    @Override
    public JsonObject serialize() {
        JsonObject data = super.serialize();

        JsonObject image = new JsonObject();
        image.addProperty("type", imgType);
        image.addProperty("data", img);

        data.add("image", image);

        return data;
    }
}
