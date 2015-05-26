package de.sebastianhesse.pandocgui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfiguration.class);

        SpringFxmlLoader loader = new SpringFxmlLoader(context);
        Parent parent = (Parent) loader.load("/fxml/main.fxml");
        primaryStage.setScene(new Scene(parent, 1000, 900));
        primaryStage.setTitle("PandocGUI");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
