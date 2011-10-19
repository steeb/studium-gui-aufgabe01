/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.gui.aufgabe01.diagramm;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 *
 * @author steeb
 */
public class WeinBalkenDiagramm extends JPanel{
    
    public WeinBalkenDiagramm(int jahrgang, int lagerdauer) {
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        double breite;
        int breite0, breite1, breite2, breite3;
        int hoehe;
        Graphics2D grphcs2d = (Graphics2D)grphcs;
        grphcs2d.setStroke(new BasicStroke(1));
        
        hoehe = this.getHeight() * 25 / 100 - 10;
        
        breite = this.getWidth() * 80 / 100;
        breite0 = (int)(breite * 10 / 100);
        breite1 = (int)(breite * 20 / 100);
        breite2 = (int)(breite * 50 / 100);
        breite3 = (int)(breite * 20 / 100);
        
        //zu früh
        grphcs2d.translate(this.getWidth() / 10, this.getHeight() / 10);
        grphcs2d.setPaint(Color.GRAY);
        grphcs2d.fill(new Rectangle(breite0, hoehe));
        grphcs2d.setPaint(Color.BLACK);
        grphcs2d.draw(new Rectangle(breite0, hoehe));
        
        //steigert sich noch
        grphcs2d.translate(breite0, 0);
        grphcs2d.setPaint(new GradientPaint(0, 50, Color.GRAY, 
                                            200, 50, Color.GREEN));
        grphcs2d.fill(new Rectangle(breite1, hoehe));
        grphcs2d.setPaint(Color.BLACK);
        grphcs2d.draw(new Rectangle(breite1, hoehe));
        
        //optimater trinkzeitpunkt
        grphcs2d.translate(breite1, 0);
        grphcs2d.setPaint(Color.GREEN);
        grphcs2d.fill(new Rectangle(breite2, hoehe));
        grphcs2d.setPaint(Color.BLACK);
        grphcs2d.draw(new Rectangle(breite2, hoehe));
        
        //überlagert
        grphcs2d.translate(breite2, 0);
        grphcs2d.setPaint(Color.YELLOW);
        grphcs2d.fill(new Rectangle(breite3, hoehe));
        grphcs2d.setPaint(Color.BLACK);
        grphcs2d.draw(new Rectangle(breite3, hoehe));
    }
   
}
