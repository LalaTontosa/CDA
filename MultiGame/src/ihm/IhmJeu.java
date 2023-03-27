package ihm;

import java.util.List;
import java.util.Scanner;

public class IhmJeu {
    Scanner sc = new Scanner(System.in);

    public String demanderNomJoueur(int numJoueur){
        String name;
        System.out.println(
                "_______________________________________\n" +
                        "|      Joueur "+numJoueur+" veuillez entrer      |\n" +
                        "|             votre nom :             |\n" +
                        "|    (Vos pions seront les Noirs )    |\n" +
                        "|                                     |\n" +
                        "|                                     |\n" +
                        "|                                     |\n" +
                        "|_____________________________________|");
        do {
            System.out.print("Réponse : ");
            name=sc.nextLine();
        }
        while(name.equals("") || !name.matches("^[a-zA-Z]*$"));
        return name;
    }

    public String demanderRejouer(){
        String rejouer;
        System.out.println(
                "_______________________________________\n" +
                        "|         Voulez-vous rejouer         |\n" +
                        "|        une autre partie? O/N :      |\n" +
                        "|_____________________________________|");
        do{
            System.out.print("Réponse : ");
            rejouer=sc.nextLine();
        }
        while(rejouer.equals("") || !rejouer.equals("O") && !rejouer.equals("N"));
        return rejouer;
    }

    public String demanderCoup(String nom){
        String coup;
        do {
            //BUG : il affiche deux le message
            System.out.print(nom + ", c'est à vous de jouer." +
                    " Saisir une ligne entre 1 et 8 suivie d'une lettre entre A et H (ex : 3 D) : ");
            coup = sc.nextLine();
        }
        while(syntaxValid(coup));
        return coup;
    }
    private boolean syntaxValid(String coup){
        return coup.equals("") || coup.length()!=3 || coup.charAt(1)!=' ';
    }

    public void demanderPasse(String nom){
        String coup;
        do {
            System.out.print(nom + ", vous ne pouvez plus poser de pion. Passez votre tour en tapant P : ");
            coup = sc.nextLine();
        }
        while(!coup.equals("P"));
    }
    public void afficherDamier(int[][] lesCases){
        int taille = lesCases.length;
        StringBuilder s = new StringBuilder("O");
        for (int i = 65; i<taille+65; i++)
            s.append("|").append(String.valueOf(Character.toChars(i))).append("|");
        s.append("\n");
        for (int i = 0; i < taille; i++) {
            s.append(i + 1);
            for (int j = 0; j < taille; j++)
                switch (lesCases[i][j]) {
                    case 0 -> s.append("\uD83D\uDFE9");
                    case 1 -> s.append("\u26AA");
                    case -1 -> s.append("\u26AB");
                }
            s.append("\n");
        }
        System.out.println(s);
    }

    public void afficherNbPions(String nom1, String nom2, int nb1, int nb2){
        System.out.println(nom1+ " : " + nb1 + " pions noirs.");
        System.out.println(nom2+ " : " + nb2 + " pions blancs.");
    }
    public void afficherScore(String nom, int nb){
        System.out.println(nom+" a gagné " + nb +" partie(s).");
    }

    public void afficherErreur(Exception e){
        System.out.println(e.getMessage());
    }

    public void afficherVainqueur(String m){
        System.out.println("Le vainqueur est : " +m);
    }


    public int choisirMode(){
        System.out.print("Voulez vous jouer en :\n 1--> 1V1\n 2--> 1vIA\nRéponse : ");
        int n;
        do{
            while(!sc.hasNextInt()){
                sc.next();
                System.out.print("Réponse : ");
            }
            n = sc.nextInt();
        }
        while(n!=1 && n!=2);
        return n;
    }

    public int choisirDifficulte(List<String> strat){
        System.out.println("Choisissez la difficulté : ");
        for(int i = 1; i <= strat.size(); i++)
            System.out.println(i + " --> " + strat.get(i-1));
        int n=-1;
        do{
            System.out.print("Réponse : ");
            if(!sc.hasNextInt()){
                sc.next();continue;
            }
            n = sc.nextInt();
        }
        while(n>strat.size() || 1>n);
        return n;
    }

    public void afficherCoup(String nom, String coup){
        System.out.println(nom + " a joué en " + coup);
    }
}
