import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


interface Drink {
    double getPrice();
    int getSweetness();


}

enum EspressoBasedDrink implements Drink {


    LONG_BLACK(1, 4, 3, 0, 5),
    CAPPUCINO(3, 5, 3, 3, 2),
    FLAT_WHITE(4, 5, 4, 4, 3),
    MAGIC_LATTE(5, 5, 5, 5, 5);


    private int body;
    private double price;
    private int sweetness;
    private int intensityInMilk;
    private int acidity;

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



    // TODO : add prices as enum attributes
}

enum FilterBasedDrink implements Drink {

    V60(3, 2),
    FRENCH_PRESS(4,3),
    COLD_BREW(5, 4);

    private double price;
    private int sweetness;

    FilterBasedDrink(double price, int sweetness) {
        this.price = price;
        this.sweetness = sweetness;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public int getSweetness() { 
        return this.sweetness;
    }

    static final Comparator<FilterBasedDrink> priceComparator = Comparator.comparing(FilterBasedDrink::getPrice);
    static final Comparator<FilterBasedDrink> sweetnessComparator = Comparator.comparing(FilterBasedDrink::getSweetness);


}

enum RoastLevel {
    LIGHT,
    MEDIUM,
    MEDIUM_DARK,
    DARK
}


enum ProcessingMethod {
    WASHED,
    NATURAL,
    ANAEROBIC_NATURAL,
    ANAEROBIC_WASHED  
}

enum Species {
    ARABICA,
    ROBUSTA,
    LIBERICA
}


class CoffeeShop {
    List<MenuItem> orders = new ArrayList<>();

    public void addOrder(MenuItem newOrder) {
        orders.add(newOrder);
    }

    public void removeOrder(MenuItem oldOrder) {
        orders.remove(oldOrder);
    }

    public void displayOrdersAndPrice() {
        for (MenuItem order : orders) {
            System.out.println(order);
        }
        System.out.println("Total Price : " + calculateTotalPrice());
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (MenuItem order : orders) {
            total += order.getTotalPrice();
        }
        return total;
    }

    public MenuItem findOrder(String orderName) {
        for (MenuItem order : orders) {
            if (order.toString().equals(orderName)) {
                return order;
            }
        }
        return null;
    }

    public List<EspressoBasedOrder> getEspressoBasedOrders() {
        List<EspressoBasedOrder> espressoOrders = new ArrayList<>();
        for (MenuItem order : orders) {
            if (order instanceof EspressoBasedOrder) {
                espressoOrders.add((EspressoBasedOrder) order);
            }
        }
        return espressoOrders;
    }


    public List<FilterBasedOrder> getFilterBasedOrders() {
        List<FilterBasedOrder> filterOrders = new ArrayList<>();
        for (MenuItem order : orders) {
            if (order instanceof FilterBasedOrder) {
                filterOrders.add((FilterBasedOrder) order);
            }
        }
        return filterOrders;
    }

    public List<MenuItem> sortByPrice() {
        orders.sort(Comparator.comparing(MenuItem::getTotalPrice));
        return orders;
    }

}

class SingleOriginBean extends CoffeeBean {

    String farmName;
    String producerName;

    @Override
    public String toString() {
        return "SingleOriginBean [farmName=" + farmName + ", producerName=" + producerName + "]";
    }
    SingleOriginBean(String origin, ProcessingMethod processingMethod, int altitude, String farmName, String producerName) {
        super(origin, processingMethod, altitude);
        this.farmName = farmName;
        this.producerName = producerName;
    }
    

   

    
}



class CoffeeBean {

    //RoastLevel roastLevel;
    String origin;
    ProcessingMethod processingMethod;
    int altitude; // For example, 2000 MASL
   // String varietal;

    CoffeeBean(String origin, ProcessingMethod processingMethod, int altitude) {
        this.origin =  origin;
      //  this.roastLevel = roastLevel;
        this.processingMethod = processingMethod;
        this.altitude = altitude;
    //    this.varietal = varietal;
    }

   @Override
   public String toString() {
    return "Origin: " + origin + ", Processing Method: " + processingMethod + ", Altitude: " + altitude + "]";
   }

    
}

class Blend {

    List<CoffeeBean> coffeeBlend;


    Blend() {
        this.coffeeBlend = new ArrayList<>();
    }


    





}





class PresetCoffee {


      public ArrayList<ArrayList<CoffeeBean>> initBlends() {

    //    espressoBlend blend = new espressoBlend();
    ArrayList<ArrayList<CoffeeBean>> allBlends = new ArrayList<>();

        CoffeeBean brazilCerrado = new CoffeeBean("Brazil", ProcessingMethod.NATURAL, 1200);
        CoffeeBean colombiaBruselas = new CoffeeBean("Colombia", ProcessingMethod.WASHED, 1400);
        CoffeeBean colombiaWushWush = new CoffeeBean("Colombia", ProcessingMethod.ANAEROBIC_NATURAL, 2000);
        CoffeeBean sumatraGayoMusara = new CoffeeBean("Indonesia", ProcessingMethod.WASHED, 1400);
        CoffeeBean javaLoa = new CoffeeBean("Indonesia", ProcessingMethod.ANAEROBIC_NATURAL, 1700);
        CoffeeBean sumatraKerinci = new CoffeeBean("Indonesia", ProcessingMethod.NATURAL, 1200);
      

        ArrayList<CoffeeBean> peanutButterBlend = new ArrayList<>();
        peanutButterBlend.add(brazilCerrado);
        peanutButterBlend.add(sumatraGayoMusara);
        peanutButterBlend.add(colombiaBruselas);
   
        allBlends.add(peanutButterBlend);
            
        
   

        return allBlends;


    }

}


abstract class MenuItem {

