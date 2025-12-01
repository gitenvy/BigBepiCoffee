import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ProjectController {

    private final ProjectModel model;
    private final Stage primaryStage;

    public ProjectController(ProjectModel model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;
    }

    public ObservableList getMenu() {
       // System.out.println("DEBUG GET MENU TRIGGERED");
        return model.getMenu();
        

    }

    public Blend getBlend(String blendName) {
        if (blendName == "Peanut Butter") {
            return model.getPeanutButterBlendObject();
        }

        
        return null;
    }

    public ObservableList<CoffeeMenuItem> getOrders() {
        return model.getOrdersList();
    }

    
    
    
}
