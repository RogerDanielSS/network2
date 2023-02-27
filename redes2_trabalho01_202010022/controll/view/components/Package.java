
/* *************************************************************** 
* Autor............: Roger Daniel Santana Simoes
* Matricula........: 202010022
* Inicio...........: 06/02/2023
* Ultima alteracao.: 19/02/2023
* Nome.............: Inundação
* Funcao...........: Simula o roteamento dentro de uma subrede usando inundação
*************************************************************** */
package controll.view.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Package {
  private Map<String, String> origin;
  private Map<String, String> destination;
  private double sumX = 0;
  private double sumY = 0;
  private int life = 6;
  private ArrayList<Map<String, String>> path = new ArrayList<>();
  private boolean dead = false;
  private int networkId = 1;

  /*
   * ***************************************************************
   * Metodo: getNetworkId
   * Funcao: retorna o id de rede
   * Parametros: vazio
   * Retorno: id de rede
   */
  public int getNetworkId() {
    return this.networkId;
  }

  /*
   * ***************************************************************
   * Metodo: setNetworkId
   * Funcao: setta o id de rede
   * Parametros: id de rede
   * Retorno: vazio
   */
  public void setNetworkId(int networkId) {
    this.networkId = networkId;
  }

  /*
   * ***************************************************************
   * Metodo: kill
   * Funcao: atualiza o status do pacote para morto
   * Parametros: vazio
   * Retorno: vazio
   */
  public void kill() {
    this.dead = true;
  }

  /*
   * ***************************************************************
   * Metodo: dead
   * Funcao: informa se o pacote esta morto
   * Parametros: vazio
   * Retorno: vazio
   */
  public boolean dead() {
    return this.dead;
  }

  /*
   * ***************************************************************
   * Metodo: descreaseLife
   * Funcao: diminui a vida (ttl) do pacote
   * Parametros: vazio
   * Retorno: vazio
   */
  public void descreaseLife() {
    life--;
  }

  /*
   * ***************************************************************
   * Metodo: setLife
   * Funcao: setta o ttl do pacote
   * Parametros: valor a ser settado
   * Retorno: vazio
   */
  public void setLife(int life) {
    this.life = life;
  }

  /*
   * ***************************************************************
   * Metodo: getLife
   * Funcao: retorna o valor do ttl
   * Parametros: vazoi
   * Retorno: valor do ttl
   */
  public int getLife() {
    return this.life;
  }

  /*
   * ***************************************************************
   * Metodo: setPath
   * Funcao: setta o path do pacote
   * Parametros: path a ser settado
   * Retorno: vazio
   */
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

  /*
   * ***************************************************************
   * Metodo: getOrigin
   * Funcao: retorna a origem do pacote
   * Parametros: void
   * Retorno: origem do pacote
   */
  public Map<String, String> getOrigin() {
    return origin;
  }

  /*
   * ***************************************************************
   * Metodo: setOrigin
   * Funcao: setta a origem do pacote =
   * Parametros: origem a ser settada
   * Retorno: vazion
   */
  public void setOrigin(Map<String, String> origin) {
    this.origin = origin;
    path.add(origin);
  }

  /*
   * ***************************************************************
   * Metodo: getDestination
   * Funcao: retorna o destino do pacote
   * Parametros: vazio
   * Retorno: vazio
   */
  public Map<String, String> getDestination() {
    return destination;
  }

  /*
   * ***************************************************************
   * Metodo: setDestination
   * Funcao: setta o destino do pacote
   * Parametros: destino do pacote
   * Retorno: vazio
   */
  public void setDestination(Map<String, String> destination) {
    this.destination = destination;
  }

  /*
   * ***************************************************************
   * Metodo: addSpot
   * Funcao: adiciona um spot ao path do pacote
   * Parametros: spot a ser add
   * Retorno: vazio
   */
  public void addSpot(Map<String, String> spot) {
    path.add(spot);

    if (path.size() > 1) {
      int size = path.size();
      sumX = (Double.parseDouble(path.get(size - 1).get("x")) - Double.parseDouble(path.get(size - 2).get("x"))) / 100;
      sumY = (Double.parseDouble(path.get(size - 1).get("y")) - Double.parseDouble(path.get(size - 2).get("y"))) / 100;
    }
  }

  /*
   * ***************************************************************
   * Metodo: getPath
   * Funcao: retorna o path do pacote
   * Parametros: vazio
   * Retorno: vazio
   */
  public ArrayList<Map<String, String>> getPath() {
    return path;
  }

  /*
   * ***************************************************************
   * Metodo: getLastSpotInPath
   * Funcao: retorna o ultimo spot no path do pacote
   * Parametros: vazio
   * Retorno: spot
   */
  public Map<String, String> getLastSpotInPath() {
    return path.get(path.size() - 1);
  }

  /*
   * ***************************************************************
   * Metodo: getLastSpotInPath
   * Funcao: retorna o penultimo spot no path do pacote
   * Parametros: vazio
   * Retorno: spot
   */
  public Map<String, String> getLastButOneSpotInPath() {
    return path.get(path.size() - 2);
  }

  /*
   * ***************************************************************
   * Metodo: setOriginAndDestination
   * Funcao: setta a origem e o destino do pacote, alem de calcular a distancia a
   * ser somada para definir visualmente a rota
   * Parametros: id de rede
   * Retorno: vazio
   */
  public void setOriginAndDestination(Map<String, String> origin, Map<String, String> destination) {
    this.origin = origin;
    addSpot(origin);
    this.destination = destination;
    sumX = (Double.parseDouble(destination.get("x")) - Double.parseDouble(origin.get("x"))) / 100;
    sumY = (Double.parseDouble(destination.get("y")) - Double.parseDouble(origin.get("y"))) / 100;
  }

  /*
   * ***************************************************************
   * Metodo: getSumX
   * Funcao: retorna o valor da soma x
   * Parametros: vazio
   * Retorno: soma x
   */
  public double getSumX() {
    return sumX;
  }

  /*
   * ***************************************************************
   * Metodo: getSumY
   * Funcao: retorna o valor da soma y
   * Parametros: vazio
   * Retorno: soma y
   */
  public double getSumY() {
    return sumY;
  }

  /*
   * ***************************************************************
   * Metodo: isArrived
   * Funcao: verifica se o path ja chegou no destino ou no ultimo spot no path
   * Parametros: posicoes atuais do pacote
   * Retorno: verdadeiro ou falso
   */
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
