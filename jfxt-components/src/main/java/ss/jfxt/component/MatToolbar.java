/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.jfxt.component;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Material toolbar.
 * @author alex
 */
public class MatToolbar extends BorderPane {
    /** Toolbar left side */
    @FXML
    private final HBox leftSide = new HBox();
    /** Toolbar right side. */
    @FXML
    private final HBox rightSide = new HBox();
    /**
     * Constructor.
     */
    public MatToolbar() {
        this.getStylesheets().add(getClass().getResource("mat-toolbar.css").toExternalFo‌​rm());
        this.getStyleClass().add("mat-toolbar");
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
}