    int quantity;
    double price;
    CoffeeBean bean;

    MenuItem(int quantity, double price, CoffeeBean bean) {
        this.quantity = quantity;
        this.price = price;
        this.bean = bean;
    }   

    public double getTotalPrice() {
        return quantity * price;
    }
    
} 




class EspressoBasedOrder extends MenuItem {

    EspressoBasedDrink espressoBasedOrder;

    EspressoBasedOrder(EspressoBasedDrink espressoBasedOrder, int quantity, double price, CoffeeBean bean) {
        super(quantity, price, bean);
        this.espressoBasedOrder = espressoBasedOrder;
    }

    public EspressoBasedDrink getEspressoBasedOrder() {
        return this.espressoBasedOrder;
    }

    public double getTotalPrice() {
        return super.getTotalPrice();
    }


}

class FilterBasedOrder extends MenuItem {

    FilterBasedDrink filterBasedOrder;

    FilterBasedOrder(FilterBasedDrink filterBasedOrder,  int quantity, double price, CoffeeBean bean) {
        super(quantity, price, bean);
        this.filterBasedOrder = filterBasedOrder;
    }

    public FilterBasedDrink getFilterBasedOrder() {
        return this.filterBasedOrder;  
    }

    public double getTotalPrice() {
        return super.getTotalPrice();
    }

}




class Main {

    public static void main(String[] args) {
        
        System.out.println("Welcome!");

        System.out.println("What would you like to do?");
        System.out.println("1. View Espresso Based Menu");
        System.out.println("2. View Filter Based Menu");
        System.out.println("3. View Orders");
        System.out.println("4. Remove an Order");

        CoffeeShop shop = new CoffeeShop();
        
        int choice = In.nextInt();

        if (choice == 1) {
            //Run drinkSubMenu(

        } else if (choice == 2) {
            //Run FBM

        } else if (choice == 3) {
         
        
        } else if (choice == 4) {

        } else {
            System.out.println("INVALID");
        }


    }

    public void drinkSubMenu() {

        System.out.println("You grab the menu. What kind of espresso-based drink are you looking for?");
        System.out.println("1. High Body");
        System.out.println("2. High sweetness");
        System.out.println("3. High fruitiness");


        int choice = In.nextInt();
        if (choice == 1) {

        List<EspressoBasedDrink> espressoList = new ArrayList<>(List.of(EspressoBasedDrink.values()));
        espressoList.sort(EspressoBasedDrink.priceComparator);

        for (EspressoBasedDrink drink : espressoList) {
        System.out.println(drink.name() + " - Price: $" + drink.getPrice());
        }
// MAKE SUBMENU TO CHOOSE BEQN THEN RETURN TO MAIN MENU

        }
        if (choice == 2) {
            //sort by sweetness
             List<EspressoBasedDrink> espressoList = new ArrayList<>(List.of(EspressoBasedDrink.values()));
            espressoList.sort(EspressoBasedDrink.sweetnessComparator);

            for (EspressoBasedDrink drink : espressoList) {
                System.out.println(drink.name() + " - Sweetness: " + drink.getPrice());
            }
        }

        if (choice == 3) {



              List<EspressoBasedDrink> espressoList = new ArrayList<>(List.of(EspressoBasedDrink.values()));
            espressoList.sort(EspressoBasedDrink.acidityComparator);

            for (EspressoBasedDrink drink : espressoList) {
                System.out.println(drink.name() + " Acidity- : " + drink.getPrice());
            }
            
        }

        if (choice == 4) {

              List<EspressoBasedDrink> espressoList = new ArrayList<>(List.of(EspressoBasedDrink.values()));
            espressoList.sort(EspressoBasedDrink.bodyComparator);

            for (EspressoBasedDrink drink : espressoList) {
                System.out.println(drink.name() + " - Strength: " + drink.getPrice());
            }
        }

        



    }

    public ArrayList<CoffeeBean> subMenuChooseBlendBean () {
        PresetCoffee preset = new PresetCoffee();
        ArrayList<ArrayList<CoffeeBean>> allBlends = preset.initBlends();

        System.out.println("\nChoose your coffee blend:");
        int index = 1;

  
        for (ArrayList<CoffeeBean> blend : allBlends) {
            for (CoffeeBean bean : blend) {
                System.out.println(index + ". " + bean.origin + " " + bean.processingMethod + " " + bean.altitude + " MASL)");
                index++;
            }
        }

        int choice = In.nextInt();

    // if choice out of bound do smth TODO
        ArrayList<CoffeeBean> selectedBean = allBlends.get(choice - 1);
        System.out.println("You chose: " + selectedBean);
        // display blend components, and their attributes! TODO

        System.out.println("\nThis blend contains:");
        for (CoffeeBean bean : selectedBean) {
            System.out.println("- " + bean.origin + " | " + bean.processingMethod + " | " + bean.altitude + " MASL");
        }



        return selectedBean;
    }
}
