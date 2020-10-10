/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.jfxt.component;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Material icon.
 * @author alex
 */
public class MatIcon extends Label {
    /** Icon font. */
    private static final Font font = Font.loadFont(MatIcon.class.getResourceAsStream("font/maticon.ttf"), 16);
    /**
     * Constructor.
     */
    public MatIcon() {
        this.setFont(font);
        this.getStylesheets().add(getClass().getResource("mat-icon.css").toExternalFo‌​rm());
        this.getStyleClass().add("material-icons");
        this.setText(String.valueOf('\ue070'));
    }
}
