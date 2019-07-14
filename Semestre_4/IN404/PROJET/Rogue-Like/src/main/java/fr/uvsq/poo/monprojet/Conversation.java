package fr.uvsq.poo.monprojet;

public interface Conversation {
	
	void envoyerMessage(Personnage p, String msg);
	void recevoirMessage(String msg);

}
