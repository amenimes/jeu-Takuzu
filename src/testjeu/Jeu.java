package testjeu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.Timer;

public class Jeu {

    Grille grille;		// Juste le graphisme
    JButton[][] matJeu; // La matrice qui contiendra la partie
    int nbCoups;		// Contient le nombre de coups joués (détection partie nulle)
    boolean enCours;	// Indique si la partie est en cours ou terminée
    Options opts;		// Une fenêtre d'options

    String[] historique;
    int n, m;
    boolean coloneIncomplete = false; 	// Variable indiquant si la ligne est completement remplis, ou non
    int nb_de_1 = 0;					// Nombre de 1 de la ligne
    int nb_de_0 = 0;					// Nombre de 0 de la ligne
    Timer timer;

    public Jeu(boolean optTrue) {
        if (optTrue) {
            opts = new Options(this);
        }

        matJeu = new JButton[n][m];

    }

    public Jeu(JButton[][] m) {
        this.matJeu = m;

    }

    public boolean toutValide() {
        boolean b = true;
        for (int j = 0; j < matJeu.length; j++) {
            if (!ttcolValide(j) || !colUnique(j)) {

                return false;
            }
        }
        for (int i = 0; i < matJeu.length; i++) {
            if (!ttligValide(i) || !ligUnique(i)) {
                return false;
            }
        }

        return b;

    }

    boolean ttligValide(int i) {

        nb_de_1 = 0;					// Nombre de 1 de la ligne
        nb_de_0 = 0;					// Nombre de 0 de la ligne

        if ("0".equals(matJeu[i][0].getText())) {
            nb_de_0++;
        } else if ("1".equals(matJeu[i][0].getText())) {
            nb_de_1++;
        }
        if ("0".equals(matJeu[i][1].getText())) {
            nb_de_0++;
        } else if ("1".equals(matJeu[i][1].getText())) {
            nb_de_1++;
        }
        // Pour chaque colone
        for (int j = 2; j < matJeu.length; j++) {
            // Si trois cases consecutives sont identique (non vide)
            if ((matJeu[i][j - 2].getText().equals(matJeu[i][j - 1].getText()) && matJeu[i][j - 1].getText().equals(matJeu[i][j].getText())) && !" ".equals(matJeu[i][j].getText())) {
                return false; // Le Takuzu est invalide
            }
            if ("0".equals(matJeu[i][j].getText())) {
                nb_de_0++;
            } else if ("1".equals(matJeu[i][j].getText())) {
                nb_de_1++;
            }
        }

        return (nb_de_1 == (matJeu.length / 2) && nb_de_0 == (matJeu.length / 2));
    }

    boolean ttcolValide(int i) {

        nb_de_1 = 0;					// Nombre de 1 de la ligne
        nb_de_0 = 0;					// Nombre de 0 de la ligne

        if ("0".equals(matJeu[0][i].getText())) {
            nb_de_0++;
        } else if ("1".equals(matJeu[0][i].getText())) {
            nb_de_1++;
        }
        if ("0".equals(matJeu[1][i].getText())) {
            nb_de_0++;
        } else if ("1".equals(matJeu[1][i].getText())) {
            nb_de_1++;
        }
        // Pour chaque lignes
        for (int j = 2; j < matJeu.length; j++) {
            // Si trois cases consecutives sont identique et remplis
            if ((matJeu[j - 2][i]).getText().equals((matJeu[j - 1][i]).getText()) && (matJeu[j - 1][i]).getText().equals((matJeu[j][i]).getText()) && !" ".equals((matJeu[j][i]).getText())) {
                return false; // Le jeu est invalide
            }
            if ("0".equals(matJeu[j][i].getText())) {
                nb_de_0++;
            } else if ("1".equals(matJeu[j][i].getText())) {
                nb_de_1++;
            }
        }

        return nb_de_1 == (matJeu.length / 2);
    }

