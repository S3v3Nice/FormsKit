package ru.s3v3nice.formskit.forms;

import cn.nukkit.utils.TextFormat;
import com.google.gson.JsonObject;

public abstract class Form {
    public static final String SIMPLE = "form";
    public static final String CUSTOM = "custom_form";
    public static final String MODAL = "modal";

    private static int count;
    private final int id;
    private final String type;
    private String title;
    private String text;
    private boolean isCancelled = false;
    private boolean isClosed = false;

    public Form(String type, String title, String text) {
        this.id = count;
        this.type = type;
        this.title = title;
        this.text = text;

        count = count == Integer.MAX_VALUE ? 0 : count + 1;
    }

    public final String getType() {
        return type;
    }

    public final int getId() {
        return id;
    }

    public final String getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        this.title = title;
    }

    public final String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public final boolean isCancelled() {
        return isCancelled;
    }

    public final void setCancelled() {
        setCancelled(true);
    }

    public final void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public final boolean isClosed() {
        return isClosed;
    }

    public final void setClosed() {
        setClosed(true);
    }

    public final void setClosed(boolean closed) {
        isClosed = closed;
    }

    public final void setError(String error, String hint) {
        setText(TextFormat.RED + "Ошибка:\n" + TextFormat.RESET + error +
                TextFormat.YELLOW + "\n\nПодсказка:\n" + TextFormat.RESET + hint);
        setCancelled();
    }

    public final void setError(String error) {
        setText(TextFormat.RED + "Ошибка:\n" + TextFormat.RESET + error);
        setCancelled();
    }

    public void onResponse() {
    }

    public void onClose() {
    }

    public JsonObject serialize() {
        JsonObject data = new JsonObject();
        data.addProperty("type", type);
        data.addProperty("title", title);

        return data;
    }
}