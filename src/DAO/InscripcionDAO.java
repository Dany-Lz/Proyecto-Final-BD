package DAO;

import Connection.ConnectionManager;
import Logic.Inscripcion;
import Aux.InscripcionListado;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class InscripcionDAO {

    public void insertar(Inscripcion inscripcion) {
        String sql = "INSERT INTO INSCRIPCION (id_atleta, id_comp, fecha_inicio_insc, resultado, esta_completa, cant_medallas, ci_entren) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, inscripcion.getIdAtleta());
            stmt.setString(2, inscripcion.getIdComp());
            stmt.setDate(3, java.sql.Date.valueOf(inscripcion.getFechaInicioReal()));
            stmt.setString(4, inscripcion.getResultado());
            stmt.setBoolean(5, inscripcion.isCompleta());
            stmt.setInt(6, inscripcion.getCantMedallas());

            // ci_entren puede ser null (si no tiene entrenador)
            if (inscripcion.getCiEntren() != null && !inscripcion.getCiEntren().isEmpty()) {
                stmt.setString(7, inscripcion.getCiEntren());
            } else {
                stmt.setNull(7, java.sql.Types.VARCHAR);
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar inscripción: " + e.getMessage(), e);
        }
    }

    public ArrayList<InscripcionListado> listarInscripciones() {
        ArrayList<InscripcionListado> lista = new ArrayList<>();
        String sql
                = "SELECT a.id_atleta, c.id_comp, "
                + "a.nombre_atl, a.apellidos_atl, "
                + "c.nombre_comp, "
                + "cd.nombre_ciudad, s.nombre_sede, "
                + "i.fecha_inicio_insc, i.fecha_fin_real, "
                + "i.resultado, i.cant_medallas, i.ci_entren "
                + "FROM INSCRIPCION i "
                + "JOIN ATLETA a ON i.id_atleta = a.id_atleta "
                + "JOIN COMPETENCIA c ON i.id_comp = c.id_comp "
                + "JOIN SEDE s ON c.id_sede = s.id_sede "
                + "JOIN CIUDAD cd ON s.id_ciudad = cd.id_ciudad "
                + "ORDER BY a.nombre_atl, c.nombre_comp";

        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String idAtleta = rs.getString("id_atleta");
                String idComp = rs.getString("id_comp");
                String nombreAtleta = rs.getString("nombre_atl") + " " + rs.getString("apellidos_atl");
                String nombreCompetencia = rs.getString("nombre_comp");
                String ciudad = rs.getString("nombre_ciudad");
                String sede = rs.getString("nombre_sede");

                LocalDate fechaInicio = rs.getDate("fecha_inicio_insc") != null
                        ? rs.getDate("fecha_inicio_insc").toLocalDate() : null;
                LocalDate fechaFin = rs.getDate("fecha_fin_real") != null
                        ? rs.getDate("fecha_fin_real").toLocalDate() : null;

                String resultado = rs.getString("resultado");
                int medallas = rs.getInt("cant_medallas");
                String entrenadorAsignado = (rs.getString("ci_entren") != null && !rs.getString("ci_entren").isEmpty())
                        ? "Sí" : "No";

                InscripcionListado inscripcion = new InscripcionListado(
                        idAtleta, idComp, nombreAtleta, nombreCompetencia,
                        ciudad, sede, fechaInicio, fechaFin, resultado, medallas, entrenadorAsignado
                );
                lista.add(inscripcion);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar inscripciones: " + e.getMessage(), e);
        }
        return lista;
    }

    // Reporte 6: Atletas incompletos
    public ArrayList<Aux.AtletaIncompletoListado> listarAtletasIncompletos() {
        ArrayList<Aux.AtletaIncompletoListado> lista = new ArrayList<>();
        String sql = "SELECT a.nombre_atl, a.apellidos_atl, i.fecha_fin_insc, i.resultado, c.fecha_fin_prevista "
                + "FROM INSCRIPCION i "
                + "JOIN ATLETA a ON i.num_id_atl = a.num_id_atl "
                + "JOIN COMPETENCIA c ON i.id_comp = c.id_comp "
                + "WHERE (i.fecha_fin_insc IS NULL AND c.fecha_fin_prevista < CURRENT_DATE) "
                + "OR i.esta_completa = FALSE";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nombreCompleto = rs.getString("nombre_atl") + " " + rs.getString("apellidos_atl");
                lista.add(new Aux.AtletaIncompletoListado(
                        nombreCompleto,
                        rs.getDate("fecha_fin_insc") != null ? rs.getDate("fecha_fin_insc").toLocalDate() : null,
                        rs.getString("resultado")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al generar reporte de atletas incompletos: " + e.getMessage(), e);
        }
        return lista;
    }
}
