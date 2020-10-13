/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.core;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import ss.fx.material.constants.Palette;

/**
 * Material theme.
 * @author alex
 */
public class Theme {
    /** Theme. */
    private static Theme theme = null;
    /** Primary color. */
    private final ObjectProperty<Color> primaryColor = new SimpleObjectProperty<>(Color.web("#1976d2"));
    /** Secondary color. */
    private final ObjectProperty<Color> secondaryColor = new SimpleObjectProperty<>(Color.web("#ff3ad5"));
    /** Primary color. */
    private final ObjectProperty<Color> lightContrastColor = new SimpleObjectProperty<>(Color.web("#ffffff"));
    /** Secondary color. */
    private final ObjectProperty<Color> darkContrastColor = new SimpleObjectProperty<>(Color.web("#000000"));
    /** Spacing unit. */
    private final ObjectProperty<Integer> spacingUnit = new SimpleObjectProperty<>(8);
    // ===================================================== PUBLIC =======================================================================
    /**
     * Apply background color.
     * @param <T> node type.
     * @param node node.
     * @param color palette color.
     * @return node.
     */
    public <T extends Parent> T applyPaletteColor(T node, Palette color) {
        ObjectProperty<Color> colorProperty = getColor(color);
        ReadOnlyStringWrapper css = new ReadOnlyStringWrapper();
        css.bind(Bindings.createStringBinding(() -> String.format(
             "-fx-background-color: %s; ", toRgba(colorProperty.get())), colorProperty));
        node.styleProperty().bind(css.getReadOnlyProperty());
        return node;
    }
    /**
     * Apply contrast color.
     * @param <T> node type.
     * @param node node.
     * @param color palette color.
     * @return node.
     */
    public <T extends Parent> T applyPaletteContrastColor(T node, Palette color) {
        ObjectProperty<Color> colorProperty = Palette.DARK.equals(color) ? darkContrastColor : lightContrastColor;
//        Parent nodeParent = node.getParent();
//        while (nodeParent != null) {
//            if (nodeParent instanceof PaletteColored) {
//                colorProperty = getColor(((PaletteColored) nodeParent).getColor());
//                colorProperty = getContrastColor(colorProperty.get());
//                break;
//            }
//            nodeParent = node.getParent();
//        }
        final ObjectProperty<Color> colorPropertyFinal = colorProperty;
        ReadOnlyStringWrapper css = new ReadOnlyStringWrapper();
        css.bind(Bindings.createStringBinding(() -> String.format(
             "-fx-text-fill: %s; ", toRgba(colorPropertyFinal.get())), colorPropertyFinal));
        node.styleProperty().bind(css.getReadOnlyProperty());
        return node;
    }
    /**
     * Get theme instance.
     * @return theme.
     */
    public synchronized static Theme getTheme() {
        if (theme == null) {
            theme = new Theme();
        }
        return theme;
    }
    // ===================================================== SET & GET ====================================================================
    public final Color getPrimaryColor() {
        return this.primaryColor.get();
    }
    public final void setPrimaryColor(Color base) {
        this.primaryColor.set(base);
    }
    // ===================================================== PRIVATE ======================================================================
    private String toRgba(Color color) {
        int r = (int) (255 * color.getRed());
        int g = (int) (255 * color.getGreen());
        int b = (int) (255 * color.getBlue());
        int a = (int) (255 * color.getOpacity());
        return String.format("#%02x%02x%02x%02x", r, g, b, a);
    }
    private ObjectProperty<Color> getColor(Palette color) {
        if (Palette.PRIMARY.equals(color)) {
            return primaryColor;
        } else {
            return secondaryColor;
        }
    }
    private ObjectProperty<Color> getContrastColor(Color color){
        float luminance = (float) (0.2126 * (color.getRed() * 2.2 / 255)
                + 0.7152 * (color.getGreen() * 2.2 / 255)
                + 0.0722 * (color.getBlue() * 2.2 / 255));
        return luminance < 140 ? lightContrastColor : darkContrastColor;
    }
}
