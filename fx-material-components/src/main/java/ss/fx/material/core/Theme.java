/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.core;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import ss.fx.material.api.PaletteColor;
import ss.fx.material.api.ThemeComponent;
import ss.fx.material.constants.Palette;

/**
 * Material theme.
 * @author alex
 */
public class Theme {
    /** Theme change flag. */
    private static final SimpleObjectProperty<Long> THEME_CHANGED = new SimpleObjectProperty<>();
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
    /** Shadows. */
    private static final DropShadow[] shadows = new DropShadow[] {
        new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0), 0, 0, 0, 0),
        new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.26), 10, 0.12, -1, 2),
        new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.26), 15, 0.16, 0, 4),
        new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.26), 20, 0.19, 0, 6),
        new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.26), 25, 0.25, 0, 8),
        new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.26), 30, 0.30, 0, 10)
    };
    // ===================================================== PUBLIC =======================================================================
    /**
     * Init theme.
     * Call this method before displaying a scene.
     * @param scene scene instance. 
     */
    public static void initTheme(Scene scene) {
        scene.getStylesheets().add(Theme.class.getResource("/ss/fx/material/style/global.css").toExternalForm());
        refresh(scene);
    }
    /**
     * Refresh theme.
     * It uses after theme changes.
     * @param scene scene instance.
     */
    public static void refresh(Scene scene) {
        THEME_CHANGED.set(System.currentTimeMillis());
    }
    /**
     * Get palette color as RGBA.
     * @param color palette color.
     * @return RGBA value of palette color.
     */
    public static String getPaletteColor(Palette color) {
        return toRgba(getColor(color));
    }
    /**
     * Get contrast RGBA color (light or dark) for palette color.
     * @param color palette color.
     * @return color value as RGBA.
     */
    public static String getContrastPaletteColor(Palette color) {
        return toRgba(getContrastColor(getColor(color)));
    }
    /**
     * Get palette color of parent node.
     * @param node current node.
     * @return palette color or null.
     */
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
    
    public static void elevation(int elevation, Parent node) {
        if (elevation < shadows.length) {
            DropShadow origin = shadows[elevation];
            DropShadow copy = new DropShadow(origin.getBlurType(), origin.getColor(), origin.getRadius(), origin.getSpread(),
                    origin.getOffsetX(), origin.getOffsetY());
            node.setEffect(copy);
        }
    }
    
    public static DropShadow getShadow(int elevation) {
        if (elevation < shadows.length) {
            DropShadow origin = shadows[elevation];
            return new DropShadow(origin.getBlurType(), origin.getColor(), origin.getRadius(), origin.getSpread(),
                    origin.getOffsetX(), origin.getOffsetY());
        }
        return null;
    }
    
    public static void subscribeThemeChanges(ThemeComponent component) {
        THEME_CHANGED.addListener((ObservableValue<? extends Long> ov, Long t, Long t1) -> {
            component.updateComponent();
        });
    }
    // ===================================================== SET & GET ====================================================================
    public static Color getPrimaryColor() {
        return primaryColor;
    }
    public static void setPrimaryColor(Color base) {
        primaryColor = base;
    }
    // ===================================================== PRIVATE ======================================================================
    /**
     * Convert color to RGBA.
     * @param color color.
     * @return RGBA value.
     */
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
        } else if (Palette.SECONDARY.equals(color)) {
            return secondaryColor;
        }
        return null;
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
}
