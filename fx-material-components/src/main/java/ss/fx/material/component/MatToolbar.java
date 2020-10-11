/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ss.fx.material.common.Theme;
import ss.fx.material.constants.Palette;

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
    private final ObjectProperty<Palette> color = new SimpleObjectProperty<>(Palette.PRIMARY);
    /**
     * Constructor.
     */
    public MatToolbar() {
        this.getStylesheets().add(getClass().getResource("mat-toolbar.css").toExternalFo‌​rm());
        this.getStyleClass().add("mat-toolbar");
        this.setLeft(leftSide);
        leftSide.setPickOnBounds(false);
        leftSide.setAlignment(Pos.CENTER_LEFT);
        this.setRight(rightSide);
        rightSide.setPickOnBounds(false);
        rightSide.setAlignment(Pos.CENTER_RIGHT);
        Theme.getTheme().applyPaletteColor(this, this.color.get());
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
        return this.color.get();
    }
    public void setColor(Palette color) {
        this.color.set(color);
    }
}
