import java.util.Comparator;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ProjectModel {

    

    
    



    
}

class Menu {



}


class Blend {

    

    ObservableList<CoffeeBean> coffeeBlend;
    private final SimpleStringProperty name;
    private final SimpleStringProperty tasteProfile;



    Blend (String name, String tasteProfile) {
        this.coffeeBlend = FXCollections.observableArrayList();
        this.name = new SimpleStringProperty(name);
        this.tasteProfile = new SimpleStringProperty(tasteProfile);
    }

    public String toString() {
        return "Blend Name: " + name + ", Beans: " + coffeeBlend.toString() + " taste profile" + this.tasteProfile;
    }



    public SimpleStringProperty getNameProperty() {
        return this.name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    
    public SimpleStringProperty getTasteProfileProperty() {
        return this.tasteProfile;
    }

    public void setTasteProfile(String tasteProfile) {
        this.tasteProfile.set(tasteProfile);
    }

  


  

}


class CoffeeBean {

    private SimpleStringProperty origin;
    ProcessingMethod processingMethod;
    private SimpleIntegerProperty altitude; 

    public SimpleIntegerProperty getAltitudeProperty() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude.set(altitude);
    }

    public SimpleStringProperty getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin.set(origin);
    }

    CoffeeBean(String origin, ProcessingMethod processingMethod, int altitude) {
        this.origin = new SimpleStringProperty(origin);
        this.processingMethod = processingMethod;
        this.altitude = new SimpleIntegerProperty(altitude);
    }


   @Override
   public String toString() {
    return "Origin: " + origin + ", Processing Method: " + processingMethod + ", Altitude: " + altitude + "]";
   }

    
}


enum ProcessingMethod {
    WASHED,
    NATURAL,
    ANAEROBIC_NATURAL,
}