import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        System.out.println("----- RESTAURANT -----\n");
        Scanner sc = new Scanner(System.in);
        boolean programa = false;
        do{
            System.out.println("Benvingut al Restaurant, per iniciar el programa indica un nombre enter entre 1 i 10:");
            int num = sc.nextInt();
            Passaplats pasa = new Passaplats(num);
            Restaurant restaurant = new Restaurant(pasa);
            restaurant.iniciRestaurant();
        }while(!programa);
    }
}

