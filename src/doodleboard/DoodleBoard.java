/*
Juliana Sonan
DoodleBoard Project
 */
package doodleboard;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DoodleBoard extends Application {
    //Class variables
    private InstructionPane instructionPane = new InstructionPane();
    private double startX = 100;
    private double startY = 100;
    private Pane pane = new Pane();
    
    @Override
    public void start(Stage primaryStage) {
        //Create new VBox
        VBox vBox = new VBox();
        //Set VBox spacing
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        //Add InstructionPane and Pane to VBox
        vBox.getChildren().addAll(instructionPane, pane);
        //Set the Pane's minimum width and height
        pane.setMinWidth(500);
        pane.setMinHeight(500);
        //Set start coordinates on left click
        //Delete line on right click
        pane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                startY = e.getY();
                startX = e.getX();
            } else if (e.getButton() == MouseButton.SECONDARY) {
                removeLine(e.getX(), e.getY());
            }
        });
        //Create lines using the arrow keys
        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                if (startY > 0) {
                    pane.getChildren().add(drawLine(startX, startY, startX, startY - 10));
                    startY -= 10;
                }
            } else if (e.getCode() == KeyCode.DOWN) {
                if (startY < pane.getHeight()) {
                    pane.getChildren().add(drawLine(startX, startY, startX, startY + 10));
                    startY += 10;
                }
            } else if (e.getCode() == KeyCode.LEFT) {
                if (startX > 0) {
                    pane.getChildren().add(drawLine(startX, startY, startX - 10, startY));
                    startX -= 10;
                }
            } else if (e.getCode() == KeyCode.RIGHT) {
                if (startX < pane.getWidth()) {
                    pane.getChildren().add(drawLine(startX, startY, startX + 10, startY));
                    startX += 10;
                }
            }
        });
        //Create a new Scene
        Scene scene = new Scene(vBox);
        //Set the stage's title
        primaryStage.setTitle("DoodleBoard");
        //Place the scene in the stage
        primaryStage.setScene(scene);
        //Display the stage
        primaryStage.show();
        //Request focus for keyboard events
        pane.requestFocus();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    //Method to draw a line
    private Line drawLine(double startX, double startY, double endX, double endY) {
        Line line = new Line(startX, startY, endX, endY);
        return line;
    }
    //Method to remove a line
    private void removeLine(double x, double y) {

        ObservableList<Node> list = pane.getChildren();
        for (int i = list.size() - 1; i >= 0; i--) {
            Node l = list.get(i);

            if (l instanceof Line && l.contains(x, y)) {
                pane.getChildren().remove(l);
                break;
            }
        }
    }
    //InstructionPane class
    class InstructionPane extends VBox {
        InstructionPane() {
            //Display instructions
            getChildren().add(new Text("Welcome to DoodleBoard!"));
            getChildren().add(new Text("INSTRUCTIONS"));
            getChildren().add(new Text("Begin by left clicking anywhere on the DoodleBoard."));
            getChildren().add(new Text("Use the arrows keys to make your creation!"));
            getChildren().add(new Text("You can remove a line by right-clicking it."));
            getChildren().add(new Text("Happy Doodling!"));
            //Set the border color, margins, and spacing
            setStyle("-fx-border-color: black; -fx-margins: 10");
            setPadding(new Insets(10));
            setSpacing(5);
        }
    }
    
    
    
}