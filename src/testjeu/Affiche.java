/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjeu;

/**
 *
 * @author Ameni Mestiri
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Affiche extends JFrame {

    JPanel panel;
    JFrame frame;

    public Affiche() {
        frame = new JFrame();

        frame.setSize(500, 300);
        frame.setVisible(true);
        panel = new JPanel();
        frame.add(panel);
    }

    public void ajouter(String l) {

        JTextArea labelArea = new JTextArea(l);
        labelArea.setEditable(false);
        labelArea.setOpaque(false);

        panel.add(labelArea);

    }
}
