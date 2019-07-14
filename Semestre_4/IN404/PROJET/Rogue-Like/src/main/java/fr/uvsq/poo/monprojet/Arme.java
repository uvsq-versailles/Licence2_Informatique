package fr.uvsq.poo.monprojet;

import java.util.ArrayList;
import java.util.List;

public class Arme extends Inventaire {
	private String nom;
	private final static int ARME = 5;
	private int degats, durabilite;// prix;

	private int posXArme; // pour sauvegarger la position courrante d'une arme en X
	private int posYArme; // pour sauvegarder la position courrante dune arme en Y


	private static List<Arme> inventaire = new ArrayList<Arme>();

	public static List<Arme> getInventaire() {	return inventaire;}

	@Override
	public String Nom() 	 										{return this.nom;}

	public String getNom() 										{return nom;}

	public void setNom(String nom) 						{this.nom = nom;}

	public int getPosXArme() 									{return posXArme;}

	public int getPosYArme() 									{return posYArme;}

	public static int getARME() 							{return ARME;}

	public int getDurabilite() 								{return durabilite;}

	public void setDurabilite(int durabilite) {this.durabilite = durabilite;}

	public void enleveDurabilite() {
		this.durabilite -= 1;
		if (this.durabilite < 0) this.durabilite = 0;
	}

	public int getDegats() 										{return degats;}

	public void setDegats(int degats) 				{this.degats = degats;}

	public Arme(int choixArme, int x, int y) {
		this.posXArme = x;
		this.posYArme = y;
		if (choixArme == 1) {
			this.nom = "épée";
			initCaracteristquesArme(2, 16);
		}
		if (choixArme == 2) {
			this.nom = "hache";
			initCaracteristquesArme(8, 15);
		}
		if (choixArme == 3) {
			this.nom = "bâton";
			initCaracteristquesArme(2, 14);
		}
		if (choixArme == 4) {
			this.nom = "lance";
			initCaracteristquesArme(5, 19);
		}
		if (choixArme == 5) {
			this.nom = "dague";
			initCaracteristquesArme(7, 15);
		}
	}

	public Arme() {
		this.nom = "armeMonstre";
		posXArme = posYArme = 0;
		initCaracteristquesArme(Terrain.alea(1,10), 1000);
	}

	public Arme(int degats, int durabilite) {
		this.nom = "Aucune Arme";
		posXArme = posYArme = 0;
		initCaracteristquesArme(degats, durabilite);
	}

	public void initCaracteristquesArme(int degats, int durabilite) {
		this.degats = degats;
		this.durabilite = durabilite;
	}

	public void supprimerArmeInventaire(Arme arme) {
		if (getInventaire().size() != 0) {
			for (int i = 0; i < getInventaire().size(); i++) {
				if (getInventaire().get(i) == arme)	getInventaire().remove(i);
			}
		}
	}

	public String afficheArme() {
		String s = "";
		for (int i = 0; i < getInventaire().size(); i++) {
			s += "i " + i + "Nom --> " + getInventaire().get(i).getNom()
					+ " dégats --> "
					+ getInventaire().get(i).getDegats() + " durabilité --> "
					+ getInventaire().get(i).getDurabilite()
					+ "\n";
		}
		return s;
	}

	@Override
	public String toString() {
		return "Nom --> " + nom + "    dégats --> "
				+ degats + "    durabilité --> " + durabilite;
	}
}
