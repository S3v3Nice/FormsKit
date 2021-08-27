package ru.s3v3nice.formskit;

import cn.nukkit.Player;
import ru.s3v3nice.formskit.forms.Form;

import java.util.HashMap;
import java.util.Map;

public final class FormsManager {
    private static final Map<String, Form> forms = new HashMap<>();

    public static void addForm(Player player, Form form) {
        String nick = player.getName().toLowerCase();
        forms.put(nick, form);
    }

    public static void removeForm(Player player) {
        String nick = player.getName().toLowerCase();
        forms.remove(nick);
    }

    public static void removeForm(Player player, Form form) {
        String nick = player.getName().toLowerCase();
        forms.remove(nick, form);
    }

    public static Form getForm(Player player) {
        String nick = player.getName().toLowerCase();
        return forms.get(nick);
    }
}