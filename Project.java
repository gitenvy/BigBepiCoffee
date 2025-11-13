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

// enum RoastLevel {
//     LIGHT,
//     MEDIUM,
//     MEDIUM_DARK,
//     DARK
// }


enum ProcessingMethod {
    WASHED,
    NATURAL,
    ANAEROBIC_NATURAL,
    ANAEROBIC_WASHED  
}




class CoffeeShop {
    List<EspressoBasedOrder> orders = new ArrayList<>();

    public void addOrder(EspressoBasedOrder newOrder) {
        orders.add(newOrder);
    }

    public void removeOrder(EspressoBasedOrder oldOrder) {
        orders.remove(oldOrder);
    }

    public void displayOrdersAndPrice() {
        for (EspressoBasedOrder order : orders) {
            System.out.println(order);
        }
        System.out.println("Total Price : " + calculateTotalPrice());
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (EspressoBasedOrder order : orders) {
            total += order.getTotalPrice();
        }
        return total;
    }

    public EspressoBasedOrder findOrder(String orderName) {
        for (EspressoBasedOrder order : orders) {
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

    public List<EspressoBasedOrder> sortByPrice() {
        orders.sort(Comparator.comparing(EspressoBasedOrder::getTotalPrice));
        return orders;
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
    String name;


    Blend(String name) {
        this.coffeeBlend = new ArrayList<>();
        this.name = name;
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
      

       // ArrayList<CoffeeBean> peanutButterBlend = new ArrayList<>();
        Blend blend = new Blend("Peanut Butter");
        blend.coffeeBlend.add(brazilCerrado);
        blend.coffeeBlend.add(sumatraGayoMusara);
       blend.coffeeBlend.add(colombiaBruselas);
        allBlends.add(blend);
            
        
   

        return allBlends;


    }

}


abstract class MenuItem {

    int quantity;
    double price;
    //CoffeeBean bean;

    MenuItem(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
     //   this.bean = bean;
    }   

    public double getTotalPrice() {
        return quantity * price;
    }
    
} 




class EspressoBasedOrder extends MenuItem {

    EspressoBasedDrink espressoBasedOrder;
    Blend blendChosen;

    EspressoBasedOrder(EspressoBasedDrink espressoBasedOrder, int quantity, double price, Blend blendChosen) {
        super(quantity, price);
        this.espressoBasedOrder = espressoBasedOrder;
        this.blendChosen = blendChosen;
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
        super(quantity, price);
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

        Main main = new Main();
        
        int choice = In.nextInt();

        if (choice == 1) {
            main.drinkSubMenu();

        } else if (choice == 2) {
            //Run FBM

        } else if (choice == 3) {
         
        
        } else if (choice == 4) {

        } else {
            System.out.println("INVALID");
        }


    }

    public void drinkSubMenu() {

         CoffeeShop shop = new CoffeeShop();
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
                System.out.println(drink.name() + " - Sweetness: " + drink.getSweetness());
            }
        }

        if (choice == 3) {



              List<EspressoBasedDrink> espressoList = new ArrayList<>(List.of(EspressoBasedDrink.values()));
            espressoList.sort(EspressoBasedDrink.acidityComparator);

            for (EspressoBasedDrink drink : espressoList) {
                System.out.println(drink.name() + " Acidity- : " + drink.getAcidity());
            }
            
        }

        if (choice == 4) {

              List<EspressoBasedDrink> espressoList = new ArrayList<>(List.of(EspressoBasedDrink.values()));
            espressoList.sort(EspressoBasedDrink.bodyComparator);

            for (EspressoBasedDrink drink : espressoList) {
                System.out.println(drink.name() + " - Strength: " + drink.getBody());
            }
        }


        System.out.println("Which drink are you going to choose? Write down the name of the drink.");

        String drinkOrder = In.nextLine().toLowerCase();

        System.out.println("How many to order?");

        int orderQuantity = In.nextInt();

         Main main = new Main();

        Blend blendChosen = main.subMenuChooseBlendBean();

       
        // TODO ask quantitiy, 
        if (drinkOrder.equals("long black")) {
            shop.orders.add(new EspressoBasedOrder(EspressoBasedDrink.LONG_BLACK, orderQuantity, EspressoBasedDrink.LONG_BLACK.getPrice(), blendChosen));
        }

        // TODO probbaly do the same for other orders
        else if (drinkOrder.equals("cappucino")) {
            shop.orders.add(new EspressoBasedOrder(EspressoBasedDrink.CAPPUCINO, orderQuantity, EspressoBasedDrink.CAPPUCINO.getPrice(), blendChosen));
        }
        else if (drinkOrder.equals("flat white")) {
            shop.orders.add(new EspressoBasedOrder(EspressoBasedDrink.FLAT_WHITE, orderQuantity, EspressoBasedDrink.FLAT_WHITE.getPrice(), blendChosen));
        }
        else if (drinkOrder.equals("magic latte")) {
            shop.orders.add(new EspressoBasedOrder(EspressoBasedDrink.MAGIC_LATTE, orderQuantity, EspressoBasedDrink.MAGIC_LATTE.getPrice(), blendChosen));
        }


        



    }

    public Blend subMenuChooseBlendBean () {
        PresetCoffee preset = new PresetCoffee();
        ArrayList<Blend> allBlends = preset.initBlends();

        System.out.println("\nChoose your coffee blend:");
        int index = 1;

  
        for (Blend blend : allBlends) {
            
                System.out.println(index + ". " + blend.name);
                index++;
            
        }

        int choice = In.nextInt();

    // if choice out of bound do smth TODO
        Blend selectedBean = allBlends.get(choice - 1);
        System.out.println("You chose: " + selectedBean.name);
        // display blend components, and their attributes! TODO

        System.out.println("\nThis blend contains:");
        for (CoffeeBean bean : selectedBean.coffeeBlend) {
            System.out.println("- " + bean.origin + " | " + bean.processingMethod + " | " + bean.altitude + " MASL");
        }



        return selectedBean;
    }
}
