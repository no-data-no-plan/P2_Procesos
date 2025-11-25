package Exercici4;

import java.util.ArrayList;
import java.util.List;

public class Lector extends Thread{
    private Articles articles;
    private String nom;
    private int numLectures;
    private List<Article> llegits;

    public Lector(String nom, Articles articles, int numLectures){
        this.articles = articles;
        this.nom = nom;
        this.numLectures = numLectures;
        this.llegits = new ArrayList<Article>();
    }

    @Override
    public void run() {
        while(llegits.size() < numLectures){
            for (int i = 0; i < numLectures; i++) {
                try {
                    Article article = articles.getArticleRandom();
                    while (llegits.contains(article)) {
                        article = articles.getArticleRandom();
                    }

                    System.out.println("||| ["+nom+"]"+ " solicita LECTURA a " + article.getTitol()+" |||");
                    System.out.println("══════════════════════════════════════════════════════════════");
                    article.llegir(nom, article);  // <-- LLAMADA AL MÉTODO DE ARTICLE
                    article.acabarLlegir(nom); // <-- LLAMADA AL MÉTODO DE ARTICLE
                    llegits.add(article);
                    Thread.sleep(500); // Tiempo entre lecturas

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }  
    }
}
