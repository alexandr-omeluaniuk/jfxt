/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.jfxt.component.common;

import java.util.function.Supplier;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import ss.jfxt.component.constants.Palette;

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
    /** Primary text color */
    private final ObjectProperty<Color> primaryTextColor = new SimpleObjectProperty<>(Color.web("#ffffff"));
    /** Secondary text color */
    private final ObjectProperty<Color> secondaryTextColor = new SimpleObjectProperty<>(Color.web("#ffffff"));
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
    public <T extends Parent> T applyBackgroundColor(T node, Palette color) {
        if (color == Palette.PRIMARY) {
            node.styleProperty().bind(backgroundColor(primaryColor));
        } else if (color == Palette.SECONDARY) {
            node.styleProperty().bind(backgroundColor(secondaryColor));
        }
        return node ;
    }
    public <T extends Parent> T applyTextFill(T node, Palette color) {
        if (color == Palette.PRIMARY) {
            node.styleProperty().bind(textFill(primaryTextColor));
        } else if (color == Palette.SECONDARY) {
            node.styleProperty().bind(textFill(secondaryTextColor));
        }
        return node ;
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
    private ReadOnlyStringProperty backgroundColor(ObjectProperty<Color> color) {
        ReadOnlyStringWrapper css = new ReadOnlyStringWrapper();
        css.bind(Bindings.createStringBinding(() -> String.format(
             "-fx-background-color: %s; ",
            toRgba(color.get())), color));
        return css.getReadOnlyProperty();
    }
    private ReadOnlyStringProperty textFill(ObjectProperty<Color> color) {
        ReadOnlyStringWrapper css = new ReadOnlyStringWrapper();
        css.bind(Bindings.createStringBinding(() -> String.format(
             "-fx-text-fill: %s; ",
            toRgba(color.get())), color));
        return css.getReadOnlyProperty();
    }
}
