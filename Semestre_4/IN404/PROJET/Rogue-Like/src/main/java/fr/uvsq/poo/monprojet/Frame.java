package fr.uvsq.poo.monprojet;

import javax.swing.JFrame;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	static Terrain t ;
	static int x = 50;
	static int y = 50;
	static keyListener kListener ;
	private static int choixClasse;

	public static int getChoixClasse() {return choixClasse;}

	public Frame(int i){
		choixClasse = i;
		t = new Terrain(choixClasse);
		kListener = new keyListener();
		this.setTitle("Projet IN404");
		this.setVisible(true);
		this.setSize(1100, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setContentPane(t);
    this.addKeyListener(kListener);
    Trame();
	}

	public Frame(){
		kListener = new keyListener();
		this.setTitle("Projet IN404");
		this.setVisible(true);
		this.setSize(1100, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setContentPane(t);
    this.addKeyListener(kListener);
    Trame();
	}

	public static void Trame() {
  	while(true) {
  		try{
  			Thread.sleep(12);
  		}
  		catch(InterruptedException e) {
  			e.printStackTrace();
  		}
  		if(kListener.left) x--;
  		if(kListener.right) x++;
  		if(kListener.up) y--;
  		if(kListener.down)y++;
  		t.repaint();
  	}
  }

}
