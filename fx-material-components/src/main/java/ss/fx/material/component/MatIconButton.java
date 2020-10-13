/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import ss.fx.material.api.ContrastColor;
import static ss.fx.material.component.MatIcon.font;
import ss.fx.material.constants.Icon;

/**
 * Icon button.
 * @author alex
 */
public class MatIconButton extends Button implements ContrastColor {
    /** Icon. */
    private final ObjectProperty<Icon> icon = new SimpleObjectProperty<>(null);
    /**
     * Constructor.
     */
    public MatIconButton() {
        this.setFont(font);
        this.getStylesheets().add(getClass().getResource("mat-icon-button.css").toExternalFo‌​rm());
        this.getStyleClass().add("mat-icon-button");
    }
    // ======================================================== SET & GET =================================================================
    public Icon getIcon() {
        return this.icon.get();
    }
    public void setIcon(Icon icon) {
        this.icon.set(icon);
        this.setText(String.valueOf(this.icon.get().getSymbol()));
    }
}
