package ru.s3v3nice.formskit.forms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ru.s3v3nice.formskit.elements.Button;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleForm extends Form {
    private final List<Button> buttons = new ArrayList<>();
    private int response;

    public SimpleForm(String title, String text) {
        super(Form.SIMPLE, title, text);
    }

    public final void addButton(Button button) {
        buttons.add(button);
    }

    public final void addButton(String text) {
        addButton(text, "", "");
    }

    public final void addButton(String text, String imgType, String img) {
        buttons.add(new Button(text, imgType, img));
    }

    public final void removeButton(int buttonIndex) {
        if (buttons.size() > buttonIndex && buttonIndex > -1) {
            buttons.remove(buttonIndex);
        }
    }

    public final void removeButton(Button button) {
        buttons.remove(button);
    }

    public final String getResponseStr() {
        return buttons.get(response).getText();
    }

    public final int getResponse() {
        return response;
    }

    public final void setResponse(int response) {
        this.response = response;
    }

    @Override
    public final void setText(String text) {
        super.setText(text);
    }

    @Override
    public final JsonObject serialize() {
        JsonObject data = super.serialize();

        JsonArray buttonsData = new JsonArray();
        for (Button button : buttons) {
            buttonsData.add(button.serialize());
        }

        data.add("buttons", buttonsData);
        data.addProperty("content", getText() + "\n\n");
        return data;
    }
}