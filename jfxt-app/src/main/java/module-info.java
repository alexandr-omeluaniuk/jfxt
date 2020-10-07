/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

module ss.jfxt.app {
    requires javafx.controls;
    requires javafx.fxml;

    opens ss.jfxt.app.controller to javafx.fxml;
    exports ss.jfxt.app;
}
