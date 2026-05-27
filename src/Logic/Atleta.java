package Logic;


public class Atleta {
    
    private String numId;
    private String nombre;
    private String apellidos;
    private int edad;
    private String sexo;
    private String contacto;
    private String categoria;
    private String idPais;

    public Atleta(String numId, String nombre, String apellidos, int edad, String sexo, String contacto, String categoria, String idPais) {
        this.numId = numId;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.sexo = sexo;
        this.contacto = contacto;
        this.categoria = categoria;
        this.idPais = idPais;
    }

    public String getNumId() {
        return numId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public String getContacto() {
        return contacto;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getIdPais() {
        return idPais;
    }
    
    
         
}
