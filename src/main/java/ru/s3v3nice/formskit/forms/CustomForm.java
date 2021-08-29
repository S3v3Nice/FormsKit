package ru.s3v3nice.formskit.forms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ru.s3v3nice.formskit.elements.CustomElement;
import ru.s3v3nice.formskit.elements.Element;
import ru.s3v3nice.formskit.elements.Label;

import java.util.LinkedList;
import java.util.List;

public abstract class CustomForm extends Form {
    private final List<CustomElement> elements = new LinkedList<>();

    public CustomForm(String title) {
        this(title, "");
    }

    public CustomForm(String title, String text) {
        super(CUSTOM, title, text);

        if (!text.isEmpty()) {
            elements.add(new Label(text + "\n\n"));
        }
    }

    public final List<CustomElement> getElements() {
        return elements;
    }

    public final void addElement(CustomElement element) {
        elements.add(element);
    }

    public final void removeElement(CustomElement element) {
        elements.remove(element);
    }

    @Override
    public final void setText(String text) {
        super.setText(text);

        if (!elements.isEmpty()) {
            Element label = elements.get(0);

            if (label instanceof Label) {
                if (text.isEmpty()) {
                    elements.remove(0);
                } else {
                    label.setText(text + "\n\n");
                }
                return;
            }
        }

        if (!text.isEmpty()) {
            elements.add(0, new Label(text + "\n\n"));
        }
    }

    @Override
    public final JsonObject serialize() {
        JsonObject data = super.serialize();

        JsonArray content = new JsonArray();
        for (Element element : elements) {
            content.add(element.serialize());
        }

        data.add("content", content);
        return data;
    }
}
