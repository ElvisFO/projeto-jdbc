package com.jdbc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.conexao.ConexaoFactory;
import com.jdbc.model.Aluno;

public class AlunoDAO {

	public static void insert(Aluno aluno) {
        if (aluno == null) {
            System.out.println("Não foi possivel fazer a inserção.");
            return;
        }
        Connection connection = ConexaoFactory.getConnection();
        PreparedStatement stmt = null;
        String query = "insert into pooaula.aluno(nome, cpf, idade) values (?, ?, ?)";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setInt(3, aluno.getIdade());
            stmt.execute();
            System.out.println("Registro inserido com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConexaoFactory.close(connection, stmt);
        }
    }

    public static void delete(Aluno aluno) {
        if (aluno == null || aluno.getId() == null) {
            System.out.println("Não foi possivel deletar aluno");
            return;
        }
        Connection connection = ConexaoFactory.getConnection();
        PreparedStatement stmt = null;
        String query = "delete from pooaula.aluno where id = ? ";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, aluno.getId());
            stmt.execute();
            System.out.println("Aluno deletado com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConexaoFactory.close(connection, stmt);
        }

    }

    public static void update(Aluno aluno) {
        if (aluno == null) {
            System.out.println("Não foi possivel atualizar os dados do aluno.");
            return;
        }
        Connection connection = ConexaoFactory.getConnection();
        PreparedStatement stmt = null;
        String query = "UPDATE `pooaula`.`aluno` SET `nome`= ?, `cpf`= ?, `idade`= ? WHERE `id`= ?";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setInt(3, aluno.getIdade());
            stmt.setInt(4, aluno.getId());
            stmt.execute();
            System.out.println("Registro atualizado com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConexaoFactory.close(connection, stmt);
        }
    }

    public static List<Aluno> selectAll() {
        Connection connection = ConexaoFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> alunoList = null;
        String query = "SELECT * FROM pooaula.aluno;";

        try {
            alunoList = new ArrayList<Aluno>();
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                alunoList.add(new Aluno(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("cpf"), rs.getInt("idade")));
            }
            return alunoList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConexaoFactory.close(connection, stmt, rs);
        }
        return null;
    }

    public static List<Aluno> serchByName(String nome) {
        Connection connection = ConexaoFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> alunoList = null;
        String query = "SELECT * FROM pooaula.aluno where nome like ?";

        try {
            alunoList = new ArrayList<Aluno>();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, '%' + nome + '%');
            rs = stmt.executeQuery();

            while (rs.next()) {
                alunoList.add(new Aluno(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("cpf"), rs.getInt("idade")));
            }
            return alunoList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConexaoFactory.close(connection, stmt, rs);
        }
        return null;
    }
}
