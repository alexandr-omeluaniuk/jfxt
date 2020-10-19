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
import javafx.scene.paint.Color;
import ss.fx.material.api.PaletteColor;
import ss.fx.material.constants.Palette;
import ss.fx.material.skin.MDButtonSkin;

/**
 * Material button.
 * @author alex
 */
public class MdButton extends Button implements PaletteColor {
    /** Palette color. */
    private final ObjectProperty<Palette> color = new SimpleObjectProperty<>(null);
    /** Variant. */
    private final ObjectProperty<Variant> variant = new SimpleObjectProperty<>(Variant.TEXT);
    
    private final ObjectProperty<Color> backgroundColor = new SimpleObjectProperty<>(null);
    
    private final ObjectProperty<Color> textColor = new SimpleObjectProperty<>(Color.rgb(0, 0, 0, 0.87));
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
    public ObjectProperty<Color> backgroundColorProperty() {
        return this.backgroundColor;
    }
    public ObjectProperty<Color> textColorProperty() {
        return this.textColor;
    }
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
