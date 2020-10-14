/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import ss.fx.material.api.ContrastColor;

/**
 * Typography.
 * @author alex
 */
public class Typography extends Label implements ContrastColor {
    /** Variant. */
    private final ObjectProperty<Variant> variant = new SimpleObjectProperty<>(null);
    
    public Typography() {
        this.getStylesheets().add(getClass().getResource("mat-typography.css").toExternalFo‌​rm());
    }
    /**
     * Typography variant.
     */
    public static enum Variant {
        H1,
        H2,
        H3,
        H4,
        H5,
        H6;
    }
    // =========================================================== SET & GET ==============================================================
    /**
     * @return the variant
     */
    public Variant getVariant() {
        return variant.get();
    }
    /**
     * @param variant the variant to set
     */
    public void setVariant(Variant variant) {
        this.variant.set(variant);
        this.getStyleClass().add(variant.name().toLowerCase());
    }
}
