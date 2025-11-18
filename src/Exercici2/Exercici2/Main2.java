package Exercici2;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("----- RESTAURANT -----\n");
        
        boolean programa = false;
        do{
            Passaplats pasa = new Passaplats(Restaurant.entrada());
            System.out.println("\nCreant passaplats...");
            System.out.println("...");
            Restaurant restaurant = new Restaurant(pasa);
            System.out.println("\nEntrant al restaurant...");
            System.out.println("...\n");
            restaurant.iniciRestaurant();
            programa = restaurant.tancarRestaurant();
            System.out.println("\n----- RESTAURANT TANCAT -----\n");
        }while(!programa);
    }
}