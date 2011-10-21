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
import java.awt.Rectangle;
import java.util.Calendar;
import javax.swing.JPanel;

/**
 *
 * @author steeb
 */
public class WeinBalkenDiagramm extends JPanel{
    
    int jahrgang;
    int lagerdauer;
    
    public WeinBalkenDiagramm(int jahrgang, int lagerdauer) {
        super();
        this.jahrgang = jahrgang;
        this.lagerdauer = lagerdauer;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
    }
    
    public void setJahrgang(int jahrgang) {
        this.jahrgang = jahrgang;
    }
    
    public void setLagerdauer(int lagerdauer) {
        this.lagerdauer = lagerdauer;
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        int fensterBreite;
        int balkenBreite0, balkenBreite1, balkenBreite2, balkenBreite3;
        int jahr0, jahr1, jahr2, jahr3;
        int balkenHoehe;
        int schriftHoehe = 12;
        Calendar cal = Calendar.getInstance();  
        Graphics2D grphcs2d = (Graphics2D)grphcs;
        grphcs2d.setStroke(new BasicStroke(1));
        
        balkenHoehe = this.getHeight() * 25 / 100 - schriftHoehe;
        
        fensterBreite = this.getWidth() * 80 / 100;
        balkenBreite3 = fensterBreite / this.lagerdauer;
        balkenBreite0 = fensterBreite * 125 / 1000; // 1/8
        balkenBreite2 = fensterBreite / 2;          // 1/2
        balkenBreite1 = fensterBreite - balkenBreite3 - balkenBreite0 
                - balkenBreite2;
        
        jahr0 = this.jahrgang;
        jahr1 = this.jahrgang + (int)Math.round(this.lagerdauer / 8.0);
        jahr2 = this.jahrgang + this.lagerdauer / 2;
        jahr3 = this.jahrgang + this.lagerdauer;
        
        //zu früh
        grphcs2d.translate(this.getWidth() / 10, this.getHeight() / 10);
        grphcs2d.setPaint(Color.GRAY);
        grphcs2d.fill(new Rectangle(balkenBreite0, balkenHoehe));
        grphcs2d.setPaint(Color.BLACK);
        grphcs2d.draw(new Rectangle(balkenBreite0, balkenHoehe));
        grphcs2d.drawString("" + jahr0, 0, balkenHoehe + schriftHoehe);
        
        //steigert sich noch
        grphcs2d.translate(balkenBreite0, 0);
        grphcs2d.setPaint(new GradientPaint(0, 0, Color.GRAY, 
                                            balkenBreite0, 0, Color.GREEN));
        grphcs2d.fill(new Rectangle(balkenBreite1, balkenHoehe));
        grphcs2d.setPaint(Color.BLACK);
        grphcs2d.draw(new Rectangle(balkenBreite1, balkenHoehe));
        grphcs2d.drawString("" + jahr1, 0, balkenHoehe + schriftHoehe);
        
        //optimater trinkzeitpunkt
        grphcs2d.translate(balkenBreite1, 0);
        grphcs2d.setPaint(Color.GREEN);
        grphcs2d.fill(new Rectangle(balkenBreite2, balkenHoehe));
        grphcs2d.setPaint(Color.BLACK);
        grphcs2d.draw(new Rectangle(balkenBreite2, balkenHoehe));
        grphcs2d.drawString("" + jahr2, 0, balkenHoehe + schriftHoehe);
        
        //überlagert
        grphcs2d.translate(balkenBreite2, 0);
        grphcs2d.setPaint(Color.YELLOW);
        grphcs2d.fill(new Rectangle(balkenBreite3, balkenHoehe));
        grphcs2d.setPaint(Color.BLACK);
        grphcs2d.draw(new Rectangle(balkenBreite3, balkenHoehe));
        grphcs2d.drawString("" + jahr3, 0, balkenHoehe + schriftHoehe);
        
        //momentanes Jahr
        grphcs2d.translate((this.jahrgang + this.lagerdauer - 
                    cal.get(Calendar.YEAR)) 
                * -balkenBreite3, - 1);
        grphcs2d.setPaint(Color.RED);
        grphcs2d.draw(new Rectangle(balkenBreite3, balkenHoehe + 2));
        grphcs2d.drawString("" + cal.get(Calendar.YEAR), 0, balkenHoehe + 1 
                + schriftHoehe);
    }
   
}
