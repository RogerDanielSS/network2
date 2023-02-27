
/* *************************************************************** 
* Autor............: Roger Daniel Santana Simoes
* Matricula........: 202010022
* Inicio...........: 06/02/2023
* Ultima alteracao.: 19/02/2023
* Nome.............: Inundação
* Funcao...........: Simula o roteamento dentro de uma subrede usando inundação
*************************************************************** */
import java.io.File;
import java.net.URL;

import controll.view.Main;
import controll.view.components.Package;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application { // Inicio da classe Principal
  /*
   * ***************************************************************
   * Metodo: main
   * Funcao: metodo padrao que executa o programa
   * Parametros: Vetor de Strings chamado args
   * Retorno: vazio
   */
  public static void main(String[] args) throws Exception { // inicio do metodo main
    launch(args);

    Main main = new Main();
    Package packag = new Package();

  }// fim do metodo main

  /*
   * ***************************************************************
   * Metodo: start
   * Funcao: metodo de Aplication sobrescrito que carrega o palco
   * Parametros: Um Stage, do javaFX
   * Retorno: vazio
   */
  @Override
  public void start(Stage stage) throws Exception { // inicio do metodo start
    URL url = new File("view/main.fxml").toURI().toURL();
    Parent root = FXMLLoader.load(url);
    // Parent root =
    // FXMLLoader.load(getClass().getClassLoader().getResource("/view/main.fxml"));
    Scene Scene = new Scene(root);
    stage.setScene(Scene);
    stage.setResizable(false);
    stage.show();
  } // fim do metodo start
} // Fim da classe Principal