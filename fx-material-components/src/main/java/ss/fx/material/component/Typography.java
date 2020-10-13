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
    /**
     * Typography variant.
     */
    public static enum Variant {
        H1("6em"),
        H2("3.75em"),
        H3("3em"),
        H4("2.125em"),
        H5("1.5em"),
        H6("1.25em");
        /** Font size. */
        private final String fontSize;
        /**
         * Constructor.
         * @param fontSize font size.
         */
        private Variant(String fontSize) {
            this.fontSize = fontSize;
        }
        /**
         * Get variant font size.
         * @return font size.
         */
        public String getFontSize() {
            return fontSize;
        }
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
        this.styleProperty().set("-fx-font-size: " + variant.getFontSize() + ";");
    }
}
