import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

//hello world

enum EspressoBasedDrink {


    LONG_BLACK(1, 4, 3, 0),
    CAPPUCINO(3, 5, 3, 3),
    FLAT_WHITE(4, 5, 4, 4),
    MAGIC_LATTE(5, 5, 5, 5);

// TODO MAKE MENU ITEMS
    private int body;

    private double price;
    private int sweetness;
    private int intensityInMilk;

    EspressoBasedDrink(int body, double price, int sweetness, int intensityInMilk) {
        this.body = body;
        this.price = price;
        this.sweetness = sweetness;
        this.intensityInMilk = intensityInMilk;
    }

    public int getBody () {
        return this.body;
    }

    public double getPrice () {
        return this.price;
    }

    public int getSweetness () {
        return this.sweetness;
    }

    public int getIntensityInMilk () {
        return this.intensityInMilk;
    }

    static final Comparator<EspressoBasedDrink> bodyComparator = Comparator.comparing(EspressoBasedDrink::getBody);
    static final Comparator<EspressoBasedDrink> priceComparator = Comparator.comparing(EspressoBasedDrink::getPrice);
    static final Comparator<EspressoBasedDrink> sweetnessComparator = Comparator.comparing(EspressoBasedDrink::getSweetness);
    static final Comparator<EspressoBasedDrink> intensityInMilkComparator = Comparator.comparing(EspressoBasedDrink::getIntensityInMilk);




    // TODO : add prices as enum attributes
}

enum RoastLevel {
    LIGHT,
    MEDIUM,
    MEDIUM_DARK,
    DARK
}

enum ShotType {
    ESPRESSO,
    RISTRETTO,
    LUNGO
}

enum ProcessingMethod {
    WASHED,
    NATURAL,
    ANAEROBIC_NATURAL,
    ANAEROBIC_WASHED,
    EXPERIMENTAL_WASHED,
    LUWAK
}

enum Species {
    ARABICA,
    ROBUSTA,
    LIBERICA
}


class CoffeeShop {


    private String espressoMachine;
    private int seatingCapacity;
    private int numberOfStaffs;

}


class Barista {

   // private 
}

class CoffeeBean {

    public CoffeeBean(RoastLevel roastLevel, ProcessingMethod processingMethod, int altitude) {
        this.roastLevel = roastLevel;
        this.processingMethod = processingMethod;
        this.altitude = altitude;
    }



    RoastLevel roastLevel;
   // String origin; 
    ProcessingMethod processingMethod;
    int altitude; // For example, 2000 MASL
    //Species species;
   // String supplier;
   //String varietal; // TODO make this an arraylist?

   

   @Override
   public String toString() {
    return "CoffeeBean [roastLevel=" + roastLevel + ", processingMethod=" + processingMethod + ", altitude=" + altitude
             + "]";
   }

    
}



class espressoBlend {

    List<blendComponent> coffeeBlend;
    ArrayList<String> varietals;
    ArrayList<String> producers;
    String blendName;



    espressoBlend(String blendName, ArrayList<blendComponent> coffeeBlend) {
        this.coffeeBlend = new ArrayList<>();
        this.blendName = blendName;
        this.coffeeBlend = coffeeBlend;
    }


  // add to blend arraylist 




}




class blendComponent {
    

    CoffeeBean coffeeBean;
    double percentage;

    blendComponent(CoffeeBean newCoffeeBean, double percentage) {
        this.coffeeBean = newCoffeeBean;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "blendComponent [coffeeBean=" + coffeeBean + ", percentage=" + percentage + "]";
    }


    
}

// Initialise all coffee blends, and return san arraylist of blends of espressoBlend objects?
class PresetCoffee {


      public ArrayList<espressoBlend> initBlends() {

    //    espressoBlend blend = new espressoBlend();

        CoffeeBean brazilCerrado = new CoffeeBean(RoastLevel.MEDIUM, ProcessingMethod.NATURAL, 1200);
        CoffeeBean colombiaBruselas = new CoffeeBean(RoastLevel.MEDIUM, ProcessingMethod.WASHED, 1400);
        CoffeeBean colombiaWushWush = new CoffeeBean(RoastLevel.LIGHT, ProcessingMethod.ANAEROBIC_NATURAL, 2000);
        CoffeeBean sumatraGayoMusara = new CoffeeBean(RoastLevel.MEDIUM, ProcessingMethod.WASHED, 1400);
        CoffeeBean javaLoa = new CoffeeBean(RoastLevel.MEDIUM_DARK, ProcessingMethod.ANAEROBIC_NATURAL, 1700);
        CoffeeBean sumatraKerinci = new CoffeeBean(RoastLevel.LIGHT, ProcessingMethod.NATURAL, 1200);
       // CoffeeBean tapanuliSidra = new CoffeeBean(null, null, 0)


        blendComponent peanutButterBlendComponent = new blendComponent(brazilCerrado, 20); // Coffee bean OBJECT AND PERCENTAGE
        blendComponent peanutButterBlendComponent2 = new blendComponent(sumatraGayoMusara, 40);
        blendComponent peanutButterBlendComponent3 = new blendComponent(colombiaBruselas, 40);

        ArrayList<blendComponent> newBlend = new ArrayList<>();
        newBlend.add(peanutButterBlendComponent);
        newBlend.add(peanutButterBlendComponent2);
        newBlend.add(peanutButterBlendComponent3);
        espressoBlend peanutButterBlend = new espressoBlend( "Peanut Butter", newBlend);

        
        
        
        
        
        
        ArrayList<blendComponent> newBlend2 = new ArrayList<>();
        newBlend2.add(new blendComponent(sumatraKerinci, 30));

        //return newBlend;


    }

}

class singleOriginBean {
    
    CoffeeBean coffeeBean;
    String region;
    String farm;
    String producerName;
    String cropYear;



}





abstract class menuItem {

    int quantity;
    int price;

} 




class espressoBasedOrder extends menuItem {

    ShotType shotType;
    EspressoBasedDrink espressoBasedOrder;

    // Store things like strength, bitterness in the enum!


}

class filterBasedOrder extends menuItem {

    int dripper;
    int paperFilter;
    
}








class main {

    public static void main(String[] args) {

        PresetCoffee presetCoffee = new PresetCoffee();
        presetCoffee.initBlends();
        // Initialise blends.
        
        System.out.println("Welcome to ENTER NAME OF COFFEE SHOP!");

        System.out.println("What kind of drink do you want to order?");
        System.out.println("1. Espresso Based");
        System.out.println("2. Filter Based");
        System.out.println("3. Matcha");

        int choice = In.nextInt();

        if (choice == 1) {

        }


    }

    public void espressoSubMenu () {

        System.out.println("You grab the menu. What kind of espresso-based drink are you looking for?");
        System.out.println("1. High Body");
        System.out.println("2. High sweetness");
        System.out.println("3. High fruitiness");


        int choice = In.nextInt();
        if (choice == 1) {
            // sort by body
         //   Collections.sort()            
        }

        if (choice == 2) {
            //sort by sweetness
        }

        if (choice == 3) {
            // MAKE FUIRTNESS
            //SORT 
        }
    }



    
}