package fr.uvsq.poo.monprojet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 *	Classe principale qui lance le jeu
 *
 * @author Shadé Aleo Afolabi, Damien De Montis, Isaac Szulek, Patrick Trestka
 * @version 11/05/2018
 */
public enum Application {
	APPLICATION;

	private static final Logger logger = LogManager.getLogger(Application.class);

	/**
	 * Cette méthode est destinée à initialiser et lancer l'exécution du programme.
	 *
	 * @param args les paramètres de la ligne de commande du shell
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unused")
	public void run(String[] args) throws IOException, ClassNotFoundException {
		logger.trace("Début du programme");
		Scanner sc = new Scanner(System.in);
		int choixPersonnage;
		int sauvegarde = 0;
		Sauvegarde save = new Sauvegarde();

		if(save.getFile().length() > 0) {
			do {
				System.out.println("Pour reprendre votre ancienne partie tappez 1 sinon 0");
				sauvegarde = sc.nextInt();
			}while (sauvegarde < 0 || sauvegarde > 1);
			sc.nextLine();
			System.out.println(sauvegarde);
		}
		if(sauvegarde == 0) {
			do {
				System.out.println("Voulez-vous être :");
				System.out.println("1) un guerrier");
				System.out.println("2) un voleur");
				System.out.println("3) un magicien");
				System.out.println("4) un prêtre");
				choixPersonnage = sc.nextInt();
			} while (choixPersonnage < 1 || choixPersonnage > 4);
			Frame frame = new Frame(choixPersonnage);
		}
		else if (sauvegarde == 1) {
			Frame frame = new Frame(2); 
		//	save.recupPersonnage();
			File file = new File("./fichier.txt");
			try (ObjectInputStream print = new ObjectInputStream(new FileInputStream(file))) {
				PJ pj=(PJ)print.readObject();
				ArrayList<Monstre> M=(ArrayList<Monstre>)print.readObject();
				ArrayList<Arme> A=(ArrayList<Arme>)print.readObject();
				ArrayList<Equipement> E=(ArrayList<Equipement>)print.readObject();
				int [][] tab = (int [][])print.readObject();
				PNJ pnj=(PNJ)print.readObject();
				int cpt  = (int)print.readObject();
				int chanceM  = (int)print.readObject();
				boolean estT = (boolean)print.readObject();
				int level = (int)print.readObject();
				
				
				
				Terrain.setPj(pj);
				Terrain.setPosXYMonstre(M);
				Terrain.setPosXYArme(A);
				Terrain.setPosXYEquipement(E);
				Terrain.setTab(tab);
				Terrain.setPnj(pnj);
				Terrain.setCompteurPorte(cpt);
				Terrain.setChanceMonstre(chanceM);
				Terrain.setEstDansTerrain(estT);
				keyListener.setNiveau(level);
				
				
				System.out.println("Récupération de la parite précédemment enregistrée.");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			sc.close();
			logger.trace("Fin du programme");
		}

	/**
	 * La méthode de classe <em>main</em> se contente de déléguer le lancement du
	 * programme à la méthode <em>run</em>.
	 *
	 * @param args les paramètres de la ligne de commande du shell
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		System.out.println("Début main");
		APPLICATION.run(args);
		System.out.println("Fin main");
	}
}
