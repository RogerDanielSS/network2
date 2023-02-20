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
  Map<AnchorPane, Package> packagesControllers = new HashMap<AnchorPane, Package>();
  Spot host1S, host2S, router1S, router2S, router3S, router4S, router5S, router6S, router7S;
  ArrayList<Package> bufferH1 = new ArrayList<>();
  ArrayList<Package> bufferH2 = new ArrayList<>();
  ArrayList<Package> bufferR1 = new ArrayList<>();
  ArrayList<Package> bufferR2 = new ArrayList<>();
  ArrayList<Package> bufferR3 = new ArrayList<>();
  ArrayList<Package> bufferR4 = new ArrayList<>();
  ArrayList<Package> bufferR5 = new ArrayList<>();
  ArrayList<Package> bufferR6 = new ArrayList<>();
  ArrayList<Package> bufferR7 = new ArrayList<>();

  private void instatiateSpots() {
    host1S = new Spot("host1");
    host2S = new Spot("host2");
    router1S = new Spot("router1");
    router2S = new Spot("router2");
    router3S = new Spot("router3");
    router4S = new Spot("router4");
    router5S = new Spot("router5");
    router6S = new Spot("router6");
    router7S = new Spot("router7");

    ArrayList<String> neightboorsH1 = new ArrayList<>();
    ArrayList<String> neightboorsR1 = new ArrayList<>();
    // ArrayList<String> neightboorsR2 = new ArrayList<>();

    neightboorsH1.add("router1");
    host1S.setRoutingTable(inundationTable("host1", neightboorsH1));

    neightboorsR1.add("host1");
    neightboorsR1.add("router2");
    neightboorsR1.add("router7");
    neightboorsR1.add("router6");
    router1S.setRoutingTable(inundationTable("router1", neightboorsR1));

    // neightboors.remove("router1");
    // neightboors.add("router5");
    // neightboors.add("host2");
    // router1S.setRoutingTable(inundationTable("router4", neightboors));

    // neightboors.remove("router3");
    // neightboors.remove("host2");
    // neightboors.add("router1");
    // router6S.setRoutingTable(inundationTable("router6", neightboors));

    // neightboors.remove("router1");
    // neightboors.add("router6");
    // neightboors.add("host1");
    // router1S.setRoutingTable(inundationTable("router1", neightboors));

    // neightboors.remove("router6");
    // neightboors.remove("host1");
    // neightboors.add("router4");
    // router3S.setRoutingTable(inundationTable("router3", neightboors));

    // neightboors.remove("router2");
    // neightboors.add("router6");
    // router5S.setRoutingTable(inundationTable("router5", neightboors));

    // neightboors.remove("router7");
    // neightboors.add("router5");
    // neightboors.add("router1");
    // neightboors.add("router2");
    // neightboors.add("router3");
    // router7S.setRoutingTable(inundationTable("router7", neightboors));

    // neightboors.remove("router1");
    // neightboors.remove("router2");
    // neightboors.remove("router3");
    // neightboors.remove("router5");
    // neightboors.remove("router6");
    // host2S.setRoutingTable(inundationTable("host2", neightboors));

    host1S.start();
    // host2S.start();
    router1S.start();
    // router2S.start();
    // router3S.start();
    // router4S.start();
    // router5S.start();
    // router6S.start();
    // router7S.start();
  }

  public ArrayList<String> getAllSpotsExcept(String spot) {
    ArrayList<String> spots = new ArrayList<>();

    spots.add("host1");
    spots.add("host2");
    spots.add("router1");
    spots.add("router2");
    spots.add("router3");
    spots.add("router4");
    spots.add("router5");
    spots.add("router6");
    spots.add("router7");

    spots.remove(spot);

    return spots;
  }

  private Map<String, ArrayList<String>> inundationTable(String currentSpot, ArrayList<String> neightboors) {
    Map<String, ArrayList<String>> inundationTable = new HashMap<String, ArrayList<String>>();

    ArrayList<String> spots = getAllSpotsExcept(currentSpot);

    for (int spotIndex = 0; spotIndex < spots.size(); spotIndex++)
      inundationTable.put(spots.get(spotIndex), neightboors);

    return inundationTable;
  }

  private void addPackage(AnchorPane packagePane) {
    System.out.println("addPackage");
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

  private Spot getSpotThread(String spot) {
    Map<String, String> coordinates = new HashMap<>();
    coordinates.put("name", spot);

    switch (spot) {
      case "host1":
        return host1S;
      case "host2":
        return host2S;
      case "router1":
        return router1S;
      case "router2":
        return router2S;
      case "router3":
        return router3S;
      case "router4":
        return router4S;
      case "router5":
        return router5S;
      case "router6":
        return router6S;
      case "router7":
        return router7S;
      default:
        return host1S;
    }
  }

  private AnchorPane createPackage() {
    AnchorPane packagePane = new AnchorPane();

    try {
      URL component_url = new File("redes2_trabalho01_202010022/view/components/package.fxml").toURI().toURL();
      FXMLLoader fxmlLoader = new FXMLLoader();

      packagePane = fxmlLoader.load(component_url.openStream());

      packagesControllers.put(packagePane, (Package) fxmlLoader.getController());
    } catch (IOException e) {
      e.printStackTrace();
    }

    return packagePane;
  }

  private synchronized AnchorPane createPackage(Package packag) {
    AnchorPane packagePane = new AnchorPane();

    try {
      URL component_url = new File("redes2_trabalho01_202010022/view/components/package.fxml").toURI().toURL();
      FXMLLoader fxmlLoader = new FXMLLoader();

      packagePane = fxmlLoader.load(component_url.openStream());

      Package packageController = (Package) fxmlLoader.getController();

      packageController.clone(packag);

      setPackagePosition(packagePane, packageController);

      packagesControllers.put(packagePane, packageController);

    } catch (IOException e) {
      e.printStackTrace();
    }

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

    packagesControllers.get(packagePane).setOriginAndDestination(getCoordinates(origin),
        getCoordinates(destination));
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    start.setOnAction(event -> {
      instatiateSpots();

      host1S.newPackage("host1", "router2");

      // AnchorPane packagePane = createPackage();

      // setPackageOriginAndDestination(packagePane, "host1", "host2");

      // packagesControllers.get(packagePane).addSpot(getCoordinates("router1"));

      // addPackage(packagePane);

      PackageManager PT = new PackageManager();
      PT.start();
      PT.setPriority(10);
    });

    teste.setOnAction(event -> {
      System.out.println(packages.size());
    });
  }

  private class PackageManager extends Thread {

    // ArrayList AnchorPane

    private int send(int packageIndex) {

      Package packageController = packagesControllers.get(packages.get(packageIndex));

      if (!packageController.isArrived((int) packages.get(packageIndex).getLayoutX(),
          (int) packages.get(packageIndex).getLayoutY())) {
        packages.get(packageIndex)
            .setLayoutX(packages.get(packageIndex).getLayoutX() + packageController.getSumX());
        packages.get(packageIndex)
            .setLayoutY(packages.get(packageIndex).getLayoutY() + packageController.getSumY());
      } else {
        Spot spot = getSpotThread(packageController.getLastSpotInPath().get("name"));
        spot.addBuffer(packageController);

        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            // removePackage(packages.get(packageIndex));
          }
        });
        return packageIndex ;

      }
      return packageIndex;
    }

    public void run() {
      while (true) {

        for (int packageIndex = 0; packageIndex < packages.size(); packageIndex++) {
           packageIndex = send(packageIndex);
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

  private class Spot extends Thread {
    private String currentSpot;
    private Map<String, ArrayList<String>> routingTable;

    public Spot(String spot) {
      this.currentSpot = spot;
    }

    public void newPackage(String origin, String destination) {
      AnchorPane packagePane = createPackage();
      setPackageOriginAndDestination(packagePane, origin, destination);
      Map<String, String> spot = getCoordinates(routingTable.get(destination).get(0));
      packagesControllers.get(packagePane).addSpot(spot);
      addPackage(packagePane);
    }

    public void newPackage(Package packag) {
      AnchorPane packagePane = createPackage(packag);
      Map<String, String> spot = getCoordinates(routingTable.get(packag.getDestination().get("name")).get(0));
      packagesControllers.get(packagePane).addSpot(spot);

      Platform.runLater(new Runnable() {
        @Override
        public void run() {
          addPackage(packagePane);
        }
      });
    }

    public void addBuffer(Package packag) {
      if (currentSpot == "router1")
        bufferR1.add(packag);
    }

    public void sendFirstPackageInBuffer() {
      Package packag = new Package();
      if (currentSpot == "router1")
        packag = bufferR1.remove(0);

      ArrayList<String> spotsForDestination = this.routingTable.get(packag.getDestination().get("name"));

      for (int spotIndex = 0; spotIndex < spotsForDestination.size(); spotIndex++) {
        newPackage(packag);
      }

    }

    private ArrayList<Package> buffer() {
      if (currentSpot == "router1")
        return bufferR1;
      return bufferH1;
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
      while (true) {
        if (this.buffer() != null)
          if (this.buffer().size() > 0) {
            sendFirstPackageInBuffer();
          }
      }
    }
  }
}