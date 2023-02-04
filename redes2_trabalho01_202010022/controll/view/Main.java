package redes2_trabalho01_202010022.controll.view;

import java.io.IOException;
import java.net.URL;
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

  private AnchorPane createPackage() {
    AnchorPane packagePane = new AnchorPane();

    try {
      URL component_url = getClass().getResource("redes2_trabalho01_202010022/view/components/package.fxml");
      FXMLLoader fxmlLoader = new FXMLLoader();

      packagePane = fxmlLoader.load(component_url.openStream());

      background.getChildren().add(packagePane);

      // Package pkg = (Package) fxmlLoader.getController();

      packagePane.setLayoutX(host1.getLayoutX());
      packagePane.setLayoutY(host1.getLayoutY());

    } catch (IOException e) {
      e.printStackTrace();
    }

    return packagePane;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    start.setOnAction(event -> {
      createPackage();
    });
  }
}