package testjeu;

public class Options {

    private int nbRow;			// Nombre de lignes
    private int nbCol;			// Nombre de colonnes

    Jeu jeu;

    public Options(Jeu j) {
        OptionsGUI optionsGUI = new OptionsGUI(this);

        this.jeu = j;

    }

    public Options(int nbRow, int nbCol, Jeu jeu) {
        this.jeu = jeu;
        this.nbRow = nbRow;
        this.nbCol = nbCol;
        setSize(nbRow, nbCol, true);
    }

    /**
     * Règle les dimensions du jeu
     *
     * @param initSize Définit si le plateau de jeu doit être initalisé avec la
     * taille entrée
     */
    public void setSize(int nbRow, int nbCol, boolean initSize) {
        this.nbRow = nbRow;
        this.nbCol = nbCol;
        if (initSize) {
            initSize(nbRow, nbCol);
        }
    }

    /**
     * Initialise le plateau de jeu
     */
    public void initSize(int nbRow, int nbCol) {
        jeu.grille = new Grille(nbRow, nbCol, jeu);
        jeu.grille.setVisible(true);

    }

    /**
     * Retourne le nombre de colonnes du jeu
     */
    public int getGameWidth() {
        return nbCol;
    }

    /**
     * Retourne le nombre de lignes du jeu
     */
    public int getGameHeight() {
        return nbRow;
    }

}
