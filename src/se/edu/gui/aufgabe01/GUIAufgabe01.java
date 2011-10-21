/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.gui.aufgabe01;

import java.io.Console;
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
    static Console c;
    
    public GUIAufgabe01(){
        super();
        this.setTitle("GUI Aufgabe 01");
        this.setBounds(0, 0, 800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(wbd);
    }
    
    public static void werteEingeben() {
        String sJahrgang;
        String sLagerdauer;
        
        GUIAufgabe01.jahrgang = 0;
        GUIAufgabe01.lagerdauer = 0;
        
        do {
            sJahrgang = c.readLine("Jahrgang: ");
            try {
                GUIAufgabe01.jahrgang = Integer.parseInt(sJahrgang);
            } catch (NumberFormatException nfe) 
            {
                System.out.println(nfe.getLocalizedMessage());
            }
        } while (GUIAufgabe01.jahrgang == 0);
        do {
            sLagerdauer = c.readLine("Lagerdauer: ");
            try {
                GUIAufgabe01.lagerdauer = Integer.parseInt(sLagerdauer);
            } catch (NumberFormatException nfe) 
            {
                System.out.println(nfe.getLocalizedMessage());
            }
        } while (GUIAufgabe01.lagerdauer == 0);
        wbd.setJahrgang(GUIAufgabe01.jahrgang);
        wbd.setLagerdauer(GUIAufgabe01.lagerdauer);
        wbd.repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        wbd = new WeinBalkenDiagramm(jahrgang, lagerdauer);
        c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        GUIAufgabe01.werteEingeben();
        gui = new GUIAufgabe01();
        gui.setVisible(true);
        while(true)
            GUIAufgabe01.werteEingeben();
    }
}
