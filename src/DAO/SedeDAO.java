
package DAO;

import Connection.ConnectionManager;
import Logic.Sede;
import java.sql.*;
import java.util.ArrayList;


public class SedeDAO {
    
    public void insertar(Sede sede) {
        String sql = "INSERT INTO SEDE (id_sede, nombre_sede, id_ciudad) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sede.getId());
            stmt.setString(2, sede.getNombre());
            stmt.setString(3, sede.getIdCiudad());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar sede: " + e.getMessage(), e);
        }
    }

    public ArrayList<Sede> listar() {
        ArrayList<Sede> lista = new ArrayList<>();
        String sql = "SELECT id_sede, nombre_sede, id_ciudad FROM SEDE ORDER BY nombre_sede";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Sede s = new Sede(rs.getString("id_sede"), rs.getString("nombre_sede"), rs.getString("id_ciudad"));
                lista.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar sedes: " + e.getMessage(), e);
        }
        return lista;
    }

    public ArrayList<Sede> listarPorCiudad(String idCiudad) {
        ArrayList<Sede> lista = new ArrayList<>();
        String sql = "SELECT id_sede, nombre_sede, id_ciudad FROM SEDE WHERE id_ciudad = ? ORDER BY nombre_sede";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idCiudad);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Sede(rs.getString("id_sede"), rs.getString("nombre_sede"), rs.getString("id_ciudad")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar sedes por ciudad: " + e.getMessage(), e);
        }
        return lista;
    }
}
