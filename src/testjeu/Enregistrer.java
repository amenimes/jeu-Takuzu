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
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Enregistrer extends JFrame {

    JLabel l;
    JTextField nom;
    JButton ok;
    JFrame frame;

    public Enregistrer() {
        frame = new JFrame("Enregistrer votre nom");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);

        l = new JLabel("vous avez gagné \n ecrire votre nom");

        nom = new JTextField(10);
        Box hBox1 = Box.createHorizontalBox();
        hBox1.add(l);
        hBox1.add(Box.createHorizontalStrut(5));
        hBox1.add(nom);
        Box hBox3 = Box.createHorizontalBox();
        ok = new JButton("OK");
        hBox3.add(Box.createGlue());

        Box vBox = Box.createVerticalBox();
        vBox.add(hBox1);

        vBox.add(Box.createGlue());
        vBox.add(ok);

        Container c = getContentPane();
        c.add(vBox, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(c);

    }

    public String name() {

        String m = nom.getText();
        ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ok) {
                    frame.setVisible(false);
                    frame.dispose();

                    System.out.println("ameni");
                }

            }
        }
        );
        return m;
    }
}
