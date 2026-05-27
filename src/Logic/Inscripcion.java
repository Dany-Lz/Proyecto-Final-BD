package Logic;

import java.time.LocalDate;


public class Inscripcion {
    private String idAtleta;
    private String idComp;
    private LocalDate fechaInicioReal;
    private LocalDate fechaFinReal;
    private String resultado;
    private boolean completa;
    private int cantMedallas;
    private String ciEntren;

    public Inscripcion(String idAtleta, String idComp, LocalDate fechaInicioReal, LocalDate fechaFinReal, String resultado, boolean completa, int cantMedallas, String ciEntren) {
        this.idAtleta = idAtleta;
        this.idComp = idComp;
        this.fechaInicioReal = fechaInicioReal;
        this.fechaFinReal = fechaFinReal;
        this.resultado = resultado;
        this.completa = completa;
        this.cantMedallas = cantMedallas;
        this.ciEntren = ciEntren;
    }

    public String getIdAtleta() {
        return idAtleta;
    }

    public String getIdComp() {
        return idComp;
    }

    public LocalDate getFechaInicioReal() {
        return fechaInicioReal;
    }

    public LocalDate getFechaFinReal() {
        return fechaFinReal;
    }

    public String getResultado() {
        return resultado;
    }

    public boolean isCompleta() {
        return completa;
    }

    public int getCantMedallas() {
        return cantMedallas;
    }

    public String getCiEntren() {
        return ciEntren;
    }
    
    
}
