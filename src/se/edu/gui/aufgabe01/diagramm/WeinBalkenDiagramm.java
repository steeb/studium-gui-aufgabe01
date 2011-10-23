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
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        int fensterBreite;
        int balkenBreite, balkenBreite0, balkenBreite1, balkenBreite2, balkenBreite3;
        int jahr0, jahr1, jahr2, jahr3;
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

        balkenBreite0 = balkenBreite * Math.round(this.lagerdauer / 8f); // 1/8
        balkenBreite1 = balkenBreite * Math.round(this.lagerdauer * 3f / 8f);
        balkenBreite3 = balkenBreite;
        balkenBreite2 = balkenBreite * Math.round(this.lagerdauer / 2f); // 1/2
           
        if (this.lagerdauer == 1)
        {
            balkenBreite0 = balkenBreite1 = 0;
            balkenBreite2 = balkenBreite3;
        } 
        else if (this.lagerdauer == 2)
        {
            balkenBreite1 += balkenBreite0;
            balkenBreite0 = 0;
        }

        jahr0 = this.jahrgang;
        jahr1 = this.jahrgang + (int) Math.round(this.lagerdauer / 8.0);
        jahr2 = this.jahrgang + this.lagerdauer / 2;
        jahr3 = this.jahrgang + this.lagerdauer;

        legendePosOben = this.getHeight() * 45 / 100;
        legendePosUnten = this.getHeight() * 9 / 10;
        legendeSpaltenHoehe = (legendePosUnten - legendePosOben) / 5;
        legendeKastenGroesse = legendeSpaltenHoehe / 2;

        //zu früh
        grphcs2d.translate(this.getWidth() / 10, 0);
        defTransform = grphcs2d.getTransform();
        grphcs2d.translate(0, this.getHeight() / 10);
        grphcs2d.setPaint(Color.GRAY);
        grphcs2d.fill(new Rectangle(balkenBreite0, balkenHoehe));
        grphcs2d.setPaint(Color.BLACK);
        grphcs2d.draw(new Rectangle(balkenBreite0, balkenHoehe));
        grphcs2d.drawString("" + jahr0, 0, balkenHoehe + schriftHoehe);

        //steigert sich noch
        grphcs2d.translate(balkenBreite0, 0);
        grphcs2d.setPaint(new GradientPaint(0, 0, Color.GRAY,
                balkenBreite1, 0, Color.GREEN));
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
        if (cal.get(Calendar.YEAR) >= this.jahrgang) {
            grphcs2d.setTransform(defTransform);
            grphcs2d.translate((cal.get(Calendar.YEAR) - this.jahrgang)
                    * balkenBreite,
                    this.getHeight() / 10 - 1);
            grphcs2d.setPaint(Color.RED);
            grphcs2d.draw(new Rectangle(balkenBreite, balkenHoehe + 2));
            grphcs2d.drawString("" + cal.get(Calendar.YEAR), 0, balkenHoehe + 1
                    + schriftHoehe);
        }

        //legende
        grphcs2d.setTransform(defTransform);
        grphcs2d.translate(0, legendePosOben);
        grphcs2d.setPaint(Color.BLACK);
        grphcs2d.drawString("Legende:", 0, 0);

        //legende zu früh
        grphcs2d.translate(0, legendeSpaltenHoehe);
        grphcs2d.setPaint(Color.GRAY);
        grphcs2d.fill(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.setPaint(Color.BLACK);
        grphcs2d.draw(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.drawString("zu früh",
                legendeKastenGroesse * 2, (int) (legendeKastenGroesse / 1.5));

        //legende steigert sich noch
        grphcs2d.translate(0, legendeSpaltenHoehe);
        grphcs2d.setPaint(new GradientPaint(0, 0, Color.GRAY,
                legendeKastenGroesse, 0, Color.GREEN));
        grphcs2d.fill(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.setPaint(Color.BLACK);
        grphcs2d.draw(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.drawString("steigert sich noch",
                legendeKastenGroesse * 2, (int) (legendeKastenGroesse / 1.5));

        //legende optimater trinkzeitpunkt
        grphcs2d.translate(0, legendeSpaltenHoehe);
        grphcs2d.setPaint(Color.GREEN);
        grphcs2d.fill(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.setPaint(Color.BLACK);
        grphcs2d.draw(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.drawString("optimaler Trinkzeitpunkt",
                legendeKastenGroesse * 2, (int) (legendeKastenGroesse / 1.5));

        //legende überlagert
        grphcs2d.translate(0, legendeSpaltenHoehe);
        grphcs2d.setPaint(Color.YELLOW);
        grphcs2d.fill(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.setPaint(Color.BLACK);
        grphcs2d.draw(new Rectangle(0, 0, legendeKastenGroesse,
                legendeKastenGroesse));
        grphcs2d.drawString("überlagert",
                legendeKastenGroesse * 2, (int) (legendeKastenGroesse / 1.5));
    }
}
