package Exercici4;

public class Article {
    private String titol;
    //Variables per control de Lector / Escriptor
    private int lectorsActius = 0;
    private int escriptorsEsperant = 0;
    private boolean escriptorActiu = false;
    //Locks per sincronització
    private final Object lock = new Object();

    public Article(String titol){
        this.titol = titol;
    }
    public Article(){
    }
    public String getTitol() {
        return titol;
    }
    public String toString() {
        return "Article{" +
                "titol=" + titol +
                '}';
    }

    public void llegir(String nom,Article article) throws InterruptedException {
        synchronized (lock) {
            while (escriptorActiu || escriptorsEsperant > 0) {
                lock.wait();
            }
            lectorsActius++;
            System.out.println("["+nom+"]" + " esta llegint "+ article.getTitol() + "...");
            System.out.println("══════════════════════════════════════════════════════════════");
            Thread.sleep(1000); // Simular tiempo de lectura
            System.out.println("Lectors actius en article "+ this.titol+": " + lectorsActius);
            System.out.println("--------------------------------------------------------------");
        }   
    }
    public void acabarLlegir(String nom) {
        synchronized(lock) {
            lectorsActius--;
            System.out.println("["+nom+"]" + " ha acabat de llegir.\n");
            System.out.println("══════════════════════════════════════════════════════════════");
            lock.notifyAll(); // Despertar escritores en espera            }
        }
    }
    
     public void escriure(String nom, Article article) throws InterruptedException {
        synchronized(lock) {
            escriptorsEsperant++;
            Thread.sleep(200);
            
            // Esperar hasta que no haya lectores ni otros escritores
            while (lectorsActius > 0 || escriptorActiu) {
                System.out.println("["+nom+"]" + " espera para escriure en l'article " + this.titol + "...");
                System.out.println("...");
                lock.wait();
            }
            escriptorsEsperant--;
            escriptorActiu = true;
            System.out.println("["+nom+"]" + " está escrivint en l'article " + this.titol + "...");
            System.out.println("...");
            Thread.sleep(2000); // Simular tiempo de escritura
        }
    }
    public void acabarEscriptura(String nom) {
        synchronized(lock) {
            escriptorActiu = false;
            System.out.println("["+nom+"]" +" ha ACABAT d'escriure en article " + this.titol);
            System.out.println("══════════════════════════════════════════════════════════════");
            System.out.println("══════════════════════════════════════════════════════════════\n");
            lock.notifyAll(); // Despertar todos (lectores y escritores)
        }
    }
}
