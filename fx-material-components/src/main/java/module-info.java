/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

module ss.jfxt.components {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens ss.fx.material.component to javafx.fxml;
    exports ss.fx.material.component;
    exports ss.fx.material.constants;
    exports ss.fx.material.core;
}
