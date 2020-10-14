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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import ss.fx.material.api.ThemeComponent;
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
    public void refresh(Scene scene) {
        scene.getStylesheets().add(getClass().getResource("/ss/fx/material/style/global.css").toExternalForm());
        walkNode(scene.getRoot(), null);
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
    public static String toRgba(Color color) {
        int r = (int) (255 * color.getRed());
        int g = (int) (255 * color.getGreen());
        int b = (int) (255 * color.getBlue());
        int a = (int) (255 * color.getOpacity());
        return String.format("#%02x%02x%02x%02x", r, g, b, a);
    }
    /**
     * Get palette color property.
     * @param color palette color.
     * @return color property.
     */
    public ObjectProperty<Color> getColor(Palette color) {
        if (Palette.PRIMARY.equals(color)) {
            return primaryColor;
        } else {
            return secondaryColor;
        }
    }
    /**
     * Get contrast color for target color.
     * @param color target color.
     * @return contrast color.
     */
    public ObjectProperty<Color> getContrastColor(Color color){
        float luminance = (float) (0.2126 * color.getRed()
                + 0.7152 * color.getGreen()
                + 0.0722 * color.getBlue()) * 100;
        return luminance < 90 ? lightContrastColor : darkContrastColor;
    }
    /**
     * Apply contrast color.
     * @param <T> node type.
     * @param node node.
     * @param color palette color.
     * @return node.
     */
    private <T extends Parent> T applyContrastColor(T node, ObjectProperty<Color> colorProperty) {
        final ObjectProperty<Color> colorPropertyFinal = colorProperty;
        ReadOnlyStringWrapper css = new ReadOnlyStringWrapper();
        css.bind(Bindings.createStringBinding(() -> String.format(
             "-fx-text-fill: %s; ", toRgba(colorPropertyFinal.get())), colorPropertyFinal));
        node.styleProperty().bind(css.getReadOnlyProperty());
        return node;
    }
    /**
     * Walk nodes tree.
     * @param parent parent node.
     * @param paletteColor parent node palette color.
     */
    private void walkNode(Parent parent, Palette paletteColor) {
        if (parent instanceof ThemeComponent) {
            ((ThemeComponent) parent).updateComponent();
        }
//        if (parent instanceof PaletteColor) {
//            paletteColor = ((PaletteColor) parent).getColor();
//        }
//        if (parent instanceof ContrastColor) {
//            if (paletteColor != null) {
//                ObjectProperty<Color> colorProperty = getColor(paletteColor);
//                colorProperty = getContrastColor(colorProperty.get());
//                applyContrastColor(parent, colorProperty);
//            }
//        }
        for (Node node : parent.getChildrenUnmodifiable()) {
            if (node instanceof Parent) {
                walkNode((Parent) node, paletteColor);
            }
        }
    }
}
