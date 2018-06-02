package de.tosken.dockerui.managedbean;

import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 * dockerui
 * User: Sebastian
 * Date: 19.05.2018
 * Time: 21:40
 */
@Named
public class IndexViewModel {
    private String inputText = "";

    public String getText() {
        return "Hello World! " + inputText;
    }

    public void onAction() {
        System.out.println("asdasd");
    }

    public void onActionListener(ActionEvent actionEvent) {
        System.out.println("asd");
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getInputText() {
        return inputText;
    }
}
