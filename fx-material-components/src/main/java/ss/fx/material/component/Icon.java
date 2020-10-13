/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import ss.fx.material.constants.MaterialIcon;

/**
 * Material icon.
 * @author alex
 */
public class Icon extends Label {
    /** Icon font. */
    protected static final Font font = Font.loadFont(Icon.class.getResourceAsStream("font/maticon.ttf"), 24);
    /** Icon. */
    private final ObjectProperty<MaterialIcon> icon = new SimpleObjectProperty<>(null);
    /**
     * Constructor.
     */
    public Icon() {
        this.setFont(font);
        this.getStylesheets().add(getClass().getResource("mat-icon.css").toExternalFo‌​rm());
        this.getStyleClass().add("material-icons");
    }
    // ======================================================== SET & GET =================================================================
    public MaterialIcon getIcon() {
        return this.icon.get();
    }
    public void setIcon(MaterialIcon icon) {
        this.icon.set(icon);
        this.setText(String.valueOf(this.icon.get().getSymbol()));
    }
}
