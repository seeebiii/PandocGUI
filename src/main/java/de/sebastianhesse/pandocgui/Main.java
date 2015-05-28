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

        context.getBeanFactory().registerResolvableDependency(Stage.class, primaryStage);

        SpringFxmlLoader loader = context.getBean(SpringFxmlLoader.class);
        Parent parent = (Parent) loader.load("/fxml/main.fxml");
        primaryStage.setScene(new Scene(parent, 1000, 600));
        primaryStage.setTitle("PandocGUI");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
