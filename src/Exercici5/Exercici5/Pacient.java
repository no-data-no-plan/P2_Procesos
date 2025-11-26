package Exercici5.Exercici5;

import java.util.Random;

// Creem la classe Pacient amb Runnable ja que els pacients seran threads independents
public class Pacient implements Runnable {
    
    private String nomPacient;
    private boolean esUrgent;
    private SalaEspera salaEspera;
    private Random random;
    
    public Pacient(String nomPacient, boolean esUrgent, SalaEspera salaEspera) {
        this.nomPacient = nomPacient;
        this.esUrgent = esUrgent;
        this.salaEspera = salaEspera;
        this.random = new Random();
    }
    
    @Override
    public void run() {
        
        // El pacient arriba al consultori
        if (esUrgent) {
            System.out.println(nomPacient + " [URGENT] ha arribat al consultori!");
        } else {
            System.out.println(nomPacient + " ha arribat al consultori!");
        }
        
        // Simulem un petit temps d'arribada
        try {
            Thread.sleep(random.nextInt(100) + 50);
        } catch (InterruptedException e) {
            System.out.println(nomPacient + " ha estat interromput!");
        }
        
        // El pacient intenta entrar a la sala d'espera
        salaEspera.entrarSalaEspera(this);
        // El pacient espera el seu torn per ser atès
        salaEspera.esperarTorn(this);
        // El pacient està sent atès pel doctor
        System.out.println("\t\t[CONSULTA] " + nomPacient + " està sent atès pel doctor...");
        
        // Simulem el temps de consulta,  més temps pels urgents!
        try {
            if (esUrgent) {
                Thread.sleep(random.nextInt(200) + 300);
            } else {
                Thread.sleep(random.nextInt(200) + 100); 
            }
        } catch (InterruptedException e) {
            System.out.println(nomPacient + " ha estat interromput durant la consulta!");
        }
        
        // El pacient finalitza la consulta
        salaEspera.sortirConsulta(this);
        
        System.out.println("\n" + nomPacient + " ha finalitzat la consulta i marxa del consultori!");
    }
    
    public String getNomPacient() {
        return nomPacient;
    }
    
    public boolean isUrgent() {
        return esUrgent;
    }
}