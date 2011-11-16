/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.gui.aufgabe03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import se.edu.gui.aufgabe03.diagramm.WeinBalkenDiagrammOhneLegende;

/**
 *
 * @author steeb
 */
public class WerteEingabe {

    static final int MAX_JAHRGANG = Calendar.getInstance().get(Calendar.YEAR);
    static final int MIN_JAHRGANG = 1800;
    static final int MAX_LAGERDAUER = 50;
    static final int MIN_LAGERDAUER = 1;
    static int jahrgang;
    static int lagerdauer;
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader c = new BufferedReader(isr);

    public static void werteEingeben(WeinBalkenDiagrammOhneLegende wbd) {
        String sJahrgang;
        String sLagerdauer;

        jahrgang = 0;
        lagerdauer = 0;

        try {
        do {
            System.out.printf("Jahrgang (%d - %d): ", MIN_JAHRGANG, MAX_JAHRGANG);
            sJahrgang = c.readLine();
            try {
                jahrgang = Integer.parseInt(sJahrgang);
                if (jahrgang > MAX_JAHRGANG || jahrgang < MIN_JAHRGANG) {
                    jahrgang = 0;
                    System.out.println("Gültigkeitsbereich nicht eingehalten!");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Falsches Format!");
            }
        } while (jahrgang == 0);
        do {
            System.out.printf("Lagerdauer (%d - %d): ", MIN_LAGERDAUER, MAX_LAGERDAUER);
            sLagerdauer = c.readLine();
            try {
                lagerdauer = Integer.parseInt(sLagerdauer);
                if (lagerdauer == 0) {
                    System.out.println("Das wäre Traubensaft :)");
                } else if (lagerdauer > MAX_LAGERDAUER || lagerdauer < MIN_LAGERDAUER) {
                    lagerdauer = 0;
                    System.out.println("Gültigkeitsbereich nicht eingehalten!");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Bitte Format einhalten!");
            }
        } while (lagerdauer == 0);
        } catch (IOException ex) {
            System.err.println(ex.getLocalizedMessage());
            System.exit(1);
        }
        wbd.setJahrgang(jahrgang);
        wbd.setLagerdauer(lagerdauer);
    }
}
