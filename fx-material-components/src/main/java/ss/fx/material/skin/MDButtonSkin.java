/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.skin;

import java.util.Optional;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
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
    
    private boolean mousePressed = false;
    
    private boolean hover = false;
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
        applyVariant(button.variantProperty().get(), button);
        button.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> playClickAnimation(1));
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
        button.hoverProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) -> {
            hover = newValue;
            applyColor(button.colorProperty().get(), button);
        });
        Theme.subscribeThemeChanges(() -> {
            applyColor(button.colorProperty().get(), button);
        });
        applyColor(button.colorProperty().get(), button);
    }
    // ==================================================== PRIVATE =======================================================================
    private void playClickAnimation(double rate) {
        if (clickAnimation != null) {
            clickAnimation.setRate(rate);
            clickAnimation.play();
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
                if (clickAnimation == null) {
                    clickAnimation = new ButtonClickTransition((DropShadow) button.getEffect());
                }
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
                    button.setStyle("-fx-text-fill: " + Theme.getPaletteColor(paletteColor) + ";"
                        + "-fx-background-color: " + (hover ? Theme.getColorWithAlpha(paletteColor, 0.04) : "transparent") + ";");
                } else {
                    button.setStyle("-fx-background-color: " + (hover ? "rgba(0, 0, 0, 0.04)" : "transparent") + ";");
                }
                break;
            case CONTAINED:
                if (optional.isPresent()) {
                    button.setStyle("-fx-background-color: "
                        + (hover ? Theme.getColorWithOffset(paletteColor, 0.8) : Theme.getPaletteColor(paletteColor))
                        + "; -fx-text-fill: " + Theme.getContrastPaletteColor(paletteColor) + ";");
                } else {
                    button.setStyle("-fx-background-color: " + (hover ? "#d5d5d5" : "#e0e0e0") + ";");
                }
                break;
            case OUTLINED:
                if (optional.isPresent()) {
                    button.setStyle("-fx-border-color: " + Theme.getPaletteColor(paletteColor) + ";"
                        + "-fx-text-fill: " + Theme.getPaletteColor(paletteColor) + ";"
                        + "-fx-background-color: " + (hover ? Theme.getColorWithAlpha(paletteColor, 0.04) : "transparent") + ";");
                } else {
                    button.setStyle("-fx-background-color: " + (hover ? "rgba(0, 0, 0, 0.04)" : "transparent") + ";");
                }
                break;
            default:
                break;
        }
    }
    // ====================================================== INNER CLASSES ===============================================================
    
    private class ButtonClickTransition extends Transition {
        
        private final Timeline timeline;
        
        public ButtonClickTransition(DropShadow buttonShadow) {
            timeline = new Timeline();
            Interpolator interpolation = Interpolator.EASE_BOTH;
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(buttonShadow.radiusProperty(), Theme.getShadow(1).radiusProperty().get(), interpolation),
                            new KeyValue(buttonShadow.spreadProperty(), Theme.getShadow(1).spreadProperty().get(), interpolation),
                            new KeyValue(buttonShadow.offsetXProperty(), Theme.getShadow(1).offsetXProperty().get(), interpolation),
                            new KeyValue(buttonShadow.offsetYProperty(), Theme.getShadow(1).offsetYProperty().get(), interpolation)
                    )
            );
            // end keyframe
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(1000),
                            new KeyValue(buttonShadow.radiusProperty(), Theme.getShadow(3).radiusProperty().get(), interpolation),
                            new KeyValue(buttonShadow.spreadProperty(), Theme.getShadow(3).spreadProperty().get(), interpolation),
                            new KeyValue(buttonShadow.offsetXProperty(), Theme.getShadow(3).offsetXProperty().get(), interpolation),
                            new KeyValue(buttonShadow.offsetYProperty(), Theme.getShadow(3).offsetYProperty().get(), interpolation)
                    )
            );
            setCycleDuration(Duration.seconds(0.1));
            setDelay(Duration.seconds(0));
        }
        
        @Override
        protected void interpolate(double d) {
            timeline.playFrom(Duration.seconds(d));
            timeline.stop();
        }
    }
}
