import java.util.Comparator;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;


public class ProjectModel {

    ObservableList<EspressoBasedDrink> espressoMenu;
    ObservableList<FilterBasedDrink> filterMenu;
    ObservableList<Drink> fullMenu;
    
    
    ProjectModel() {
        this.espressoMenu = FXCollections.observableArrayList(EspressoBasedDrink.values());
        this.filterMenu = FXCollections.observableArrayList(FilterBasedDrink.values());
        fullMenu = FXCollections.observableArrayList();
        fullMenu.addAll(espressoMenu);
        fullMenu.addAll(filterMenu);

    }

    public ObservableList espressoMenuList() {
        return espressoMenu;
    }

    public ObservableList filterMenuList() {
        return filterMenu;
    }

    public ObservableList getMenu() {

        return fullMenu;

    }

    

    

    
}

interface Drink {

    double getPrice();
    int getSweetness();


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

enum EspressoBasedDrink implements Drink {


    LONG_BLACK(1, 4, 3, 0, 5),
    CAPPUCINO(3, 5.5, 3, 3, 2),
    FLAT_WHITE(4, 5, 4, 4, 3),
    MAGIC_LATTE(5, 6, 5, 5, 5);


    final private int body;
    final private double price;
    final private int sweetness;
    final private int intensityInMilk;
    final private int acidity;

    EspressoBasedDrink(int body, double price, int sweetness, int intensityInMilk, int acidity) {
        this.body = body;
        this.price = price;
        this.sweetness = sweetness;
        this.intensityInMilk = intensityInMilk;
        this.acidity = acidity;
    }

    public int getBody () {
        return this.body;
    }

    @Override
    public double getPrice () {
        return this.price;
    }

    @Override
    public int getSweetness () {
        return this.sweetness;
    }

    public int getIntensityInMilk () {
        return this.intensityInMilk;
    }

    public int getAcidity () {
        return this.acidity;
    }

    static final Comparator<EspressoBasedDrink> bodyComparator = Comparator.comparing(EspressoBasedDrink::getBody);
    static final Comparator<EspressoBasedDrink> priceComparator = Comparator.comparing(EspressoBasedDrink::getPrice);
    static final Comparator<EspressoBasedDrink> sweetnessComparator = Comparator.comparing(EspressoBasedDrink::getSweetness);
    static final Comparator<EspressoBasedDrink> intensityInMilkComparator = Comparator.comparing(EspressoBasedDrink::getIntensityInMilk);
    static final Comparator<EspressoBasedDrink> acidityComparator = Comparator.comparing(EspressoBasedDrink::getAcidity);

}


enum FilterBasedDrink implements Drink {

    V60(3, 2, 5),
    FRENCH_PRESS(4,3, 3),
    COLD_BREW(5, 4, 1);

    private double price;
    private int sweetness;
    private int clarity;

    FilterBasedDrink(double price, int sweetness, int clarity) {
        this.price = price;
        this.sweetness = sweetness;
        this.clarity = clarity;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public int getSweetness() { 
        return this.sweetness;
    }

    public int getClarity() {
        return this.clarity;
    }

    static final Comparator<FilterBasedDrink> priceComparator = Comparator.comparing(FilterBasedDrink::getPrice);
    static final Comparator<FilterBasedDrink> sweetnessComparator = Comparator.comparing(FilterBasedDrink::getSweetness);
    static final Comparator<FilterBasedDrink> clarityComparator = Comparator.comparing(FilterBasedDrink::getClarity);

}