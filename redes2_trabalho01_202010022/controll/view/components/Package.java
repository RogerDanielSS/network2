package redes2_trabalho01_202010022.controll.view.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Package {
  private Map<String, String> origin;
  private Map<String, String> destination;
  private double sumX = 0;
  private double sumY = 0;
  private int life = 4;
  private ArrayList<Map<String, String>> path = new ArrayList<>();
  private boolean dead = false;
  private int networkId = 1;

  public Package() {

  }

  public int getNetworkId() {
    return this.networkId;
  }

  public void setNetworkId(int networkId) {
    this.networkId = networkId;
  }

  public void kill() {
    this.dead = true;
  }

  public boolean dead() {
    return this.dead;
  }

  public void setDead(boolean dead) {
    this.dead = dead;
  }

  public void descreaseLife() {
    life--;
  }

  public void setLife(int life) {
    this.life = life;
  }

  public int getLife() {
    return this.life;
  }

  public void setPath(ArrayList<Map<String, String>> path) {
    for (int pathIndex = 0; pathIndex < path.size(); pathIndex++) {
      ArrayList<String> keys = new ArrayList<>(path.get(pathIndex).keySet());
      Map<String, String> temp = new HashMap<String, String>();
      for (int hashIndex = 0; hashIndex < keys.size(); hashIndex++) {
        temp.put(keys.get(hashIndex), path.get(pathIndex).get(keys.get(hashIndex)));
      }

      this.path.add(temp);
    }
  }

  public void clone(Package packag) {
    this.origin = packag.getOrigin();
    this.destination = packag.getDestination();

    this.path = packag.getPath();
  }

  public Map<String, String> getOrigin() {
    return origin;
  }

  public void setOrigin(Map<String, String> origin) {
    this.origin = origin;
    path.add(origin);
  }

  public Map<String, String> getDestination() {
    return destination;
  }

  public void setDestination(Map<String, String> destination) {
    this.destination = destination;
  }

  public void addSpot(Map<String, String> spot) {
    path.add(spot);

    if (path.size() > 1) {
      int size = path.size();
      sumX = (Double.parseDouble(path.get(size - 1).get("x")) - Double.parseDouble(path.get(size - 2).get("x"))) / 100;
      sumY = (Double.parseDouble(path.get(size - 1).get("y")) - Double.parseDouble(path.get(size - 2).get("y"))) / 100;
    }
  }

  public ArrayList<Map<String, String>> getPath() {
    return path;
  }

  public Map<String, String> getLastSpotInPath() {
    return path.get(path.size() - 1);
  }

  public Map<String, String> getLastButOneSpotInPath() {
    return path.get(path.size() - 2);
  }

  public void setOriginAndDestination(Map<String, String> origin, Map<String, String> destination) {
    this.origin = origin;
    addSpot(origin);
    this.destination = destination;
    sumX = (Double.parseDouble(destination.get("x")) - Double.parseDouble(origin.get("x"))) / 100;
    sumY = (Double.parseDouble(destination.get("y")) - Double.parseDouble(origin.get("y"))) / 100;
  }

  public double getSumX() {
    return sumX;
  }

  public double getSumY() {
    return sumY;
  }

  public boolean isArrived(int x, int y) {
    if (path.get(path.size() - 1).get("name") == "host2") {
      System.out.println("destination");
      System.out.println((int) Double.parseDouble(path.get(path.size() - 1).get("x")));
      System.out.println((int) Double.parseDouble(path.get(path.size() - 1).get("y")));
      System.out.println("package");
      System.out.println("x: " + x + "y: " + y);
    }
    return (int) Double.parseDouble(destination.get("x")) == x &&
        (int) Double.parseDouble(destination.get("y")) == y
        || (int) Double.parseDouble(path.get(path.size() - 1).get("x")) == x &&
            (int) Double.parseDouble(path.get(path.size() - 1).get("y")) == y
        || (int) Double.parseDouble(destination.get("x")) == x - 1 &&
            (int) Double.parseDouble(destination.get("y")) == y
        || (int) Double.parseDouble(path.get(path.size() - 1).get("x")) == x - 1 &&
            (int) Double.parseDouble(path.get(path.size() - 1).get("y")) == y
        || (int) Double.parseDouble(destination.get("x")) == x &&
            (int) Double.parseDouble(destination.get("y")) == y - 1
        || (int) Double.parseDouble(path.get(path.size() - 1).get("x")) == x &&
            (int) Double.parseDouble(path.get(path.size() - 1).get("y")) == y - 1
        || (int) Double.parseDouble(destination.get("x")) == x + 1 &&
            (int) Double.parseDouble(destination.get("y")) == y
        || (int) Double.parseDouble(path.get(path.size() - 1).get("x")) == x + 1 &&
            (int) Double.parseDouble(path.get(path.size() - 1).get("y")) == y
        || (int) Double.parseDouble(destination.get("x")) == x &&
            (int) Double.parseDouble(destination.get("y")) == y + 1
        || (int) Double.parseDouble(path.get(path.size() - 1).get("x")) == x &&
            (int) Double.parseDouble(path.get(path.size() - 1).get("y")) == y + 1;
  }
}
