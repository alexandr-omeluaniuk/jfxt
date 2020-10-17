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
    private final ObjectProperty<Palette> color = new SimpleObjectProperty<>(null);
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
        applyColor(color.get());
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
        this.label.addListener((ObservableValue<? extends String> ov, String oldValue, String newValue) -> {
            this.setText(newValue.toUpperCase());
        });
        this.variant.addListener((ObservableValue<? extends Variant> ov, Variant oldValue, Variant newValue) -> {
            applyVariant(newValue);
        });
        this.color.addListener((ObservableValue<? extends Palette> ov, Palette oldValue, Palette newValue) -> {
            applyColor(newValue);
        });
        applyVariant(this.variant.get());
    }
    // ===================================================== PRIVATE ======================================================================
    
    private void applyVariant(Variant variant) {
        this.getStyleClass().clear();
        this.getStyleClass().add("button");
        switch (variant) {
            case TEXT:
                this.getStyleClass().add("text-button");
                break;
            case CONTAINED:
                this.getStyleClass().add("contained-button");
                Theme.elevation(1, this);
                break;
            case OUTLINED:
                this.getStyleClass().add("outlined-button");
                break;
            default:
                break;
        }
    }
    
    private void applyColor(Palette paletteColor) {
        if (paletteColor != null) {
            switch (variant.get()) {
                case TEXT:
                    this.setStyle("-fx-text-fill: " + Theme.getPaletteColor(paletteColor) + ";");
                    break;
                case CONTAINED:
                    this.setStyle("-fx-background-color: " + Theme.getPaletteColor(paletteColor)
                            + "; -fx-text-fill: " + Theme.getContrastPaletteColor(paletteColor) + ";");
                    break;
                case OUTLINED:
                    this.setStyle("-fx-border-color: " + Theme.getPaletteColor(paletteColor) + ";"
                            + "-fx-text-fill: " + Theme.getPaletteColor(paletteColor) + ";");
                    break;
                default:
                    break;
            }
        }
    }
    // ===================================================== SET & GET ====================================================================
    public String getLabel() {
        return label.get();
    }
    public void setLabel(String label) {
        this.label.set(label);
    }
    public Palette getColor() {
        return this.color.get();
    }
    public void setColor(Palette color) {
        this.color.set(color);
    }
    public Variant getVariant() {
        return this.variant.get();
    }
    public void setVariant(Variant variant) {
        this.variant.set(variant);
    }
}
