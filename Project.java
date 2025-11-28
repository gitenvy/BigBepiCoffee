import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


interface Drink {
    double getPrice();
    int getSweetness();


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

enum ProcessingMethod {
    WASHED,
    NATURAL,
    ANAEROBIC_NATURAL,
}




class CoffeeShop {
    private List<CoffeeMenuItem> orders = new ArrayList<>();

    public List<CoffeeMenuItem> getOrders() {
        return orders;
    }

    public void setOrders(List<CoffeeMenuItem> orders) {
        this.orders = orders;
    }

    public void addOrder(CoffeeMenuItem newOrder) {
        orders.add(newOrder);
    }

    public void removeOrder(String orderName) {
        CoffeeMenuItem orderToRemove = null;
        for (CoffeeMenuItem order : orders) {
            if (order.getName().equalsIgnoreCase(orderName)) {
                orderToRemove = order;
                break;
            }
        }
        if (orderToRemove != null) {
            orders.remove(orderToRemove);
            System.out.println("Order removed - " + orderToRemove);
        } else {
            System.out.println("Order not found.");
        }
    }

    public void displayOrdersAndPrice() {
        for (CoffeeMenuItem order : orders) {
            System.out.println(order);
        }
        if (orders.isEmpty()) {
            System.out.println("No orders placed yet.");
        } else {
        System.out.println("Total Price : $" + calculateTotalPrice());
        }
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (CoffeeMenuItem order : orders) {
            total += order.getTotalPrice();
        }
        return total;
    }

    public CoffeeMenuItem findOrder(String orderName) {
        for (CoffeeMenuItem order : orders) {
            if (order.getName().equalsIgnoreCase(orderName)) {
                return order;
            }
        }
        return null;
    }

    public CoffeeMenuItem findOrder(int quantity) {
        for (CoffeeMenuItem order : orders) {
            if (order.getQuantity() == quantity) {
                return order;
            }
        }
        return null;
    }
    

    public List<EspressoBasedOrder> getEspressoBasedOrders() {
        List<EspressoBasedOrder> espressoOrders = new ArrayList<>();

        for (CoffeeMenuItem order : orders) {
            if (order instanceof EspressoBasedOrder) {
                espressoOrders.add((EspressoBasedOrder) order);
            }
        }
        if (espressoOrders.isEmpty()) {
            System.out.println("No espresso-based orders found.");
        }
        return espressoOrders;
    }


    public List<FilterBasedOrder> getFilterBasedOrders() {
        List<FilterBasedOrder> filterOrders = new ArrayList<>();
        for (CoffeeMenuItem order : orders) {
            if (order instanceof FilterBasedOrder) {
                filterOrders.add((FilterBasedOrder) order);
            } 
        }
        if (filterOrders.isEmpty()) {
            System.out.println("No filter-based orders found.");
        }
        return filterOrders;
    }

    public List<CoffeeMenuItem> sortByPrice() {
        orders.sort(Comparator.comparing(CoffeeMenuItem::getTotalPrice));
        return orders;
    }

    public void viewWholeMenu() {
        List<EspressoBasedDrink> espressoMenu = Arrays.asList(EspressoBasedDrink.values());
        List<FilterBasedDrink> filterMenu = Arrays.asList(FilterBasedDrink.values());

    	System.out.println("---------- Espresso Menu -----------");
    	System.out.println("---------------------------");
        for(EspressoBasedDrink espressoItem : espressoMenu) {
        	System.out.println(espressoItem.name() + " | Price: $" + espressoItem.getPrice());
        }

		System.out.println("---------- Filter Menu -----------");
    	System.out.println("---------------------------");

        for(FilterBasedDrink filterItem : filterMenu) {
        	System.out.println(filterItem.name() + " | Price: $" + filterItem.getPrice());
       	}
		System.out.println("\n\n");
    }

	

}


class CoffeeBean {

