package modeleSys.strategie;

import modeleSys.interfaces.JeuPlateau;
import modeleSys.exception.NotAdapted;
import modeleSys.interfaces.Strategie;
import modeleSys.othello.Othello;

import java.util.List;
import java.util.Random;

public class OthelloNaive implements Strategie {
    @Override
    public Object appliquer(JeuPlateau jeuPlateau){
        List<String> coupPossible = jeuPlateau.coupPossible();
        return coupPossible.get(new Random().nextInt(0,coupPossible.size()));
    }

    @Override
    public String getName() {
        return "Naive";
    }
}
