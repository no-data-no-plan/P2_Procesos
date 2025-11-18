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
        
        Thread cuiner1 = new Cuiner("Cuiner1",passaplats);
        Thread cuiner2 = new Cuiner("Cuiner2",passaplats);
        Thread cambrer1 = new Cambrer("Cambrer1",passaplats);
        Thread cambrer2 = new Cambrer("Cambrer2",passaplats);
        //Iniciar el passaplats
        System.out.println("\n----- PASSAPLATS DEL RESTAURANT -----");
        //Iniciar cuiners i cambrers
        cuiner1.start();
        cuiner2.start();
        cambrer1.start();
        cambrer2.start();
        try{
            // cuiner1.join(); //Espera a que acabin els cuiners
            // cuiner2.join();
            cambrer1.join(); //Detenir als cambrers
            cambrer2.join();
            
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public static boolean tancarRestaurant(){
        System.out.println("\n----- FI DE SERVEI -----");
        System.out.println("Tancant Restaurant...");
        return true;
    }
}
