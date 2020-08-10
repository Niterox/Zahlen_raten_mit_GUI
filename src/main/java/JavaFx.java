import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class JavaFx extends Application {
    public static BorderPane root;
    public static ImageView downView;
    public static ImageView downGreyView;
    public static ImageView upView;
    public static ImageView upGreyView;
    public static ImageView correctView;
    public static ImageView correctGreyView;
    public static Integer textFieldNumber;
    private TextField textField;


    public void start(Stage primaryStage) {
        root = new BorderPane();
        HBox node = new HBox();
        root.setTop(node);

        //adds label
        Label label = new Label("Zahl zwischen " + Zahl.getMin() + " und \r" + Zahl.getMax() + " eingeben:  ");
        node.getChildren().add(label);

        //adds textfield
        textField = new TextField();
        textField.setOnAction(e -> {
            doControl();
        });
        node.getChildren().add(textField);


        //adds button
        Button button = new Button("raten");
        button.setOnMouseClicked(e -> {
            doControl();
        });
        node.getChildren().add(button);
        node.setPrefHeight(40);


        //Creating image down
        Image imageDown = new Image(this.getClass().getResourceAsStream("down.png"));
        downView = createImage(imageDown);

        //Creating image down_grey
        Image imageDownGrey = new Image(this.getClass().getResourceAsStream("down_grey.png"));
        downGreyView = createImage(imageDownGrey);
        root.setLeft(downGreyView);

        //Creating image up
        Image imageUp = new Image(this.getClass().getResourceAsStream("up.png"));
        upView = createImage(imageUp);

        //Creating image up_grey
        Image imageUpGrey = new Image(this.getClass().getResourceAsStream("up_grey.png"));
        upGreyView = createImage(imageUpGrey);
        root.setCenter(upGreyView);

        //Creating image correct
        Image imageCorrect = new Image(this.getClass().getResourceAsStream("correct.png"));
        correctView = createImage(imageCorrect);

        //Creating image correct_grey
        Image imageCorrectGrey = new Image(this.getClass().getResourceAsStream("correct_grey.png"));
        correctGreyView = createImage(imageCorrectGrey);
        root.setRight(correctGreyView);


        Scene scene = new Scene(root, 320, 118);

        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("zahlen_raten.png")));
        primaryStage.setTitle("Zahlen raten");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ImageView createImage(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    private void doControl() {
        if (textField.getText().equals("")) {
            errorIsNullBlock();
        } else {
            try {
                textFieldNumber = Integer.parseInt(textField.getText());
                if (textFieldNumber < Zahl.getMin() || textFieldNumber > Zahl.getMax()) {
                    errorIsOutOfRange();
                } else {
                    Zahl.usrNumber = textFieldNumber;
                    Zahl.compareNumber(Zahl.usrNumber);
                    textField.clear();
                }
            } catch (NumberFormatException e1) {
                errorIsTextBlock();
            }
        }
    }


    private void errorIsTextBlock() {
        String errorMessage = "Bitte nur Ganzzahlen eingeben \r";
        errorBlock(errorMessage);
    }

    private void errorIsNullBlock() {
        String errorMessage = "Textfeld darf nicht leer sein \r";
        errorBlock(errorMessage);
    }

    private void errorIsOutOfRange() {
        String errorMessage = "Zahl muss zwischen 0 und 1000 liegen \r";
        errorBlock(errorMessage);
    }

    private void errorBlock(String errorMessage) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        VBox vbox = new VBox(new Text(errorMessage));
        Button errorButton = new Button("OK");
        errorButton.setDefaultButton(true);
        errorButton.setOnAction(e -> {
            dialogStage.close();
        });

        vbox.getChildren().add(errorButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));

        dialogStage.setScene(new Scene(vbox));
        dialogStage.getIcons().add(new Image(this.getClass().getResourceAsStream("error.png")));
        dialogStage.setTitle("Error");
        dialogStage.show();
    }

    public static void launchApp() {
        launch();
    }
}
