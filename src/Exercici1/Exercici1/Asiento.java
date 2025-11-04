package Exercici1.Exercici1;

public class Asiento {

    private int idAsiento;
    private boolean ocupado;

    public Asiento(int idAsiento){
        this.idAsiento = idAsiento;
        // Por defecto empezamos con los asientos libres
        this.ocupado = false;
    }

    public int getIdAsiento(){
        return idAsiento;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}