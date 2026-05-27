package Logic;

import java.time.LocalDate;


public class Competencia {
    
    private String id;
    private String nombre;
    private String estado;
    private LocalDate fechaInicioPrevista;
    private LocalDate fechaFinPrevista;
    private String idSede;

    public Competencia(String id, String nombre, String estado, LocalDate fechaInicioPrevista, LocalDate fechaFinPrevista, String idSede) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.fechaInicioPrevista = fechaInicioPrevista;
        this.fechaFinPrevista = fechaFinPrevista;
        this.idSede = idSede;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getFechaInicioPrevista() {
        return fechaInicioPrevista;
    }

    public LocalDate getFechaFinPrevista() {
        return fechaFinPrevista;
    }

    public String getIdSede() {
        return idSede;
    }
    
    
}
