package DAO;

import Connection.ConnectionManager;
import Logic.Ciudad;
import java.sql.*;
import java.util.ArrayList;

public class CiudadDAO {

    public void insertar(Ciudad ciudad) {
        String sql = "INSERT INTO CIUDAD (id_ciudad, nombre_ciudad, id_pais) VALUES (?, ?, ?)";
        
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ciudad.getId());
            stmt.setString(2, ciudad.getNombre());
            stmt.setString(3, ciudad.getIdPais());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar ciudad: " + e.getMessage(), e);
        }
    }

    public ArrayList<Ciudad> listar() {
        ArrayList<Ciudad> lista = new ArrayList<>();
        String sql = "SELECT id_ciudad, nombre_ciudad, id_pais FROM CIUDAD ORDER BY nombre_ciudad";
        
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Ciudad c = new Ciudad(rs.getString("id_ciudad"), rs.getString("nombre_ciudad"), rs.getString("id_pais"));
                lista.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar ciudades: " + e.getMessage(), e);
        }
        return lista;
    }

    public ArrayList<Ciudad> listarPorPais(String idPais) {
        ArrayList<Ciudad> lista = new ArrayList<>();
        String sql = "SELECT id_ciudad, nombre_ciudad, id_pais FROM CIUDAD WHERE id_pais = ? ORDER BY nombre_ciudad";
        
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idPais);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Ciudad(rs.getString("id_ciudad"), rs.getString("nombre_ciudad"), rs.getString("id_pais")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar ciudades por país: " + e.getMessage(), e);
        }
        return lista;
    }
}