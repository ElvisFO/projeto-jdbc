package com.jdbc.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoFactory {

	private static final String STRING_CONEXAO = "jdbc:mysql://localhost:3306/pooaula";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection getConnection() {
        try {
            System.out.println("Conexão feita com Sucesso");
            return DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void close(Connection conn, PreparedStatement stmt) {
        close(conn);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
        close(conn, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
