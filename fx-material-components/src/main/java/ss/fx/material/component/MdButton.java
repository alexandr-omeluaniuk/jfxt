/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import ss.fx.material.api.PaletteColor;
import ss.fx.material.constants.Palette;
import ss.fx.material.skin.MDButtonSkin;

/**
 * Material button.
 * @author alex
 */
public class MdButton extends Button implements PaletteColor {
    /** Color. */
    private final ObjectProperty<Palette> color = new SimpleObjectProperty<>(null);
    /** Variant. */
    private final ObjectProperty<Variant> variant = new SimpleObjectProperty<>(Variant.TEXT);
    /**
     * Constructor.
     */
    public MdButton() {
        init();
    }
    // ===================================================== CONFIGURATION ================================================================
    /**
     * Button variant.
     */
    public static enum Variant {
        CONTAINED,
        OUTLINED,
        TEXT;
    }
    // ===================================================== PRIVATE ======================================================================
    private void init() {
        this.getStylesheets().add(getClass().getResource("mat-button.css").toExternalFo‌​rm());
    }
    // ===================================================== PRIVATE ======================================================================
    @Override
    protected Skin<?> createDefaultSkin() {
        return new MDButtonSkin(this);
    }
    // ===================================================== SET & GET ====================================================================
    public ObjectProperty<Palette> colorProperty() {
        return this.color;
    }
    @Override
    public Palette getColor() {
        return this.color.get();
    }
    @Override
    public void setColor(Palette color) {
        this.color.set(color);
    }
    public ObjectProperty<Variant> variantProperty() {
        return this.variant;
    }
    public Variant getVariant() {
        return this.variant.get();
    }
    public void setVariant(Variant variant) {
        this.variant.set(variant);
    }
}
