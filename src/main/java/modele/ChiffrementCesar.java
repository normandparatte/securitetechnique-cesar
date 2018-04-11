package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Cours :       Sécurité technique
 * Description : Programme permettant de chiffrer et de déchiffrer
 *               des messages ou des fichiers selon le chiffrement de César
 * Auteurs :     Francis Hêche & Normand
 * Paratte Date    :     Avril 2018
 */

public class ChiffrementCesar {

  public static String chiffrementPhrase(String phraseADechiffrer, int decalage) {
    StringBuilder phraseDeRetour = new StringBuilder("");

    // Explose la phrase dans un tableau
    String[] caracteres = phraseADechiffrer.split("");

    // Parcours le tableau
    for (int i = 0; i < caracteres.length; ++i) {
      //Ne change pas les espaces
      if (caracteres[i].charAt(0) != ' ') {
        // Decale selon le décalage choisi
        phraseDeRetour.append(String.valueOf((char) (caracteres[i].charAt(0) + decalage)));
      } else {
        phraseDeRetour.append(" ");
      }
    }

    return phraseDeRetour.toString();
  }

  public static void chiffrementFichier(String cheminFichier, int decalage)
      throws IOException {
    String line = null;

    //OUVERTURE DU FICHIER

    String[] lignesFichiers = new String[1000];
    int i = 0;

    // Ouverture du fichier à chiffrer ou déchiffrer
    BufferedReader br = new BufferedReader(new FileReader(cheminFichier));

    // Lecture du fichier ligne par ligne
    while ((line = br.readLine()) != null) {
      lignesFichiers[i] = line;
      i++;
    }
    br.close();

    // Création du fichier de sortie
    PrintWriter writer = new PrintWriter(cheminFichier, "UTF-8");

    for (i = 0; lignesFichiers[i] != null; ++i) {
      // Ecriture du chiffrement dans le fichier de sortie
      writer.println(chiffrementPhrase(lignesFichiers[i], decalage));
    }
    writer.flush();
  }
}
