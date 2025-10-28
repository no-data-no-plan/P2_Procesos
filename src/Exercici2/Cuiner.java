import java.util.Random;

public class Cuiner {
    private String nom;
    public Cuiner(String nom){
        this.nom = nom;
    }
    public static void cuinar(){
        Random rd = new Random();
        String plat1 = "Pasta";
        String plat2 = "Paella";
        String plat3 = "Carn";
        String plat4 = "Peix";
        String plat5 = "Llegums";

        int numRandom = rd.nextInt(5)+1;
        
    }
}
