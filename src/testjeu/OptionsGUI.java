package testjeu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class OptionsGUI extends JFrame implements ActionListener {

    Options opts;

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints constraints = new GridBagConstraints();
    JPanel pane = new JPanel();

    JLabel niv = new JLabel(" Niveau du jeux  ");

    JRadioButton m = new JRadioButton("6*6");
    JRadioButton d = new JRadioButton("4*4", true);
    JRadioButton h = new JRadioButton("8*8");
    ButtonGroup taille = new ButtonGroup();
    JPanel taillePane = new JPanel();
    JButton ok = new JButton("Ok");

    public OptionsGUI(Options opts) {

        super("Options de jeu");
        setSize(600, 400);
        setLocation(50, 50);

        pane.setLayout(gbl);
        gbl.setConstraints(niv, constraints);
        pane.add(niv);

        taillePane.setLayout(new BorderLayout());
        taille.add(m);  // On ajoute au groupe
        taille.add(d);
        taille.add(h);
        taillePane.add(d, "North");    // On ajoute au panel
        taillePane.add(m, "South");
        taillePane.add(h);
        pane.add(taillePane);

// Bouton Ok
        ok.addActionListener(this);

        buildConstraints(constraints, 1, 6, 2, 1, 0, 20);
        gbl.setConstraints(ok, constraints);
        pane.add(ok);

        setContentPane(pane);
        setVisible(true);
        this.opts = opts;

    }

    /**
     * Sets the good settings for the computer difficulty slider
     */
    public void buildConstraints(GridBagConstraints gbc, int gx, int gy, int gw, int gh, int wx, int wy) {
        gbc.gridx = gx;			// Coordonnées dans la "grille"
        gbc.gridy = gy;
        gbc.gridwidth = gw;		// Nombre de cellules sur lesquelles s'étend l'objet
        gbc.gridheight = gh;
        gbc.weightx = wx;		// "Largeur", en proportion
        gbc.weighty = wy;
    }

    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();
        int nbCol = 4, nbRow = 4;
        if (src == ok) {

            if (d.isSelected()) {
                nbRow = 4;
                nbCol = 4;
            } else if (m.isSelected()) {
                nbRow = 6;
                nbCol = 6;
            } else if (h.isSelected()) {
                nbRow = 8;
                nbCol = 8;
            }
            opts.setSize(nbRow, nbCol, true);

            setVisible(false);

        }

    }

}
