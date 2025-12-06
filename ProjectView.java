import java.lang.ModuleLayer.Controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
        
        initiateWelcomeScreen(); // The first intialization of the welcome screen, which is the first screen.


    }


    private void mainMenu() {

       
        createAndConfigurePane(); 
        createAndLayoutControls();  // Configure the main vbox and the main menu.
       
        
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void initiateWelcomeScreen() {

        VBox welcomeVbox;
        Stage welcomeStage = new Stage();
       
        welcomeStage.setTitle("Coffee Shop Ordering System");

        Label welcomeLabel = new Label("Welcome to the Coffee Shop's POS ordering system");
        Button startButton = new Button("Tap to start");
      

        startButton.setOnAction(event -> { // If clicked, will proceed to the main screen, which
            welcomeStage.close();           // stores main functionality.
            mainMenu();
        });

        HBox welcomeRow = new HBox(5, welcomeLabel);
        welcomeRow.setAlignment(Pos.CENTER);

        HBox startRow = new HBox(5, startButton);
        startRow.setAlignment(Pos.CENTER);

        welcomeVbox = new VBox(5, welcomeRow, startRow);
        
        Scene scene = new Scene(welcomeVbox, 600, 300);

        welcomeStage.setScene(scene);

    
        welcomeStage.show();

    }

    public Parent asParent() {
        return vbox;
    }

   


    private void createAndLayoutControls () {
        

        this.viewWholeMenuButton = new Button("View the whole menu");
      
        viewWholeMenuButton.setOnAction(event -> {
          
            this.menuWindow(); // Opens the menu window method upon clicking View the whole menu
           
        }); 

      
        

        this.btnViewOrders = new Button("View orders");

        btnViewOrders.setOnAction(event -> {


            this.viewOrders(); //  Opens the viewOrder() method upon clicking the View orders.
            
        });



        this.btnRemoveOrder = new Button("Remove an order");
        btnRemoveOrder.setOnAction(event -> {
            this.removeOrderWindow();   // execute the removeOrderWindow() upon removing an order
        });

        this.btnCheckout = new Button("Checkout");
        btnCheckout.setOnAction(event -> {
            this.checkoutWindow();      // Moves on the checkout window.
        });

        HBox menuButtonsOrderRow = new HBox(10, viewWholeMenuButton, btnViewOrders, btnRemoveOrder);
        menuButtonsOrderRow.setAlignment(Pos.CENTER);
        HBox menuBuyAndCheckoutRow = new HBox(5,btnCheckout);
        menuBuyAndCheckoutRow.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(menuButtonsOrderRow, menuBuyAndCheckoutRow);





        

        



    }

    public void viewOrders() {

            
    
            ListView<CoffeeMenuItem> orderList = new ListView<>();

            for (CoffeeMenuItem item : model.getOrdersList()) {
                orderList.getItems().add(item);
            }

            TextField sortField = new TextField();
            sortField.textProperty().addListener((obs, old, newValue) -> {
               
            

            ObservableList<CoffeeMenuItem> sortedOrders = FXCollections.observableArrayList();

            if (newValue.isEmpty()) {
                orderList.setItems(controller.getOrders());
                return;
            }

            for (CoffeeMenuItem menuItem : controller.getOrders()) {
                if (menuItem instanceof EspressoBasedOrder) {
                    EspressoBasedOrder eMenuItem = (EspressoBasedOrder) menuItem;
                    if (eMenuItem.getName().toLowerCase().startsWith(newValue.toLowerCase())) {
                        sortedOrders.add(menuItem);
                    }
                }
                if (menuItem instanceof FilterBasedOrder) {
                    FilterBasedOrder fMenuItem = (FilterBasedOrder) menuItem;
                    if (fMenuItem.getName().toLowerCase().startsWith(newValue.toLowerCase())) {
                        sortedOrders.add(menuItem);
                    }
                }
            }

            orderList.setItems(sortedOrders);
            
            });

            HBox sortNameRow = new HBox(5, new Label("Sort by name: "), sortField);

            Button sortByTypeEspBtn = new Button("Sort by Espresso-Based");
            Button sortByTypeFilterBtn = new Button("Sort by Filter-Based");

            HBox sortTypeRow = new HBox(5, new Label("Sort by drink type: "), sortByTypeEspBtn, sortByTypeFilterBtn);

            sortByTypeEspBtn.setOnAction(event -> {

                ObservableList<CoffeeMenuItem> sortedEsp = FXCollections.observableArrayList();
                  for (CoffeeMenuItem item : model.getOrdersList()) {
                    if (item instanceof EspressoBasedOrder) {
                        sortedEsp.add(item);
                    }

                orderList.setItems(sortedEsp);
            }
            });

            sortByTypeFilterBtn.setOnAction(event -> {
                 ObservableList<CoffeeMenuItem> sortedFilter = FXCollections.observableArrayList();
                  for (CoffeeMenuItem item : model.getOrdersList()) {
                    if (item instanceof FilterBasedOrder) {
                        sortedFilter.add(item);
                    }

                orderList.setItems(sortedFilter);
                  }
            });




            Stage orderStage = new Stage();
            VBox root = new VBox(sortNameRow,sortTypeRow, orderList);
            Scene scene = new Scene(root, 400, 300);

            orderStage.initOwner(primaryStage);
            orderStage.initModality(Modality.APPLICATION_MODAL); 


            orderStage.setScene(scene);
            orderStage.setTitle("Orders");
            orderStage.show();

        
          
    }

    public void menuWindow() {

        Stage menuStage = new Stage();

        menuStage.initOwner(primaryStage);
        menuStage.initModality(Modality.APPLICATION_MODAL);

        menuStage.setTitle("Menu");
       
        TableView<Drink> menuTable = new TableView<>();
        TableColumn<Drink, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Drink, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getPrice()));

        menuTable.getColumns().addAll(nameCol, priceCol);
        menuTable.setItems(controller.getMenu());


      
        ToggleGroup blendGroup = new ToggleGroup(); 
        RadioButton peanutButterBlendRadioBtn = new RadioButton("Peanut Butter Blend");
        peanutButterBlendRadioBtn.setToggleGroup(blendGroup);
        RadioButton berryBlendRadioBtn = new RadioButton("Berry Blend");
        berryBlendRadioBtn.setToggleGroup(blendGroup);
        RadioButton caramellyBlendRadioBtn = new RadioButton("Caramelly Blend");
        caramellyBlendRadioBtn.setToggleGroup(blendGroup);

        
        TextField sortField = new TextField();

        sortField.textProperty().addListener((obs, old, newValue) -> {
          

            ObservableList<Drink> sortedMenu = FXCollections.observableArrayList();

            if (newValue.isEmpty()) {
                menuTable.setItems(controller.getMenu());
                return;
            }

            for (Drink menuItem : controller.getMenu()) {
                if (menuItem instanceof EspressoBasedDrink) {
                    EspressoBasedDrink eMenuItem = (EspressoBasedDrink) menuItem;
                    if (eMenuItem.name().toLowerCase().startsWith(newValue.toLowerCase())) {
                        sortedMenu.add(menuItem);
                    }
                }
                if (menuItem instanceof FilterBasedDrink) {
                    FilterBasedDrink fMenuItem = (FilterBasedDrink) menuItem;
                    if (fMenuItem.name().toLowerCase().startsWith(newValue.toLowerCase())) {
                        sortedMenu.add(menuItem);
                    }
                }
            }

            menuTable.setItems(sortedMenu);
            
        });

        Label sortNameLabel = new Label("Sort by name: ");

        Label sortPriceLabel = new Label("Sort by price: ");
        Button sortLowtoHighPriceBtn = new Button("Low to high");
        Button sortHighToLowPriceBtn = new Button("High to low");

        sortLowtoHighPriceBtn.setOnAction(event -> {
            ObservableList<Drink> sortedList = controller.sortMenuItemByPriceHighToLow(); 
        
            menuTable.setItems(sortedList);
        });

        sortHighToLowPriceBtn.setOnAction(event -> {
            ObservableList<Drink> sortedList = controller.sortMenuItemByPriceLowToHigh(); 
        
            menuTable.setItems(sortedList);
        });

        HBox sortPriceRow = new HBox(5, sortPriceLabel, sortHighToLowPriceBtn, sortLowtoHighPriceBtn);



        

        

        HBox sortNameMenuRow = new HBox(5, sortNameLabel, sortField);

        //

        
        
        

        HBox blendRow = new HBox(5, new Label("Choose a blend: "),peanutButterBlendRadioBtn, berryBlendRadioBtn, caramellyBlendRadioBtn);
      

        TextField qtyField = new TextField();

        HBox qtyOrderRow = new HBox(5, new Label("How many to order?"), qtyField);
      
      
        Button addItemBtn = new Button("Add Item");
        addItemBtn.setOnAction(event -> {
            controller.handleAddItem(menuTable, qtyField, blendGroup, menuStage);
            menuStage.close();
            
        });

        
        addItemBtn.setAlignment(Pos.CENTER);
        
        VBox menuVbox = new VBox(10, sortNameMenuRow,sortPriceRow, menuTable, qtyOrderRow, addItemBtn, blendRow);



        Scene scene = new Scene(menuVbox);
        menuStage.setScene(scene);

        menuStage.initOwner(primaryStage); 
        menuStage.show();
    }


    public void removeOrderWindow() {
        ListView<String> orderList = new ListView<>();

            for (CoffeeMenuItem item : controller.getOrders()) {
               
                orderList.getItems().add(item.toString());
                
            }
            
            Stage orderStage = new Stage();

            Button removeBtn = new Button("Remove");
            removeBtn.setOnAction(event -> {
                int selectedItemIndex = orderList.getSelectionModel().getSelectedIndex();
                controller.getOrders().remove(selectedItemIndex);
                
                orderStage.close();
                
            });

         


            HBox removeRow = new HBox(5, removeBtn);
            


            
            VBox root = new VBox(5, orderList, removeBtn);
            Scene scene = new Scene(root, 400, 300);

            orderStage.setScene(scene);
            orderStage.setTitle("Orders");
            

           
            orderStage.initOwner(primaryStage);
            orderStage.initModality(Modality.APPLICATION_MODAL); 

            orderStage.show();

    }

    public void checkoutWindow() {

        Stage checkoutStage = new Stage();
       
        checkoutStage.initOwner(primaryStage);
        checkoutStage.initModality(Modality.APPLICATION_MODAL); 


        checkoutStage.setTitle("Checkout");
        TableView<CoffeeMenuItem> checkoutTable = new TableView<>();

        TableColumn<CoffeeMenuItem, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<CoffeeMenuItem, Double> priceCol = new TableColumn<>("Total Price");
        priceCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getTotalPrice()));

        TableColumn<CoffeeMenuItem, Integer> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getQuantity()));

        checkoutTable.getColumns().addAll(nameCol, qtyCol, priceCol);
        checkoutTable.setItems(controller.getOrders());

        Label totalLabel = new Label("Total Price for Checkout: $ " +  model.getCheckoutPrice());

        Button payBtn = new Button("Checkout & Exit");
        payBtn.setOnAction(event -> {
            checkoutStage.close();
            primaryStage.close();

        });

        VBox root = new VBox(checkoutTable, totalLabel, payBtn);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 400, 300);
        checkoutStage.setScene(scene);
        checkoutStage.initOwner(primaryStage);
        checkoutStage.show();

    }

   






 


    private void createAndConfigurePane() {

        vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);
       


}





}
