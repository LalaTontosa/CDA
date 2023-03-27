package modeleSys.interfaces;

import java.util.List;

public interface JeuPlateau {
    public String getNameGame();

    public List<String> coupPossible();

    public void jouer();
}
