/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.jfxt.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import ss.jfxt.component.constants.Icon;

/**
 * Material icon.
 * @author alex
 */
public class MatIcon extends Label {
    /** Icon font. */
    private static final Font font = Font.loadFont(MatIcon.class.getResourceAsStream("font/maticon.ttf"), 24);
    /** Icon. */
    private final ObjectProperty<Icon> icon = new SimpleObjectProperty<>(null);
    /**
     * Constructor.
     */
    public MatIcon() {
        this.setFont(font);
        this.getStylesheets().add(getClass().getResource("mat-icon.css").toExternalFo‌​rm());
        this.getStyleClass().add("material-icons");
    }
    public Icon getIcon() {
        return this.icon.get();
    }
    public void setIcon(Icon icon) {
        this.icon.set(icon);
        this.setText(String.valueOf(this.icon.get().getSymbol()));
    }
}
