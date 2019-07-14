package fr.uvsq.poo.monprojet;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class keyListener implements KeyListener {
	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	public static boolean armes = false;
	public static boolean equipements = false;
	public static boolean deplacements = false;
	private static Sauvegarde s;
	private static ArrayList<Monstre> posXYMonstre;
	private static int ApparitionMonstre = 3;
	private static int Niveau = 1;

	public static int getApparitionMonstre() 	{return ApparitionMonstre;}

	public static int getNiveau() 						{return Niveau;}

	public static void setNiveau(int n) 			{Niveau = n;}

	@Override
	public void keyTyped(KeyEvent e) {}

		int x, y;

		/**
		* Permet de récuper les entrées clavier
		*
		* @param e c'est la touche du clavier qu'on a presser
		*/
	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public void keyPressed(KeyEvent e) {
		x = Terrain.getPj().getPosXJoueur();
		y = Terrain.getPj().getPosYJoueur();
			if (e.getKeyCode() == 38) {
			// Je regarde si l'élément suivant est un sol
			if (Terrain.valTab(this.x - 1, this.y) == Terrain.getSol()) {
				deplacements = true;
				Terrain.getPj().deplacement(this.x - 1, this.y);
			}

			// Je regarde si l'élément suivant est une Arme
			else if ((Terrain.valTab(this.x - 1, this.y) == Arme.getARME())
			|| Terrain.valTab(this.x - 1, this.y) == Equipement.getEQUIPEMENT()) {
				if (Terrain.getPj().inventaire.ajoutObjetInventaire(Terrain.selecArmeTerrain(x - 1, y))) {
					deplacements = true;
					Terrain.getPj().deplacement(this.x - 1, this.y);
					up = true;
				}
				else if (Terrain.getPj().inventaire.ajoutObjetInventaire(Terrain.selecEquipementTerrain(x - 1, y))) {
					deplacements = true;
					Terrain.getPj().deplacement(this.x - 1, this.y);
					up = true;
				}
			}

			// Je regarde si je tombe sur un monstre
			else if (Terrain.valTab(this.x - 1, this.y) == Monstre.getNumeroM()) {
				posXYMonstre = Terrain.getPosXYMonstre();
				for (int i = 0; i < posXYMonstre.size(); i++) {
					if (posXYMonstre.get(i).getMonstreX() == (x - 1)) {
						if (posXYMonstre.get(i).getMonstreY() == y) {
							Terrain.getPj().utiliserArme(posXYMonstre.get(i));
							up = true;
						}
					}
				}
			}
			// Si je tombe sur un pnj gentil
			else if (Terrain.valTab(x - 1, y) == PNJ.getPNJ()) {
				PNJ pnj = new PNJ(Terrain.getPosXPnj(), Terrain.getPosYPnj());
				pnj.envoyerMessage(Terrain.getPj(), pnj.parler(Terrain.getPj()));
				up = true;
			}

			// je regarde si je passe au niveau supérieur
			else if (Terrain.valTab(this.x - 1, this.y) == Terrain.getPorte()) {
				ApparitionMonstre++;
				Niveau++;
				if (Terrain.estDansTerrain == true) {	Terrain t = new Terrain();}
				if (Terrain.estDansTerrain == false) {
					if (posXYMonstre.size() != 0)
					System.out.println("Vous devez tuez tous les monstres pour sortir");
					else {Terrain t = new Terrain();}
				}
				up = true;
			}

			// Donjon
			else if (Terrain.valTab(this.x - 1, this.y) == Terrain.getDonjon()) {
				// limite d'apparition de monstre dans le donjon à 15
				if (ApparitionMonstre < 15) {	ApparitionMonstre++;}
				if (Terrain.getPj().getMonnaie() >= 1 + getNiveau()) {
					Terrain t = new Terrain(12, 30);
				}
				else if (Terrain.getPj().getMonnaie() < 1 + getNiveau()) {
					System.out.println("Vous n'avez pas assez de pièces");
				}
				up = true;
			}
		}

		if (e.getKeyCode() == 40) {
			// Je regarde si l'élément suivant est un sol
			if (Terrain.valTab(this.x + 1, this.y) == Terrain.getSol()) {
				deplacements = true;
				Terrain.getPj().deplacement(this.x + 1, this.y);
				down = true;
			}

			// Je regarde si l'élément suivant est un Objet
			else if (Terrain.valTab(this.x + 1, this.y) == Arme.getARME()
			|| Terrain.valTab(this.x + 1, this.y) == Equipement.getEQUIPEMENT()) {
				if (Terrain.getPj().inventaire.ajoutObjetInventaire(Terrain.selecArmeTerrain(x + 1, y))) {
					deplacements = true;
					Terrain.getPj().deplacement(this.x + 1, this.y);
					down = true;
				}
				else if (Terrain.getPj().inventaire.ajoutObjetInventaire(Terrain.selecEquipementTerrain(x + 1, y))) {
					deplacements = true;
					Terrain.getPj().deplacement(this.x + 1, this.y);
					down = true;
				}
			}

			// Je regarde si je tmbe sur un monstre
			else if (Terrain.valTab(x + 1, y) == Monstre.getNumeroM()) {
				posXYMonstre = Terrain.getPosXYMonstre();
				for (int i = 0; i < posXYMonstre.size(); i++) {
					if (posXYMonstre.get(i).getMonstreX() == (x + 1)) {
						if (posXYMonstre.get(i).getMonstreY() == y) {
							Terrain.getPj().utiliserArme(posXYMonstre.get(i));
							down = true;
						}
					}
				}
			}
			// Si je tombe sur un pnj gentil
			else if (Terrain.valTab(x + 1, y) == PNJ.getPNJ()) {
				PNJ pnj = new PNJ(Terrain.getPosXPnj(), Terrain.getPosYPnj());
				pnj.envoyerMessage(Terrain.getPj(), pnj.parler(Terrain.getPj()));
				down = true;
			}

			// Niveau suivant
			else if (Terrain.valTab(x + 1, y) == Terrain.getPorte()) {
				ApparitionMonstre++;
				Niveau++;
				if (Terrain.estDansTerrain == true) {Terrain t = new Terrain();}
				if (Terrain.estDansTerrain == false) {
					if (posXYMonstre.size() != 0)
					System.out.println("Vous devez tuez tous les monstres pour sortir");
					else {Terrain t = new Terrain();}
				}
				down = true;
			} // Donjon
			else if (Terrain.valTab(x + 1, y) == Terrain.getDonjon()) {
				// limite d'apparition de monstre dans le donjon à 15
				if (ApparitionMonstre < 15)
				ApparitionMonstre++;
				if (Terrain.getPj().getMonnaie() >= 1 + getNiveau()) {
					Terrain t = new Terrain(12, 30);
				}
				else if (Terrain.getPj().getMonnaie() < 1 + getNiveau()) {
					System.out.println("Vous n'avez pas assez de pièces");
				}
				down = true;
			}
		}

		if (e.getKeyCode() == 37) {
			// Je regarde si l'élément suivant est un sol
			if (Terrain.valTab(this.x, this.y - 1) == Terrain.getSol()) {
				deplacements = true;
				Terrain.getPj().deplacement(this.x, this.y - 1);
				left = true;
			}

			// Je regarde si l'élément suivant est un Objet
			else if (Terrain.valTab(this.x, this.y - 1) == Arme.getARME()
			|| Terrain.valTab(this.x, this.y - 1) == Equipement.getEQUIPEMENT()) {
				if (Terrain.getPj().inventaire.ajoutObjetInventaire(Terrain.selecArmeTerrain(x, y - 1))) {
					deplacements = true;
					Terrain.getPj().deplacement(this.x, this.y - 1);
					left = true;
				} else if (Terrain.getPj().inventaire.ajoutObjetInventaire(Terrain.selecEquipementTerrain(x, y - 1))) {
					deplacements = true;
					Terrain.getPj().deplacement(this.x, this.y - 1);
					left = true;
				}
			}
				// Je regarde si je tmbe sur un monstre
			else if (Terrain.valTab(x, y - 1) == Monstre.getNumeroM()) {
				posXYMonstre = Terrain.getPosXYMonstre();
				for (int i = 0; i < posXYMonstre.size(); i++) {
					if (posXYMonstre.get(i).getMonstreX() == (x)) {
						if (posXYMonstre.get(i).getMonstreY() == y - 1) {
							Terrain.getPj().utiliserArme(posXYMonstre.get(i));
							left = true;
						}
					}
				}
			}
				// Si je tombe sur un pnj gentil
			else if (Terrain.valTab(x, y - 1) == PNJ.getPNJ()) {
				PNJ pnj = new PNJ(Terrain.getPosXPnj(), Terrain.getPosYPnj());
				pnj.envoyerMessage(Terrain.getPj(), pnj.parler(Terrain.getPj()));
				left = true;
			}
				// Niveau suivant
			else if (Terrain.valTab(this.x, this.y - 1) == Terrain.getPorte()) {
				ApparitionMonstre++;
				Niveau++;
				if (Terrain.estDansTerrain == true) {
					Terrain t = new Terrain();
				}
				if (Terrain.estDansTerrain == false) {
					if (posXYMonstre.size() != 0)
					System.out.println("Vous devez tuez tous les monstres pour sortir");
					else {
						Terrain t = new Terrain();
					}
				}
				left = true;
			}
				// Donjon
			else if (Terrain.valTab(this.x, this.y - 1) == Terrain.getDonjon()) {
				// limite d'apparition de monstre dans le donjon à 15
				if (ApparitionMonstre < 15)
				ApparitionMonstre++;
				if (Terrain.getPj().getMonnaie() >= 1 + getNiveau()) {
					Terrain t = new Terrain(12, 30);
				} else if (Terrain.getPj().getMonnaie() < 1 + getNiveau()) {
					System.out.println("Vous n'avez pas assez de pièces");
				}
				left = true;
			}
		}

		if (e.getKeyCode() == 39) {
			// Je regarde si l'élément suivant est un sol
			if (Terrain.valTab(this.x, this.y + 1) == Terrain.getSol()) {
				deplacements = true;
				Terrain.getPj().deplacement(this.x, this.y + 1);
				right = true;
			}

				// Je regarde si l'élément suivant est un Objet
			else if (Terrain.valTab(this.x, this.y + 1) == Arme.getARME()
			|| Terrain.valTab(this.x, this.y + 1) == Equipement.getEQUIPEMENT()) {
				if (Terrain.getPj().inventaire.ajoutObjetInventaire(Terrain.selecArmeTerrain(x, y + 1))) {
					deplacements = true;
					Terrain.getPj().deplacement(this.x, this.y + 1);
					right = true;
				} else if (Terrain.getPj().inventaire.ajoutObjetInventaire(Terrain.selecEquipementTerrain(x, y + 1))) {
					deplacements = true;
					Terrain.getPj().deplacement(this.x, this.y + 1);
					right = true;
				}
			}
			// Je regarde si je tmbe sur un monstre
			else if (Terrain.valTab(x, y + 1) == Monstre.getNumeroM()) {
				posXYMonstre = Terrain.getPosXYMonstre();
				for (int i = 0; i < posXYMonstre.size(); i++) {
					if (posXYMonstre.get(i).getMonstreX() == x) {
						if (posXYMonstre.get(i).getMonstreY() == y + 1) {
							Terrain.getPj().utiliserArme(posXYMonstre.get(i));
							right = true;
						}
					}
				}
			}
				// Si je tombe sur un pnj gentil
			else if (Terrain.valTab(x, y + 1) == PNJ.getPNJ()) {
				PNJ pnj = new PNJ(Terrain.getPosXPnj(), Terrain.getPosYPnj());
					pnj.envoyerMessage(Terrain.getPj(), pnj.parler(Terrain.getPj()));
					right = true;
				}

				// Niveau suivant
			else if (Terrain.valTab(x, y + 1) == Terrain.getPorte()) {
				ApparitionMonstre = ApparitionMonstre + 1;
				Niveau++;
				if (Terrain.estDansTerrain == true) {
					Terrain t = new Terrain();
				}
				if (Terrain.estDansTerrain == false) {
					if (posXYMonstre.size() != 0)
					System.out.println("Vous devez tuez tous les monstres pour sortir");
					else {
						Terrain t = new Terrain();
					}
				}
				right = true;
			}
			// Donjon
			else if (Terrain.valTab(x, y + 1) == Terrain.getDonjon()) {
					// limite d'apparition de monstre dans le donjon à 15
					if (ApparitionMonstre < 15)
					ApparitionMonstre++;
					if (Terrain.getPj().getMonnaie() >= 1 + getNiveau()) {
						Terrain t = new Terrain(12, 30);
					} else if (Terrain.getPj().getMonnaie() < 1 + getNiveau()) {
						System.out.println("Vous n'avez pas assez de pièces");
					}
					right = true;
				}
			}

			else if (e.getKeyCode() == 65) {armes = true;}
		 	else if (e.getKeyCode() == 69) {equipements = true;}
			else if (e.getKeyCode() == 83) { // sauvegarde avec "s"
				try {
					s = new Sauvegarde();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				s.Personnage();
			}
			else if (e.getKeyCode() == 82) { // Récupération avec "r"
				try {
					s.recupPersonnage();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}

		}

	@Override
	public void keyReleased(KeyEvent e) {
		// Inventaire.afficheInventaire();
		Frame.t.deplacementMonstre();
		if (e.getKeyCode() == 38) {
			up = false;
			deplacements = false;
		}
		if (e.getKeyCode() == 40) {
			down = false;
			deplacements = false;
		}
		if (e.getKeyCode() == 37) {
			left = false;
			deplacements = false;
		}
		if (e.getKeyCode() == 39) {
			right = false;
			deplacements = false;
		}
		if (e.getKeyCode() == 65) {
			Terrain.getPj().inventaire.choisirElement();
			armes = false;
		}
			if (e.getKeyCode() == 69) {
			Terrain.getPj().inventaire.choisirElement();
			equipements = false;
		}
	}
}
