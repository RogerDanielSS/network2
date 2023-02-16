package redes2_trabalho01_202010022.controll.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.application.Platform;
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
  private ImageView host1, host2, router1, router2, router3, router4, router5, router6, router7;

  @FXML
  private Button start, teste;

  ArrayList<AnchorPane> packages = new ArrayList<>();
  ArrayList<Package> packagesControllers = new ArrayList<>();
  Spot host1S, host2S, router1S, router2S, router3S, router4S, router5S, router6S, router7S;

  private void instatiateSpots(){
    host1S = new Spot("host1");
    host2S = new Spot("host2");
    router1S = new Spot("router1");
    router2S = new Spot("router2");
    router3S = new Spot("router3");
    router4S = new Spot("router4");
    router5S = new Spot("router5");
    router6S = new Spot("router6");
    router7S = new Spot("router7");
  }

  private void addPackage(AnchorPane packagePane) {
    background.getChildren().add(packagePane);
    packages.add(packagePane);
  }

  private void removePackage(AnchorPane packagePane) {
    background.getChildren().remove(packagePane);
    packages.remove(packagePane);
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

  private synchronized AnchorPane clonePackage(Package packag, String subdestination) {
    AnchorPane packagePane = new AnchorPane();

    try {
      URL component_url = new File("redes2_trabalho01_202010022/view/components/package.fxml").toURI().toURL();
      FXMLLoader fxmlLoader = new FXMLLoader();

      packagePane = fxmlLoader.load(component_url.openStream());

      Package packageController = (Package) fxmlLoader.getController();

      packageController.clone(packag);

      setPackagePosition(packagePane, packageController);

      packageController.addSpot(getCoordinates(subdestination));

      packagesControllers.add(packageController);

    } catch (IOException e) {
      e.printStackTrace();
    }

    addPackage(packagePane);

    return packagePane;
  }

  private void setPackagePosition(AnchorPane packagePane, Package packageController) {
    ArrayList<Map<String, String>> path = packageController.getPath();

    ImageView subOrigin = getSpot(path.get(path.size() - 1).get("name"));

    packagePane.setLayoutX(subOrigin.getLayoutX() + subOrigin.getFitWidth() / 2);
    packagePane.setLayoutY(subOrigin.getLayoutY() + subOrigin.getFitHeight() / 2);
  }

  private void setPackageOriginAndDestination(AnchorPane packagePane, String origin, String destination) {
    packagePane.setLayoutX(getSpot(origin).getLayoutX() + getSpot(origin).getFitWidth() / 2);
    packagePane.setLayoutY(getSpot(origin).getLayoutY() + getSpot(origin).getFitHeight() / 2);

    packagesControllers.get(packagesControllers.size() - 1).setOriginAndDestination(getCoordinates(origin),
        getCoordinates(destination));
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    start.setOnAction(event -> {
      AnchorPane packagePane = createPackage();

      setPackageOriginAndDestination(packagePane, "router3", "router2");

      addPackage(packagePane);

      PackageManager PT = new PackageManager();
      PT.start();
      PT.setPriority(10);
    });

    teste.setOnAction(event -> {
      AnchorPane packagePane = createPackage();

      setPackageOriginAndDestination(packagePane, "router1", "router7");

      removePackage(packages.get(0));
    });
  }

  private class PackageManager extends Thread {

    // ArrayList AnchorPane

    private void send(int packageIndex) {
      System.out.println("\n" + packageIndex + "\n");

      Package packageController = packagesControllers.get(packageIndex);

      if (!packageController.isArrived((int) packages.get(packageIndex).getLayoutX(),
          (int) packages.get(packageIndex).getLayoutY())) {

        packages.get(packageIndex)
            .setLayoutX(packages.get(packageIndex).getLayoutX() + packageController.getSumX());
        packages.get(packageIndex)
            .setLayoutY(packages.get(packageIndex).getLayoutY() + packageController.getSumY());
      } else {
        getSpot(packageController.getLastSpotInPath().get("name")); // create new method that returns thread

        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            removePackage(packages.get(packageIndex));
          }
        });
        // packages.remove(packageIndex); // use to send package to router
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

  public class Spot extends Thread {
    private String currentSpot;
    Map<String, ArrayList<String>> routingTable;
    ArrayList<Package> buffer;

    public Spot(String spot){
      this.currentSpot = spot;
    } 

    public void sendFirstPackageInBuffer() {

      Package packag = buffer.remove(0);

      ArrayList<String> spotsForDestination = this.routingTable.get(packag.getDestination());

      for (int spotIndex = 0; spotIndex > spotsForDestination.size(); spotIndex++) {
        clonePackage(packag, spotsForDestination.get(spotIndex));
      }
    }

    public void setRoutingTable(Map<String, ArrayList<String>> routingTable) {
      this.routingTable = routingTable;
    }

    public void addToRoutingTable(String key, String value) {
      ArrayList<String> routes = this.routingTable.get(key);

      routes.add(value);

      this.routingTable.put(key, routes);
    }

    public void ReplaceRouteInRoutingTable(String key, String value) {
      ArrayList<String> routes = new ArrayList<String>();

      routes.add(value);

      this.routingTable.put(key, routes);
    }

    public void setCurrentSpot(String currentSpot) {
      this.currentSpot = currentSpot;
    }

    public String getCurrentSpot() {
      return this.currentSpot;
    }

    public void run() {

    }
  }
}