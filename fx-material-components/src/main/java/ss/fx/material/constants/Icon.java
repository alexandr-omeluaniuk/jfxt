/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ss.fx.material.constants;

/**
 * Material icons symbols.
 * @author alex
 */
public enum Icon {
    CLOSE('\ue5cd'),
    MENU('\ue5d2');
    /** Symbol. */
    private final Character symbol;
    /**
     * Constructor.
     * @param symbol 
     */
    private Icon(Character symbol) {
        this.symbol = symbol;
    }
    /**
     * Get UTF-8 symbol.
     * @return symbol.
     */
    public Character getSymbol() {
        return symbol;
    }
}