    private String origin;
    ProcessingMethod processingMethod;
    private int altitude; 

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    CoffeeBean(String origin, ProcessingMethod processingMethod, int altitude) {
        this.origin =  origin;
        this.processingMethod = processingMethod;
        this.altitude = altitude;
    }


   @Override
   public String toString() {
    return "Origin: " + origin + ", Processing Method: " + processingMethod + ", Altitude: " + altitude + "]";
   }

    
}

class Blend {

    List<CoffeeBean> coffeeBlend;
    private String name;
    private String tasteProfile;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getTasteProfile() {
        return tasteProfile;
    }

    public void setTasteProfile(String tasteProfile) {
        this.tasteProfile = tasteProfile;
    }

  


    Blend(String name, String tasteProfile) {
        this.coffeeBlend = new ArrayList<>();
        this.name = name;
        this.tasteProfile = tasteProfile;
    }

    public String toString() {
        return "Blend Name: " + name + ", Beans: " + coffeeBlend.toString() + " taste profile" + this.tasteProfile;
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
        return "Order: " + espressoBasedOrder.name() + ", Quantity: " + quantity + ", Blend: " + blendChosen.getName() + ", Total Price: $" + getTotalPrice();
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
        return "Order: " + filterBasedOrder.name() + ", Quantity: " + quantity + ", Blend: " + blendChosen.getName() + ", Total Price: $" + getTotalPrice();
    }

}




class MainMenu {

    public static void main(String[] args) {

        CoffeeShop shop = new CoffeeShop();
        MainMenu main = new MainMenu();

        System.out.println("Welcome to the Coffee Shop!");


        while (true) {
        
      
        System.out.println("What would you like to do?");
        System.out.println("1. View Whole Menu");
        System.out.println("2. Buy Espresso Based Item");
        System.out.println("3. Buy Filter Based Item");
        System.out.println("4. View Orders");
        System.out.println("5. Remove an Order");
        System.out.println("6. Find a Specific Order by Name");
        System.out.println("7. Find your Orders by Quantity");
        System.out.println("8. View Espresso Based Orders");
        System.out.println("9. View Filter Based Orders");
        System.out.println("10. Checkout and Pay (Exit)");
        System.out.println("\n");
        
        System.out.println("Please enter the number of your choice: ");
        System.out.println("---------------------");
        System.out.println("\n");
        int choice = In.nextInt();

        if (choice == 1) {
            shop.viewWholeMenu();
        }

        else if (choice == 2) {
            main.espressoSubMenu(shop);
            continue;

        } else if (choice == 3) {
            main.filterBasedSubMenu(shop);
            continue;

        } else if (choice == 4) {
            shop.displayOrdersAndPrice();
            continue;
        
        } else if (choice == 5) {
            System.out.println("Enter the name of the order to remove:");
            String orderName = In.nextLine();
            shop.removeOrder(orderName);
            continue;

        } else if (choice == 6) {
            System.out.println("Enter the name of the order to find:");
            String orderName = In.nextLine();
            CoffeeMenuItem foundOrder = shop.findOrder(orderName);
            if (foundOrder != null) {
                System.out.println("Order Found - " + foundOrder);
            } else {
                System.out.println("Order not found.");
            }
            continue;

        } else if (choice == 7) {
            System.out.println("Enter the quantity of the order to find:");
            int quantity = In.nextInt();
            CoffeeMenuItem foundOrder = shop.findOrder(quantity);
            if (foundOrder != null) {
                System.out.println("Order Found - " + foundOrder);
            } else {
                System.out.println("Order not found.");
            }
            continue;

        } else if (choice == 8) {
            List<EspressoBasedOrder> espressoOrders = shop.getEspressoBasedOrders();
            for (EspressoBasedOrder order : espressoOrders) {
                System.out.println(order);
            }

        } else if (choice == 9) {
            List<FilterBasedOrder> filterOrders = shop.getFilterBasedOrders();
            for (FilterBasedOrder order : filterOrders) {
                System.out.println(order);
            }

        } else if (choice == 10) {
            double totalPrice = shop.calculateTotalPrice();
            System.out.println("Your total price is: $" + totalPrice);
            System.out.println("Thank you for visiting!");
            break;



        }
      
        
        
        
        else if (choice > 10) {
            System.out.println("Invalid choice. Please try again.");
        }

        }
    }
            

