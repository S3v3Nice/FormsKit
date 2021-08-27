# FormsKit

## Introduction

Library for Cloudburst Nukkit that makes creating and managing forms very convenient and easy.
It has a few key advantages over other form libraries:

* The form is created via a separate class, allowing you to customise the form in the most convenient way.
* The code that is executed after a player response is placed in the body of the onResponse (or onClose) method in the form class.
* It is possible to "cancell" form closure by calling setCancelled(). And calling setError() will cancel the form closure and set the error message.
* You can change the form title, text, buttons, etc. at any time.

## Sending the form
```java
FormsKit.send(player, new TestForm());
```

## Form types

**SimpleForm example:**
```java
package ru.s3v3nice.testforms.forms;

import cn.nukkit.Player;
import ru.s3v3nice.formskit.elements.Button;
import ru.s3v3nice.formskit.forms.SimpleForm;

public class TestForm extends SimpleForm {
    private final Player player;

    public TestForm(Player player) {
        super("Form title", "Some text...");
        this.player = player;
        
        addButton("Button 1 text");
        addButton(new Button("Button 2 text", "path", "textures/ui/color_plus"));
    }

    @Override
    public void onResponse() {
        int clickedButton = getResponse();
        if (clickedButton == 0) {
            player.sendMessage("You clicked 1 button");
        } else {
            setError("You should not click this button, muahahaha");
        }
    }

    @Override
    public void onClose() {
        player.sendMessage("You closed form");
    }
}
```
![screenshot of sample](http://images.vfl.ru/ii/1630056009/f4807997/35644700_m.png)

**CustomForm example:**
```java
package ru.s3v3nice.testforms.forms;

import cn.nukkit.Player;
import ru.s3v3nice.formskit.elements.Input;
import ru.s3v3nice.formskit.elements.Toggle;
import ru.s3v3nice.formskit.forms.CustomForm;

public class TestForm2 extends CustomForm {
    private final Player player;
    private Input cvvInput;
    private Toggle scamToggle;

    public TestForm2(Player player) {
        super("Form title!", "Optional text");
        this.player = player;

        addElement(cvvInput = new Input("Enter your cvv here", "For example, 123"));
        addElement(scamToggle = new Toggle("Don't scam me", false));
    }

    @Override
    public void onResponse() {
        String cvv = cvvInput.getValue();
        boolean isScam = !scamToggle.getValue();

        if (isScam) {
            player.sendMessage("Haha, you're scammed, we know your your cvv is " + cvv);
        } else {
            player.sendMessage("Okay, sorry");
        }
    }
}
```
![screenshot of sample](http://images.vfl.ru/ii/1630056024/e5b69e79/35644703_m.png)

**ModalForm example:**
```java
package ru.s3v3nice.testforms.forms;

import cn.nukkit.Player;
import ru.s3v3nice.formskit.forms.ModalForm;

public class TestForm3 extends ModalForm {
    private final Player player;

    public TestForm3(Player player) {
        super("WHAT ARE YOU DOING?", "ARE YOU SURE?", "Yes...", "No!");
        this.player = player;
    }

    @Override
    public void onResponse() {
        if (isResponse()) {
            player.kick("Oh, you chose yes... Bye-bye :c");
        } else {
            player.sendMessage("You chose no, thank you :3");
        }
    }
}
```
![screenshot of sample](http://images.vfl.ru/ii/1630056025/d55aedfd/35644709_m.png)
