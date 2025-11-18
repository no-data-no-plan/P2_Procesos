package Exercici5.Exercici5;

// Creem la classe Doctor amb Runnable ja que el doctor serà un thread independent
public class Doctor implements Runnable {
    
    private String nomDoctor;
    private SalaEspera salaEspera;
    private int pacientsAtesos;
    private int maxPacients;
    
    public Doctor(String nomDoctor, SalaEspera salaEspera, int maxPacients) {
        this.nomDoctor = nomDoctor;
        this.salaEspera = salaEspera;
        this.pacientsAtesos = 0;
        this.maxPacients = maxPacients;
    }
    
    @Override
    public void run() {
        
        System.out.println("\n" + nomDoctor + " ha arribat al consultori i comença la jornada!");
        
        // El doctor atén pacients fins arribar al màxim
        while (pacientsAtesos < maxPacients) {
            
            System.out.println("\t[DOCTOR] " + nomDoctor + " està esperant pacients... " +
                             "(Atesos: " + pacientsAtesos + "/" + maxPacients + ")");
            
            // El doctor espera que hi hagi un pacient per atendre
            Pacient pacientActual = salaEspera.cridarSeguientPacient();
            
            if (pacientActual != null) {
                // El doctor està atenent el pacient
                System.out.println("\t[DOCTOR] " + nomDoctor + " comença a atendre a " + 
                                 pacientActual.getNomPacient());
                
                // Notifiquem al pacient que pot entrar a la consulta
                salaEspera.atenderPacient(pacientActual);
                
                // Esperem que el pacient acabi la consulta
                salaEspera.esperarFinConsulta();
                
                pacientsAtesos++;
                
                System.out.println("\t[DOCTOR] " + nomDoctor + " ha finalitzat amb " + 
                                 pacientActual.getNomPacient() + 
                                 " (Total atesos: " + pacientsAtesos + ")");
            }
        }
        
        System.out.println("\n" + nomDoctor + " ha finalitzat la jornada! " +
                          "Ha atès un total de " + pacientsAtesos + " pacients!");
    }
    
    public String getNomDoctor() {
        return nomDoctor;
    }
}