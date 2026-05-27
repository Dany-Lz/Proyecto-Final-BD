package Logic;

import java.time.LocalDate;


public class Marca {
    private String id;
    private String idAtleta;
    private String idComp;
    private double valor;
    private int puesto;
    private LocalDate fechaRegistro;

    public Marca(String id, String idAtleta, String idComp, double valor, int puesto, LocalDate fechaRegistro) {
        this.id = id;
        this.idAtleta = idAtleta;
        this.idComp = idComp;
        this.valor = valor;
        this.puesto = puesto;
        this.fechaRegistro = fechaRegistro;
    }

    public String getId() {
        return id;
    }

    public String getIdAtleta() {
        return idAtleta;
    }

    public String getIdComp() {
        return idComp;
    }

    public double getValor() {
        return valor;
    }

    public int getPuesto() {
        return puesto;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }
    
    
}
