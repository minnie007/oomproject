package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.PointLight;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Box box;
    @FXML
    private PointLight pointLight1,pointLight2;
    @FXML
    private Slider slider;
    @FXML
    private Pane pane;
    @FXML
    private Label label;


    private double anchorX,anchorY;
    private double initAngleX = 0,initAngleY = 0;

    private DoubleProperty anchorAngleX = new SimpleDoubleProperty(0);
    private DoubleProperty anchorAngleY = new SimpleDoubleProperty(0);


    public void wireFrame(){
        box.setDrawMode(DrawMode.LINE);
    }

    public void fillMode(){
        box.setDrawMode(DrawMode.FILL);
    }

    public void violet(){
        pointLight1.setColor(Color.VIOLET);
        pointLight2.setColor(Color.VIOLET);
    }

    public void indigo(){
        pointLight1.setColor(Color.INDIGO);
        pointLight2.setColor(Color.INDIGO);
    }

    public void blue(){
        pointLight1.setColor(Color.BLUE);
        pointLight2.setColor(Color.BLUE);
    }

    public void green(){
        pointLight1.setColor(Color.GREEN);
        pointLight2.setColor(Color.GREEN);
    }

    public void yellow(){
        pointLight1.setColor(Color.YELLOW);
        pointLight2.setColor(Color.YELLOW);
    }

    public void orange(){
        pointLight1.setColor(Color.ORANGE);
        pointLight2.setColor(Color.ORANGE);
    }

    public void red(){
        pointLight1.setColor(Color.RED);
        pointLight2.setColor(Color.RED);
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        pane.setOnMouseClicked(event ->{
            if(event.isShiftDown())
                slider.setDisable(false);
        });
        slider.setLayoutX(1500);
        slider.setLayoutY(500);
        label.setLayoutX(1590);
        label.setLayoutY(530);
        PhongMaterial gold = new PhongMaterial();
        gold.setDiffuseColor(Color.LIGHTBLUE);
        gold.setSpecularColor(Color.LIGHTBLUE);
        gold.setSpecularPower(10);
        box.setMaterial(gold);
        box.setLayoutX(1000);
        box.setLayoutY(500);
        Rotate rotateX = new Rotate(0,Rotate.X_AXIS);
        Rotate rotateY = new Rotate(0,Rotate.Y_AXIS);

        box.getTransforms().addAll(rotateX,rotateY);

        rotateX.angleProperty().bind(anchorAngleX);
        rotateY.angleProperty().bind(anchorAngleY);

        box.setOnMousePressed(event ->{
            slider.setDisable(true);
            box.requestFocus();
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();

            initAngleX = anchorAngleX.get();
            initAngleY = anchorAngleY.get();
        });

        box.setOnMouseDragged(event ->{
            anchorAngleX.set(initAngleX + (anchorY - event.getSceneY()));
            anchorAngleY.set(initAngleY - (anchorX - event.getSceneX()));
        });


        box.setOnKeyPressed(event -> {
            if(box.isFocused()) {
                if (!event.isShiftDown()) {
                    switch (event.getCode()) {
                        case UP:
                            box.setLayoutY(box.getLayoutY() - 1.0);
                            break;
                        case DOWN:
                            box.setLayoutY(box.getLayoutY() + 1.0);
                            break;
                        case LEFT:
                            box.setLayoutX(box.getLayoutX() - 1.0);
                            break;
                        case RIGHT:
                            box.setLayoutX(box.getLayoutX() + 1.0);
                            break;
                        case Z: box.setTranslateZ(box.getTranslateZ() + 1.0);
                            break;
                        case P:
                            box.setScaleX(box.getScaleX() * 1.01);
                            box.setScaleY(box.getScaleY() * 1.01);
                            box.setScaleZ(box.getScaleZ() * 1.01);
                            break;
                        case M:
                            box.setScaleX(box.getScaleX() / 1.01);
                            box.setScaleY(box.getScaleY() / 1.01);
                            box.setScaleZ(box.getScaleZ() / 1.01);
                            break;
                    }
                }
                else {
                    switch(event.getCode()){
                        case UP:
                            anchorAngleX.set(anchorAngleX.get() + slider.getValue());
                            break;
                        case Z:
                            box.setTranslateZ(box.getTranslateZ() - 1.0);
                            break;
                        case DOWN:
                            anchorAngleX.set(anchorAngleX.get() - slider.getValue());
                            break;
                        case RIGHT:
                            anchorAngleY.set(anchorAngleY.get() + slider.getValue());
                            break;
                        case LEFT:
                            anchorAngleY.set(anchorAngleY.get() - slider.getValue());
                            break;
                    }

                }
            }
        });

    }

}
