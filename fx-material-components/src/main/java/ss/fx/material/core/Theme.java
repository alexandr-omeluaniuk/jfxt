/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.core;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import ss.fx.material.api.PaletteColor;
import ss.fx.material.api.ThemeComponent;
import ss.fx.material.constants.Palette;

/**
 * Material theme.
 * @author alex
 */
public class Theme {
    /** Primary color. */
    private static Color primaryColor = Color.web("#1976d2");
    /** Secondary color. */
    private static Color secondaryColor = Color.web("#ff3ad5");
    /** Light contrast color. */
    private static Color lightContrastColor = Color.web("#ffffff");
    /** Dark contrast color. */
    private static Color darkContrastColor = Color.web("#000000");
    /** Spacing unit. */
    private static Short spacingUnit = 8;
    // ===================================================== PUBLIC =======================================================================
    public static void initTheme(Scene scene) {
        scene.getStylesheets().add(Theme.class.getResource("/ss/fx/material/style/global.css").toExternalForm());
        refresh(scene);
    }
    public static void refresh(Scene scene) {
        walkNode(scene.getRoot(), null);
    }
    public static String hexColor(Palette color) {
        return toRgba(getColor(color));
    }
    public static String hexContrastColor(Palette color) {
        return toRgba(getContrastColor(getColor(color)));
    }
    public static Palette getParentPaletteColor(Parent node) {
        Parent current = node.getParent();
        while (current != null) {
            if (current instanceof PaletteColor) {
                return ((PaletteColor) current).getColor();
            }
            current = current.getParent();
        }
        return null;
    }
    // ===================================================== SET & GET ====================================================================
    public static Color getPrimaryColor() {
        return primaryColor;
    }
    public static void setPrimaryColor(Color base) {
        primaryColor = base;
    }
    // ===================================================== PRIVATE ======================================================================
    private static String toRgba(Color color) {
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
    private static Color getColor(Palette color) {
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
    private static Color getContrastColor(Color color){
        float luminance = (float) (0.2126 * color.getRed()
                + 0.7152 * color.getGreen()
                + 0.0722 * color.getBlue()) * 100;
        return luminance < 90 ? lightContrastColor : darkContrastColor;
    }
    /**
     * Walk nodes tree.
     * @param parent parent node.
     * @param paletteColor parent node palette color.
     */
    private static void walkNode(Parent parent, Palette paletteColor) {
        if (parent instanceof ThemeComponent) {
            ((ThemeComponent) parent).updateComponent();
        }
        for (Node node : parent.getChildrenUnmodifiable()) {
            if (node instanceof Parent) {
                walkNode((Parent) node, paletteColor);
            }
        }
    }
}
