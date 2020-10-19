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
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author alex
 */
public class HoverTransition extends Transition {
    
    private final Timeline timeline;
    
    public HoverTransition(Color origin, Color hover, ObjectProperty<Color> nodeProperty) {
        timeline = new Timeline();
        Interpolator interpolation = Interpolator.EASE_OUT;
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(nodeProperty, origin, interpolation)
                )
        );
        // end keyframe
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(2000),
                        new KeyValue(nodeProperty, hover, interpolation)
                )
        );
        setCycleDuration(Duration.seconds(0.4));
        setDelay(Duration.seconds(0));
    }

    @Override
    protected void interpolate(double d) {
        timeline.playFrom(Duration.seconds(d));
        timeline.stop();
    }
}
