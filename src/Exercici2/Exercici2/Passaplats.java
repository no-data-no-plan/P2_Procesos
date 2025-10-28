package Exercici2;


import java.util.ArrayList;
import java.util.List;

public class Passaplats {
    private List<Plat> llista;
    private int MAX_CAP;  

    public Passaplats(int MAX_CAP){
        this.llista = new ArrayList<>();
        this.MAX_CAP = MAX_CAP;
    }
    //synchronized s'utilitza per evitar problemes de concurrencia
    public synchronized void posarPlat(Plat plat) throws InterruptedException{
        while(llista.size() >= MAX_CAP){
            System.out.println("Passaplats ple! Esperant...");
            wait();
        }
        llista.add(plat);
        System.out.println("\nPlat afegit al Passaplats!!!");
        notifyAll();
    }
    public synchronized Plat treurePlat() throws InterruptedException {
        while (llista.isEmpty()){
            System.out.println("El passaplats està buit! Cambrer esperant...\n");
            wait(); //El cambrer espera
        }
        Plat plat = llista.remove(0);
        System.out.println("Plat "+ plat.getNom()+ " recollit del passaplats per ");
        notifyAll();//Avisa als cuiners de que hi ha espai
        return plat;
    }
    public List<Plat> getLlista() {
        return llista;
    }
}
