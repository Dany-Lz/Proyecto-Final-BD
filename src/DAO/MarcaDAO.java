package DAO;

import Connection.ConnectionManager;
import Logic.Marca;
import java.sql.*;
import java.util.ArrayList;

public class MarcaDAO {

    public void insertar(Marca marca) {
        String sql = "INSERT INTO MARCA (id_marca, num_id_atl, id_comp, valor_marca, puesto, fecha_reg_marca) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, marca.getId());
            stmt.setString(2, marca.getIdAtleta());
            stmt.setString(3, marca.getIdComp());
            stmt.setDouble(4, marca.getValor());
            stmt.setInt(5, marca.getPuesto());
            stmt.setDate(6, java.sql.Date.valueOf(marca.getFechaRegistro()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar marca: " + e.getMessage(), e);
        }
    }

    public ArrayList<Marca> listarPorInscripcion(String idAtleta, String idComp) {
        ArrayList<Marca> lista = new ArrayList<>();
        String sql = "SELECT id_marca, num_id_atl, id_comp, valor_marca, puesto, fecha_reg_marca FROM MARCA WHERE num_id_atl = ? AND id_comp = ? ORDER BY fecha_reg_marca";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idAtleta);
            stmt.setString(2, idComp);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Marca m = new Marca(
                    rs.getString("id_marca"),
                    rs.getString("num_id_atl"),
                    rs.getString("id_comp"),
                    rs.getDouble("valor_marca"),
                    rs.getInt("puesto"),
                    rs.getDate("fecha_reg_marca").toLocalDate()
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar marcas: " + e.getMessage(), e);
        }
        return lista;
    }
}