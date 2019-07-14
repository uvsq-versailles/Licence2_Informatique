package fr.uvsq.poo.monprojet;

import java.io.Serializable;

abstract class Personnage implements Serializable{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	protected int vie, vie_max;
	public Inventaire inventaire;
	private static int JOUEUR = 3;
	protected Arme armePersonnage;
	protected String type;

	public Personnage(int i) {
		armePersonnage = new Arme(0,10000);
		if (i == 1) {initialisationGuerrier();}

		else if (i == 2) {initialisationVoleur();}

		else if (i == 3) {initialisationMagicien();}

		else if (i == 4) {initialisationPrêtre();}
	}

	public Personnage() {type = "PNJ gentil";}

	protected String getType() {return this.type;}

	public Arme getArmePersonnage() {return armePersonnage;}

	public void setArmePersonnage(Arme armePersonnage) {
		this.armePersonnage = armePersonnage;
	}

	public int getVie() 				{return vie;}

	public int getVie_max() 		{return vie_max;}

	public void setVie(int vie) {this.vie = vie;}

	public int getJOUEUR() 			{return JOUEUR;}

	protected void recevoirDegats(int nbDegats)
	{
	    this.vie -= nbDegats;
	    if (this.vie <= 0)  this.vie = 0;
	}

	protected void initialisationGuerrier() {
		vie_max = vie = 40;
		inventaire = new Inventaire(6);
		type = "guerrier";
		System.out.println("Je suis un " + type);
	}

	protected void initialisationVoleur() {
		vie_max = vie = 35;
		type = "voleur";
		inventaire = new Inventaire(8);
		System.out.println("Je suis un " + type);
	}

	protected void initialisationMagicien() {
		vie_max = vie = 30;
		type = "magicien";
		inventaire = new Inventaire(2);
		System.out.println("Je suis un " + type);
	}

	protected void initialisationPrêtre() {
		vie_max = vie = 30;
		type = "prêtre";
		inventaire = new Inventaire(4);
		System.out.println("Je suis un " + type);
	}

	protected abstract void deplacement(int x, int y);

}
