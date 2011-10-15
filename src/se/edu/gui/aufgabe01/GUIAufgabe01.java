/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.edu.gui.aufgabe01;

import javax.swing.JFrame;
import se.edu.gui.aufgabe01.diagramm.WeinBalkenDiagramm;

/**
 *
 * @author steeb
 */
public class GUIAufgabe01 extends JFrame{
    
    public GUIAufgabe01(){
        super();
        this.setTitle("GUI Aufgabe 01");
        this.setBounds(100, 100, 100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new WeinBalkenDiagramm());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUIAufgabe01 gui = new GUIAufgabe01();
        gui.setVisible(true);
    }
}
