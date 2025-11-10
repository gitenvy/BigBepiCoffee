import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

enum EspressoBasedDrink {
    LONG_BLACK,
    CAPPUCINO,
    FLAT_WHITE,
    MAGIC_LATTE,
    CORTADO

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

abstract class CoffeeBean {

    RoastLevel roastLevel;
    String origin; 
    ProcessingMethod processingMethod;
    int altitude; // For example, 2000 MASL
    Species species;
    String supplier;




    
    public String toString () {
        return this.roastLevel + " | Origin: " + this.origin + " | " + this.processingMethod; 
    }
}



class espressoBlend {

    List<blendComponent> blend;


    espressoBlend() {
        List<blendComponent> blend = new ArrayList<>();
    }

    

    
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


class singleOriginBean {
    

   // Thinking how it will be different in the main code..


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

