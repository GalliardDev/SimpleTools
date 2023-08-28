package es.exmaster.simpletools.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.exmaster.simpletools.Main;

public class SimpleToolsDAO {
	private static final String URL = "jdbc:sqlite:" + Main.BDD;

	public static void inicializarBaseDeDatos() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            // Crear tabla hortelanos
            stmt.execute("CREATE TABLE IF NOT EXISTS players (" +
                    "username VARCHAR(255) NOT NULL, " +
                    "uuid VARCHAR(36) NOT NULL, " +
                    "first_join TEXT NOT NULL, " +
                    "last_login TEXT NOT NULL, " +
                    "playtime TEXT DEFAULT 0," +
                    "UNIQUE (uuid)" +
                    ")");

            // Crear tabla ingresos
            stmt.execute("CREATE TABLE IF NOT EXISTS blocked_worlds (" +
                    "worlds TEXT" +
                    ")");

            // Crear tabla pagos
            stmt.execute("CREATE TABLE IF NOT EXISTS global_chest (" +
                    "chest_contents TEXT" +
                    ")");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static List<String> leerTabla(String nombreTabla) {
	    String query = "SELECT * FROM " + nombreTabla;
	    List<String> aux = new ArrayList<>();

	    try (Connection conn = DriverManager.getConnection(URL);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {
	        ResultSetMetaData metaData = rs.getMetaData();
	        int columnCount = metaData.getColumnCount();

	        while (rs.next()) {
	            StringBuilder dataBuilder = new StringBuilder();

	            for (int i = 1; i <= columnCount; i++) {
	                String value = rs.getString(i);
	                dataBuilder.append(value);

	                if (i < columnCount) {
	                    dataBuilder.append(";");
	                }
	            }

	            aux.add(dataBuilder.toString());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return aux;
	}
    
    public static void agregarDatos(String nombreTabla, String[] valores) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO ").append(nombreTabla).append(" VALUES (");

        for (int i = 0; i < valores.length; i++) {
            queryBuilder.append("?");

            if (i < valores.length - 1) {
                queryBuilder.append(", ");
            }
        }

        queryBuilder.append(")");

        String query = queryBuilder.toString();

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            for (int i = 0; i < valores.length; i++) {
                stmt.setString(i + 1, valores[i]);
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modificarDatos(String nombreTabla, String columnaBuscar, String valorBuscar, String[] columnas, String[] nuevosValores) {
        if (columnas.length != nuevosValores.length) {
        	Main.plugin.getLogger().severe("Error con el número de columnas");
        }

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE ").append(nombreTabla).append(" SET ");

        for (int i = 0; i < columnas.length; i++) {
            queryBuilder.append(columnas[i]).append("=?");

            if (i < columnas.length - 1) {
                queryBuilder.append(", ");
            }
        }

        queryBuilder.append(" WHERE ").append(columnaBuscar).append("=?");

        String query = queryBuilder.toString();

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            for (int i = 0; i < nuevosValores.length; i++) {
                stmt.setString(i + 1, nuevosValores[i]);
            }

            stmt.setString(nuevosValores.length + 1, valorBuscar);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void modificarDatosDobleEntrada(String nombreTabla, String columna1, String dato1, String columna2, String dato2, String[] columnas, String[] nuevosValores) {
        if (columnas.length != nuevosValores.length) {
        	Main.plugin.getLogger().severe("Error con el número de columnas");
        }

        String query = "UPDATE "+nombreTabla+" SET ";

        for (int i = 0; i < columnas.length; i++) {
            query += columnas[i] + " = ?";

            if (i < columnas.length - 1) {
                query += ", ";
            }
        }

        query += " WHERE "+columna1+" = ? AND "+columna2+" = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            for (int i = 0; i < nuevosValores.length; i++) {
                stmt.setString(i + 1, nuevosValores[i]);
            }

            stmt.setString(nuevosValores.length + 1, dato1);
            stmt.setString(nuevosValores.length + 2, dato2);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<String> buscarDatos(String nombreTabla, String columna, String valor) {
        String query = "SELECT * FROM " + nombreTabla + " WHERE " + columna + " = ?";
        List<String> aux = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, valor);

            try (ResultSet rs = stmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    StringBuilder dataBuilder = new StringBuilder();

                    for (int i = 1; i <= columnCount; i++) {
                        String value = rs.getString(i);
                        dataBuilder.append(value);

                        if (i < columnCount) {
                            dataBuilder.append(";");
                        }
                    }

                    aux.add(dataBuilder.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aux;
    }
    
    public static List<String> buscarDatosDobleEntrada(String nombreTabla, String columna1, String dato1, String columna2, String dato2) {
        
        // Crear la consulta SQL con las condiciones de búsqueda por nombre y apellidos
        String query = "SELECT * FROM " + nombreTabla + " WHERE " + columna1 + " LIKE ? AND " + columna2 + " LIKE ?";
        List<String> aux = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + dato1 + "%");
            stmt.setString(2, "%" + dato2 + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
            	ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                while (rs.next()) {
                	StringBuilder dataBuilder = new StringBuilder();

                    for (int i = 1; i <= columnCount; i++) {
                        String value = rs.getString(i);
                        dataBuilder.append(value);

                        if (i < columnCount) {
                            dataBuilder.append(";");
                        }
                    }

                    aux.add(dataBuilder.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aux;
    }



    public static void eliminarDatos(String nombreTabla, String columnaId, String id) {
        String query = "DELETE FROM " + nombreTabla + " WHERE " + columnaId + "=?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void eliminarDatosDobleEntrada(String nombreTabla, String columna1, String valor1, String columna2, String valor2) {
        String query = "DELETE FROM " + nombreTabla + " WHERE " + columna1 + " = ? AND " + columna2 + " = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, valor1);
            stmt.setString(2, valor2);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static String getValorPorPropiedad(String nombreTabla, String columnaBuscar, String valorBuscar, String columnaObtener) {
        String query = "SELECT " + columnaObtener + " FROM " + nombreTabla + " WHERE " + columnaBuscar + " = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, valorBuscar);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(columnaObtener);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
}
