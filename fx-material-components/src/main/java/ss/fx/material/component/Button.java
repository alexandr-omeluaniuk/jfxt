/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import ss.fx.material.api.ThemeComponent;
import ss.fx.material.constants.Palette;
import ss.fx.material.core.Theme;

/**
 * Material button.
 * @author alex
 */
public class Button extends javafx.scene.control.Button implements ThemeComponent {
    /** Text. */
    private final ObjectProperty<String> label = new SimpleObjectProperty<>("");
    /** Color. */
    private final ObjectProperty<Palette> color = new SimpleObjectProperty<>(Palette.PRIMARY);
    /** Variant. */
    private final ObjectProperty<Variant> variant = new SimpleObjectProperty<>(Variant.TEXT);
    /**
     * Constructor.
     */
    public Button() {
        init();
    }
    @Override
    public void updateComponent() {
        this.setStyle("-fx-background-color: " + Theme.getPaletteColor(color.get())
                + "; -fx-text-fill: " + Theme.getContrastPaletteColor(color.get()) + ";");
    }
    // ===================================================== CONFIGURATION ================================================================
    public static enum Variant {
        CONTAINED,
        OUTLINED,
        TEXT;
    }
    // ===================================================== PRIVATE ======================================================================
    private void init() {
        this.getStylesheets().add(getClass().getResource("mat-button.css").toExternalFo‌​rm());
        this.getStyleClass().add("button");
        this.label.addListener((ObservableValue<? extends String> ov, String oldValue, String newValue) -> {
            this.setText(newValue.toUpperCase());
        });
        this.variant.addListener((ObservableValue<? extends Variant> ov, Variant oldValue, Variant newValue) -> {
            
        });
    }
    // ===================================================== SET & GET ====================================================================
    /**
     * @return the label
     */
    public String getLabel() {
        return label.get();
    }
    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label.set(label);
    }
    public Palette getColor() {
        return this.color.get();
    }
    public void setColor(Palette color) {
        this.color.set(color);
    }
}
