/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.animation;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author alex
 */
public class HoverTransition extends Transition {
    
    private final Region node;
    
    private final Color origin;
    
    private final Color hover;
    
    public HoverTransition(Color origin, Color hover, Region node) {
        this.node = node;
        this.origin = origin;
        this.hover = hover;
        setCycleDuration(Duration.millis(1000));
        setInterpolator(Interpolator.EASE_OUT);
    }

    @Override
    protected void interpolate(double d) {
        //System.out.println(d);
        //this.node.setBackground(new Background(new BackgroundFill(getIntermediateColor(d), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    private Color getIntermediateColor(double progress) {
        return null;
    }
}
