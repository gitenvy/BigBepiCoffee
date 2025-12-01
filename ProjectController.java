import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
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

    public ObservableList getMenu() {
       // System.out.println("DEBUG GET MENU TRIGGERED");
        return model.getMenu();
        

    }

    public Blend getBlend(String blendName) {
        if (blendName.equals("Peanut Butter") ) {
            return model.getPeanutButterBlendObject();
        }

        
        return null;
    }

    public ObservableList<CoffeeMenuItem> getOrders() {
        return model.getOrdersList();
    }

    public void handleAddItem(ListView<Drink> menuList, TextField qtyField, ToggleGroup blendGroup, Stage menuStage) {
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
                //TODO add filter logic
            menuStage.close();
           

    

    }

    public void viewOrders() {
           

            
            // EspressoBasedOrder hi = (EspressoBasedOrder) orders.get(0);
            // System.out.println(hi.blendChosen);
            // System.out.println(hi.quantity);
            // System.out.println(hi.price);

            for (CoffeeMenuItem item : model.getOrdersList()) {
                if (item instanceof EspressoBasedOrder) {
                    System.out.println(item.name + " " + item.blendChosen);
                }
                if (item instanceof FilterBasedOrder) {
                    System.out.println(item.name + item.quantity);
                }
            }


    }

    
    
    
}
