Projet rogue-like 

Shadé ALAOAFOLABI 
Isaac SZULEK
Patrick TRESTKA 
Damien DE MONTIS


Ce qui a été fait et pas fait

visualisation de l'univers en mode texte  ===> Réalisation d’une interface graphique
actions du joueur seront saisies au clavier ==> Fait, mouvement du joueur via flèches
interface spécifique pourra être proposée pour des situations particulières (création du personnage, sélection d'un sort, discussion, ...) ==>Réalisée lorsqu’on s’approche d’un pnj, lorsqu’on sélectionne une arme ou bien quand on veut traverser certaines portes etc.
L'état de la partie devra pouvoir être sauvegardée et rechargée à tout moment. ===>Fait. Mais impossible de recharger dès le début d’une partie une ancienne sauvegarde.

Procédure de compilation et d’exécution 
La compilation et l'exécution se fait comme demandé, c’est à dire ./gradlew build  puis l’exécution du script dans build/distribution.

Manuel utilisateur 

    Exécution du programme 
Une fois le script lancé, une fenêtre graphique s’ouvre avec le terrain délimité par des ‘#’ qui représente les murs. À droite du terrain il y a les informations sur le niveau dans lequel se trouve le personnage, le nombre de pièce à disposition, l’arme en cours d’utilisation avec ses nombres de dégâts et sa durabilité. Puis dans l’inventaire, les armes ainsi que les potions de vies. 

    Comment jouer ?
 
_ déplacement
Vous êtes le ‘@’, pour le déplacer utiliser les flèches directionnelles du clavier. En appuyant une fois et en relâchant vous allez vous déplacer d’une case. Si vous laissez une des flèches pressés, le personnage utilise son pouvoir qui est le déplacement à la vitesse de la lumière, les monstres ne bougent plus car le personnage va trop vite, ce pouvoir fonctionne uniquement en ligne droite. 

_ ramasser une arme, un équipement 
    Les armes sont représentées par ‘A’ , pour ramasser l’arme il suffit de marcher dessus. Pour sortir l’arme de l’inventaire et la mettre dans la main du personnage il faut appuyer sur la touche ‘A’ du clavier.
    L’équipement comme les potions de vies, sont représentés par ‘E’, pour ramasser l’équipement il faut marcher dessus, il sera automatiquement mis dans votre inventaire. 

_ les monstres
    Les monstres sont représentés par ‘M’, ils se déplacent aléatoirement à chacune de vos actions, sauf si vous utilisez votre pouvoir de déplacement à la vitesse de la lumière. Ils ont de base une arme, qui peut être de même type que la vôtre, mais avec une plus grande durabilité. Ils vous attaquent dès que vous êtes à une case à côté d’eux. 

_ utiliser une potion de vie ou une arme
    Pour utiliser l’arme, elle doit être dans votre main (voir section : ramasser une arme), puis il suffit de se déplacer sur le monstre que l’on veut attaquer. Lorsque la durabilité de votre arme arrive à 0, elle se casse et vous pouvez en ramasser une nouvelle ou prendre la première arme dans votre inventaire.
    Pour utiliser une potion, il faut appuyer sur la touche ‘E’  du clavier. Vous allez alors recevoir autant de points de vie qu’afficher par la potion (dans la limite du nombre maximal de points de vie). 

_ Donjon 
    L’entrée du donjon est représenté par ‘]’.
    Pour entrer dans un donjon il faut payer le nombre de pièce (qui est affiché par un message dans le terminal). Plus vous augmentez de niveau plus il faudra donner de pièces pour y entrer. 
    Pour sortir du donjon il faut vaincre tous les monstres présents. Le nombre de monstre est aléatoire entre 2 et 15, dans les premiers niveau du jeux la limite est plus faible supérieur. Puis le passage vers une nouveau terrain représenté par ‘[’ est débloqué alors vous pouvez continuer votre quête. 

_ Passer au niveau supérieur 
Utilisez le passage ‘[’, cela vous progresser dans les niveaux. (conseils des développeurs, vous pouvez passer de niveau en niveau si vous cherchez juste à avoir le plus grand niveau dans le score final mais vous allez avoir peu de pièce et ne pourrez plus rentrer si facilement dans un donjon).





Scénario de démonstration
Le joueur tape 0 dans le terminal car il n’a pas d’ancienne partie, il choisit ensuite un voleur en tapant 2 dans le terminal. 
La fenêtre avec le terrain et ses informations s’ouvre. Le joueur se déplace sur la carte et rencontre un monstre qui l’attaque et lui enlève 4 point de vie. Comme le joueur n’a pas d’armes, il est obligé de fuir et d’en chercher une. Il se dirige donc vers une arme qui s’ajoute dans son inventaire quand il passe dessus et la choisit en appuyant sur la touche A.Il s’agit d’une lance.
 Il retourne vers le monstre pour prendre sa revanche et l’élimine d’un coup. Il perd au passage encore 4 point de vie. 
Le joueur décide d’aller chercher un équipement (à savoir les potions de vie) pour se soigner et ramasse une grande potion de vie qu’il utilise avec la touche E.
 Cette potion lui rapporte 8 points de vie ce qui le ramène au max de ses points de vie à savoir 35 comme il est un voleur.
Il décide de continuer et passe à d’autres terrains en passant par une porte bleu. 
Avant de tuer d’autres monstres il décide de sauvegarder en appuyant sur la touche S.
Le joueur se rend ensuite compte qu’il a bien fait de sauvegarder car ses pv étaient trop bas. Il décide de revenir donc en arrière à sa sauvegarde en appuyant sur la touche R et de chercher une potion pour le combat à venir. 
Après avoir éliminé les monstres il décide d’aller parler à un pnj. Il appuie sur 2 après s’être approché du pnj et constate d’après ses paroles qu’il a assez de pièces pour rentrer dans un donjon à savoir la porte rouge. Il passe donc la porte rouge et rentre dans le donjon en payant le prix d’entrée et s’aperçoit qu’il y a de nombreux monstres. 
Après avoir combattu vaillamment et n’ayant pas sauvegarder ni pris de potion de vie, les point de vie du joueurs tombent à zéro.
L’écran de game over apparaît et s’affiche alors son score. 
Le joueur n’a qu’une hâte c’est de battre son score personnel en recommençant une nouvelle partie !


     Manuel technique
Classe abstraite Personnage
→ Sert à mettre les caractéristiques en commun pour tous les types de personnage : PJ, PNJ et Monstre. Tous les personnages peuvent donc se déplacer, avoir une arme etc. 
→ Nous avons également créé deux interfaces. La première concerne l’attaque entre deux Personnage et elle n’est implémentée que dans PJ et Monstre car le PNJ ne fait que faire la conversation avec notre PJ. C’est donc pour cela que nous avons créé une interface Conversation entre ces deux dernières classes.

Classe Inventaire
→ Cette classe est la classe mère des équipements et des armes. Elle a été créée pour stocker les objets ramassés par notre PJ sur le terrain.
→ Dans cette classe nous avons fait de la généricité pour qu’on puisse ajouter dans notre liste d’inventaire uniquement des objets héritant de la classe Inventaire. 
Classe Sauvegarde
→Celle-ci nous permet de sauvegarder le jeu à un instant T. 
Bien qu’écrire l’état de chaque objet dans un fichier de sauvegarde nous aurait aidé à mieux comprendre tout ce qui a été sauvegardé dans notre fichier. Nous avons opté pour la sérialisation des objets car selon nous c’est ce qui est plus adapté à la programmation orientée objet.


Classe Terrain
→Cette classe contient de nombreuses variables statiques pour éviter les conflits d’instances objets. Elle gère tous les éléments d’un terrain dans un tableau en les associant aux bonnes instances de classes.

Notre fenêtre graphique
→ Bien que cela n’ait pas été demandé dans le projet il nous a été jugé utile de trouver un moyen simple d’afficher en direct chaque déplacements du joueur. L’interface utilisée utilise Graphics g.

