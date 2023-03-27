package controleurSys;

import ihm.IhmSys;
import modeleSys.interfaces.JeuPlateau;
import modeleSys.othello.Othello;

import java.util.ArrayList;

public class ControleurSys {
    private final IhmSys ihmSys;
    private final static ArrayList<JeuPlateau> lesJeux = new ArrayList<JeuPlateau>();

    //pour ajouter les jeux
    static{ lesJeux.add(new Othello()); }
    public ControleurSys(IhmSys i){
        ihmSys = i;
    }

    //lancer le systeme
    public void jouerUnJeu(){
        ihmSys.afficherLesJeux(lesJeux);
        JeuPlateau j = ihmSys.demanderUnJeu(lesJeux);
        j.jouer();
    }

}
