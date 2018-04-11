package vue;

import java.io.IOException;
import java.util.Scanner;
import modele.ChiffrementCesar;

/**
 * Cours :       Sécurité technique
 * Description : Programme permettant de chiffrer et de déchiffrer
 *               des messages ou des fichiers selon le chiffrement de César
 * Auteurs :     Francis Hêche & Normand Paratte
 * Date    :     Avril 2018
 */

public class Menu {
  public static void menu() throws IOException {
    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.println("Que voulez-vous faire ?");
      System.out.println("1. Chiffrer une phrase");
      System.out.println("2. Déchiffrer une phrase");
      System.out.println("3. Chiffrer un fichier");
      System.out.println("4. Déchiffrer un fichier");
      System.out.println("0. Sortir de l'application");
      System.out.print("\nVotre choix : ");
      String str = sc.nextLine();
      selection(str);
    }
  }

  public static void selection(String num) throws IOException {
    Scanner sc = new Scanner(System.in);
    String param1 = "";
    String param2 = "";
    Boolean param3 = false;
    String saisie = "";
    String resultat = "";

    switch (num.charAt(0)) {
      case '0':
        System.exit(0);
      case '1':
        System.out.println("\nVeuillez entrer la phrase à chiffrer :");
        param1 = sc.next();

        System.out.println("\nVeuillez indiquer la clé de chiffrage :");
        param2 = sc.next();

        System.out.println("\nVoulez-vous limiter le chiffrement à l'alphabet ? (O = Oui, N = Non)");
        saisie=sc.next();
        if(saisie.equals("O")||saisie.equals("o")){
          param3=true;
        } else
        {
          param3=false;
        };

        resultat = ChiffrementCesar.chiffrementPhrase(param1, Integer.valueOf(param2), param3);

        System.out.println("Voici la phrase chiffrée :\n");
        System.out.println(resultat + "\n");
        break;
      case '2':
        System.out.println("\nVeuillez entrer la phrase à déchiffrer :");
        param1 = sc.next();

        System.out.println("\nVeuillez indiquer la clé de déchiffrage :");
        param2 = sc.next();

        System.out.println("\nVoulez-vous limiter le chiffrement à l'alphabet ? (O = Oui, N = Non)");
        saisie=sc.next();
        if(saisie.equals("O")||saisie.equals("o")){
          param3=true;
        } else
        {
          param3=false;
        };

        resultat = ChiffrementCesar.chiffrementPhrase(param1, Integer.valueOf(param2), param3);

        System.out.println("Voici la phrase déchiffrée :\n");
        System.out.println(resultat + "\n");
        break;
      case '3':

        System.out.println("\nVeuillez indiquer le fichier à chiffrer :");
        param1 = sc.next();

        System.out.println("\nVeuillez indiquer la clé de chiffrage :");
        param2 = sc.next();

        ChiffrementCesar.chiffrementFichier(param1, Integer.valueOf(param2));
        System.out.println("Le fichier a été chiffré");
        break;
      case '4':
        System.out.println("\nVeuillez indiquer le fichier à déchiffrer :");
        param1 = sc.next();

        System.out.println("\nVeuillez indiquer la clé de déchiffrage :");
        param2 = sc.next();

        ChiffrementCesar.chiffrementFichier(param1, Integer.valueOf(param2));
        System.out.println("Le fichier a été déchiffré");
        break;
      default:
        System.out.println("\n\nUne erreur est survenue, veuillez recommencer !\n");
        menu();
        break;
    }
  }
}
