
enum coffeeType {
    LONG_BLACK,
    CAPPUCINO,
    FLAT_WHITE,
    MAGIC_LATTE,
    CORTADO

    // TODO : add prices as enum attributes
}

enum roastLevel {
    LIGHT,
    MEDIUM,
    MEDIUM_DARK,
    DARK
}

enum shotType {
    ESPRESSO,
    RISTRETTO,
    LUNGO
}

enum processingMethod {
    WASHED,
    NATURAL,
    ANAEROBIC_NATURAL,
    ANAEROBIC_WASHED,
    EXPERIMENTAL_WASHED,
    LUWAK
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

    String roastLevel;
    String origin; 
    String processingMethod;

    public String toString () {
        return this.roastLevel + " | Origin: " + this.origin + " | " + this.processingMethod; 
    }
}


abstract class menuOrder {

    int coffeeType; // Use enum
    int quantity;

} 


class espressoBasedOrder extends menuOrder {

    String shotType 

}