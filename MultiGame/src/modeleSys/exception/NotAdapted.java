package modeleSys.exception;

public class NotAdapted extends Exception{
    public NotAdapted(){
        super("La strategie n'est pas adaptée à ce jeu");
    }
}
