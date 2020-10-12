/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import ss.fx.material.api.PaletteColored;
import ss.fx.material.common.Theme;
import static ss.fx.material.component.MatIcon.font;
import ss.fx.material.constants.Icon;
import ss.fx.material.constants.Palette;

/**
 * Icon button.
 * @author alex
 */
public class MatIconButton extends Button implements PaletteColored {
    /** Icon. */
    private final ObjectProperty<Icon> icon = new SimpleObjectProperty<>(null);
    /** Color. */
    private final ObjectProperty<Palette> color = new SimpleObjectProperty<>(Palette.LIGHT);
    /**
     * Constructor.
     */
    public MatIconButton() {
        this.setFont(font);
        this.getStylesheets().add(getClass().getResource("mat-icon-button.css").toExternalFo‌​rm());
        this.getStyleClass().add("mat-icon-button");
        Theme.getTheme().applyPaletteContrastColor(this, this.color.get());
    }
    // ======================================================== SET & GET =================================================================
    public Icon getIcon() {
        return this.icon.get();
    }
    public void setIcon(Icon icon) {
        this.icon.set(icon);
        this.setText(String.valueOf(this.icon.get().getSymbol()));
    }
    @Override
    public Palette getColor() {
        return this.color.get();
    }
    @Override
    public void setColor(Palette color) {
        this.color.set(color);
    }
}
