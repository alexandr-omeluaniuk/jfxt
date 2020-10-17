/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import static ss.fx.material.component.MdIcon.font;
import ss.fx.material.constants.MaterialIcon;
import ss.fx.material.core.Theme;

/**
 * Icon button.
 * @author alex
 */
public class MdIconButton extends Button {
    /** Icon. */
    private final ObjectProperty<MaterialIcon> icon = new SimpleObjectProperty<>(null);
    /**
     * Constructor.
     */
    public MdIconButton() {
        this.setFont(font);
        this.getStylesheets().add(getClass().getResource("mat-icon-button.css").toExternalFo‌​rm());
        this.getStyleClass().add("mat-icon-button");
        Theme.subscribeThemeChanges(() -> {
            this.setStyle("-fx-text-fill: " + Theme.getContrastPaletteColor(Theme.getParentPaletteColor(this)) + ";");
        });
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
