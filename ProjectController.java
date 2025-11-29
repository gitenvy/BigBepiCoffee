import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
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

    public void getMenu() {
        System.out.println("DEBUG GET MENU TRIGGERED");
        model.getMenu();

    }

    public void menuWindow() {
        Stage menuStage = new Stage();

        menuStage.setTitle("Menu");
        ListView<Drink> menuList = new ListView<>();
        menuList.setItems(model.getMenu());

        VBox menuVbox = new VBox(10, menuList);

        Scene scene = new Scene(menuVbox);
        menuStage.setScene(scene);

        menuStage.initOwner(primaryStage); 
        menuStage.show();
    }
    
}
