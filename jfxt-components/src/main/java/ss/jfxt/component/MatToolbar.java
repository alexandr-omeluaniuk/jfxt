/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.jfxt.component;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ss.jfxt.component.common.Theme;
import ss.jfxt.component.constants.Palette;

/**
 * Material toolbar.
 * @author alex
 */
public class MatToolbar extends BorderPane {
    /** Toolbar left side */
    private final HBox leftSide = new HBox();
    /** Toolbar right side. */
    private final HBox rightSide = new HBox();
    /** Color. */
    private Palette color;
    /**
     * Constructor.
     */
    public MatToolbar() {
        System.out.println(color);
        this.getStylesheets().add(getClass().getResource("mat-toolbar.css").toExternalFo‌​rm());
        this.getStyleClass().add("mat-toolbar");
        Theme.getTheme().applyBackgroundColor(this, Palette.SECONDARY);
        this.setLeft(leftSide);
        leftSide.setPickOnBounds(false);
        this.setRight(rightSide);
        rightSide.setPickOnBounds(false);
    }
    // ============================================================= SET & GET ============================================================
    public void setLeftSide(Node... nodes) {
        this.leftSide.getChildren().setAll(nodes);
    }
    public ObservableList<Node> getLeftSide() {
        return this.leftSide.getChildren();
    }
    public void setRightSide(Node... nodes) {
        this.rightSide.getChildren().setAll(nodes);
    }
    public ObservableList<Node> getRightSide() {
        return this.rightSide.getChildren();
    }
    public Palette getColor() {
        return color;
    }
    public void setColor(Palette color) {
        this.color = color;
    }
}
