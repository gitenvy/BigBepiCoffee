import java.lang.ModuleLayer.Controller;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
     int windowOpened = 0;

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
    private ListView<Drink> listView;


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
      
        viewWholeMenuButton.setOnAction(event -> {
          
            this.menuWindow();
           
        }); // execute sub window

        // should be in view orders. TODO 
        

        this.btnBuyEspressoItem = new Button("Buy Espresso-based Item");
        this.btnBuyFilterItem = new Button("Buy Fiter-based Item");
        this.btnViewOrders = new Button("View orders");

        btnViewOrders.setOnAction(event -> {
                windowOpened = 1;

        // TODO modality is broken
        if (windowOpened == 1) {
            ObservableList orders = controller.getOrders();
            EspressoBasedOrder hi = (EspressoBasedOrder) orders.get(0);
            System.out.println(hi.blendChosen);
            System.out.println(hi.quantity);
            System.out.println(hi.price);
        }
        });



        this.btnRemoveOrder = new Button("Remove an order");
        this.btnFindOrderByName = new Button("Find an order by name");
        this.btnFindOrdersByQuantity = new Button("Find order by quantitity");
        this.btnViewEspressoOrders = new Button("View Espresso Orders");
        this.btnViewFilterOrders = new Button("View Filter Orders");
        this.btnCheckout = new Button("Checkout");

        HBox menuButtonsOrderRow = new HBox(10, viewWholeMenuButton, btnViewOrders, btnRemoveOrder, btnFindOrderByName, btnFindOrdersByQuantity, btnViewEspressoOrders, btnViewFilterOrders);
        HBox menuBuyAndCheckoutRow = new HBox(5, btnBuyEspressoItem, btnBuyFilterItem, btnCheckout);

        vbox.getChildren().addAll(menuButtonsOrderRow, menuBuyAndCheckoutRow);





        

        



    }

    public void menuWindow() {
        Stage menuStage = new Stage();

        menuStage.setTitle("Menu");
        ListView<Drink> menuList = new ListView<>();
        menuList.setItems(controller.getMenu());

      
        ToggleGroup blendGroup = new ToggleGroup(); 
        RadioButton peanutButterBlendRadioBtn = new RadioButton("Peanut Butter");
        peanutButterBlendRadioBtn.setToggleGroup(blendGroup);
        
        

        HBox blendRow = new HBox(5, new Label("Choose a blend: "),peanutButterBlendRadioBtn);
      

        TextField qtyField = new TextField();

        HBox qtyOrderRow = new HBox(5, new Label("How many to order?"), qtyField);
      
        // TODO SET UP INTEGER PARSING!!!!
        Button addItemBtn = new Button("Add Item");
        addItemBtn.setOnAction(event -> {
            Drink selected = menuList.getSelectionModel().getSelectedItem();

          
            String blendName = ((RadioButton) blendGroup.getSelectedToggle()).getText();
            Blend selectedBlend = controller.getBlend(blendName);


            if (selected == null) {
                System.out.println("Nothing selected!");
                return;
            }

            System.out.println("You selected: " + selected);

            //int orderQuantity = 1;     // temporary
            int orderQuantity = Integer.parseInt(qtyField.getText());

       

            if (selected instanceof EspressoBasedDrink espressoDrink) {
                model.addEspressoOrder(espressoDrink, orderQuantity, selectedBlend);
            } 
           // else if (selected instanceof FilterBasedDrink filterDrink) {
              //  model.addFilterOrder(filterDrink, quantity, blend);
                //TODO add filter logic
            menuStage.close();
           

    
        });

        
        
        VBox menuVbox = new VBox(10, menuList, qtyOrderRow, addItemBtn, blendRow);



        Scene scene = new Scene(menuVbox);
        menuStage.setScene(scene);

        menuStage.initOwner(primaryStage); 
        menuStage.show();
        }



    private void createAndConfigurePane() {

        vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);
        ListView<Drink> menuList = new ListView<>();
        vbox.getChildren().addAll(menuList);
        // TODO create a seperate menulis windows, should not be in vbox.
    

    }





}