    boolean ligValide(int i) {
        coloneIncomplete = false; 	// Variable indiquant si la ligne est completement remplis, ou non
        nb_de_1 = 0;					// Nombre de 1 de la ligne
        nb_de_0 = 0;					// Nombre de 0 de la ligne

        if ("0".equals(matJeu[i][0].getText())) {
            nb_de_0++;
        } else if ("1".equals(matJeu[i][0].getText())) {
            nb_de_1++;
        } else {
            coloneIncomplete = true; 	// On definit coloneIncomplete a vrai
        }
        if ("0".equals(matJeu[i][1].getText())) {
            nb_de_0++;
        } else if ("1".equals(matJeu[i][1].getText())) {
            nb_de_1++;
        } else {
            coloneIncomplete = true; 	// On definit coloneIncomplete a vrai
        }
        // Pour chaque colone
        for (int j = 2; j < matJeu.length; j++) {
            // Si trois cases consecutives sont identique (non vide)
            if ((matJeu[i][j - 2].getText().equals(matJeu[i][j - 1].getText()) && matJeu[i][j - 1].getText().equals(matJeu[i][j].getText())) && !" ".equals(matJeu[i][j].getText())) {
                return false; // Le Takuzu est invalide
            }
            if ("0".equals(matJeu[i][j].getText())) {
                nb_de_0++;
            } else if ("1".equals(matJeu[i][j].getText())) {
                nb_de_1++;
            } else {
                coloneIncomplete = true; 	// On definit coloneIncomplete a vrai
            }
        }

        return (nb_de_1 == (matJeu.length / 2) && nb_de_0 == (matJeu.length / 2)) || (coloneIncomplete && nb_de_0 <= (matJeu.length / 2) && nb_de_1 <= (matJeu.length / 2));
    }

//Verifie la validite de la colone
    boolean colValide(int i) {

        coloneIncomplete = false; 	// Variable indiquant si la ligne est completement remplis, ou non
        nb_de_1 = 0;					// Nombre de 1 de la ligne
        nb_de_0 = 0;					// Nombre de 0 de la ligne

        if ("0".equals(matJeu[0][i].getText())) {
            nb_de_0++;
        } else if ("1".equals(matJeu[0][i].getText())) {
            nb_de_1++;
        } else {
            coloneIncomplete = true; 	// On definit coloneIncomplete a vrai
        }
        if ("0".equals(matJeu[1][i].getText())) {
            nb_de_0++;
        } else if ("1".equals(matJeu[1][i].getText())) {
            nb_de_1++;
        } else {
            coloneIncomplete = true; 	// On definit coloneIncomplete a vrai
        }
        // Pour chaque lignes
        for (int j = 2; j < matJeu.length; j++) {
            // Si trois cases consecutives sont identique et remplis
            if ((matJeu[j - 2][i]).getText().equals((matJeu[j - 1][i]).getText()) && (matJeu[j - 1][i]).getText().equals((matJeu[j][i]).getText()) && !" ".equals((matJeu[j][i]).getText())) {
                return false; // Le jeu est invalide
            }
            if ("0".equals(matJeu[j][i].getText())) {
                nb_de_0++;
            } else if ("1".equals(matJeu[j][i].getText())) {
                nb_de_1++;
            } else {
                coloneIncomplete = true; 	// On definit coloneIncomplete a vrai
            }
        }

        return nb_de_1 == (matJeu.length / 2) || (coloneIncomplete && nb_de_1 <= (matJeu.length / 2));
    }

//Verifie qu'une ligne n'a pas de double dans le jeu
    boolean ligUnique(int j) {
        // Pour chaque ligne
        for (int i = 0; i < j; i++) {
            // Si deux lignes sont identique
            if (Arrays.equals(this.matJeu[i], this.matJeu[j])) {
                return false;
            }
        }

        return true;
    }

////Verifie qu'une colone n'a pas de double dans le jeu
    boolean colUnique(int j) {
        // Pour chaque ligne
        for (int i = 0; i < j; i++) {
            // Si deux lignes sont identique
            if (Arrays.equals(this.matJeu[i], this.matJeu[j])) {
                return false;
            }
        }

        return true;
    }

    public static void nouveauJeu() {
        Jeu j = new Jeu(true);
    }

    public void enregistrer(File file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(fw);
            out.write("Jeu Takuzu \n");  // En-tête de fichier
            out.write(opts.getGameHeight() + " ");
            out.write(opts.getGameWidth() + " ");

            for (int i = 0; i < opts.getGameHeight(); i++) {
                for (int j = 0; j < opts.getGameWidth(); j++) {

                    if (!" ".equals(matJeu[i][j].getText())) {
                        out.write(matJeu[i][j].getText() + " ");
                    } else {
                        out.write("2 ");
                    }
                }
            }

            out.close();
        } catch (IOException e) {

        }
    }

    public void ouvrir(File file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader out = new BufferedReader(fr);
            String line = out.readLine();
            if (!line.equals("\n")) {  // Test de validité de fichier
                line = out.readLine();
                StringTokenizer s = new StringTokenizer(line);
                int nbR = Integer.parseInt(s.nextToken());
                int nbC = Integer.parseInt(s.nextToken());

                line = out.readLine();
                while ((line = out.readLine()) != null) {
                    System.out.println("page");
                }
                JButton[][] mat = new JButton[nbR][nbC];
                int l = 0;

                for (int i = 0; i < nbR; i++) {
                    for (int j = 0; j < nbC; j++) {
                        mat[i][j] = new JButton();
                        String b = s.nextToken();
                        if (!"2".equals(b)) {
                            mat[i][j].setText(b);
                        } else {
                            mat[i][j].setText(" ");
                        }

                    }

                }
                for (int i = 0; i < nbR; i++) {
                    for (int j = 0; j < nbC; j++) {

                        System.out.println(mat[i][j].getText());

                    }
                }

                this.matJeu = mat;
                if (nbR == opts.getGameWidth()) {
                    this.grille.affiche(mat, nbR, nbC);
                } else {
                    Saisie.erreurMsgOk("Le fichier que vous tentez d'ouvrir n'est pas un fichier de jeu valide \n car le taille n'est pas compatible.", "Access violation error ;o)");
                }
                System.out.println("apres affiche");

                out.close();

            } else {
                Saisie.erreurMsgOk("Le fichier que vous tentez d'ouvrir n'est pas un fichier de jeu valide.", "Access violation error ;o)");
            }
        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {

        Jeu jeu = new Jeu(true);

    }

}
