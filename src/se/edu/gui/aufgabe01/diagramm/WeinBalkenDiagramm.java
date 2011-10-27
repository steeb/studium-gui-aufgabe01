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
import java.awt.geom.AffineTransform;
import java.util.Calendar;
import javax.swing.JPanel;

/**
 *
 * @author steeb
 */
public class WeinBalkenDiagramm extends JPanel {

    int jahrgang;
    int lagerdauer;
    
    private static final Color COLOR_ZU_FRUEH = Color.GRAY;
    private static final Color COLOR_OPTIMAL = Color.GREEN;
    private static final Color COLOR_UEBERLAGERT = Color.YELLOW;
    private static final Color COLOR_RAHMEN = Color.BLACK;
    private static final Color COLOR_AKTUELLES_JAHR = Color.RED;
    
    private static final float ANTEIL_ZU_FRUEH = 8f;
    private static final float ANTEIL_OPTIMAL = 2f;

    public WeinBalkenDiagramm(int jahrgang, int lagerdauer) {
        super();
        this.setJahrgang(jahrgang);
        this.setLagerdauer(lagerdauer);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
    }

    public final void setJahrgang(int jahrgang) {
        this.jahrgang = jahrgang + 1;
    }

    public final void setLagerdauer(int lagerdauer) {
        this.lagerdauer = lagerdauer;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        int fensterBreite;
        int balkenBreite, balkenBreiteZuFrueh, balkenBreiteSteigertSich, 
                balkenBreiteOptimal, balkenBreiteUeberlagert;
        int jahrZuFrueh, jahrSteigertSich, jahrOptimal, jahrUeberlagert;
        int balkenHoehe;
        int schriftHoehe = 12;
        int legendePosOben, legendePosUnten;
        int legendeSpaltenHoehe;
        int legendeKastenGroesse;
        AffineTransform defTransform;
        Calendar cal = Calendar.getInstance();
        Graphics2D grphcs2d = (Graphics2D) grphcs;

        grphcs2d.setStroke(new BasicStroke(1));

        fensterBreite = this.getWidth() * 80 / 100;

        balkenHoehe = this.getHeight() * 25 / 100 - schriftHoehe;
        balkenBreite = fensterBreite / (this.lagerdauer + 1);

        balkenBreiteZuFrueh      = balkenBreite * 
                Math.round(this.lagerdauer / ANTEIL_ZU_FRUEH);
        balkenBreiteOptimal      = balkenBreite * 
                Math.round(this.lagerdauer / ANTEIL_OPTIMAL);
        balkenBreiteUeberlagert  = balkenBreite;
        balkenBreiteSteigertSich = balkenBreite * 
                this.lagerdauer - (balkenBreiteZuFrueh + balkenBreiteOptimal);
           
        if (this.lagerdauer == 1)
        {
            balkenBreiteZuFrueh = balkenBreiteSteigertSich = 0;
            balkenBreiteOptimal = balkenBreiteUeberlagert;
        } 
        else if (this.lagerdauer == 2)
        {
            balkenBreiteSteigertSich = balkenBreite;
            balkenBreiteOptimal = balkenBreite;
            balkenBreiteZuFrueh = 0;
        }

        jahrZuFrueh = this.jahrgang;
        jahrSteigertSich = this.jahrgang + 
                (int) Math.round(this.lagerdauer / ANTEIL_ZU_FRUEH);
        jahrOptimal = this.jahrgang + this.lagerdauer / (int)ANTEIL_OPTIMAL;
        jahrUeberlagert = this.jahrgang + this.lagerdauer;

        legendePosOben = this.getHeight() * 45 / 100;
        legendePosUnten = this.getHeight() * 9 / 10;
        legendeSpaltenHoehe = (legendePosUnten - legendePosOben) / 5;
        legendeKastenGroesse = legendeSpaltenHoehe / 2;

        //zu früh
        grphcs2d.translate(this.getWidth() / 10, 0);
        defTransform = grphcs2d.getTransform();
        grphcs2d.translate(0, this.getHeight() / 10);
        grphcs2d.setPaint(COLOR_ZU_FRUEH);
        grphcs2d.fill(new Rectangle(balkenBreiteZuFrueh, balkenHoehe));
        grphcs2d.setPaint(COLOR_RAHMEN);
        grphcs2d.draw(new Rectangle(balkenBreiteZuFrueh, balkenHoehe));
        grphcs2d.drawString("" + jahrZuFrueh, 0, balkenHoehe + schriftHoehe);

        //steigert sich noch
        grphcs2d.translate(balkenBreiteZuFrueh, 0);
        grphcs2d.setPaint(new GradientPaint(0, 0, COLOR_ZU_FRUEH,
                balkenBreiteSteigertSich, 0, COLOR_OPTIMAL));
        grphcs2d.fill(new Rectangle(balkenBreiteSteigertSich, balkenHoehe));
        grphcs2d.setPaint(COLOR_RAHMEN);
        grphcs2d.draw(new Rectangle(balkenBreiteSteigertSich, balkenHoehe));
        grphcs2d.drawString("" + jahrSteigertSich, 0, balkenHoehe + schriftHoehe);

        //optimater trinkzeitpunkt
        grphcs2d.translate(balkenBreiteSteigertSich, 0);
        grphcs2d.setPaint(COLOR_OPTIMAL);
        grphcs2d.fill(new Rectangle(balkenBreiteOptimal, balkenHoehe));
        grphcs2d.setPaint(COLOR_RAHMEN);
        grphcs2d.draw(new Rectangle(balkenBreiteOptimal, balkenHoehe));
        grphcs2d.drawString("" + jahrOptimal, 0, balkenHoehe + schriftHoehe);

        //überlagert
        grphcs2d.translate(balkenBreiteOptimal, 0);
        grphcs2d.setPaint(COLOR_UEBERLAGERT);
        grphcs2d.fill(new Rectangle(balkenBreiteUeberlagert, balkenHoehe));
        grphcs2d.setPaint(COLOR_RAHMEN);
        grphcs2d.draw(new Rectangle(balkenBreiteUeberlagert, balkenHoehe));
        grphcs2d.drawString("" + jahrUeberlagert, 0, balkenHoehe + schriftHoehe);

        //momentanes Jahr
        if (cal.get(Calendar.YEAR) >= this.jahrgang
                && this.jahrgang + this.lagerdauer + 1 > cal.get(Calendar.YEAR)) {
            grphcs2d.setTransform(defTransform);
            grphcs2d.translate((cal.get(Calendar.YEAR) - this.jahrgang)
                    * balkenBreite,
                    this.getHeight() / 10 - 1);
            grphcs2d.setPaint(COLOR_AKTUELLES_JAHR);
            grphcs2d.draw(new Rectangle(balkenBreite, balkenHoehe + 2));
            grphcs2d.drawString("" + cal.get(Calendar.YEAR), 0, balkenHoehe + 1
                    + schriftHoehe);
        }

        //legende
        grphcs2d.setTransform(defTransform);
        grphcs2d.translate(0, legendePosOben);
        grphcs2d.setPaint(COLOR_RAHMEN);
        grphcs2d.drawString("Legende:", 0, 0);

        //legende zu früh
        grphcs2d.translate(0, legendeSpaltenHoehe);
        grphcs2d.setPaint(COLOR_ZU_FRUEH);
        grphcs2d.fill(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.setPaint(COLOR_RAHMEN);
        grphcs2d.draw(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.drawString("zu früh",
                legendeKastenGroesse * 2, (int) (legendeKastenGroesse / 1.5));

        //legende steigert sich noch
        grphcs2d.translate(0, legendeSpaltenHoehe);
        grphcs2d.setPaint(new GradientPaint(0, 0, COLOR_ZU_FRUEH,
                legendeKastenGroesse, 0, COLOR_OPTIMAL));
        grphcs2d.fill(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.setPaint(COLOR_RAHMEN);
        grphcs2d.draw(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.drawString("steigert sich noch",
                legendeKastenGroesse * 2, (int) (legendeKastenGroesse / 1.5));

        //legende optimater trinkzeitpunkt
        grphcs2d.translate(0, legendeSpaltenHoehe);
        grphcs2d.setPaint(COLOR_OPTIMAL);
        grphcs2d.fill(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.setPaint(COLOR_RAHMEN);
        grphcs2d.draw(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.drawString("optimaler Trinkzeitpunkt",
                legendeKastenGroesse * 2, (int) (legendeKastenGroesse / 1.5));

        //legende überlagert
        grphcs2d.translate(0, legendeSpaltenHoehe);
        grphcs2d.setPaint(COLOR_UEBERLAGERT);
        grphcs2d.fill(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.setPaint(COLOR_RAHMEN);
        grphcs2d.draw(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.drawString("überlagert",
                legendeKastenGroesse * 2, (int) (legendeKastenGroesse / 1.5));
    }
}
