package DAO;

import Connection.ConnectionManager;
import Logic.Competencia;
import java.sql.*;
import java.util.ArrayList;
import Aux.CompetenciaListado;

public class CompetenciaDAO {

    public void insertar(Competencia competencia) {
        String sql = "INSERT INTO COMPETENCIA (id_comp, nombre_comp, estado, fecha_inicio_comp, fecha_fin_prevista, id_sede) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, competencia.getId());
            stmt.setString(2, competencia.getNombre());
            stmt.setString(3, competencia.getEstado());
            stmt.setDate(4, java.sql.Date.valueOf(competencia.getFechaInicioPrevista()));
            stmt.setDate(5, java.sql.Date.valueOf(competencia.getFechaFinPrevista()));
            stmt.setString(6, competencia.getIdSede());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar competencia: " + e.getMessage(), e);
        }
    }

    public ArrayList<Competencia> listar() {
        ArrayList<Competencia> lista = new ArrayList<>();
        String sql = "SELECT id_comp, nombre_comp, estado, fecha_inicio_comp, fecha_fin_prevista, id_sede FROM COMPETENCIA ORDER BY nombre_comp";

        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Competencia c = new Competencia(
                        rs.getString("id_comp"),
                        rs.getString("nombre_comp"),
                        rs.getString("estado"),
                        rs.getDate("fecha_inicio_comp").toLocalDate(),
                        rs.getDate("fecha_fin_prevista").toLocalDate(),
                        rs.getString("id_sede")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar competencias: " + e.getMessage(), e);
        }
        return lista;
    }

    public void modificar(Competencia competencia) {
        String sql = "UPDATE COMPETENCIA SET nombre_comp = ?, fecha_inicio_comp = ?, fecha_fin_prevista = ?, id_sede = ? WHERE id_comp = ?";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, competencia.getNombre());
            stmt.setDate(2, java.sql.Date.valueOf(competencia.getFechaInicioPrevista()));
            stmt.setDate(3, java.sql.Date.valueOf(competencia.getFechaFinPrevista()));
            stmt.setString(4, competencia.getIdSede());
            stmt.setString(5, competencia.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al modificar competencia: " + e.getMessage(), e);
        }
    }

    public void eliminar(String idComp) {
        String sql = "DELETE FROM COMPETENCIA WHERE id_comp = ?";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idComp);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar competencia: " + e.getMessage(), e);
        }
    }

    public Competencia obtenerPorId(String idComp) {
        Competencia comp = null;
        String sql = "SELECT id_comp, nombre_comp, estado, fecha_inicio_comp, fecha_fin_prevista, id_sede FROM COMPETENCIA WHERE id_comp = ?";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idComp);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                comp = new Competencia(
                        rs.getString("id_comp"),
                        rs.getString("nombre_comp"),
                        rs.getString("estado"),
                        rs.getDate("fecha_inicio_comp").toLocalDate(),
                        rs.getDate("fecha_fin_prevista").toLocalDate(),
                        rs.getString("id_sede")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener competencia: " + e.getMessage(), e);
        }
        return comp;
    }

    public ArrayList<Competencia> listarProgramadas() {
        ArrayList<Competencia> lista = new ArrayList<>();
        String sql = "SELECT id_comp, nombre_comp, fecha_inicio_comp, fecha_fin_prevista, id_sede "
                + "FROM COMPETENCIA WHERE estado = 'Programada' ORDER BY fecha_inicio_comp";

        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Competencia c = new Competencia(
                        rs.getString("id_comp"),
                        rs.getString("nombre_comp"),
                        "Programada", // estado (aunque ya sabemos que es Programada)
                        rs.getDate("fecha_inicio_comp").toLocalDate(),
                        rs.getDate("fecha_fin_prevista").toLocalDate(),
                        rs.getString("id_sede")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar competencias programadas: " + e.getMessage(), e);
        }
        return lista;
    }

    // Reporte 2: Listado de competencias con cantidad de participantes
    public ArrayList<CompetenciaListado> listarConParticipantes() {
        ArrayList<CompetenciaListado> lista = new ArrayList<>();
        String sql
                = "SELECT c.id_comp, c.nombre_comp, c.estado, "
                + "cd.nombre_ciudad, s.nombre_sede, "
                + "COUNT(DISTINCT i.id_atleta) as participantes "
                + "FROM COMPETENCIA c "
                + "JOIN SEDE s ON c.id_sede = s.id_sede "
                + "JOIN CIUDAD cd ON s.id_ciudad = cd.id_ciudad "
                + "LEFT JOIN INSCRIPCION i ON c.id_comp = i.id_comp "
                + "GROUP BY c.id_comp, c.nombre_comp, c.estado, cd.nombre_ciudad, s.nombre_sede "
                + "ORDER BY c.nombre_comp";

        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new CompetenciaListado(
                        rs.getString("id_comp"),
                        rs.getString("nombre_comp"),
                        rs.getString("nombre_ciudad"),
                        rs.getString("nombre_sede"),
                        rs.getString("estado"),
                        rs.getInt("participantes")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar competencias: " + e.getMessage(), e);
        }
        return lista;
    }
}
