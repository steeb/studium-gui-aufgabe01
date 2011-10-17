/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.gui.aufgabe01.diagramm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 *
 * @author steeb
 */
public class Balken extends JPanel{
    
    int jahrgang;
    int lagerdauer;
    int heightBalken;
    
    public Balken(int jahrgang, int lagerdauer) {
        super();
        this.jahrgang = jahrgang;
        this.lagerdauer = lagerdauer;
        this.heightBalken = this.getHeight() - 10;
        this.setBackground(Color.magenta);
    }

    @Override
    public void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D grphcs2d = (Graphics2D)grphcs;
        Dimension d = this.getSize(null);
        grphcs2d.draw(new Rectangle.Double(10, 10, 100 + d.getWidth() * 0.5 , 100));
    }
    
}
