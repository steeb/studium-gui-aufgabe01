/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.gui.aufgabe01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import javax.swing.JFrame;
import se.edu.gui.aufgabe01.diagramm.WeinBalkenDiagramm;

/**
 *
 * @author steeb
 */
public class GUIAufgabe01 extends JFrame{
    
    static int jahrgang;
    static int lagerdauer;
    static GUIAufgabe01 gui;
    static WeinBalkenDiagramm wbd;
    static BufferedReader c;
    static final int MAX_JAHRGANG = Calendar.getInstance().get(Calendar.YEAR);
    static final int MIN_JAHRGANG = 1800;
    static final int MAX_LAGERDAUER = 50;
    static final int MIN_LAGERDAUER = 1;
    
    public GUIAufgabe01(){
        super();
        this.setTitle("GUI Aufgabe 01");
        this.setBounds(0, 0, 800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(wbd);
    }
    
    public static void werteEingeben() throws IOException {
        String sJahrgang;
        String sLagerdauer;
        
        GUIAufgabe01.jahrgang = 0;
        GUIAufgabe01.lagerdauer = 0;
        
        do {
            System.out.printf("Jahrgang (%d - %d): ",
                    GUIAufgabe01.MIN_JAHRGANG, GUIAufgabe01.MAX_JAHRGANG);
            sJahrgang = c.readLine();
            try {
                GUIAufgabe01.jahrgang = Integer.parseInt(sJahrgang);
                if (GUIAufgabe01.jahrgang > GUIAufgabe01.MAX_JAHRGANG ||
                        GUIAufgabe01.jahrgang < GUIAufgabe01.MIN_JAHRGANG ) {
                    GUIAufgabe01.jahrgang = 0;
                    System.out.println("Gültigkeitsbereich nicht eingehalten!");
                }
            } catch (NumberFormatException nfe) 
            {
                System.out.println("Falsches Format!");
            }
        } while (GUIAufgabe01.jahrgang == 0);
        do {
            System.out.printf("Lagerdauer (%d - %d): ",
                    GUIAufgabe01.MIN_LAGERDAUER, GUIAufgabe01.MAX_LAGERDAUER);
            sLagerdauer = c.readLine();
            try {
                GUIAufgabe01.lagerdauer = Integer.parseInt(sLagerdauer);
                if (GUIAufgabe01.lagerdauer == 0) {
                    System.out.println("Das wäre Traubensaft :)");
                }
                else if (GUIAufgabe01.lagerdauer > GUIAufgabe01.MAX_LAGERDAUER || 
                        GUIAufgabe01.lagerdauer < GUIAufgabe01.MIN_LAGERDAUER ) {
                    GUIAufgabe01.lagerdauer = 0;
                    System.out.println("Gültigkeitsbereich nicht eingehalten!");
                }
            } catch (NumberFormatException nfe) 
            {
                System.out.println("Falsches Format!");
            }
        } while (GUIAufgabe01.lagerdauer == 0);
        wbd.setJahrgang(GUIAufgabe01.jahrgang);
        wbd.setLagerdauer(GUIAufgabe01.lagerdauer);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        wbd = new WeinBalkenDiagramm(jahrgang, lagerdauer);
        InputStreamReader isr = new InputStreamReader(System.in);
        c = new BufferedReader(isr);
        GUIAufgabe01.werteEingeben();
        gui = new GUIAufgabe01();
        gui.setVisible(true);
        while(true)
            GUIAufgabe01.werteEingeben();
    }
}
