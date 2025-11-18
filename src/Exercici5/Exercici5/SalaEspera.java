package Exercici5.Exercici5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Usem "synchronized" per evitar que dos fils puguin accedir simultàniament a lo mateix (race condition)
public class SalaEspera {
    
    private int capacitatMaxima;
    private Queue<Pacient> cuaPacientsNormals;
    private Queue<Pacient> cuaPacientsUrgents;
    private ArrayList<Pacient> pacientsEnSala;
    private Pacient pacientEnConsulta;
    private boolean doctorDisponible;
    
    public SalaEspera(int capacitatMaxima) {
        this.capacitatMaxima = capacitatMaxima;
        this.cuaPacientsNormals = new LinkedList<>();
        this.cuaPacientsUrgents = new LinkedList<>();
        this.pacientsEnSala = new ArrayList<>();
        this.pacientEnConsulta = null;
        this.doctorDisponible = true;
    }
    
    // Mètode sincronitzat per entrar a la sala d'espera
    public synchronized void entrarSalaEspera(Pacient pacient) {
        
        // Mentre la sala estigui plena, el pacient espera fora
        while (pacientsEnSala.size() >= capacitatMaxima) {
            System.out.println("\t[SALA PLENA] " + pacient.getNomPacient() + 
                             " espera fora. Capacitat: " + pacientsEnSala.size() + 
                             "/" + capacitatMaxima);
            try {
                wait(); // Esperem fins que s'alliberi espai
            } catch (InterruptedException e) {
                System.out.println(pacient.getNomPacient() + " ha estat interromput esperant fora!");
            }
        }
        
        // El pacient entra a la sala
        pacientsEnSala.add(pacient);
        
        // L'afegim a la cua corresponent segons prioritat
        if (pacient.isUrgent()) {
            cuaPacientsUrgents.add(pacient);
            System.out.println("\t[SALA] " + pacient.getNomPacient() + 
                             " [URGENT] entra a la sala. Pacients urgents esperant: " + 
                             cuaPacientsUrgents.size());
        } else {
            cuaPacientsNormals.add(pacient);
            System.out.println("\t[SALA] " + pacient.getNomPacient() + 
                             " entra a la sala. Pacients normals esperant: " + 
                             cuaPacientsNormals.size());
        }
        
        System.out.println("\t\tTotal pacients en sala: " + pacientsEnSala.size() + 
                         "/" + capacitatMaxima);
        
        // Notifiquem al doctor que hi ha un nou pacient
        notifyAll();
    }
    
    // Mètode sincronitzat per que el pacient esperi el seu torn
    public synchronized void esperarTorn(Pacient pacient) {
        
        // El pacient espera mentre no sigui el seu torn
        while (pacientEnConsulta != pacient) {
            try {
                wait(); // Esperem el nostre torn
            } catch (InterruptedException e) {
                System.out.println(pacient.getNomPacient() + " ha estat interromput esperant torn!");
            }
        }
    }
    
    // Mètode sincronitzat per que el doctor cridi al següent pacient
    public synchronized Pacient cridarSeguientPacient() {
        
        // El doctor espera mentre no hi hagi pacients
        while (cuaPacientsUrgents.isEmpty() && cuaPacientsNormals.isEmpty()) {
            System.out.println("\t[DOCTOR] No hi ha pacients. El doctor espera...");
            try {
                wait(); // El doctor s'adorm fins que arribi un pacient
            } catch (InterruptedException e) {
                System.out.println("El doctor ha estat interromput!");
            }
        }
        
        // Prioritat: primer els urgents, després els normals
        Pacient seguent = null;
        if (!cuaPacientsUrgents.isEmpty()) {
            seguent = cuaPacientsUrgents.poll();
            System.out.println("\t[PRIORITAT] Es crida un pacient URGENT: " + 
                             seguent.getNomPacient());
        } else if (!cuaPacientsNormals.isEmpty()) {
            seguent = cuaPacientsNormals.poll();
            System.out.println("\t[TORN] Es crida un pacient normal: " + 
                             seguent.getNomPacient());
        }
        
        return seguent;
    }
    
    // Mètode sincronitzat per atendre un pacient
    public synchronized void atenderPacient(Pacient pacient) {
        pacientEnConsulta = pacient;
        doctorDisponible = false;
        
        System.out.println("\t[CONSULTA] " + pacient.getNomPacient() + 
                         " entra a la consulta");
        
        // Notifiquem a tots els pacients (el seleccionat pot entrar)
        notifyAll();
    }
    
    // Mètode sincronitzat per que el doctor esperi que acabi la consulta
    public synchronized void esperarFinConsulta() {
        while (!doctorDisponible) {
            try {
                wait(); // El doctor espera que el pacient acabi
            } catch (InterruptedException e) {
                System.out.println("El doctor ha estat interromput durant consulta!");
            }
        }
    }
    
    // Mètode sincronitzat per que el pacient surti de la consulta
    public synchronized void sortirConsulta(Pacient pacient) {
        
        // El pacient surt de la consulta
        pacientEnConsulta = null;
        doctorDisponible = true;
        
        // El pacient també surt de la sala
        pacientsEnSala.remove(pacient);
        
        System.out.println("\t[CONSULTA] " + pacient.getNomPacient() + 
                         " surt de la consulta. Pacients restants en sala: " + 
                         pacientsEnSala.size());
        
        // Notifiquem a tots (doctor pot atendre següent, nous pacients poden entrar)
        notifyAll();
    }
    
    // Mètode sincronitzat per mostrar l'estat de la sala
    public synchronized void mostrarEstat() {
        int totalUrgents = cuaPacientsUrgents.size();
        int totalNormals = cuaPacientsNormals.size();
        int totalEnSala = pacientsEnSala.size();
        
        System.out.println("\nESTAT SALA D'ESPERA:");
        System.out.println("Capacitat: " + totalEnSala + "/" + capacitatMaxima);
        System.out.println("Pacients urgents esperant: " + totalUrgents);
        System.out.println("Pacients normals esperant: " + totalNormals);
        System.out.println("Doctor disponible: " + (doctorDisponible ? "Sí" : "No"));
    }
}