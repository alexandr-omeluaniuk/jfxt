/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.jfxt.app.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author alex
 */
public class PrimaryController {
    @FXML
    private Label toolbarLabel;
    
    @FXML
    public void initialize() {
        toolbarLabel.setText("Hello Alex");
    }
    
    @FXML
    public void onClose() {
        Platform.exit();
    }
}
