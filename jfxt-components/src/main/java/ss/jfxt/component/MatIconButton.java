/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.jfxt.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import static ss.jfxt.component.MatIcon.font;
import ss.jfxt.component.constants.Icon;

/**
 * Icon button.
 * @author alex
 */
public class MatIconButton extends Button {
    /** Icon. */
    private final ObjectProperty<Icon> icon = new SimpleObjectProperty<>(null);
    /**
     * Constructor.
     */
    public MatIconButton() {
        this.setFont(font);
        this.getStylesheets().add(getClass().getResource("mat-icon.css").toExternalFo‌​rm());
        this.getStyleClass().add("material-icons");
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
