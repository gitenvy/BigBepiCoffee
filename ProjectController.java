import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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

    public ObservableList<Drink> getMenu() {
    
        return model.getMenu();
        

    }

    public Blend getBlend(String blendName) {
        if (blendName.equals("Peanut Butter Blend") ) {
            return model.getPeanutButterBlendObject();
        }
        else if (blendName.equals("Berry Blend")) {
            return model.getBerryBlendObject();
        }
        else if (blendName.equals("Caramelly Blend")) {
            return model.getCaramellyBlendObject();
        }


        
        return null;
    }

    public ObservableList<CoffeeMenuItem> getOrders() {
        return model.getOrdersList();
    }

    public void handleAddItem(TableView<Drink> menuList, TextField qtyField, ToggleGroup blendGroup, Stage menuStage) {
            Drink selected = menuList.getSelectionModel().getSelectedItem();

          
            String blendName = ((RadioButton) blendGroup.getSelectedToggle()).getText();
            Blend selectedBlend = this.getBlend(blendName);


            if (selected == null) {
                System.out.println("Nothing selected!");
                return;
            }

            System.out.println("You selected: " + selected);

          
            int orderQuantity = Integer.parseInt(qtyField.getText());

       

            if (selected instanceof EspressoBasedDrink espressoDrink) {
                model.addEspressoOrder(espressoDrink, orderQuantity, selectedBlend);
            } 
            else if (selected instanceof FilterBasedDrink filterDrink) {
                model.addFilterOrder(filterDrink, orderQuantity, selectedBlend);
            }
               
            menuStage.close();
           

    

    }

    


    public int findOrderByName(String name, ObservableList<Drink> menuList) {
       
        int sortIndex = 0;
       

        for (int i =0; i < model.getMenu().size(); i++) {
            Drink item = model.getMenu().get(i);

            if (item instanceof EspressoBasedDrink) {
                EspressoBasedDrink eItem = (EspressoBasedDrink) item;
                if (eItem.name().equals(name)) {
                    return i;
                }
            }
            else if (item instanceof FilterBasedDrink) {
                FilterBasedDrink fItem = (FilterBasedDrink) item;
                 if (fItem.name().equals(name)) {
                    return i;
                }
            }
        
        }

        

        return 0;

        
    
    
    }


    public ObservableList<Drink> sortMenuItemByPriceLowToHigh() {
       

        ObservableList<Drink> menuList = model.getMenu();

        menuList.sort(Comparator.comparing(Drink::getPrice).reversed());

        return menuList;

    }

    public ObservableList<Drink> sortMenuItemByPriceHighToLow() {
        ObservableList<Drink> menuList = model.getMenu();

        menuList.sort(Comparator.comparing(Drink::getPrice));

        return menuList;
    }

    
}
