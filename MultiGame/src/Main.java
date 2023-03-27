import controleurSys.ControleurSys;
import ihm.IhmJeu;
import ihm.IhmSys;
import modeleSys.exception.NotConform;
import modeleSys.othello.FacadeOthello;
import modeleSys.othello.Othello;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NotConform {
        ControleurSys c = new ControleurSys(new IhmSys());
        c.jouerUnJeu();
    }
}