import java.lang.ModuleLayer.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ProjectView {
     private ProjectController controller;
    private ProjectModel model;
    private Stage primaryStage;
    private VBox vbox;
    private Label welcomeLabel;

    private Label whatToDoLabel;

    private Button viewWholeMenuButton;
    private Button btnViewWholeMenu;
    private Button btnBuyEspressoItem;
    private Button btnBuyFilterItem;
    private Button btnViewOrders;
    private Button btnRemoveOrder;
    private Button btnFindOrderByName;
    private Button btnFindOrdersByQuantity;
    private Button btnViewEspressoOrders;
    private Button btnViewFilterOrders;
    private Button btnCheckout;


    public ProjectView (ProjectController controller, ProjectModel model, Stage primaryStage) {

        this.controller = controller;
        this.model = model;
        this.primaryStage = primaryStage;

        createAndConfigurePane();
        createAndLayoutControls();
        updateControllerFromListeners();
        observeModelAndUpdateControls();


    }

    public Parent asParent() {
        return vbox;
    }

    private void observeModelAndUpdateControls() {
        // TODO
    }

    private void updateControllerFromListeners() {
        // TODO
    }


    private void createAndLayoutControls () {

        this.viewWholeMenuButton = new Button("View the whole menu");
        this.btnBuyEspressoItem = new Button("Buy Espresso-based Item");
        this.btnBuyFilterItem = new Button("Buy Fiter-based Item");
        this.btnViewOrders = new Button("View orders");
        this.btnRemoveOrder = new Button("Remove an order");
        this.btnFindOrderByName = new Button("Find an order by name");
        this.btnFindOrdersByQuantity = new Button("Find order by quantitity");
        this.btnViewEspressoOrders = new Button("View Espresso Orders");
        this.btnViewFilterOrders = new Button("View Filter Orders");
        this.btnCheckout = new Button("Checkout");

        HBox menuButtonsOrderRow = new HBox(5, viewWholeMenuButton, btnViewOrders, btnRemoveOrder, btnFindOrderByName, btnFindOrdersByQuantity, btnViewEspressoOrders, btnViewFilterOrders);
        HBox menuBuyAndCheckoutRow = new HBox(5, btnBuyEspressoItem, btnBuyFilterItem, btnCheckout);

        vbox.getChildren().addAll(menuButtonsOrderRow, menuBuyAndCheckoutRow);





        

        



    }

    private void createAndConfigurePane() {

        vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);

    }





}