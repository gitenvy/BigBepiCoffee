import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class ProjectMVC extends Application {

   @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Coffee Shop");
        ProjectModel model = new ProjectModel();
        ProjectController controller = new ProjectController(model, primaryStage);
        ProjectView view = new ProjectView(controller, model, primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
}
