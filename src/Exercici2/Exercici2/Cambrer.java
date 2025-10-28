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
            while(true){
                Plat plat = passaplats.treurePlat();
                if(plat != null){
                    System.out.println(nom + " ha recollit " + plat.getNom());
                    servir(plat);
                }else{
                    System.out.println(nom +" esperant plats de cuina...\n");
                    Thread.sleep(1000);
                }
            }
        }catch (InterruptedException e){
            System.out.println(nom + " ha acabat el seu torn...");
        }
    }
    public void servir(Plat plat){
        try{
            System.out.println(nom + " servint " + plat.getNom()+ " al client...");
            Thread.sleep(2000);
            System.out.println("S'ha entregat "+ plat.getNom()+ "al client!\n" );
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public String getNom() {
        return nom;
    }
}