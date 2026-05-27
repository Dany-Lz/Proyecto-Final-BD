package Logic;


public class Ciudad {
    private String id;
    private String nombre;
    private String idPais;

    public Ciudad(String id, String nombre, String idPais) {
        this.id = id;
        this.nombre = nombre;
        this.idPais = idPais;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdPais() {
        return idPais;
    }
    
    
}
