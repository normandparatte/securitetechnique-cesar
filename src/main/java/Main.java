import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import vue.Menu;

/**
 * Cours :       Sécurité technique
 * Description : Programme permettant de chiffrer et de déchiffrer
 *               des messages ou des fichiers selon le chiffrement de César
 * Auteurs :     Francis Hêche & Normand Paratte
 * Date    :     Avril 2018
 */

public class Main {
  public static void main(String[] args)
      throws FileNotFoundException, UnsupportedEncodingException, IOException {
    Menu.menu();
  }
}