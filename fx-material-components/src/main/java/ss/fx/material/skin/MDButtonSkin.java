/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.skin;

import javafx.animation.Transition;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.input.MouseEvent;
import ss.fx.material.component.MdButton;
import ss.fx.material.component.MdButton.Variant;
import ss.fx.material.constants.Palette;
import ss.fx.material.core.Theme;

/**
 * Button skin.
 * @author alex
 */
public class MDButtonSkin extends ButtonSkin {
    /** Click animation. */
    private Transition clickAnimation;
    /**
     * Constructor.
     * @param button 
     */
    public MDButtonSkin(MdButton button) {
        super(button);
        button.labelProperty().addListener((ObservableValue<? extends String> ov, String oldValue, String newValue) -> {
            button.setText(newValue.toUpperCase());
        });
        button.variantProperty().addListener((ObservableValue<? extends MdButton.Variant> ov,
                MdButton.Variant oldValue, MdButton.Variant newValue) -> {
            applyVariant(newValue, button);
        });
        button.colorProperty().addListener((ObservableValue<? extends Palette> ov, Palette oldValue, Palette newValue) -> {
            applyColor(newValue, button);
        });
        applyVariant(button.variantProperty().get(), button);
        button.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> startClickAnimation());
        Theme.subscribeThemeChanges(() -> {
            applyColor(button.colorProperty().get(), button);
        });
    }
    // ==================================================== PRIVATE =======================================================================
    private void startClickAnimation() {
        if (clickAnimation != null) {
//            if (!clickedAnimation.getCurrentTime().equals(clickedAnimation.getCycleDuration()) || rate != 1) {
//                clickedAnimation.setRate(rate);
//                clickedAnimation.play();
//            }
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
                button.getStyleClass().add("contained-button");
                Theme.elevation(1, button);
                break;
            case OUTLINED:
                button.getStyleClass().add("outlined-button");
                break;
            default:
                break;
        }
    }
    
    public void applyColor(Palette paletteColor, MdButton button) {
        if (paletteColor != null) {
            Variant variant = button.variantProperty().get();
            switch (variant) {
                case TEXT:
                    button.setStyle("-fx-text-fill: " + Theme.getPaletteColor(paletteColor) + ";");
                    break;
                case CONTAINED:
                    button.setStyle("-fx-background-color: " + Theme.getPaletteColor(paletteColor)
                            + "; -fx-text-fill: " + Theme.getContrastPaletteColor(paletteColor) + ";");
                    break;
                case OUTLINED:
                    button.setStyle("-fx-border-color: " + Theme.getPaletteColor(paletteColor) + ";"
                            + "-fx-text-fill: " + Theme.getPaletteColor(paletteColor) + ";");
                    break;
                default:
                    break;
            }
        }
    }
}
