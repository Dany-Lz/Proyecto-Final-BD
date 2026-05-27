package Logic;


public class Sede {
    private String id;
    private String nombre;
    private String idCiudad;

    public Sede(String id, String nombre, String idCiudad) {
        this.id = id;
        this.nombre = nombre;
        this.idCiudad = idCiudad;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdCiudad() {
        return idCiudad;
    }
    
    
}
