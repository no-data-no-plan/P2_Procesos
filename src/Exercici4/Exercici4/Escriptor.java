package Exercici4;

public class Escriptor extends Thread{
    private Articles articles;
    private String nom;
    private int numEscriptures;

    public Escriptor(String nom, Articles articles,int numEscriptures){
        this.nom = nom;
        this.articles = articles;
        this.numEscriptures = 2;
    }

    @Override
    public void run() {
        for (int i = 0; i < numEscriptures; i++) {
            try {
                Article article = articles.getArticleRandom();

                System.out.println("||| ["+nom+"]" + " solicita ESCRITURA a " + article.getTitol()+" |||");
                System.out.println("══════════════════════════════════════════════════════════════");
                article.escriure(nom, article);  // <-- LLAMADA AL MÉTODO DE ARTICLE
                
                article.acabarEscriptura(nom);  // <-- LLAMADA AL MÉTODO DE ARTICLE
                Thread.sleep(500);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
