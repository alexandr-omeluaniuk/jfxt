/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.jfxt.component;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 *
 * @author alex
 */
public class MaterialIcon extends Label {
    private static Font font = null;
    static {
            font = Font.loadFont(MaterialIcon.class.getResource("/ss/jfxt/component/material-icons.woff2").toExternalForm(), 10);
    }
    
    public void setIcon(String icon) {
        System.out.println(font);
        this.setFont(font);
        this.setText(icon);
    }
}
