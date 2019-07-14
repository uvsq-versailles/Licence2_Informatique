package fr.uvsq.poo.monprojet;

@SuppressWarnings("unused")
class Monstre extends Personnage implements Interaction {
	private boolean estMort = false;
	private static final long serialVersionUID = 1L;
	private static final int MONSTRE = 6;
	private int posXMonstre; // pour sauvegarger la position courrante du Monstre en X
	private int posYMonstre; // pour sauvegarder la position courrante du Monstre en Y

	public Monstre(int posXMonstre, int posYMonstre, int vie) {
		super();
		this.posXMonstre = posXMonstre;
		this.posYMonstre = posYMonstre;
		this.vie = vie;
		Arme a = new Arme();
		armePersonnage = a;
	}

	public int getPosXMonstre() {return posXMonstre;}

	public int getPosYMonstre() {return posYMonstre;}

	public void setMonstre(int x, int y) {
		posXMonstre = x;
		posYMonstre = y;
	}

	public int getMonstreX() {return posXMonstre;}

	public int getMonstreY() {return posYMonstre;}


	@Override
	public void utiliserArme(Personnage p) {
		armePersonnage.enleveDurabilite();
		if (p.getVie() > 0) {
			p.recevoirDegats(armePersonnage.getDegats());
			if (p.getVie() == 0) {
				if (p instanceof PJ) {
					((PJ) p).setMort(true);
				}
			}
		}
		if (armePersonnage.getDurabilite() == 0) {
			inventaire.supprimerArmeInventaire(this);
		}
	}
	public static int getNumeroM() {return MONSTRE;}

	@Override
	public boolean mort() {return estMort;}

	@Override
	public void setMort(boolean rep) {
		estMort = rep;
		Terrain.supprimerMonstreTerrain();
	}

	@Override
	protected void deplacement(int hasard, int mvt) {
		switch (hasard) {
		case 0:
			if (Terrain.valTab(posXMonstre , posYMonstre + mvt) == Terrain.getSol()) {
				Terrain.changeValTab(posXMonstre, posYMonstre, Terrain.getSol());
				posYMonstre += mvt;
				Terrain.changeValTab(posXMonstre, posYMonstre, MONSTRE);
			}
			break;
		case 1:
			if (Terrain.valTab(posXMonstre, posYMonstre+mvt) == Terrain.getSol()) {
				Terrain.changeValTab(posXMonstre, posYMonstre, Terrain.getSol());
				posYMonstre += mvt;
				Terrain.changeValTab(posXMonstre, posYMonstre, MONSTRE);
			}
			break;
		case 2:
			if (Terrain.valTab(posXMonstre +mvt , posYMonstre) == Terrain.getSol()) {
				Terrain.changeValTab(posXMonstre, posYMonstre, Terrain.getSol());
				posXMonstre += mvt;
				Terrain.changeValTab(posXMonstre, posYMonstre, MONSTRE);
			}
			break;
		case 3:
			if (Terrain.valTab(posXMonstre + mvt, posYMonstre) == Terrain.getSol()) {
				Terrain.changeValTab(posXMonstre, posYMonstre, Terrain.getSol());
				posXMonstre += mvt;
				Terrain.changeValTab(posXMonstre, posYMonstre, MONSTRE);
			}
			break;
		default:
			break;
		}
		// si c'est notre pj
		if (Terrain.valTab(posXMonstre, posYMonstre + 1) == PJ.getJoueur()) {
			this.utiliserArme(Terrain.getPj());
		}
		if (Terrain.valTab(posXMonstre, posYMonstre - 1) == PJ.getJoueur()) {
			this.utiliserArme(Terrain.getPj());
		}
		if (Terrain.valTab(posXMonstre + 1, posYMonstre) == PJ.getJoueur()) {
			this.utiliserArme(Terrain.getPj());
		}
		if (Terrain.valTab(posXMonstre + mvt - 1, posYMonstre) == PJ.getJoueur()) {
			this.utiliserArme(Terrain.getPj());
		}
	}

}
