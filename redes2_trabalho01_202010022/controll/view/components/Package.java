package redes2_trabalho01_202010022.controll.view.components;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Package {
  private Map<String, String> origin;
  private Map<String, String> destination;
  private double sumX = 0;
  private double sumY = 0;
  private ArrayList<Map<String, String>> path = new ArrayList<>();

  public Package(){

  }

  public void setPath(ArrayList<Map<String, String>> path){
    System.out.println("set Path");
    for(int pathIndex = 0; pathIndex < path.size(); pathIndex++){
      Map<String, String> temp = path.get(pathIndex).entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> String.valueOf(e.getValue())));

      path.add(temp);
    }
  }

  public void clone(Package packag){
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
      // System.out.println("path.get(size - 1)");
      // System.out.println(path.get(size - 1));
      // System.out.println("path.get(size - 2)");
      // System.out.println(path.get(size - 2));
    }
  }

  public ArrayList<Map<String, String>> getPath() {
    return path;
  }

  public Map<String, String> getLastSpotInPath(){
    return path.get(path.size() - 1);
  }
  public void setOriginAndDestination(Map<String, String> origin, Map<String, String> destination){
    this.origin = origin;
    addSpot(origin);
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
    (int) Double.parseDouble(destination.get("y")) == y || (int) Double.parseDouble(path.get(path.size() - 1).get("x")) == x && 
    (int) Double.parseDouble(path.get(path.size() - 1).get("y")) == y;
  }
}
