package fr.uvsq.poo.monprojet;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventaire<T extends Inventaire> implements Serializable{
	private int tailleInventaire;
	private ArrayList<Inventaire> listeInventaire;
	private static boolean ajout;

	public int getTailleInventaire() {return tailleInventaire;}

	public String Nom() {return "";}


	public void afficheInventaire() {
		for (Inventaire inv : listeInventaire) {
			System.out.println(inv.toString());
		}
	}

	public ArrayList<Inventaire> getListeInventaire() {
		return listeInventaire;
	}

	public void setListeInventaire(ArrayList<Inventaire> listeInventaire) {
		this.listeInventaire = listeInventaire;
	}

	public Inventaire() {}

	public Inventaire(int tailleInventaire) {
		this.tailleInventaire = tailleInventaire;
		listeInventaire = new ArrayList<Inventaire>();
	}

	public boolean ajoutObjetInventaire(T objet) {
		if (objet != null) {
			ajout = true;

			if (listeInventaire.size() < tailleInventaire) {
				for (Inventaire inv : listeInventaire) {
					if (inv.Nom() == objet.Nom())
						ajout = false;
				}
				if (ajout == true) {
					listeInventaire.add(objet);
					return true;
				}

				else {
					System.out.println("Vous possédez déjà cette arme dans votre inventaire --> " + objet.Nom());
					return false;
				}
			}
			else {
				System.out.println("Vous avez trop d'objets dans votre inventaire");
				return false;
			}
		}
		return false;

	}

	public void supprimerArmeInventaire(Personnage pj) {
		for (Inventaire a : listeInventaire) {
			if (a.equals(pj.armePersonnage)) {
				pj.armePersonnage = new Arme(0, 10000);
				listeInventaire.remove(a);
			}
		}

	}

	// ############################################################
	// ------------------DEBUT CHOIX ARME -----------------------
	// ############################################################
	public void choisirElement() {
		for (Inventaire inv : listeInventaire) {
			if (keyListener.armes == true) {
				if (inv instanceof Arme) {
					Terrain.getPj().setArmePersonnage((Arme) inv);
					break;
				}
			}
			else if (keyListener.equipements == true) {
				if (inv instanceof Equipement) {
					Terrain.getPj().ajoutVie(((Equipement) inv).getPotionVie());
					listeInventaire.remove(inv);
					break;
				}
			}
		}

	}

	// ############################################################
	// ------------------FIN CHOIX ARME -----------------------
	// ############################################################

}
