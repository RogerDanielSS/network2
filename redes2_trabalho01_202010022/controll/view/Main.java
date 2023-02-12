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
  private ImageView router6;

  @FXML
  private ImageView router7;

  @FXML
  private Button start, teste;

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
      case "router6":
        coordinates.put("x", "" + (router6.getLayoutX() + router6.getFitWidth() / 2));
        coordinates.put("y", "" + (router6.getLayoutY() + router6.getFitHeight() / 2));
        break;
      case "router7":
        coordinates.put("x", "" + (router7.getLayoutX() + router7.getFitWidth() / 2));
        coordinates.put("y", "" + (router7.getLayoutY() + router7.getFitHeight() / 2));
        break;
      default:
        coordinates.put("x", "" + (host1.getLayoutX() + host1.getFitWidth() / 2));
        coordinates.put("y", "" + (host1.getLayoutY() + host1.getFitHeight() / 2));
    }

    return coordinates;
  }

  private ImageView getSpot(String spot) {
    Map<String, String> coordinates = new HashMap<>();
    coordinates.put("name", spot);

    switch (spot) {
      case "host1":
        return host1;
      case "host2":
        return host2;
      case "router1":
        return router1;
      case "router2":
        return router2;
      case "router3":
        return router3;
      case "router4":
        return router4;
      case "router5":
        return router5;
      case "router6":
        return router6;
      case "router7":
        return router7;
      default:
        return host1;
    }
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

  private void setPackageOriginAndDestination(AnchorPane packagePane, String origin, String destination) {
    packagePane.setLayoutX(getSpot(origin).getLayoutX() + getSpot(origin).getFitWidth() / 2);
    packagePane.setLayoutY(getSpot(origin).getLayoutY() + getSpot(origin).getFitHeight() / 2);

    packagesControllers.get(packagesControllers.size() - 1).setOriginAndDestination(getCoordinates(origin), getCoordinates(destination));
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    start.setOnAction(event -> {
      AnchorPane packagePane = createPackage();

      setPackageOriginAndDestination(packagePane, "router3", "router2");

      addPackage(packagePane);

      PackageThread PT = new PackageThread();
      PT.start();
    });
    
    teste.setOnAction(event -> {
      AnchorPane packagePane = createPackage();

      setPackageOriginAndDestination(packagePane, "router1", "router7");
      
      addPackage(packagePane);
    });
  }

  private class PackageThread extends Thread {

    // ArrayList AnchorPane

    private void send(int packageIndex) {
      System.out.println("\n" + packageIndex + "\n");

      if (!packagesControllers.get(packageIndex).isArrived((int) packages.get(packageIndex).getLayoutX(),
          (int) packages.get(packageIndex).getLayoutY())) {

        packages.get(packageIndex)
            .setLayoutX(packages.get(packageIndex).getLayoutX() + packagesControllers.get(packageIndex).getSumX());
        packages.get(packageIndex)
            .setLayoutY(packages.get(packageIndex).getLayoutY() + packagesControllers.get(packageIndex).getSumY());
      }
    }

    public void run() {
      while (true) {

        for (int packageIndex = 0; packageIndex < packages.size(); packageIndex++) {
          send(packageIndex);
        }

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