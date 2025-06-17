package datos.repository;

import datos.model.Estudiante;
import java.util.*;

public class EstudianteRepository {
    // Mapa que almacena los estudiantes usando su ID como clave
    private final Map<Integer, Estudiante> estudiantes = new HashMap<>();

    // Agrega un nuevo estudiante al mapa, usando su ID como clave
    public void agregar(Estudiante estudiante) {
        estudiantes.put(estudiante.getid(), estudiante);
    }

    // Devuelve un estudiante por su ID, o null si no existe
    public Estudiante obtenerPorid(int id) {
        return estudiantes.get(id);
    }

    // Retorna una lista con todos los estudiantes registrados
    public List<Estudiante> obtenerTodos() {
        return new ArrayList<>(estudiantes.values());
    }

    // Actualiza un estudiante (sobrescribe si ya existe con ese ID)
    public void actualizar(Estudiante estudiante) {
        estudiantes.put(estudiante.getid(), estudiante);
    }

    // Elimina un estudiante del mapa usando su ID
    public void eliminar(int id) {
        estudiantes.remove(id);
    }

    // Verifica si un estudiante con el ID dado existe en el mapa
    public boolean existe(int id) {
        return estudiantes.containsKey(id);
    }
}
