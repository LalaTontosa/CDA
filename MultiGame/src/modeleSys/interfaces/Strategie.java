package modeleSys.interfaces;

public interface Strategie {
    public Object appliquer(JeuPlateau jeuPlateau);
    public String getName();
}
