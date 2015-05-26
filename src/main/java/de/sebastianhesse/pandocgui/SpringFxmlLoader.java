package de.sebastianhesse.pandocgui;

import javafx.fxml.FXMLLoader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * This class acts as a Spring and FXML loader.
 * It uses the normal {@link FXMLLoader} which is extended with a controller factory and the location of FXML files.
 * The controller factory uses Spring's {@link ApplicationContext} to load beans.
 *
 * Created by SebastianHesse on 25.05.2015.
 */
public class SpringFxmlLoader {

    private ApplicationContext context;

    public SpringFxmlLoader(ApplicationContext appContext) {
        this.context = appContext;
    }

    /**
     * Loads the root FXML file and uses Spring's context to get controllers.
     *
     * @param resource location of FXML file
     * @return parent object of FXML layout, see {@link FXMLLoader#load(InputStream)}
     * @throws IOException in case of problems with FXML file
     */
    public Object load(final String resource) throws IOException {
        try (InputStream fxmlStream = getClass().getResourceAsStream(resource)) {
            FXMLLoader loader = new FXMLLoader();
            // set location of fxml files to FXMLLoader
            URL location = getClass().getResource(resource);
            loader.setLocation(location);
            // set controller factory
            loader.setControllerFactory(context::getBean);
            // load FXML
            return loader.load(fxmlStream);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }
}
