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

  int packageId = 0;
  Map<Integer, AnchorPane> packages = new HashMap<Integer, AnchorPane>();
  Map<Integer, Package> packagesControllers = new HashMap<Integer, Package>();
  Spot host1S, host2S, router1S, router2S, router3S, router4S, router5S, router6S, router7S;
  ArrayList<Integer> bufferH1 = new ArrayList<>();
  ArrayList<Integer> bufferR1 = new ArrayList<>();
  ArrayList<Integer> bufferR2 = new ArrayList<>();
  ArrayList<Integer> bufferH2 = new ArrayList<>();
  ArrayList<Integer> bufferR3 = new ArrayList<>();
  ArrayList<Integer> bufferR4 = new ArrayList<>();
  ArrayList<Integer> bufferR5 = new ArrayList<>();
  ArrayList<Integer> bufferR6 = new ArrayList<>();
  ArrayList<Integer> bufferR7 = new ArrayList<>();

  public synchronized int addPackageId() {
    return packageId++;
  }

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
    ArrayList<String> neightboorsH2 = new ArrayList<>();
    ArrayList<String> neightboorsR1 = new ArrayList<>();
    ArrayList<String> neightboorsR2 = new ArrayList<>();
    ArrayList<String> neightboorsR3 = new ArrayList<>();
    ArrayList<String> neightboorsR4 = new ArrayList<>();
    ArrayList<String> neightboorsR5 = new ArrayList<>();
    ArrayList<String> neightboorsR6 = new ArrayList<>();
    ArrayList<String> neightboorsR7 = new ArrayList<>();
    // ArrayList<String> neightboorsR2 = new ArrayList<>();

    neightboorsH1.add("router1");
    host1S.setRoutingTable(inundationTable("host1", neightboorsH1));

    neightboorsH2.add("router4");
    host2S.setRoutingTable(inundationTable("host2", neightboorsH2));

    neightboorsR1.add("host1");
    neightboorsR1.add("router2");
    neightboorsR1.add("router7");
    neightboorsR1.add("router6");
    router1S.setRoutingTable(inundationTable("router1", neightboorsR1));

    neightboorsR2.add("router1");
    neightboorsR2.add("router7");
    neightboorsR2.add("router3");
    router2S.setRoutingTable(inundationTable("router2", neightboorsR2));

    neightboorsR3.add("router2");
    neightboorsR3.add("router7");
    neightboorsR3.add("router4");
    router3S.setRoutingTable(inundationTable("router3", neightboorsR3));

    neightboorsR4.add("host2");
    neightboorsR4.add("router3");
    neightboorsR4.add("router7");
    neightboorsR4.add("router5");
    router4S.setRoutingTable(inundationTable("router4", neightboorsR4));

    neightboorsR5.add("router6");
    neightboorsR5.add("router7");
    neightboorsR5.add("router4");
    router5S.setRoutingTable(inundationTable("router5", neightboorsR5));

    neightboorsR6.add("router1");
    neightboorsR6.add("router7");
    neightboorsR6.add("router5");
    router6S.setRoutingTable(inundationTable("router6", neightboorsR6));

    neightboorsR7.add("router1");
    neightboorsR7.add("router2");
    neightboorsR7.add("router3");
    neightboorsR7.add("router4");
    neightboorsR7.add("router5");
    neightboorsR7.add("router6");
    router7S.setRoutingTable(inundationTable("router7", neightboorsR7));

    host1S.start();
    host2S.start();
    router1S.start();
    router2S.start();
    router3S.start();
    router4S.start();
    router5S.start();
    router6S.start();
    router7S.start();
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

  private void addPackage(int id) {
    background.getChildren().add(packages.get(id));
  }

  private void addPackage(AnchorPane packagePane, int id) {
    background.getChildren().add(packagePane);
    packages.put(id, packagePane);
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

  private int createPackage() {
    AnchorPane packagePane = new AnchorPane();
    int id = addPackageId();

    try {
      URL component_url = new File("redes2_trabalho01_202010022/view/components/package.fxml").toURI().toURL();
      FXMLLoader fxmlLoader = new FXMLLoader();

      packagePane = fxmlLoader.load(component_url.openStream());

      packages.put(id, packagePane);
      packagesControllers.put(id, (Package) fxmlLoader.getController());
    } catch (IOException e) {
      e.printStackTrace();
    }

    return id;
  }

  private int createPackage(Package packag, ArrayList<Map<String, String>> path) {
    AnchorPane packagePane = new AnchorPane();
    int id = addPackageId();

    try {
      URL component_url = new File("redes2_trabalho01_202010022/view/components/package.fxml").toURI().toURL();
      FXMLLoader fxmlLoader = new FXMLLoader();

      packagePane = fxmlLoader.load(component_url.openStream());

      Package packageController = (Package) fxmlLoader.getController();

      packageController.setOriginAndDestination(packag.getOrigin(), packag.getDestination());
      packageController.setPath(path);

      setPackagePosition(packagePane, packageController);

      packages.put(id, packagePane);
      packagesControllers.put(id, packageController);

    } catch (IOException e) {
      e.printStackTrace();
    }

    return id;
  }

  private void setPackagePosition(AnchorPane packagePane, Package packageController) {
    ArrayList<Map<String, String>> path = packageController.getPath();

    ImageView subOrigin = getSpot(path.get(path.size() - 1).get("name"));

    packagePane.setLayoutX(subOrigin.getLayoutX() + subOrigin.getFitWidth() / 2);
    packagePane.setLayoutY(subOrigin.getLayoutY() + subOrigin.getFitHeight() / 2);
  }

  private void setPackageOriginAndDestination(int id, String origin, String destination) {
    packages.get(id).setLayoutX(getSpot(origin).getLayoutX() + getSpot(origin).getFitWidth() / 2);
    packages.get(id).setLayoutY(getSpot(origin).getLayoutY() + getSpot(origin).getFitHeight() / 2);

    packagesControllers.get(id).setOriginAndDestination(getCoordinates(origin),
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

    private int send(int id) {
      
      if (!packagesControllers.get(id).isArrived((int) packages.get(id).getLayoutX(),
          (int) packages.get(id).getLayoutY())) {
        packages.get(id)
            .setLayoutX(packages.get(id).getLayoutX() + packagesControllers.get(id).getSumX());
        packages.get(id)
            .setLayoutY(packages.get(id).getLayoutY() + packagesControllers.get(id).getSumY());
      } else {
        Spot spot = getSpotThread(packagesControllers.get(id).getLastSpotInPath().get("name"));
        spot.addBuffer(id);

        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            removePackage(packages.get(id));
          }
        });
        return id;

      }
      return id;
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
      int id = createPackage();

      setPackageOriginAndDestination(id, origin, destination);
      Map<String, String> spot = getCoordinates(routingTable.get(destination).get(0));
      packagesControllers.get(id).addSpot(spot);
      // System.out.println(packagesControllers.get(id).getPath());
      addPackage(id);
    }

    public void newPackage(Package packag, String subdestination, ArrayList<Map<String, String>> path) {
      int id = createPackage(packag, path);

      packagesControllers.get(id).addSpot(getCoordinates(subdestination));

      System.out.println(id);
      System.out.println(packagesControllers.get(id).getPath() + "\n\n\n");

      Platform.runLater(new Runnable() {
        @Override
        public void run() {
          addPackage(id);
        }
      });
    }

    public void addBuffer(int id) {
        bufferR1.add(id);
    }

    public void sendFirstPackageInBuffer() {
      int id = bufferR1.remove(0);

      ArrayList<String> spotsForDestination = this.routingTable.get(packagesControllers.get(id).getDestination().get("name"));

      ArrayList<Map<String, String>> path = new ArrayList<>();

      path.addAll(packagesControllers.get(id).getPath());

      for (int spotIndex = 0; spotIndex < spotsForDestination.size(); spotIndex++) {
        path.addAll(packagesControllers.get(id).getPath());
        newPackage(packagesControllers.get(id), spotsForDestination.get(spotIndex), path);
      }

    }

    private ArrayList<Integer> buffer() {
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