package redes2_trabalho01_202010022.controll.view.components;

import java.util.ArrayList;

public class Package {
  private String origin;
  private String destination;
  private ArrayList<String> path = new ArrayList<>();

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public void addSpot(String spot) {
    path.add(spot);
  }

  public ArrayList<String> getPath(){
    return path;
  }
}
