package datos.model;

// Clase que representa a un estudiante (modelo de datos)
public class Estudiante {
    // Atributos privados del estudiante
    private int id;
    private String nombre;
    private int edad;

    // Constructor que inicializa los valores de un estudiante
    public Estudiante(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getter para obtener el ID del estudiante
    public int getid() { return id; }

    // Setter para cambiar el ID del estudiante
    public void setid(int id) { this.id = id; }

    // Getter para obtener el nombre del estudiante
    public String getNombre() { return nombre; }

    // Setter para cambiar el nombre del estudiante
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Getter para obtener la edad del estudiante
    public int getEdad() { return edad; }

    // Setter para cambiar la edad del estudiante
    public void setEdad(int edad) { this.edad = edad; }

    // Método que devuelve una representación en texto del estudiante
    @Override
    public String toString() {
        return "id: " + id + ", Nombre: " + nombre + ", Edad: " + edad;
    }
}
