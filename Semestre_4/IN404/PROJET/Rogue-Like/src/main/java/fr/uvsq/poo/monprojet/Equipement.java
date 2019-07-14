package fr.uvsq.poo.monprojet;

import java.util.ArrayList;
import java.util.List;

public class Equipement extends Inventaire {

	private String nomPotion;
	private int regen;
	public static ArrayList<String> nomEquipement;
	private static List<Equipement> inventaire = new ArrayList<Equipement>();
	private int posXEquipement;
	private int posYEquipement;
	private final static int EQUIPEMENT = 7;

	@Override
	public String Nom() 											 {return this.nomPotion;}

	public String getNomPotion() 							 {return nomPotion;}

	public void setNomPotion(String nomPotion) {this.nomPotion = nomPotion;}

	public int getPotionVie() 								 {return regen;}

	public void setPotionVie(int potionVie) {this.regen = potionVie;}

	public static List<Equipement> getInventaire() {return inventaire;}

	public static void setInventaire(List<Equipement> inventaire) {
		Equipement.inventaire = inventaire;
	}

	public static int getEQUIPEMENT() {return EQUIPEMENT;}

	public int getPosXEquipement() {return posXEquipement;}

	public void setPosXEquipement(int posXEquipement) {
		this.posXEquipement = posXEquipement;
	}

	public int getPosYEquipement() {return posYEquipement;}

	public void setPosYEquipement(int posYEquipement) {
		this.posYEquipement = posYEquipement;
	}

	public Equipement(int choixPotion, int x, int y) {

		this.posXEquipement = x;
		this.posYEquipement = y;
		if (choixPotion == 1) {
			nomPotion = "Petite potion de vie";
			regen = 4;
		}
		if (choixPotion == 2)
			nomPotion = "Grande potion de vie";
		regen = 8;

	}

	public void supprimerArmeInventaire(Equipement equipement) {
		if (getInventaire().size() != 0) {
			for (int i = 0; i < getInventaire().size(); i++) {
				if (getInventaire().get(i) == equipement)
					getInventaire().remove(i);
			}
		}
	}

	public String afficheEquipement() {
		String s = "";
		for (int i = 0; i < getInventaire().size(); i++) {
			s += "i = " + i + Arme.getInventaire().size() + "Nom --> " + getInventaire().get(i).getNomPotion();

		}
		return s;
	}

	@Override
	public String toString() {
		return "Nom --> " + nomPotion + " régen --> " + regen + "\n";

	}



}
