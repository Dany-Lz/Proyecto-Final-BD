package DAO;

import Connection.ConnectionManager;
import Logic.Entrenador;
import Aux.EntrenadorListado;
import java.sql.*;
import java.util.ArrayList;

public class EntrenadorDAO {

    public void insertar(Entrenador entrenador) {
        String sql = "INSERT INTO ENTRENADOR (ci_entren, nombre_entren, apellidos_entren, direccion_entren, especialidad) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entrenador.getCi());
            stmt.setString(2, entrenador.getNombre());
            stmt.setString(3, entrenador.getApellidos());
            stmt.setString(4, entrenador.getDireccion());
            stmt.setString(5, entrenador.getEspecialidad());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar entrenador: " + e.getMessage(), e);
        }
    }

    public ArrayList<Entrenador> listar() {
        ArrayList<Entrenador> lista = new ArrayList<>();
        String sql = "SELECT ci_entren, nombre_entren, apellidos_entren, direccion_entren, especialidad FROM ENTRENADOR ORDER BY nombre_entren";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Entrenador e = new Entrenador(
                        rs.getString("ci_entren"),
                        rs.getString("nombre_entren"),
                        rs.getString("apellidos_entren"),
                        rs.getString("direccion_entren"),
                        rs.getString("especialidad")
                );
                lista.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entrenadores: " + e.getMessage(), e);
        }
        return lista;
    }

    public void modificar(Entrenador entrenador) {
        String sql = "UPDATE ENTRENADOR SET nombre_entren = ?, apellidos_entren = ?, direccion_entren = ?, especialidad = ? WHERE ci_entren = ?";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entrenador.getNombre());
            stmt.setString(2, entrenador.getApellidos());
            stmt.setString(3, entrenador.getDireccion());
            stmt.setString(4, entrenador.getEspecialidad());
            stmt.setString(5, entrenador.getCi());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al modificar entrenador: " + e.getMessage(), e);
        }
    }

    public void eliminar(String ciEntren) {
        String sql = "DELETE FROM ENTRENADOR WHERE ci_entren = ?";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ciEntren);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar entrenador: " + e.getMessage(), e);
        }
    }

    public Entrenador obtenerPorId(String ciEntren) {
        Entrenador ent = null;
        String sql = "SELECT ci_entren, nombre_entren, apellidos_entren, direccion_entren, especialidad FROM ENTRENADOR WHERE ci_entren = ?";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ciEntren);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ent = new Entrenador(
                        rs.getString("ci_entren"),
                        rs.getString("nombre_entren"),
                        rs.getString("apellidos_entren"),
                        rs.getString("direccion_entren"),
                        rs.getString("especialidad")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener entrenador: " + e.getMessage(), e);
        }
        return ent;
    }

    // Reporte 4: Listado de entrenadores con cantidad de atletas entrenados
    public ArrayList<EntrenadorListado> listarConAtletasEntrenados() {
        ArrayList<EntrenadorListado> lista = new ArrayList<>();
        String sql = "SELECT e.ci_entren, e.nombre_entren, e.apellidos_entren, e.direccion_entren, e.especialidad, COUNT(i.id_atleta) as total_atletas "
                + "FROM ENTRENADOR e "
                + "LEFT JOIN INSCRIPCION i ON e.ci_entren = i.ci_entren "
                + "GROUP BY e.ci_entren, e.nombre_entren, e.apellidos_entren, e.direccion_entren, e.especialidad "
                + "ORDER BY e.nombre_entren";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nombreCompleto = rs.getString("nombre_entren") + " " + rs.getString("apellidos_entren");
                lista.add(new EntrenadorListado(
                        rs.getString("ci_entren"),
                        nombreCompleto,
                        rs.getString("direccion_entren"),
                        rs.getString("especialidad"),
                        rs.getInt("total_atletas")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al generar reporte de entrenadores: " + e.getMessage(), e);
        }
        return lista;
    }
}
