package DAO;

import Logic.Pais;
import java.util.ArrayList;
import java.sql.*;
import Connection.ConnectionManager;

public class PaisDAO {

    public void insertar(Pais pais) {
        String sql = "INSERT INTO PAIS (id_pais, nombre_pais) VALUES (?, ?)";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pais.getId());
            stmt.setString(2, pais.getNombre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar país: " + e.getMessage(), e);
        }
    }

    public ArrayList<Pais> listar() {
        ArrayList<Pais> lista = new ArrayList<>();
        String sql = "SELECT id_pais, nombre_pais FROM PAIS ORDER BY nombre_pais";

        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pais p = new Pais(
                        rs.getString("id_pais"),
                        rs.getString("nombre_pais")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar paises: " + e.getMessage(), e);
        }
        return lista;
    }

    public Pais obtenerPorId(String idPais) {
        Pais pais = null;
        String sql = "SELECT id_pais, nombre_pais FROM PAIS WHERE id_pais = ?";
        
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idPais);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pais = new Pais(rs.getString("id_pais"), rs.getString("nombre_pais"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener país: " + e.getMessage(), e);
        }
        return pais;
    }
}
