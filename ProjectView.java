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
        
        initiateWelcomeScreen();


    }


    private void mainMenu() {

       
        createAndConfigurePane();
        createAndLayoutControls();
        updateControllerFromListeners();
        observeModelAndUpdateControls();

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
      

        startButton.setOnAction(event -> {
            welcomeStage.close();
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


            this.viewOrders();
            
        });



        this.btnRemoveOrder = new Button("Remove an order");
        btnRemoveOrder.setOnAction(event -> {
            this.removeOrderWindow();
        });
        this.btnFindOrderByName = new Button("Find an order by name");
      //  btnFindOrderByName.setOnAction(event -> {
       //     this.findOrderByNameWindows();
       // });
        this.btnFindOrdersByQuantity = new Button("Find order by quantitity");
        this.btnViewEspressoOrders = new Button("View Espresso Orders");
        this.btnViewFilterOrders = new Button("View Filter Orders");
        this.btnCheckout = new Button("Checkout");

        HBox menuButtonsOrderRow = new HBox(10, viewWholeMenuButton, btnViewOrders, btnRemoveOrder, btnFindOrderByName, btnFindOrdersByQuantity, btnViewEspressoOrders, btnViewFilterOrders);
        HBox menuBuyAndCheckoutRow = new HBox(5, btnBuyEspressoItem, btnBuyFilterItem, btnCheckout);

        vbox.getChildren().addAll(menuButtonsOrderRow, menuBuyAndCheckoutRow);





        

        



    }

    public void viewOrders() {
    
            ListView<CoffeeMenuItem> orderList = new ListView<>();

            for (CoffeeMenuItem item : model.getOrdersList()) {
                orderList.getItems().add(item);
            }

            TextField sortField = new TextField();

        sortField.textProperty().addListener((obs, old, newValue) -> {
            // int sortIndex = controller.findOrderByName(newValue);
            // ObservableList<Drink> sortedList = FXCollections.observableArrayList();
            // sortedList.add(controller.getMenu().get(sortIndex));
            // menuList.setItems(sortedList);
            

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

            orderStage.setScene(scene);
            orderStage.setTitle("Orders");
            orderStage.show();

            

          


    }

    public void menuWindow() {

        // TODO : Make a tableView instead.

        Stage menuStage = new Stage();





        menuStage.setTitle("Menu");
        //ListView<Drink> menuList = new ListView<>();
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

        // 
        TextField sortField = new TextField();

        sortField.textProperty().addListener((obs, old, newValue) -> {
            // int sortIndex = controller.findOrderByName(newValue);
            // ObservableList<Drink> sortedList = FXCollections.observableArrayList();
            // sortedList.add(controller.getMenu().get(sortIndex));
            // menuList.setItems(sortedList);

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

        // Button sortNameButton = new Button("Sort");

        // sortNameButton.setOnAction(event -> {
        //     int sortIndex = controller.findOrderByName(sortField.getText());
        //     //
            
        //     //
        //     ObservableList<Drink> sortedList = FXCollections.observableArrayList();
        //     sortedList.add(controller.getMenu().get(sortIndex));
        //     menuList.setItems(sortedList);
        // });

        Label sortPriceLabel = new Label("Sort by price: ");
        Button sortLowtoHighPriceBtn = new Button("Low to high");
        Button sortHighToLowPriceBtn = new Button("High to low");

        sortLowtoHighPriceBtn.setOnAction(event -> {
            ObservableList<Drink> sortedList = controller.sortMenuItemByPriceHighToLow(); // wrong one.
         //  System.out.println("TRGGGERED");
            menuTable.setItems(sortedList);
        });

        sortHighToLowPriceBtn.setOnAction(event -> {
            ObservableList<Drink> sortedList = controller.sortMenuItemByPriceLowToHigh(); // wrong one.
         //  System.out.println("TRGGGERED");
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
        // TODO why no work
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
            // TODO OVERLAODING
            Stage orderStage = new Stage();

            Button removeBtn = new Button("Remove");
            removeBtn.setOnAction(event -> {
                int selectedItemIndex = orderList.getSelectionModel().getSelectedIndex();
                controller.getOrders().remove(selectedItemIndex);
                // SINCE LIST INDEX FOLLOWS ORDERS LIST INDEX, THIS WORKS!!!!!!
                orderStage.close();
                
            });

         


            HBox removeRow = new HBox(5, removeBtn);
            


            
            VBox root = new VBox(5, orderList, removeBtn);
            Scene scene = new Scene(root, 400, 300);

            orderStage.setScene(scene);
            orderStage.setTitle("Orders");
            orderStage.initOwner(primaryStage); 
            orderStage.show();

    }

   






 //   }


    private void createAndConfigurePane() {

        vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);
       
    

    }





}