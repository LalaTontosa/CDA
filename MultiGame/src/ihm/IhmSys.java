package ihm;

import modeleSys.interfaces.JeuPlateau;

import java.util.ArrayList;
import java.util.Scanner;

public class IhmSys {
    public void afficherLesJeux(ArrayList<JeuPlateau> lesJeuxes){
        int i = 0;
        for (JeuPlateau j : lesJeuxes)
            System.out.println(++i + " --> " + j.getNameGame());
    }

    public JeuPlateau demanderUnJeu(ArrayList<JeuPlateau> lesJeuxes){
        Scanner sc = new Scanner(System.in);
        int n=-1;
        do{
            System.out.print("Choisissez un Jeu : ");
            if (!sc.hasNextInt()) {
                sc.next(); continue;
            }
            n = sc.nextInt();
        }
        while(lesJeuxes.size()<n || n<1);
        return lesJeuxes.get(n-1);
    }
}
