/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.gui.aufgabe01.diagramm;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author steeb
 */
public class Balken extends JPanel{
    
    public Balken(int jahrgang, int lagerdauer) {
        super();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.draw3DRect(10, 101, 20, 40, true);
        grphcs.drawLine(10, 10, this.getWidth() - 10, 10);
    }
    
}
