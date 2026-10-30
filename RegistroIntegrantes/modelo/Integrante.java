package modelo;

/**
 * MODELO: representa a un integrante del proyecto educativo.
 * Solo guarda datos; no conoce la interfaz ni la lógica de validación.
 */
public class Integrante {

    private String nombres;
    private String apellidos;
    private String repositorio;
    private String tipoEstudiante; // "Nuevo" o "Antiguo"
    private String proyecto;

    public Integrante(String nombres, String apellidos, String repositorio,
                      String tipoEstudiante, String proyecto) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.repositorio = repositorio;
        this.tipoEstudiante = tipoEstudiante;
        this.proyecto = proyecto;
    }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getRepositorio() { return repositorio; }
    public void setRepositorio(String repositorio) { this.repositorio = repositorio; }

    public String getTipoEstudiante() { return tipoEstudiante; }
    public void setTipoEstudiante(String tipoEstudiante) { this.tipoEstudiante = tipoEstudiante; }

    public String getProyecto() { return proyecto; }
    public void setProyecto(String proyecto) { this.proyecto = proyecto; }

    public String getNombreCompleto() { return nombres + " " + apellidos; }

    @Override
    public String toString() {
        return "Integrante{" + getNombreCompleto() + ", repositorio=" + repositorio
                + ", tipo=" + tipoEstudiante + ", proyecto=" + proyecto + "}";
    }
}
