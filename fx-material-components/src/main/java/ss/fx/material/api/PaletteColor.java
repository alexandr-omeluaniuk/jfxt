/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.api;

import ss.fx.material.constants.Palette;

/**
 * Has palette color property.
 * @author alex
 */
public interface PaletteColor {
    /**
     * Get palette color.
     * @return palette color.
     */
    public Palette getColor();
    /**
     * Set palette color.
     * @param color palette color.
     */
    public void setColor(Palette color);
}
