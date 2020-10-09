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
//    public ObjectProperty<Color> primaryColorProperty() {
//        return primaryColor;
//    }
//    public final Color getPrimaryColor() {
//        return primaryColorProperty().get();
//    }
//    public final void setPrimaryColor(Color base) {
//        primaryColorProperty().set(base);
//    }
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
}
