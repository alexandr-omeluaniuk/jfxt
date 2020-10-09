/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

module ss.jfxt.components {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens ss.jfxt.component to javafx.fxml;
    exports ss.jfxt.component;
}
