package modeleSys.exception;

public class GameOver extends Exception{
    public GameOver(){
        super("La partie est finie.");
    }
}
