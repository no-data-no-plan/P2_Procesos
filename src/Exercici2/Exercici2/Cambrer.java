package Exercici2;

public class Cambrer extends Thread{
    private String nom;
    private Passaplats passaplats;
    public Cambrer(String nom, Passaplats passaplats){
        this.nom = nom;
        this.passaplats = passaplats;
    }
    @Override
    public void run(){
        try{
            int cont = 0;
            while(!Thread.currentThread().isInterrupted() && cont < 5){
                Plat plat = passaplats.treurePlat();
                if(plat != null){
                    System.out.println("\n"+nom + " ha recollit " + plat.getNom());
                    servir(plat);
                    cont++;
                }else{
                    System.out.println("\n"+nom +" esperant plats de cuina...\n");
                    Thread.sleep(1000);
                }
            }
        }catch (InterruptedException e){
            System.out.println(nom + " ha acabat el seu torn...");
        }
    }
    public void servir(Plat plat) throws InterruptedException{
        try{
            System.out.println("\n"+nom + " servint " + plat.getNom()+ " al client...");
            Thread.sleep(1000);
            System.out.println("\nS'ha entregat "+ plat.getNom()+ " al client!\n" );
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public String getNom() {
        return nom;
    }
}