package testjeu;

import java.io.*;
import java.awt.*;
import java.awt.RenderingHints.Key;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.io.*;
import java.text.*;

import java.util.Collections;

import java.util.Vector;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;

public class Grille extends JFrame implements MouseListener, ActionListener, WindowListener {

    static int nbGrilles;
    // Contient le nombre de fenetres actuellement ouvertes

    JPanel global = new JPanel();

    JPanel pane = new JPanel();
    GridLayout gridLay;
    JPanel pane2;

    JToolBar bar = new JToolBar();
    ImageIcon nou = new ImageIcon(new ImageIcon("C:\\Users\\Ameni Mestiri\\Documents\\NetBeansProjects\\testjeu\\src\\testjeu\\nouv.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
    JButton nouv = new JButton(nou);
    ImageIcon ope = new ImageIcon(new ImageIcon("C:\\Users\\Ameni Mestiri\\Documents\\NetBeansProjects\\testjeu\\src\\testjeu\\open.jpg").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
    JButton open = new JButton(ope);
    ImageIcon savea = new ImageIcon(new ImageIcon("C:\\Users\\Ameni Mestiri\\Documents\\NetBeansProjects\\testjeu\\src\\testjeu\\save.jpg").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
    JButton saveas = new JButton(savea);
    ImageIcon ferme = new ImageIcon(new ImageIcon("C:\\Users\\Ameni Mestiri\\Documents\\NetBeansProjects\\testjeu\\src\\testjeu\\fermer.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
    JButton fermer = new JButton(ferme);
    ImageIcon sco = new ImageIcon(new ImageIcon("C:\\Users\\Ameni Mestiri\\Documents\\NetBeansProjects\\testjeu\\src\\testjeu\\sco.jpg").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
    JButton score = new JButton(sco);
    ImageIcon ver = new ImageIcon(new ImageIcon("C:\\Users\\Ameni Mestiri\\Documents\\NetBeansProjects\\testjeu\\src\\testjeu\\verif.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
    JButton verif = new JButton(ver);
    ImageIcon cond = new ImageIcon(new ImageIcon("C:\\Users\\Ameni Mestiri\\Documents\\NetBeansProjects\\testjeu\\src\\testjeu\\cond.jpg").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
    JButton con = new JButton(cond);
    DecimalFormat formater = new DecimalFormat("00");
    int heure = 0, minute = 0, seconde = 0;

    private int delais = 1000;
    JLabel statusBar = new JLabel(new DecimalFormat("00").format(heure) + ":" + new DecimalFormat("00").format(minute) + ":" + new DecimalFormat("00").format(seconde), JLabel.LEFT);
    String s;
    private ActionListener tache_timer;
    Timer timer1;
    int nbRow, nbCol;
    JButton b;

    public Grille(int nbRow, int nbCol, Jeu jeu) {

        super("JEU ");

        setSize(700, 600);
        setLocation(50, 50);
        this.nbRow = nbRow;
        this.nbCol = nbCol;

        global.setLayout(new BorderLayout());

        gridLay = new GridLayout(nbRow, nbCol, 0, 0);
        pane.setLayout(gridLay);

        makeToolBar(jeu);
        global.add(bar, "North");

        makeCells(nbRow, nbCol, jeu);
        global.add(pane, "Center");
        chrono();
        global.add(statusBar, "South");

        setContentPane(global);
        setVisible(false);

        addWindowListener(this);

        nbGrilles++;

    }
    JButton[][] array;

    public void makeCells(int nbRow, int nbCol, Jeu jeu) {
        array = new JButton[nbRow][nbCol];
        for (int i = 0; i < nbRow; ++i) {
            for (int j = 0; j < nbRow; ++j) {
                JButton b = new JButton(" ");
                Font f = b.getFont().deriveFont(28.0f);

                b.setFont(f);
                b.setBackground(null);
                array[i][j] = b;
                pane.add(array[i][j]);
            }
        }
        //pour remplir initialement par 0 et 1 par random
        for (int i = 0; i < nbRow; i++) {
            for (int j = 0; j < nbRow; j++) {
                int nb = (int) (Math.random() * 5);
                if (i >= 2) {
                    if ((array[i - 2][j].getText()).equals(array[i - 1][j].getText()) && !(array[i - 1][j].getText()).equals(String.valueOf(nb)) && nb != 2 && nb != 3 && nb != 4) {
                        array[i][j].setText(String.valueOf(nb));
                    }
                } else if (j >= 2) {
                    if ((array[i][j - 2].getText()).equals(array[i][j - 1].getText()) && !(array[i][j - 1].getText()).equals(String.valueOf(nb)) && nb != 2 && nb != 3 && nb != 4) {
                        array[i][j].setText(String.valueOf(nb));
                    }
                } else if (i < 2 || j < 2) {
                    if (nb != 2 && nb != 3 && nb != 4) {
                        array[i][j].setText(String.valueOf(nb));
                    }
                }

            }
        }

        for (int i = 0; i < nbRow; i++) {
            for (int j = 0; j < nbRow; j++) {
                b = array[i][j];

                array[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent clic) {

                        if (((JButton) clic.getSource()).getText() == " ") {
                            ((JButton) clic.getSource()).setBackground(Color.green);
                            ((JButton) clic.getSource()).setText("0");
                        } else if (((JButton) clic.getSource()).getText() == "0") {

                            ((JButton) clic.getSource()).setBackground(Color.blue);
                            ((JButton) clic.getSource()).setText("1");
                        } else if (((JButton) clic.getSource()).getText() == "1") {
                            ((JButton) clic.getSource()).setBackground(null);
                            ((JButton) clic.getSource()).setText(" ");
                        }

                        for (int i = 0; i < jeu.matJeu.length; i++) {
                            Color defaultColor = ((JButton) clic.getSource()).getBackground();
                            if (!jeu.ligValide(i) || !jeu.colValide(i)) {

                                ((JButton) clic.getSource()).setBackground(Color.yellow);

                            } else {

                                ((JButton) clic.getSource()).setBackground(defaultColor);

                            }

                        }

                    }
                }
                );

            }
        }
        jeu.matJeu = array;

    }

    public void affiche(JButton[][] m, int nbR, int nbC) {

        pane.removeAll();
        pane.repaint();

        for (int i = 0; i < nbR; i++) {
            for (int j = 0; j < nbC; j++) {
                array[i][j] = new JButton();
                Font f = array[i][j].getFont().deriveFont(28.0f);
                array[i][j].setFont(f);
                array[i][j] = m[i][j];
                pane.add(array[i][j]);

            }
        }
        global.add(pane, "Center");

        for (int i = 0; i < nbR; i++) {
            for (int j = 0; j < nbC; j++) {

                array[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent clic) {
                        if (((JButton) clic.getSource()).getText() == " ") {
                            ((JButton) clic.getSource()).setBackground(Color.green);
                            ((JButton) clic.getSource()).setText("0");
                        } else if (((JButton) clic.getSource()).getText() == "0") {

                            ((JButton) clic.getSource()).setBackground(Color.blue);
                            ((JButton) clic.getSource()).setText("1");
                        } else if (((JButton) clic.getSource()).getText() == "1") {
                            ((JButton) clic.getSource()).setBackground(null);
                            ((JButton) clic.getSource()).setText(" ");
                        }
                    }
                }
                );

            }
        }
    }

    public void makeToolBar(Jeu jeu) {
        // button our les condition du jeu
        con.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Affiche a = new Affiche();
                a.frame.setTitle("Les Conditions du Jeu");
                InputStream flux;
                try {
                    flux = new FileInputStream("C:\\Users\\Ameni Mestiri\\Documents\\NetBeansProjects\\testjeu\\src\\regle.txt");

                    InputStreamReader lecture = new InputStreamReader(flux);
                    BufferedReader buff = new BufferedReader(lecture);
                    String ligne;

                    String l = new String("");
                    String tt = new String("");

                    while ((ligne = buff.readLine()) != null) {
                        tt += ligne;
                        tt += "\n";
                    }

                    a.ajouter(tt);

                    buff.close();

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Grille.class
                            .getName()).log(Level.SEVERE, null, ex);

                } catch (IOException ex) {
                    Logger.getLogger(Grille.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        );
        nouv.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Jeu.nouveauJeu();
            }
        });
        open.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(jeu.grille);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    jeu.ouvrir(fc.getSelectedFile());
                }
            }
        });
        saveas.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(jeu.grille);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    jeu.enregistrer(fc.getSelectedFile());
                }
            }

        });

        fermer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ok = Saisie.question_ouinon("Etes-vous sûr de vouloir fermer toutes les fenêtres ?", "Fermer le programme");
                if (ok == 0) {
                    System.exit(0);
                }
            }
        }
        );
        //button pour afficher les 10 meilleur score
        score.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Affiche a = new Affiche();
                a.frame.setTitle("Meilleur Score");

                InputStream flux;
                try {
                    flux = new FileInputStream("C:\\Users\\Ameni Mestiri\\Documents\\NetBeansProjects\\testjeu\\src\\score.txt");

                    InputStreamReader lecture = new InputStreamReader(flux);
                    BufferedReader buff = new BufferedReader(lecture);
                    String ligne;

                    String l = new String("");
                    // un vector pour que les score soit ordonner
                    Vector V = new Vector();

                    while ((ligne = buff.readLine()) != null) {

                        V.addElement(ligne);

                    }

                    Collections.sort(V);
                    Collections.reverse(V);

                    if (V.size() > 10) {
                        for (int i = 0; i < 10; i++) {
                            int taille = V.size();

                            l += (String) V.lastElement();
                            l += "\n";
                            V.remove(taille - 1);
                        }
                        //si la taille de vector est infereur a 10 il féaut afficher just les scores exist
                    } else {
                        int taille = V.size();
                        for (int i = 0; i < taille; i++) {

                            l += (String) V.lastElement();
                            l += "\n";
                            V.remove(V.size() - 1);
                        }
                    }

                    a.ajouter(l);
                    buff.close();

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Grille.class
                            .getName()).log(Level.SEVERE, null, ex);

                } catch (IOException ex) {
                    Logger.getLogger(Grille.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        );
        verif.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                timer1.stop();
                s = statusBar.getText();
                if (jeu.toutValide() == false) {

                    int ok = Saisie.question_ouinon("Vous avez perdu ,voulez vous recommencer?", "Perdu");
                    if (ok == 1) {
                        System.exit(0);
                    } else {
                        dispose();
                        Jeu.nouveauJeu();
                    }

                } else {

                    try {

                        File file = new File("C:\\Users\\Ameni Mestiri\\Documents\\NetBeansProjects\\testjeu\\src\\score.txt");

                        // créer le fichier s'il n'existe pas
                        if (!file.exists()) {
                            file.createNewFile();
                        }

                        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                        BufferedWriter bw = new BufferedWriter(fw);

                        bw.write(s);
                        bw.write("\n");
                        bw.close();

                    } catch (IOException eb) {
                        eb.printStackTrace();
                    }

                    int ok = Saisie.question_ouinon("Vous avez gagnée , voulez vous recommencer ? ", "Gagnée");
                    if (ok == 1) {
                        System.exit(0);
                    } else {
                        dispose();
                        Jeu.nouveauJeu();
                    }

                }
            }
        }
        );

        bar.add(nouv);

        bar.add(open);

        bar.add(saveas);

        bar.add(score);

        bar.add(verif);
        bar.add(con);
        bar.add(fermer);

    }

    public void chrono() {
        tache_timer = new ActionListener() {
            public void actionPerformed(ActionEvent e1) {

                seconde++;
                if (seconde == 60) {
                    seconde = 0;
                    minute++;
                }
                if (minute == 60) {
                    minute = 0;
                    heure++;
                }
                //Afficher le chrono dans un JLabel
                statusBar.setText(new DecimalFormat("00").format(heure) + ":" + new DecimalFormat("00").format(minute) + ":" + new DecimalFormat("00").format(seconde));

            }
        };
        //Action et temps execution de la tache
        timer1 = new Timer(delais, tache_timer);
        //Demarrer le chrono
        timer1.start();
    }

    public void mouseClicked(MouseEvent evt) {

    }

    public void mouseEntered(MouseEvent evt) {

    }

    public void mouseExited(MouseEvent evt) {
    }

    public void mousePressed(MouseEvent evt) {

    }

    public void mouseReleased(MouseEvent evt) {

    }

    public void windowActivated(java.awt.event.WindowEvent windowEvent) {
    }

    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
    }

    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        nbGrilles--;
        if (nbGrilles == 0) // quit if 0 games opened
        {
            System.exit(-1);
        }
    }

    public void windowDeactivated(java.awt.event.WindowEvent windowEvent) {
    }

    public void windowDeiconified(java.awt.event.WindowEvent windowEvent) {
    }

    public void windowIconified(java.awt.event.WindowEvent windowEvent) {
    }

    public void windowOpened(java.awt.event.WindowEvent windowEvent) {
    }

    void addActionListener(ActionListener actionListener) {

    }

    public void actionPerformed(ActionEvent e) {

    }

}
