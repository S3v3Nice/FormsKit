package ru.s3v3nice.formskit.forms;

import com.google.gson.JsonObject;

public abstract class ModalForm extends Form {
    private final String button1Text;
    private final String button2Text;
    private boolean response;

    public ModalForm(String title, String text, String button1Text, String button2Text) {
        super(MODAL, title, text);

        this.button1Text = button1Text;
        this.button2Text = button2Text;
    }

    public final boolean isResponse() {
        return response;
    }

    public final void setResponse(boolean response) {
        this.response = response;
    }

    @Override
    public final void setText(String text) {
        super.setText(text);
    }

    @Override
    public final JsonObject serialize() {
        JsonObject data = super.serialize();

        data.addProperty("content", getText());
        data.addProperty("button1", button1Text);
        data.addProperty("button2", button2Text);

        return data;
    }
}
