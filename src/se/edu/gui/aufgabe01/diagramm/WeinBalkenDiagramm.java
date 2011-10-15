/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.gui.aufgabe01.diagramm;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author steeb
 */
public class WeinBalkenDiagramm extends JPanel{
    
    public WeinBalkenDiagramm() {
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        this.add(new Balken(2002, 10));
    }
   
}