    public void espressoSubMenu(CoffeeShop shop) {

        System.out.println("You grab the menu. What kind of espresso-based drink are you looking for?");
        System.out.println("1. Low price");
        System.out.println("2. High sweetness");
        System.out.println("3. High fruitiness");
        System.out.println("4. Mouthfeel/Body");
        System.out.println("5. Most milky");
        System.out.println("---------------------");
        System.out.println("Please enter the number of your choice: ");
        System.out.println("\n");

        int choice = In.nextInt();
        if (choice == 1) {

        List<EspressoBasedDrink> espressoList = new ArrayList<>(List.of(EspressoBasedDrink.values()));
        espressoList.sort(EspressoBasedDrink.priceComparator);

        for (EspressoBasedDrink drink : espressoList) {
        System.out.println(drink.name() + " - Price: $" + drink.getPrice());
        }

        }
        if (choice == 2) {
            //sort by sweetness
             List<EspressoBasedDrink> espressoList = new ArrayList<>(List.of(EspressoBasedDrink.values()));
            espressoList.sort(EspressoBasedDrink.sweetnessComparator.reversed());

            for (EspressoBasedDrink drink : espressoList) {
                System.out.println(drink.name() + " - Sweetness: " + drink.getSweetness());
            }
        }

        if (choice == 3) {



              List<EspressoBasedDrink> espressoList = new ArrayList<>(List.of(EspressoBasedDrink.values()));
            espressoList.sort(EspressoBasedDrink.acidityComparator.reversed());

            for (EspressoBasedDrink drink : espressoList) {
                System.out.println(drink.name() + " - Acidity: " + drink.getAcidity());
            }
            
        }

        if (choice == 4) {

              List<EspressoBasedDrink> espressoList = new ArrayList<>(List.of(EspressoBasedDrink.values()));
            espressoList.sort(EspressoBasedDrink.bodyComparator.reversed());

            for (EspressoBasedDrink drink : espressoList) {
                System.out.println(drink.name() + " - Strength: " + drink.getBody());
            }
        }

        if (choice == 5) {

              List<EspressoBasedDrink> espressoList = new ArrayList<>(List.of(EspressoBasedDrink.values()));
            espressoList.sort(EspressoBasedDrink.intensityInMilkComparator.reversed());

            for (EspressoBasedDrink drink : espressoList) {
                System.out.println(drink.name() + " - Milk Intensity: " + drink.getIntensityInMilk());
            }
        }


        System.out.println("Which drink are you going to choose? Write down the name of the drink.");

        String drinkOrder = In.nextLine().toLowerCase();

        System.out.println("How many to order?");

        int orderQuantity = In.nextInt();
        System.out.println("\n");

        Blend blendChosen = subMenuChooseBlendBean();

       
        
        if (drinkOrder.equals("long black")) {
            shop.getOrders().add(new EspressoBasedOrder("Long Black", EspressoBasedDrink.LONG_BLACK, orderQuantity, EspressoBasedDrink.LONG_BLACK.getPrice(), blendChosen));
        }

       
        else if (drinkOrder.equals("cappucino")) {
            shop.getOrders().add(new EspressoBasedOrder("Cappucino", EspressoBasedDrink.CAPPUCINO, orderQuantity, EspressoBasedDrink.CAPPUCINO.getPrice(), blendChosen));
        }
        else if (drinkOrder.equals("flat white")) {
            shop.getOrders().add(new EspressoBasedOrder("Flat White", EspressoBasedDrink.FLAT_WHITE, orderQuantity, EspressoBasedDrink.FLAT_WHITE.getPrice(), blendChosen));
        }
        else if (drinkOrder.equals("magic latte")) {
            shop.getOrders().add(new EspressoBasedOrder("Magic Latte", EspressoBasedDrink.MAGIC_LATTE, orderQuantity, EspressoBasedDrink.MAGIC_LATTE.getPrice(), blendChosen));
        }


        



    }

