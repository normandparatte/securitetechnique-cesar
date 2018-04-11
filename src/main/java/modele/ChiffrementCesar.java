package modele;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Cours :       Sécurité technique
 * Description : Programme permettant de chiffrer et de déchiffrer
 *               des messages ou des fichiers selon le chiffrement de César
 * Auteurs :     Francis Hêche & Normand Paratte
 * Date    :     Avril 2018
 */


public class ChiffrementCesar {

  public static String chiffrementPhrase(String phraseADechiffrer, int decalage,
      boolean uniquementAlphabet) {
    StringBuilder phraseDeRetour = new StringBuilder("");
    int decalageReel=0;
    int decalagePossible = 0;
    int codeAsciiMin = 0;
    int codeAsciiMax = 0;
    // Explose la phrase dans un tableau
    String[] caracteres = phraseADechiffrer.split("");


    // Parcours le tableau
    for (int i = 0; i < caracteres.length; ++i) {
      //Ne change pas les espaces
      if(caracteres[i].length()>0) {
        if (caracteres[i].charAt(0) != ' ') {
          if (uniquementAlphabet) {
            // ----- Décalage circulaire -----
            // Modulo de 26 pour enlever les tours de l'alphabet inutiles
            decalageReel = decalage % 26;

            if (caracteres[i].charAt(0) >= 65 && caracteres[i].charAt(0) <= 90) {
              // ----- Si c'est une majuscule -----
              codeAsciiMin = 65;
              codeAsciiMax = 90;
            } else if (caracteres[i].charAt(0) >= 97 && caracteres[i].charAt(0) <= 122) {
              codeAsciiMin = 97;
              codeAsciiMax = 122;
            }

            // Calcul le décalage possible
            decalagePossible = codeAsciiMax - caracteres[i].charAt(0);

            if (decalageReel > decalagePossible) {
              // Si on ne peut pas décaler sans sortir de l'alphabet
              // Recalcule depuis "a"
              decalageReel -= decalagePossible;
              // Enlève encore 1 car on effectue déjà un décalage en se replacant sur le a
              decalageReel -= 1;
              phraseDeRetour.append(String.valueOf((char) (codeAsciiMin + decalageReel)));
            } else {
              // Sinon applique le décalage
              phraseDeRetour.append(String.valueOf((char) (caracteres[i].charAt(0) + decalageReel)));
            }
          } else {
            // Decale selon le décalage choisi
            phraseDeRetour.append(String.valueOf((char) (caracteres[i].charAt(0) + decalage)));
          }
        } else {
          phraseDeRetour.append(" ");
        }
      }else{
        phraseDeRetour.append("");
      }
    }

    return phraseDeRetour.toString();
  }

  public static String chiffrementPhrase(String phraseADechiffrer, int decalage) {
    return chiffrementPhrase(phraseADechiffrer, decalage, true);
  }

  public static String chiffrementPhrase(String phraseADechiffrer) throws IOException {
    StringBuilder phraseDeRetour = new StringBuilder("");

    // Explose la phrase dans un tableau
    String[] caracteres = phraseADechiffrer.split("");

    String[] motsDico = chargerDictionnaire();

    for(int i=0;i<motsDico.length;++i){
      System.out.println(motsDico[i]);
    }
    //ici normand enlever quand chargerDictionnaire est prêt
    System.exit(0);
    // Boucle sur toutes les possibilités (26 lettres de l'alphabet)
    for (int i=0;i<26;++i){
      //Efface la phrase de retour
      phraseDeRetour.delete(0,phraseDeRetour.length());

      phraseDeRetour.append(chiffrementPhrase(phraseADechiffrer));

      // Récupère tous les mots dans un tableau
      String[] mots = phraseADechiffrer.split(" ");

      for(i=0;i<mots.length;++i){
        //ici normand comparer mots[i] avec le dico et arreter si correspondance
      }
    }

    return phraseDeRetour.toString();
  }

  public static String[] chargerDictionnaire() throws IOException {
    String line = null;
    InputStream ips = Toolkit.getDefaultToolkit().getClass().getClassLoader().getResourceAsStream("resources\fr.txt");
    InputStreamReader ipsr = new InputStreamReader(ips);
    BufferedReader br = new BufferedReader(ipsr);
    StringBuilder motsDico = new StringBuilder("");

    // Lecture du fichier ligne par ligne
    while ((line = br.readLine()) != null) {
      motsDico.append(line+";");
    }
    br.close();

    return motsDico.toString().split(";");
  }

  public static void chiffrementFichier(String cheminFichier, int decalage)
      throws IOException {
    String line = null;

    String[] lignesFichiers = new String[10000];
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