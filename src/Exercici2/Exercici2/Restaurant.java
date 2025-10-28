package Exercici2;

import java.util.Scanner;

public class Restaurant {
    private static Passaplats passaplats;

    public Restaurant(Passaplats passaplats){
        this.passaplats = passaplats;
    }
    public static int entrada(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Benvingut al Restaurant, per iniciar el programa indica un nombre enter entre 1 i 10:");
        int num;
        do{
            num = sc.nextInt();
            if(num > 10 || num < 1){
                System.out.println("El valor introduït és incorrecte, torna a provar:\n");
            }
        }while(num > 10 || num < 1);
        sc.close();
        return num;
    }
    public static void iniciRestaurant(){
        boolean ok = false;
        do{
            Thread cuiner1 = new Cuiner("Cuiner1",passaplats);
            Thread cuiner2 = new Cuiner("Cuiner2",passaplats);
            Thread cambrer1 = new Cambrer("Cuiner3",passaplats);
            Thread cambrer2 = new Cambrer("Cuiner4",passaplats);
            menu(cuiner1,cuiner2,cambrer1,cambrer2);
        }while (!ok);
    }
    public static boolean tancarRestaurant(){
        return true;
    }
    public static void menu(Thread cuiner1,Thread cuiner2,Thread cambrer1,Thread cambrer2){
        System.out.println("----- PASSAPLATS DEL RESTAURANT -----");
        cuiner1.start();
        cuiner2.start();
        cambrer1.start();
        cambrer2.start();
        
    }
}