    public Blend subMenuChooseBlendBean () {
        PresetCoffee preset = new PresetCoffee();
        ArrayList<Blend> allBlends = preset.initBlends();

        System.out.println("Choose your coffee blend:\n Write down the number you want to pick.");
        int index = 1;

  
        for (Blend blend : allBlends) {
            
                System.out.println(index + ". " + blend.getName() + " Taste Profile: " + blend.getTasteProfile());
                index++;
            
        }

        int choice = In.nextInt();
        System.out.println("\n");

   
        Blend selectedBlend = allBlends.get(choice - 1);
        System.out.println("You chose: " + selectedBlend.getName());
      

        System.out.println("\nThis blend contains:");
        for (CoffeeBean bean : selectedBlend.coffeeBlend) {
            System.out.println("- " + bean.getOrigin() + " | " + bean.processingMethod + " | " + bean.getAltitude() + " MASL");
        }

        System.out.println("\n");
        



        return selectedBlend;
    }

    public void filterBasedSubMenu(CoffeeShop shop) {
        System.out.println("You grab the menu. What kind of filter-based drink are you looking for?");
        System.out.println("1. Low price");
        System.out.println("2. High sweetness");
        System.out.println("3. High Clarity");
        System.out.println("Enter the number of your choice: ");

        int choice = In.nextInt();
        System.out.println("\n");
        if (choice == 1) {

        List<FilterBasedDrink> filterList = new ArrayList<>(List.of(FilterBasedDrink.values()));
        filterList.sort(FilterBasedDrink.priceComparator);

        for (FilterBasedDrink drink : filterList) {
        System.out.println(drink.name() + " - Price: $" + drink.getPrice());
        }
        }

        if (choice == 2) {
            List<FilterBasedDrink> filterList = new ArrayList<>(List.of(FilterBasedDrink.values()));
            filterList.sort(FilterBasedDrink.sweetnessComparator.reversed());

            for (FilterBasedDrink drink : filterList) {
                System.out.println(drink.name() + " - Sweetness: " + drink.getSweetness());
            }
        }


        if (choice == 3) {
            List<FilterBasedDrink> filterList = new ArrayList<>(List.of(FilterBasedDrink.values()));
            filterList.sort(FilterBasedDrink.clarityComparator.reversed());

            for (FilterBasedDrink drink : filterList) {
                System.out.println(drink.name() + " - Clarity: " + drink.getClarity());
            }
        }

        System.out.println("Which drink are you going to choose? Write down the name of the drink.");

        String drinkOrder = In.nextLine().toLowerCase();

        System.out.println("How many to order?");

        int orderQuantity = In.nextInt();
        System.out.println("\n");

        Blend blendChosen = subMenuChooseBlendBean();

       
       
        if (drinkOrder.equals("v60")) {
            shop.getOrders().add(new FilterBasedOrder("V60", FilterBasedDrink.V60, orderQuantity, FilterBasedDrink.V60.getPrice(), blendChosen));
            System.out.println("\n");
        }

    
        else if (drinkOrder.equals("french press")) {
            shop.getOrders().add(new FilterBasedOrder("French Press", FilterBasedDrink.FRENCH_PRESS, orderQuantity, FilterBasedDrink.FRENCH_PRESS.getPrice(), blendChosen));
            System.out.println("\n");
        }

        else if (drinkOrder.equals("cold brew")) {
            shop.getOrders().add(new FilterBasedOrder("Cold Brew", FilterBasedDrink.COLD_BREW, orderQuantity, FilterBasedDrink.COLD_BREW.getPrice(), blendChosen));
            System.out.println("\n");
    }

    }
}
