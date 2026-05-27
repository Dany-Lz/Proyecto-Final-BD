package Logic;


public class Entrenador {
    private String ci;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String especialidad;

    public Entrenador(String ci, String nombre, String apellidos, String direccion, String especialidad) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.especialidad = especialidad;
    }

    public String getCi() {
        return ci;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    
    
}
