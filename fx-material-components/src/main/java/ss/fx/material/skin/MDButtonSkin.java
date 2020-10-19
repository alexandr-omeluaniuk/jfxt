/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.skin;

import java.util.Optional;
import javafx.animation.Transition;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import ss.fx.material.animation.HoverTransition;
import ss.fx.material.animation.ShadowTransition;
import ss.fx.material.component.MdButton;
import ss.fx.material.component.MdButton.Variant;
import ss.fx.material.constants.Palette;
import ss.fx.material.core.Theme;

/**
 * Button skin.
 * @author alex
 */
public final class MDButtonSkin extends ButtonSkin {
    /** Click animation. */
    private Transition clickAnimation;
    /** Hover animation. */
    private Transition hoverAnimation;
    
    private boolean mousePressed = false;
    /**
     * Constructor.
     * @param button 
     */
    public MDButtonSkin(MdButton button) {
        super(button);
        button.variantProperty().addListener((ObservableValue<? extends MdButton.Variant> ov,
                MdButton.Variant oldValue, MdButton.Variant newValue) -> {
            applyVariant(newValue, button);
        });
        button.colorProperty().addListener((ObservableValue<? extends Palette> ov, Palette oldValue, Palette newValue) -> {
            applyColor(newValue, button);
        });
        button.backgroundColorProperty().addListener((ObservableValue<? extends Color> ov, Color oldValue, Color newValue) -> {
            button.setStyle("-fx-background-color: " + Theme.toRgba(newValue));
        });
        button.textColorProperty().addListener((ObservableValue<? extends Color> ov, Color oldValue, Color newValue) -> {
            button.setTextFill(newValue);
        });
//        button.borderProperty().addListener((ObservableValue<? extends Border> ov, Border t, Border t1) -> {
//            applyColor(button.getColor(), button);
//        });
        button.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> playClickAnimation(1));
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> playHoverAnimation(1));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> playHoverAnimation(-1));
        button.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> mousePressed = true);
        button.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> mousePressed = false);
        button.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> mousePressed = false);
        button.armedProperty().addListener((o, oldVal, newVal) -> {
            if (newVal) {
                if (!mousePressed) {
                    playClickAnimation(1);
                }
            } else {
                playClickAnimation(-1);
            }
        });
        Theme.subscribeThemeChanges(() -> {
            applyColor(button.colorProperty().get(), button);
        });
        applyColor(button.colorProperty().get(), button);
        applyVariant(button.variantProperty().get(), button);
    }
    // ==================================================== PRIVATE =======================================================================
    private void playClickAnimation(double rate) {
        if (clickAnimation != null) {
            clickAnimation.setRate(rate);
            clickAnimation.play();
        }
    }
    
    private void playHoverAnimation(double rate) {
        if (hoverAnimation != null) {
            hoverAnimation.setRate(rate);
            hoverAnimation.play();
        }
    }
    
    private void applyVariant(MdButton.Variant variant, MdButton button) {
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
        switch (variant) {
            case TEXT:
                button.getStyleClass().add("text-button");
                break;
            case CONTAINED:
                Theme.elevation(1, button);
                clickAnimation = new ShadowTransition(1, 3, (DropShadow) button.getEffect());
                button.getStyleClass().add("contained-button");
                break;
            case OUTLINED:
                button.getStyleClass().add("outlined-button");
                break;
            default:
                break;
        }
    }
    
    private void applyColor(Palette paletteColor, MdButton button) {
        Variant variant = button.variantProperty().get();
        Optional<Palette> optional = Optional.ofNullable(paletteColor);
        switch (variant) {
            case TEXT:
                if (optional.isPresent()) {
                    button.backgroundColorProperty().set(Color.TRANSPARENT);
                    button.textColorProperty().set(Theme.paletteColor(paletteColor));
                } else {
                    button.backgroundColorProperty().set(Color.TRANSPARENT);
                    button.textColorProperty().set(Color.rgb(0, 0, 0, 0.87));
                }
                break;
            case CONTAINED:
                if (optional.isPresent()) {
                    button.backgroundColorProperty().set(Theme.paletteColor(paletteColor));
                    button.textColorProperty().set(Theme.contrastPaletteColor(paletteColor));
                } else {
                    button.backgroundColorProperty().set(Color.web("#e0e0e0"));
                    button.textColorProperty().set(Color.rgb(0, 0, 0, 0.87));
                }
                break;
            case OUTLINED:
                if (optional.isPresent()) {
                    button.backgroundColorProperty().set(Color.TRANSPARENT);
                    button.textColorProperty().set(Theme.paletteColor(paletteColor));
                    button.setBorder(new Border(new BorderStroke(Theme.paletteColor(paletteColor),
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
                } else {
                    button.backgroundColorProperty().set(Color.TRANSPARENT);
                    button.textColorProperty().set(Color.rgb(0, 0, 0, 0.87));
                    button.setBorder(new Border(new BorderStroke(Color.rgb(0, 0, 0, 0.87),
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
                }
                break;
            default:
                break;
        }
        Color color = button.backgroundColorProperty().get();
        Color hoverColor = color.equals(Color.TRANSPARENT) ? Color.rgb(0, 0, 0, 0.04) : Theme.getColorWithOffset(color, 0.8);
        hoverAnimation = new HoverTransition(color, hoverColor, button.backgroundColorProperty());
    }
}
