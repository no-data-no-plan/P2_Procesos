package Exercici2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cuiner extends Thread{
    private String nom;
    private Passaplats passaplats;
    public Cuiner(String nom,Passaplats passaplats){
        this.nom = nom;
        this.passaplats = passaplats;
    }
    @Override
    public void run() {
        // Este código se ejecutará cuando hagas cuiner.start()
        
        try {
            //farem que cada cuiner faci 5 plats 
            for (int i = 0; i < 5; i++){
                System.out.println(nom + " está cocinando...");
                Thread.sleep(2000);
                Plat plat = cuinar();
                System.out.println(nom + " ha preparado: " + plat.getNom());
                passaplats.posarPlat(plat);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static Plat cuinar(){
        Random rd = new Random();
        System.out.println("Marchando!!");
        System.out.println("...");
        System.out.println("...\n");
        Plat plat1 = new Plat("Pasta", 1);
        Plat plat2 = new Plat("Paella", 2);
        Plat plat3 = new Plat("Carn", 3);
        Plat plat4 = new Plat("Peix", 4);
        Plat plat5 = new Plat("Llegums", 5);
        List<Plat> plats = new ArrayList<>();
        plats.add(plat1);
        plats.add(plat2);
        plats.add(plat3);
        plats.add(plat4);
        plats.add(plat5);
        int numRandom = rd.nextInt(5)+1;
        return plats.get(numRandom);
    }
}
