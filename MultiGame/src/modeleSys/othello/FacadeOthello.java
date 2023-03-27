package modeleSys.othello;

import ihm.IhmJeu;
import modeleSys.Joueur.Joueur;
import modeleSys.Joueur.JoueurHumain;
import modeleSys.Joueur.JoueurRobot;
import modeleSys.exception.*;
public class FacadeOthello{
    private Othello othello;
    private final IhmJeu ihm = new IhmJeu();

    private Joueur joueur1;
    private Joueur joueur2;

    public FacadeOthello(Othello othello){
        this.othello = othello;
    }


    public void jouer() {
        creerJoueurs();
        lancerJeu();
        finJeu();
    }

    public void creerJoueurs(){
        switch (ihm.choisirMode()) {
            case 1 -> creer1V1();
            case 2 -> creer1VIA();
        }
    }
    private void creer1V1(){
        this.joueur1 = new JoueurHumain("tom");
        this.joueur2 = new JoueurHumain("olivier");
    }

    private void creer1VIA(){
        this.joueur1 = new JoueurHumain("tom");
        JoueurRobot j = new JoueurRobot();
        int n = ihm.choisirDifficulte(Othello.getStrats());
        j.initialiserStrat(Othello.getUnStrategie(n-1));
        this.joueur2 = j;
    }

    private void lancerJeu(){
        String rejouer;
        do {
            commencerPartie();
            rejouer = ihm.demanderRejouer();
        }
        while(rejouer.equals("O"));
    }

    private void finJeu(){
        ihm.afficherScore(joueur1.getName(), joueur1.getPartieGagnee());
        ihm.afficherScore(joueur2.getName(), joueur2.getPartieGagnee());
    }

    private void commencerPartie(){
        othello = new Othello();othello.initialiserDamier();
        Joueur joueurCourant;
        do {
            othello.changerJoueurCourant();
            try{
                joueurCourant = othello.getJCourant()==1? joueur2 : joueur1;
                if(passeOblige(joueurCourant)) continue;
                EtatPartie();
                mettrePion(joueurCourant);
            }
            catch(GameOver | NotAdapted e){
                ihm.afficherErreur(e);break;
            }
        }
        while (true);
        resultat();
    }

    private void mettrePion(Joueur joueur) throws NotAdapted {
        String coup;
        do {
            if(joueur instanceof JoueurRobot) coup=(String)((JoueurRobot) joueur).appliquerStrategie(othello);
            else coup = ihm.demanderCoup(joueur.getName());
            try {
                int x = Integer.parseInt(coup.substring(0,1))-1;int y = coup.charAt(2)-65;
                othello.mettreUnPion(x,y);
                ihm.afficherCoup(joueur.getName(),coup);
                break;
            }catch (NotConform | InvalidMove c){
                ihm.afficherErreur(c);
            }
        }
        while (true);
    }

    private boolean passeOblige(Joueur jc) throws GameOver {
        if(othello.coupPossible().isEmpty()){
            othello.changerJoueurCourant();
            if(othello.coupPossible().isEmpty()) throw new GameOver();
            othello.changerJoueurCourant();
            EtatPartie();
            if(jc instanceof JoueurHumain) ihm.demanderPasse(getNameJC());
            return true;
        }
        return false;
    }
    private void EtatPartie(){
        ihm.afficherDamier(othello.getDamier().getCases());
        ihm.afficherNbPions(joueur1.getName(),joueur2.getName(), othello.getNbPionsNoir(), othello.getNbPionsBlanc());
        System.out.println(othello.coupPossible());
    }
    private void resultat(){
        String gagnant;
        if(othello.vainqueur()==0) {gagnant="ex aequo"; joueur1.incrementePG();
            joueur2.incrementePG();}
        else if(othello.vainqueur()>0) {gagnant = joueur1.getName(); joueur1.incrementePG();}
        else {gagnant= joueur2.getName(); joueur2.incrementePG();}
        ihm.afficherVainqueur(gagnant);
    }

    private String getNameJC(){
        return (othello.getJCourant()==-1? joueur1.getName(): joueur2.getName());
    }


}
