package redes2_trabalho01_202010022.controll.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import redes2_trabalho01_202010022.controll.view.components.Package;

public class Main implements Initializable {

  @FXML
  private AnchorPane background;

  @FXML
  private ImageView host1;

  @FXML
  private ImageView host2;

  @FXML
  private ImageView router5;

  @FXML
  private ImageView router4;

  @FXML
  private ImageView router3;

  @FXML
  private ImageView router2;

  @FXML
  private ImageView router1;

  @FXML
  private Button start;

  ArrayList<AnchorPane> packages = new ArrayList<>();
  ArrayList<Package> packagesControllers = new ArrayList<>();

  private void addPackage(AnchorPane packagePane) {
    background.getChildren().add(packagePane);
    packages.add(packagePane);
  }

  private Map<String, String> getCoordinates(String spot) {
    Map<String, String> coordinates = new HashMap<>();
    coordinates.put("name", spot);

    switch (spot) {
      case "host1":
        coordinates.put("x", "" + (host1.getLayoutX() + host1.getFitWidth() / 2));
        coordinates.put("y", "" + (host1.getLayoutY() + host1.getFitHeight() / 2));
        break;
      case "host2":
        coordinates.put("x", "" + (host2.getLayoutX() + host2.getFitWidth() / 2));
        coordinates.put("y", "" + (host2.getLayoutY() + host2.getFitHeight() / 2));
        break;
      case "router1":
        coordinates.put("x", "" + (router1.getLayoutX() + router1.getFitWidth() / 2));
        coordinates.put("y", "" + (router1.getLayoutY() + router1.getFitHeight() / 2));
        break;
      case "router2":
        coordinates.put("x", "" + (router2.getLayoutX() + router2.getFitWidth() / 2));
        coordinates.put("y", "" + (router2.getLayoutY() + router2.getFitHeight() / 2));
        break;
      case "router3":
        coordinates.put("x", "" + (router3.getLayoutX() + router3.getFitWidth() / 2));
        coordinates.put("y", "" + (router3.getLayoutY() + router3.getFitHeight() / 2));
        break;
      case "router4":
        coordinates.put("x", "" + (router4.getLayoutX() + router4.getFitWidth() / 2));
        coordinates.put("y", "" + (router4.getLayoutY() + router4.getFitHeight() / 2));
        break;
      case "router5":
        coordinates.put("x", "" + (router5.getLayoutX() + router5.getFitWidth() / 2));
        coordinates.put("y", "" + (router5.getLayoutY() + router5.getFitHeight() / 2));
        break;
      default:
        coordinates.put("x", "" + (host1.getLayoutX() + host1.getFitWidth() / 2));
        coordinates.put("y", "" + (host1.getLayoutY() + host1.getFitHeight() / 2));
    }

    return coordinates;
  }

  private AnchorPane createPackage() {
    AnchorPane packagePane = new AnchorPane();

    try {
      URL component_url = new File("redes2_trabalho01_202010022/view/components/package.fxml").toURI().toURL();
      FXMLLoader fxmlLoader = new FXMLLoader();

      packagePane = fxmlLoader.load(component_url.openStream());

      packagesControllers.add((Package) fxmlLoader.getController());

    } catch (IOException e) {
      e.printStackTrace();
    }

    return packagePane;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    start.setOnAction(event -> {
      AnchorPane packagePane = createPackage();
      addPackage(packagePane);

      packagePane.setLayoutX(host1.getLayoutX());
      packagePane.setLayoutY(host1.getLayoutY());

      packagesControllers.get(0).setOriginAndDestination(getCoordinates("host1"), getCoordinates("router1"));

      PackageThread PT = new PackageThread();
      PT.start();
    });
  }

  private class PackageThread extends Thread {

    // ArrayList AnchorPane

    private void send(String originSpot, String destinationSpot, AnchorPane packagePane) {
      Map<String, Integer> origin = getCoordinates(originSpot);
      Map<String, Integer> destination = getCoordinates(destinationSpot);

      double x = origin.get("x") - destination.get("x");
      double y = origin.get("y") - destination.get("y");

      x /= 200;
      y /= 200;

      while (packagePane.getLayoutX() != destination.get("x")) {
        packagePane.setLayoutX(x);
        packagePane.setLayoutY(y);

        try {
          sleep(1000);
        } catch (InterruptedException e) {
          System.out.println("error in sleep method");
          e.printStackTrace();
        }
      }
    }// send method ends here

    /*********************************************************************
     * Metodo: setVel
     * Funcao: Muda a velocidade dos carros
     * Parametros: Nova velocidade
     * Retorno: void
     *********************************************************************/
    // public void setVel(double vel) {
    // this.vel = vel;
    // }

    /*********************************************************************
     * Metodo: run
     * Funcao: Executa a rotina da thread
     * Parametros: void
     * Retorno: void
     *********************************************************************/
    public void run() {
      while (true) {
        if (!packagesControllers.get(0).isArrived((int) packages.get(0).getLayoutX(),
            (int) packages.get(0).getLayoutY())) {

          packages.get(0).setLayoutX(packages.get(0).getLayoutX() + packagesControllers.get(0).getSumX());
          packages.get(0).setLayoutY(packages.get(0).getLayoutY() + packagesControllers.get(0).getSumY());
          System.out.println(packages.get(0).getLayoutX());
          System.out.println(packages.get(0).getLayoutY());
          System.out.println(packagesControllers.get(0).getDestination());

        }
        System.out.println(packagesControllers.get(0).getSumX());

        try {
          sleep(100);
        } catch (InterruptedException e) {
          System.out.println("error in sleep method");
          e.printStackTrace();
        }
      }
    }// run method ends here
  }// car class ends here
}