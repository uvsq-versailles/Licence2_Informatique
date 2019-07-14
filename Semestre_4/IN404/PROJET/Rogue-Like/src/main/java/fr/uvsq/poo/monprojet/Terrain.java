package fr.uvsq.poo.monprojet;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("unused")
public class Terrain extends JPanel implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int SOL = 0;
	private static final int MUR = 1;
	private static final int PORTE = 2;
	private static final int DONJON = 10;
	public static int LARGEUR = 25;
	public static int HAUTEUR = 50;
	private static int tab[][] = new int[LARGEUR][HAUTEUR];
	public static int compteurPorte = 0;
	private static ArrayList<Arme> posXYArme = new ArrayList<Arme>();
	private static ArrayList<Equipement> posXYEquipement = new ArrayList<Equipement>();
	private static ArrayList<Monstre> posXYMonstre;
	private static int ChanceMonstre;
	private static PJ pj;
	private static PNJ pnj;
	private static int posXPnj; // pour sauvegarger la position courante du PNJ en X
	private static int posYPnj; // pour sauvegarder la position courante du PNJ en Y
	public static boolean estDansTerrain = true;


	// ---------SETER GETTER ------Terrain----------

	public static int getLARGEUR() 										{return LARGEUR;}
	public static int getHAUTEUR() 										{return HAUTEUR;}
	public static void setLARGEUR(int lARGEUR) 				{LARGEUR = lARGEUR;}
	public static void setHAUTEUR(int hAUTEUR) 				{HAUTEUR = hAUTEUR;}
	public static int getCompteurPorte() 							{return compteurPorte;}
	public static void setCompteurPorte(int cptPorte) {compteurPorte = cptPorte;}
	public static int getSol() 												{return SOL;}
	public static int getPorte() 											{return PORTE;}
	public static int getDonjon() 										{return DONJON;}
	public static int getMur() 												{return MUR;}
	public static boolean isEstDansTerrain() 					{return estDansTerrain;}
	public static void setEstDansTerrain(boolean estDansTerrain) {
		Terrain.estDansTerrain = estDansTerrain;
	}

	// ---------SETER GETTER ------tab----------
	public static int valTab(int x, int y) 							{return tab[x][y];}
	public static void changeValTab(int x, int y, int z){tab[x][y] = z;}
	public static int[][] getTab() 											{return tab;}
	public static void setTab(int[][] tab)							{Terrain.tab = tab;}

	// ---------SETER GETTER ------PJ----------
	public static PJ getPj() 											{return pj;}
	public static void setPj(PJ Pj) 							{pj = Pj;}
	public static ArrayList<Arme> getPosXYArme()	{return posXYArme;}
	public static ArrayList<Equipement> getPosXYEquipement() {
		return posXYEquipement;
	}
	public static void setPosXYArme(ArrayList<Arme> posXYArme) {
		Terrain.posXYArme = posXYArme;
	}
	public static void setPosXYEquipement(ArrayList<Equipement> posXYEquipement) {
		Terrain.posXYEquipement = posXYEquipement;
	}

	// ----------SETER GETER--------------Monstre---------
	public static ArrayList<Monstre> getPosXYMonstre() {return posXYMonstre;}
	public static int getChanceMonstre() 							 {return ChanceMonstre;}
	public static void setChanceMonstre(int chanceMonstre) {
		ChanceMonstre = chanceMonstre;
	}
	public static void setPosXYMonstre(ArrayList<Monstre> posXYMonstre) {
		Terrain.posXYMonstre = posXYMonstre;
	}

		// ----------SETER GETER--------------PNJ---------
		public static int getPosXPnj() 							{return posXPnj;}
		public static int getPosYPnj() 							{return posYPnj;}
		public static void setPosXPnj(int posXPnj) 	{Terrain.posXPnj = posXPnj;}
		public static void setPosYPnj(int posYPnj) 	{Terrain.posYPnj = posYPnj;}
		public static PNJ getPnj() 									{return pnj;}
		public static void setPnj(PNJ p) 						{pnj = p;}


	public void paintComponent(Graphics g) {
		int x = 20, y = 20, k = 0, l = 160;
		int level = keyListener.getNiveau();
		if (pj.mort()) {
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getSize().width, this.getSize().height);
			g.setColor(Color.black);
			g.drawString("GAME OVER ", 400, 200);
			g.drawString("Niveau atteint " + level, 400, 250);
			g.drawString("Expérience " + pj.getExperience(), 400, 275);
			g.drawString("Vous aviez " + pj.getMonnaie()+" pièces", 400, 300);
		}
		else {
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getSize().width, this.getSize().height);
			g.setColor(Color.black);
			g.drawString("Niveau " + level, 600, 30);
			g.drawString("Vous avez "+pj.getMonnaie()+" pièces",600, 45);
			g.drawString("Arme PJ " + pj.getArmePersonnage().toString(), 600, 70);
			g.drawString("Vie du PJ :" + pj.getVie(), 600, 90);
			g.drawString("Expérience PJ :" + pj.getExperience(), 600, 120);

			// J'affiche l'inventaire
			g.drawString("Inventaire PJ :", 600, 140);

			g.drawString("Informations :", 600, 400);
			g.drawString("A pour sélectionner la première arme de l'inventaire ", 600, 420);
			g.drawString("E pour sélectionner le premier équipement de l'inventaire ", 600, 440);
			g.drawString("S pour sauvegarder la partie en cours ", 600, 460);
			g.drawString("R pour pour reprendre à tout moment la dernière partie sauvegarder ", 600, 480);

			@SuppressWarnings({ "rawtypes", "unchecked" })
			ArrayList<Inventaire> inventaire = pj.inventaire.getListeInventaire();
			for (@SuppressWarnings("rawtypes") Inventaire inv : inventaire) {
				g.drawString("i : " + k + " " + inventaire.get(k++).toString(), 600, l += 15);
			}

			for (int i = 0; i < LARGEUR; i++) {
				for (int j = 0; j < HAUTEUR; j++) {
					if (tab[i][j] == SOL) {
						g.drawString(".", x += 10, y);
					} else if (tab[i][j] == MUR) {
						g.drawString("#", x += 10, y);
					} else if (tab[i][j] == PORTE) {
						g.setColor(Color.blue);
						g.drawString("[", x += 10, y);
						g.setColor(Color.black);
					} else if (tab[i][j] == PJ.getJoueur()) {
						g.setColor(Color.red);
						g.drawString("@", x += 10, y);
						g.setColor(Color.black);
					} else if (tab[i][j] == Arme.getARME()) {
						g.setColor(Color.pink);
						g.drawString("A", x += 10, y);
						g.setColor(Color.black);
					} else if (tab[i][j] == Monstre.getNumeroM()) {
						g.setColor(Color.MAGENTA);
						g.drawString("M", x += 10, y);
						g.setColor(Color.black);
					} else if (tab[i][j] == Equipement.getEQUIPEMENT()) {
						g.setColor(Color.GREEN);
						g.drawString("E", x += 10, y);
						g.setColor(Color.black);
					} else if (tab[i][j] == DONJON) {
						g.setColor(Color.red);
						g.drawString("]", x += 10, y);
						g.setColor(Color.black);
					} else if (tab[i][j] == PNJ.getPNJ()) {
						g.setColor(Color.MAGENTA);
						g.drawString("P", x += 10, y);
						g.setColor(Color.black);
					}
				}
				y += 20;
				x = 20;
			}

		}
	}

	public Terrain(int choixclassePJ) {
		super();
		posXYMonstre = new ArrayList<Monstre>();
		int hasard, hasardMonstre = alea(1, keyListener.getApparitionMonstre());
		for (int i = 0; i < LARGEUR; i++) {
			for (int j = 0; j < HAUTEUR; j++) {
				hasard = alea(1, 10);
				if (hasard <= 8)		 	initSol(i, j);
				if (i == 0)						initMur(i, j);
				if (i == LARGEUR - 1)	initMur(i, j);
				if (j == 0)						initMur(i, j);
				if (j == HAUTEUR - 1)	initMur(i, j);
				if (hasard > 8) 			initMur(i, j);
			}
		}

		for (int i = 0; i < hasardMonstre; i++) {
			initMonstre(alea(1, LARGEUR - 2), alea(1, HAUTEUR - 2));
		}

		initDonjon(alea(1, LARGEUR), alea(1, HAUTEUR));
		initPorte(alea(1, LARGEUR), alea(1, HAUTEUR));
		initArme(alea(1, LARGEUR - 2), alea(1, HAUTEUR - 2));
		initEquipement(alea(1, LARGEUR - 2), alea(1, HAUTEUR - 2));
		initPNJ(alea(1, LARGEUR - 2), alea(1, HAUTEUR - 2));
		int x = alea(1, LARGEUR - 2);
		int y = alea(1, HAUTEUR - 2);
		initPJ(x, y);
		pj = new PJ(choixclassePJ, x,y);

		// je parcours le tableau et à chaque fois que je rencontre 5 je créé une arme
		for (int i = 0; i < LARGEUR; i++) {
			for (int j = 0; j < HAUTEUR; j++) {
				if (tab[i][j] == Arme.getARME())
					posXYArme.add(new Arme((alea(1, 5)), i, j));
				if (tab[i][j] == Equipement.getEQUIPEMENT())
					posXYEquipement.add(new Equipement((alea(1, 2)), i, j));
				if (tab[i][j] == Monstre.getNumeroM()) {
					posXYMonstre.add(new Monstre(i, j, alea(1, 9)));
				}
			}
		}

	}

	public Terrain() {
		super();
		estDansTerrain = true;
		posXYMonstre = new ArrayList<Monstre>();
		int hasard, hasardMonstre = alea(1, keyListener.getApparitionMonstre());
		for (int i = 0; i < LARGEUR; i++) {
			for (int j = 0; j < HAUTEUR; j++) {
				hasard = alea(1, 10);
				if (hasard <= 8)			initSol(i, j);
				if (i == 0)						initMur(i, j);
				if (i == LARGEUR - 1)	initMur(i, j);
				if (j == 0)						initMur(i, j);
				if (j == HAUTEUR - 1)	initMur(i, j);
				if (hasard > 8) 			initMur(i, j);
			}
		}

		for (int i = 0; i < hasardMonstre; i++) {
			initMonstre(alea(1, LARGEUR - 2), alea(1, HAUTEUR - 2));
		}

		initDonjon(alea(1, LARGEUR-1), alea(1, HAUTEUR-1));
		initPorte(alea(1, LARGEUR - 2), alea(1, HAUTEUR - 2));
		initArme(alea(1, LARGEUR - 2), alea(1, HAUTEUR - 2));
		initEquipement(alea(1, LARGEUR - 2), alea(1, HAUTEUR - 2));
		initPNJ(alea(1, LARGEUR - 2), alea(1, HAUTEUR - 2));
		int x = alea(1, LARGEUR - 2);
		int y = alea(1, HAUTEUR - 2);
		initPJ(x, y);
		pj.setPosXJoueur(x);
		pj.setPosYJoueur(y);
		// je parcours le tableau et à chaque fois que je rencontre 5 je créé une arme

		for (int i = 0; i < LARGEUR; i++) {
			for (int j = 0; j < HAUTEUR; j++) {
				if (tab[i][j] == Arme.getARME())
					posXYArme.add(new Arme((alea(1, 5)), i, j));
				if (tab[i][j] == Equipement.getEQUIPEMENT())
					posXYEquipement.add(new Equipement((alea(1, 2)), i, j));
				if (tab[i][j] == Monstre.getNumeroM()) {
					posXYMonstre.add(new Monstre(i, j, alea(1, 9)));
				}
			}
		}

	}

	// -------------------------DEBUT DONJON
	// ------------------------------------------
	public Terrain(int lenght, int height) {
		super();
		estDansTerrain = false;
		int decalx = 6;
		int decaly = 3;
		lenght += decaly;
		height += decalx;
		pj.setMonnaie(-keyListener.getNiveau()-1);
		posXYMonstre = new ArrayList<Monstre>();
		int hasard, hasardMonstre = alea(2, keyListener.getApparitionMonstre() + 2);

		for (int i = 0; i < LARGEUR; i++)
			for (int j = 0; j < HAUTEUR; j++)
				initMur(i, j);

		for (int i = decaly; i < lenght; i++)
			for (int j = decalx; j < height; j++) {
				initSol(i, j);
				hasard = alea(1, 100);
				if (hasard > 95)
					initMur(i, j);
			}
		for (int i = 0; i < hasardMonstre; i++) {
			initMonstre(alea(decaly, lenght - decaly), alea(decalx, height - decalx));
		}

		int x = alea(decaly, lenght - decaly);
		int y = alea(decalx, height - decalx);
		initPorte(alea(decaly, lenght - decaly), alea(decalx, height - decalx));
		initPJ(x, y);
		pj.setPosXJoueur(x);
		pj.setPosYJoueur(y);
		initArme(alea(decaly, lenght - decaly), alea(decalx, height - decalx));
		initArme(alea(decaly, lenght - decaly), alea(decalx, height - decalx));
		initEquipement(alea(decaly, lenght - decaly), alea(decalx, height - decalx));


		// je parcours le tableau et à chaque fois que je rencontre 5 je créé une arme

		for (int i = 0; i < LARGEUR; i++) {
			for (int j = 0; j < HAUTEUR; j++) {
				if (tab[i][j] == Arme.getARME())
					posXYArme.add(new Arme((alea(1, 5)), i, j));
				if (tab[i][j] == Equipement.getEQUIPEMENT())
					posXYEquipement.add(new Equipement((alea(1, 2)), i, j));
				if (tab[i][j] == Monstre.getNumeroM()) {
					// Monstre m = new Monstre(i, j);
					posXYMonstre.add(new Monstre(i, j, alea(1, 15)));

				}
			}
		}
	}
	// ############################################################
	// --------------------------FIN DONJON -----------------------
	// ############################################################

	public static void supprimerArmeTerrain(int x, int y) {
		for (int i = 0; i < posXYArme.size(); i++) {
			if (posXYArme.get(i).getPosXArme() == x) {
				if (posXYArme.get(i).getPosYArme() == y) {
					posXYArme.remove(i);
				}
			}
		}

	}

	public static void supprimerMonstreTerrain() {
		for (int i = 0; i < posXYMonstre.size(); i++) {
			if (posXYMonstre.get(i).mort()) {
				tab[posXYMonstre.get(i).getMonstreX()][posXYMonstre.get(i).getMonstreY()] = getSol();
				posXYMonstre.remove(i);
			}

		}

	}

	public static Arme selecArmeTerrain(int x, int y) {
		for (int i = 0; i < posXYArme.size(); i++) {
			if (posXYArme.get(i).getPosXArme() == x)
				if (posXYArme.get(i).getPosYArme() == y)
					return posXYArme.get(i);
		}
		return null;

	}

	public static Equipement selecEquipementTerrain(int x, int y) {
		for (int i = 0; i < posXYEquipement.size(); i++) {
			if (posXYEquipement.get(i).getPosXEquipement() == x)
				if (posXYEquipement.get(i).getPosYEquipement() == y)
					return posXYEquipement.get(i);
		}
		return null;

	}

	public static Monstre selectMonstre(int x, int y) {
		for (int i = 0; i < posXYMonstre.size(); i++) {
			if (posXYMonstre.get(i).getMonstreX() == x)
				if (posXYMonstre.get(i).getMonstreY() == y)
					return posXYMonstre.get(i);
		}
		return null;
	}

	public static int alea(int min, int max) {
		Random r = new Random();
		return min + r.nextInt(max);
	}

	public void initEquipement(int x, int y) {
		tab[x][y] = Equipement.getEQUIPEMENT();
	}

	public void initArme(int x, int y) {
		tab[x][y] = Arme.getARME();
	}

	public void initSol(int x, int y) {
		tab[x][y] = SOL;
	}

	public void initMur(int x, int y) {
		tab[x][y] = MUR;
	}

	public void initPorte(int x, int y) {
		tab[x][y] = PORTE;
	}

	public void initPJ(int x, int y) {
		tab[x][y] = PJ.getJoueur();
	}

	public void initPNJ(int x, int y) {
		tab[x][y] = PNJ.getPNJ();
		pnj = new PNJ(x,y);
	}

	public void initMonstre(int x, int y) {
		tab[x][y] = Monstre.getNumeroM();
	}

	public void initDonjon(int x, int y) {
		tab[x][y] = DONJON;
	}


	protected void deplacementMonstre() {
		for (int i = 0; i < posXYMonstre.size(); i++) {
			posXYMonstre.get(i).deplacement(alea(0,4), alea(-1,3));
		}
	}

}
