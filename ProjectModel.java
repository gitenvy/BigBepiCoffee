import java.util.ArrayList;
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
    ObservableList<CoffeeMenuItem> orders;
    PresetCoffee preset = new PresetCoffee();
    ArrayList<Blend> allBlends = preset.initBlends();

    
    

    
        //orders.add(new CoffeeMenuItem(menuItem.name, menuItem.quantity, menuItem.price, menuItem.blendChosen));
        // get the details...

    public void addEspressoOrder(EspressoBasedDrink drink, int qty, Blend blend) {
        EspressoBasedOrder order = new EspressoBasedOrder(drink.name(),drink,qty, drink.getPrice(), blend);
        orders.add(order);
    }
    public void addFilterOrder(FilterBasedDrink drink, int qty, Blend blend) {
        FilterBasedOrder order = new FilterBasedOrder(drink.name(), drink, qty, drink.getPrice(), blend);
        orders.add(order);
    }

    public ObservableList<CoffeeMenuItem> getOrdersList() {
        return this.orders;
    }
    
    ProjectModel() {
        this.espressoMenu = FXCollections.observableArrayList(EspressoBasedDrink.values());
        this.filterMenu = FXCollections.observableArrayList(FilterBasedDrink.values());
        fullMenu = FXCollections.observableArrayList();
        fullMenu.addAll(espressoMenu);
        fullMenu.addAll(filterMenu);
        this.orders = FXCollections.observableArrayList();
      


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

    public Blend getPeanutButterBlendObject() {
        
        return allBlends.get(0);
    }
    public Blend getBerryBlendObject() {
        return allBlends.get(1);
    }
    public Blend getCaramellyBlendObject() {
        return allBlends.get(2);
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
        return "Blend Name: " + this.name;
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


abstract class CoffeeMenuItem {

  

    protected String name;
    protected int quantity;
    protected double price;
	protected Blend blendChosen;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   


    CoffeeMenuItem(String name, int quantity, double price, Blend blendChosen) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
		this.blendChosen = blendChosen;
    }   

    public double getTotalPrice() {
        return quantity * price;
    }

    public String getName() {
        return this.name;
    }  
      public void setName(String name) {
        this.name = name;
    }

    public Blend getBlend() {
        return this.blendChosen;
    }

    public abstract String toString();

    
}




class EspressoBasedOrder extends CoffeeMenuItem {

    private EspressoBasedDrink espressoBasedOrder;
   
    EspressoBasedOrder(String name, EspressoBasedDrink espressoBasedOrder, int quantity, double price, Blend blendChosen) {
        super(name, quantity, price, blendChosen);
        this.espressoBasedOrder = espressoBasedOrder;
   
    }

    public EspressoBasedDrink getEspressoBasedOrder() {
        return this.espressoBasedOrder;
    }

    @Override
    public double getTotalPrice() {
        return super.getTotalPrice();
    }

    public Blend getBlend() {
        return super.getBlend();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String toString() {
        String drinkName = this.name;
        String blendName;

       
        blendName = blendChosen.toString();  
        

    return drinkName + " × " + quantity + " (Blend: " + blendName + ")";
}



}

class FilterBasedOrder extends CoffeeMenuItem {

    FilterBasedDrink filterBasedOrder;
 
    

    FilterBasedOrder(String name, FilterBasedDrink filterBasedOrder,  int quantity, double price, Blend blendChosen) {
        super(name, quantity, price, blendChosen);
        this.filterBasedOrder = filterBasedOrder;
    
    }

    public FilterBasedDrink getFilterBasedOrder() {
        return this.filterBasedOrder;  
    }

    @Override  
    public double getTotalPrice() {
        return super.getTotalPrice();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Blend getBlend() {
        return super.getBlend();
    }


   @Override
    public String toString() {
        String drinkName = this.name;
        return drinkName + " × " + quantity + "\n" + blendChosen.toString();
    }


}


class PresetCoffee {


    public ArrayList<Blend> initBlends() {

    //    espressoBlend blend = new espressoBlend();
    ArrayList<Blend> allBlends = new ArrayList<>();

        CoffeeBean brazilCerrado = new CoffeeBean("Brazil", ProcessingMethod.NATURAL, 1200);
        CoffeeBean colombiaBruselas = new CoffeeBean("Colombia", ProcessingMethod.WASHED, 1400);
        CoffeeBean colombiaWushWush = new CoffeeBean("Colombia", ProcessingMethod.ANAEROBIC_NATURAL, 2000);
        CoffeeBean sumatraGayoMusara = new CoffeeBean("Indonesia", ProcessingMethod.WASHED, 1400);
        CoffeeBean javaLoa = new CoffeeBean("Indonesia", ProcessingMethod.ANAEROBIC_NATURAL, 1700);
        CoffeeBean sumatraKerinci = new CoffeeBean("Indonesia", ProcessingMethod.NATURAL, 1200);
        CoffeeBean javaFloresManggarai = new CoffeeBean("Indonesia", ProcessingMethod.WASHED, 1500);
        CoffeeBean brazilAltoCaparao = new CoffeeBean("Brazil", ProcessingMethod.NATURAL, 1100);
      

        Blend peanutBlend = new Blend("Peanut Butter", "Chocolatey and Caramelly");
        peanutBlend.coffeeBlend.add(brazilCerrado);
        peanutBlend.coffeeBlend.add(sumatraGayoMusara);
        peanutBlend.coffeeBlend.add(colombiaBruselas);
        allBlends.add(peanutBlend);


        Blend berryBlend = new Blend("Berry Bomb", "Juicy and Fruity");
        berryBlend.coffeeBlend.add(sumatraKerinci);
        berryBlend.coffeeBlend.add(javaLoa);
        berryBlend.coffeeBlend.add(colombiaWushWush);
        allBlends.add(berryBlend);

        Blend caramellyBlend = new Blend("Tropical Punch", "Full of tropical fruits");
        caramellyBlend.coffeeBlend.add(brazilAltoCaparao);
        caramellyBlend.coffeeBlend.add(javaFloresManggarai);
        allBlends.add(caramellyBlend);
   

        return allBlends;


    }

}