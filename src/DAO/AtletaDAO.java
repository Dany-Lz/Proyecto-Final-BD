package DAO;

import Connection.ConnectionManager;
import Logic.Atleta;
import Aux.*;
import java.sql.*;
import java.util.ArrayList;

public class AtletaDAO {

    public void insertar(Atleta atleta) {
        String sql = "INSERT INTO ATLETA (id_atleta, nombre_atl, apellidos_atl, edad, sexo, contacto, categ_deport, id_pais) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, atleta.getNumId());
            stmt.setString(2, atleta.getNombre());
            stmt.setString(3, atleta.getApellidos());
            stmt.setInt(4, atleta.getEdad());
            stmt.setString(5, atleta.getSexo());
            stmt.setString(6, atleta.getContacto());
            stmt.setString(7, atleta.getCategoria());
            stmt.setString(8, atleta.getIdPais());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar atleta: " + e.getMessage(), e);
        }
    }

    public ArrayList<Atleta> listar() {
        ArrayList<Atleta> lista = new ArrayList<>();
        String sql = "SELECT id_atleta, nombre_atl, apellidos_atl, edad, sexo, contacto, categ_deport, id_pais FROM ATLETA ORDER BY nombre_atl";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Atleta a = new Atleta(
                        rs.getString("id_atleta"),
                        rs.getString("nombre_atl"),
                        rs.getString("apellidos_atl"),
                        rs.getInt("edad"),
                        rs.getString("sexo"),
                        rs.getString("contacto"),
                        rs.getString("categ_deport"),
                        rs.getString("id_pais")
                );
                lista.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar atletas: " + e.getMessage(), e);
        }
        return lista;
    }
    
    public void modificar(Atleta atleta) {
        String sql = "UPDATE ATLETA SET nombre_atl = ?, apellidos_atl = ?, edad = ?, sexo = ?, contacto = ?, categ_deport = ?, id_pais = ? WHERE id_atleta = ?";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, atleta.getNombre());
            stmt.setString(2, atleta.getApellidos());
            stmt.setInt(3, atleta.getEdad());
            stmt.setString(4, atleta.getSexo());
            stmt.setString(5, atleta.getContacto());
            stmt.setString(6, atleta.getCategoria());
            stmt.setString(7, atleta.getIdPais());
            stmt.setString(8, atleta.getNumId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al modificar atleta: " + e.getMessage(), e);
        }
    }

    public void eliminar(String numIdAtl) {
        String sql = "DELETE FROM ATLETA WHERE id_atleta = ?";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numIdAtl);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar atleta: " + e.getMessage(), e);
        }
    }
    
    public Atleta obtenerPorId(String idAtleta) {
        Atleta a = null;
        String sql = "SELECT id_atleta, nombre_atl, apellidos_atl, edad, sexo, contacto, categ_deport, id_pais FROM ATLETA WHERE id_atleta = ?";
        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idAtleta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                a = new Atleta(
                        rs.getString("id_atleta"),
                        rs.getString("nombre_atl"),
                        rs.getString("apellidos_atl"),
                        rs.getInt("edad"),
                        rs.getString("sexo"),
                        rs.getString("contacto"),
                        rs.getString("categ_deport"),
                        rs.getString("id_pais")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener atleta: " + e.getMessage(), e);
        }
        return a;
    }

    //Reporte 1: Listado de atletas con total de medallas
    public ArrayList<AtletaListado> listarConMedallas() {
        ArrayList<AtletaListado> lista = new ArrayList<>();
        String sql
                = "SELECT p.nombre_pais, a.nombre_atl, a.apellidos_atl, a.id_atleta, "
                + "COUNT(DISTINCT i.id_comp) as cant_competencias, "
                + "COALESCE(SUM(i.cant_medallas), 0) as total_medallas "
                + "FROM ATLETA a "
                + "JOIN PAIS p ON a.id_pais = p.id_pais "
                + "LEFT JOIN INSCRIPCION i ON a.id_atleta = i.id_atleta "
                + "GROUP BY a.id_atleta, a.nombre_atl, a.apellidos_atl, p.nombre_pais "
                + "ORDER BY p.nombre_pais, a.nombre_atl";

        try (Connection conn = ConnectionManager.getInstance().retrieveConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nombreCompleto = rs.getString("nombre_atl") + " " + rs.getString("apellidos_atl");
                lista.add(new AtletaListado(
                        rs.getString("nombre_pais"),
                        nombreCompleto,
                        rs.getString("id_atleta"),
                        rs.getInt("cant_competencias"),
                        rs.getInt("total_medallas")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al generar reporte de atletas: " + e.getMessage(), e);
        }
        return lista;
    }
}
