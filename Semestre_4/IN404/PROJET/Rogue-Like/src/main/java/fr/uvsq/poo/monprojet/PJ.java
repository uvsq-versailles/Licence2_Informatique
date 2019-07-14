package fr.uvsq.poo.monprojet;

@SuppressWarnings("unused")
class PJ extends Personnage implements Interaction, Conversation {

	private static final long serialVersionUID = 1L;
	private int posXJoueur; // pour sauvegarger la position courrante du joueur en X
	private int posYJoueur; // pour sauvegarder la position courrante du joueur en Y
	private final static int JOUEUR = 3;
	private boolean estMort = false;
	private int experience = 0;
	private int monnaie = 0;
	private static int choixClasseJoueur;

	public PJ(int i, int x, int y) {
		super(i);
		choixClasseJoueur = i;
		posXJoueur = x;
		posYJoueur = y;
	}

	public static int getJoueur() 						{return JOUEUR;}

	public int getExperience() 								{return experience;}

	public void setExperience(int experience) {this.experience = experience;}

	public int getMonnaie() 									{return monnaie;}

	public void setMonnaie(int monnaie) 			{this.monnaie += monnaie;}

	public void setPosXJoueur(int posXJoueur) {this.posXJoueur = posXJoueur;}

	public void setPosYJoueur(int posYJoueur) {this.posYJoueur = posYJoueur;}

	public int getPosXJoueur() 								{return posXJoueur;}

	public int getPosYJoueur() 								{return posYJoueur;}

	public int getChoixClasseJoueur() 				{return choixClasseJoueur;}

	public void ajoutExperience(int val) 			{this.experience += val;}

	public void ajoutVie(int pv) {
		if ((this.vie += pv) > this.vie_max)
			this.vie = this.vie_max;
	}

	@Override
	public boolean mort() 						{return estMort;}

	@Override
	public void setMort(boolean rep) 	{estMort = rep;}

	@Override
	public void utiliserArme(Personnage p) {
		armePersonnage.enleveDurabilite();
		if (p.getVie() > 0) {
			p.recevoirDegats(armePersonnage.getDegats());
			if (p.getVie() == 0){
				if (p instanceof Monstre) {
					experience += 10;
					((Monstre) p).setMort(true);
					setMonnaie(1);
					if (experience % 100 == 0) {
						vie += 5;
					}
				}
			}
		}
		if (armePersonnage.getDurabilite() == 0) {
			inventaire.supprimerArmeInventaire(this);
		}
	}

	public void utiliserPotion(Equipement p) {
		if (vie + p.getPotionVie() <= vie_max)
			vie = vie + p.getPotionVie();
		else
			vie = vie_max;
	}

	@Override
	public void envoyerMessage(Personnage p, String msg) {
		String rep = type + "  : " + msg + "";
		((PNJ) p).recevoirMessage(rep);
	}

	@Override
	public void recevoirMessage(String msg) {
		System.out.println(msg);
	}

	@Override
	protected void deplacement(int x, int y) {
		if (keyListener.deplacements == true) {
			Terrain.changeValTab(posXJoueur, posYJoueur, Terrain.getSol());
			this.posXJoueur = x;
			this.posYJoueur = y;
			Terrain.changeValTab(posXJoueur, posYJoueur, JOUEUR);
		}
	}

}
