package controlador;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import modelo.Integrante;
import vista.FormularioIntegrante;

/**
 * CONTROLADOR: atiende los eventos de la vista, valida los datos,
 * crea el modelo (Integrante) y lo guarda en memoria (ArrayList).
 */
public class IntegranteController {

    private final FormularioIntegrante vista;
    private final ArrayList<Integrante> integrantes = new ArrayList<>();

    public IntegranteController(FormularioIntegrante vista) {
        this.vista = vista;
        vista.addRegistrarListener(e -> registrar());
        vista.addLimpiarListener(e -> vista.limpiarFormulario());
    }

    /** RF-04, RF-05, RF-06: validar, crear Integrante, almacenar y confirmar. */
    private void registrar() {
        List<String> errores = validar();
        if (!errores.isEmpty()) {
            vista.mostrarError("Corrija lo siguiente:\n- " + String.join("\n- ", errores));
            return;
        }
        Integrante nuevo = new Integrante(vista.getNombres(), vista.getApellidos(),
                vista.getRepositorio(), vista.getTipoEstudiante(), vista.getProyecto());
        integrantes.add(nuevo);
        vista.mostrarMensaje("Integrante registrado correctamente:\n" + nuevo.getNombreCompleto());
        vista.limpiarFormulario();
    }


    private List<String> validar() {
        List<String> errores = new ArrayList<>();
        if (vista.getNombres().isEmpty()) errores.add("Los nombres son obligatorios.");
        if (vista.getApellidos().isEmpty()) errores.add("Los apellidos son obligatorios.");
        String repo = vista.getRepositorio();
        if (repo.isEmpty()) errores.add("El repositorio es obligatorio.");
        else if (!urlValida(repo)) errores.add("La URL del repositorio no tiene un formato válido (ej: https://github.com/usuario/repo).");
        if (vista.getTipoEstudiante().isEmpty()) errores.add("Seleccione el tipo de estudiante (Nuevo o Antiguo).");
        if (vista.getProyecto() == null) errores.add("Seleccione un proyecto educativo.");
        return errores;
    }

    /** Formato básico: esquema http/https, sin espacios y con un host que contenga un punto. */
    private boolean urlValida(String s) {
        if (s.contains(" ")) return false;
        try {
            URI u = new URI(s);
            String esquema = u.getScheme();
            String host = u.getHost();
            return esquema != null && (esquema.equals("http") || esquema.equals("https"))
                    && host != null && host.contains(".") && !host.endsWith(".");
        } catch (Exception e) {
            return false;
        }
    }

    public List<Integrante> getIntegrantes() { return integrantes; }
}
