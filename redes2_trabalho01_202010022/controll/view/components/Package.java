package redes2_trabalho01_202010022.controll.view.components;

import java.util.ArrayList;
import java.util.Map;

public class Package {
  private Map<String, String> origin;
  private Map<String, String> destination;
  private double sumX = 0;
  private double sumY = 0;
  private ArrayList<Map<String, String>> path = new ArrayList<>();

  public Map<String, String> getOrigin() {
    return origin;
  }

  public void setOrigin(Map<String, String> origin) {
    this.origin = origin;
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
      sumX = (Double.parseDouble(path.get(size - 2).get("x")) - Double.parseDouble(path.get(size - 1).get("x"))) / 200;
      sumX = (Double.parseDouble(path.get(size - 2).get("y")) - Double.parseDouble(path.get(size - 1).get("y"))) / 200;
    } else {
      sumX = (Double.parseDouble(path.get(0).get("x")) - Double.parseDouble(origin.get("x"))) / 200;
      sumX = (Double.parseDouble(path.get(0).get("y")) - Double.parseDouble(origin.get("y"))) / 200;
    }
  }

  public ArrayList<Map<String, String>> getPath() {
    return path;
  }

  public void setOriginAndDestination(Map<String, String> origin, Map<String, String> destination){
    this.origin = origin;
    this.destination = destination;
    sumX = (Double.parseDouble(destination.get("x")) - Double.parseDouble(origin.get("x"))) / 100;
    sumY = (Double.parseDouble(destination.get("y")) - Double.parseDouble(origin.get("y"))) / 100;
  }

  public double getSumX(){
    return sumX;
  }

  public double getSumY(){
    return sumY;
  }

  public boolean isArrived(int x, int y){
    return (int) Double.parseDouble(destination.get("x")) == x && 
    (int) Double.parseDouble(destination.get("y")) == y;
  }
}
