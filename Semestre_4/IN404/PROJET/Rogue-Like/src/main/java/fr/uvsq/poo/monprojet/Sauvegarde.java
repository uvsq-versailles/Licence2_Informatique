package fr.uvsq.poo.monprojet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Sauvegarde {

	private static File file;

	public Sauvegarde() throws IOException {
		file = new File("./fichier.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
	}

	public File getFile() {
		return file;
	}

	public void Personnage() {
		try (ObjectOutputStream print = new ObjectOutputStream(new FileOutputStream(file))) {
			print.writeObject(Terrain.getPj());
			print.writeObject(Terrain.getPosXYMonstre());
			print.writeObject(Terrain.getPosXYArme());
			print.writeObject(Terrain.getPosXYEquipement());
			print.writeObject(Terrain.getTab());
			print.writeObject(Terrain.getPnj());
			print.writeObject(Terrain.getCompteurPorte());
			print.writeObject(Terrain.getChanceMonstre());
			print.writeObject(Terrain.isEstDansTerrain());
			print.writeObject(keyListener.getNiveau());


			System.out.println("Sauvegarde réussie");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Sauvegarde échouée");
		}


	}
	public void recupPersonnage() throws ClassNotFoundException {
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

}
