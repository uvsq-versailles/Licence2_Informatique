package fr.uvsq.poo.monprojet;

import java.util.Scanner;
@SuppressWarnings("unused")
public class PNJ extends Personnage implements Conversation {
	private static final long serialVersionUID = 1L;
	private int posXPnj; // pour sauvegarger la position courante du PNJ en X
	private int posYPnj; // pour sauvegarder la position courante du PNJ en Y
	private static final int PNJ = 8;

	public PNJ(int posXPnj, int posYPnj) {
		super();
		armePersonnage = null;
		this.posXPnj = posXPnj;
		this.posYPnj = posYPnj;
	}

	public static int getPNJ() {return PNJ;}

	public String parler(Personnage p) {
		Scanner sc = new Scanner(System.in);
		int i = 0;
		try {
			do {
				System.out.println(this.type + " : Bonjour, Pour plus d'informations sur le jeu tappez(1), pour plus d'informations sur votre personnage (2)");
				i = sc.nextInt();
				sc.nextLine();
			} while (i < 1 || i > 2);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		((PJ)p).envoyerMessage(this, ""+i);
		return ""+i;
	}

	public String infoJeu() {
		return "A => Armes   E => Equipements  Pour les prendre il suffis de passer dessus. Si vous voulez Selectionner une arme tapez 'A' de même pour un équipement 'E'.";
	}

	public String infoJoueur(PJ pj) {
		int piece = keyListener.getNiveau()-pj.getMonnaie();
		return "Vous êtes un "+ pj.getType()+" vous avez "
		 		+ pj.getMonnaie() + "€ "+ pj.getExperience()+" xP "
				+ pj.getVie() + " pv. \n  Il vous manque "
				+piece+" pièces pour passer la porte rouge!";
	}

	@Override
	public void envoyerMessage(Personnage p, String msg) {
		String rep = type + "  : " + msg + "";
		if(msg.equals("1"))envoyerMessage(p, infoJeu());
		else if(msg.equals("2"))envoyerMessage(p, infoJoueur((PJ) p));
		else ((PJ) p).recevoirMessage(rep);
	}

	@Override
	public void recevoirMessage(String msg) {System.out.println(msg);}

	@Override
	protected void deplacement(int x, int y) {}

}
