package Exercici4;

import java.util.List;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        System.out.println("═════════════════════════════════════════════════");
        System.out.println("  REDACCIÓ I LECTURA COL·LABORATIVA D'ARTICLES  ");
        System.out.println("═════════════════════════════════════════════════\n");
        boolean ok = false;
        int articlesPerLector = 0;
        int articlesPerEscriptor = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Inicialitzant articles...\n");

        Article article1 = new Article("Article Python");
        Article article2 = new Article("Article Dart");
        Article article3 = new Article("Article Java");
        Article article4 = new Article("Article HTML");

        List<Article> llista = List.of(article1, article2, article3, article4);
        Articles articles = new Articles(llista);
        
        while(!ok){
            System.out.println("Quants articles vols que pugui llegir cada lector? (Màxim " +llista.size()+")");
            articlesPerLector = sc.nextInt();
            System.out.println("Quants articles vols que pugui escriure cada escriptor? (Màxim " +llista.size()+")");
            articlesPerEscriptor = sc.nextInt();
            if(articlesPerLector > 0 && articlesPerLector <=llista.size() && articlesPerEscriptor >0 && articlesPerEscriptor <=llista.size()){
                ok = true;
            }else{
                System.out.println("Valor incorrecte. Torna-ho a provar.\n");
            }
        }
        sc.close();

        System.out.println("\nConfiguració:");
        System.out.println("  - Cada lector llegirà " + articlesPerLector + " articles diferents");
        System.out.println("  - Cada escriptor escriurà en " + articlesPerEscriptor + " articles diferents");

        Thread lector1 = new Lector("Dani", articles,articlesPerLector);
        Thread lector2 = new Lector("Edim", articles,articlesPerLector);
        Thread lector3 = new Lector("Marco", articles,articlesPerLector);
        Thread lector4 = new Lector("Ramon", articles,articlesPerLector);
        Thread escriptor1 = new Escriptor("Samuel",articles,articlesPerEscriptor);
        Thread escriptor2 = new Escriptor("Nicolas",articles,articlesPerEscriptor);

        System.out.println("\nLectors i escriptors preparats.");
        System.out.println("Iniciant lectura i escriptura d'articles...");
        System.out.println("--------------------------------------------\n");

        lector1.start();
        lector2.start();
        lector3.start();
        lector4.start();
        escriptor1.start();
        escriptor2.start();

        try {
            lector1.join();
            lector2.join();
            lector3.join();
            lector4.join();
            escriptor1.join();
            escriptor2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();    
        }
        System.out.println("\n----- FI DE LECTURA I ESCRIPTURA D'ARTICLES -----");
    }
}