package logica_negocio;

import datos.model.Estudiante;
import datos.repository.EstudianteRepository;

import java.util.List;

public class EstudianteService {
    // Instancia del repositorio para acceder a los datos
    private final EstudianteRepository repo = new EstudianteRepository();

    // Agrega un nuevo estudiante al repositorio
    public void agregarEstudiante(Estudiante estudiante) {
        repo.agregar(estudiante);
    }

    // Busca un estudiante por su ID
    public Estudiante buscarEstudiante(int id) {
        return repo.obtenerPorid(id);
    }

    // Obtiene la lista completa de estudiantes
    public List<Estudiante> listarEstudiantes() {
        return repo.obtenerTodos();
    }

    // Actualiza los datos de un estudiante, solo si existe en el repositorio
    public void actualizarEstudiante(Estudiante estudiante) {
        if (repo.existe(estudiante.getid())) {
            repo.actualizar(estudiante);
        }
    }

    // Elimina un estudiante por ID, solo si existe
    public void eliminarEstudiante(int id) {
        if (repo.existe(id)) {
            repo.eliminar(id);
        }
    }
}
