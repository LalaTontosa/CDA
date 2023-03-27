package modeleSys.Joueur;

import modeleSys.interfaces.JeuPlateau;
import modeleSys.interfaces.Strategie;

public class JoueurRobot extends Joueur{
    private Strategie strategie;
    public JoueurRobot(){
        super("IA");
    }

    //lui attribuer une strategie par defaut
    public void initialiserStrat(Strategie strat){
        this.strategie = strat;
    }


    //utiliser la strategie
    //Peut etre qu'il faut une petite modif ?????
    public Object appliquerStrategie(JeuPlateau jeuPlateau){
        return strategie.appliquer(jeuPlateau);
    }


}
