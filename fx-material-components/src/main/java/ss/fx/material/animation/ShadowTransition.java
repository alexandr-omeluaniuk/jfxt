/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.animation;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.effect.DropShadow;
import javafx.util.Duration;
import ss.fx.material.core.Theme;

/**
 * Shadow transition.
 * @author alex
 */
public class ShadowTransition extends Transition {
    /** Timeline. */
    private final Timeline timeline;
    /**
     * Constructor.
     * @param elevationFrom start elevation.
     * @param elevationTo end elevation.
     * @param nodeShadow node shadow.
     */
    public ShadowTransition(int elevationFrom, int elevationTo, DropShadow nodeShadow) {
        timeline = new Timeline();
        Interpolator interpolation = Interpolator.EASE_BOTH;
        DropShadow shadowFrom = Theme.getShadow(elevationFrom);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(nodeShadow.radiusProperty(), shadowFrom.radiusProperty().get(), interpolation),
                        new KeyValue(nodeShadow.spreadProperty(), shadowFrom.spreadProperty().get(), interpolation),
                        new KeyValue(nodeShadow.offsetXProperty(), shadowFrom.offsetXProperty().get(), interpolation),
                        new KeyValue(nodeShadow.offsetYProperty(), shadowFrom.offsetYProperty().get(), interpolation)
                )
        );
        // end keyframe
        DropShadow shadowTo = Theme.getShadow(elevationTo);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000),
                        new KeyValue(nodeShadow.radiusProperty(), shadowTo.radiusProperty().get(), interpolation),
                        new KeyValue(nodeShadow.spreadProperty(), shadowTo.spreadProperty().get(), interpolation),
                        new KeyValue(nodeShadow.offsetXProperty(), shadowTo.offsetXProperty().get(), interpolation),
                        new KeyValue(nodeShadow.offsetYProperty(), shadowTo.offsetYProperty().get(), interpolation)
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
