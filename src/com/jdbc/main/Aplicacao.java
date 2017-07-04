package com.jdbc.main;

import java.util.List;

import com.jdbc.controller.AlunoDAO;
import com.jdbc.model.Aluno;

public class Aplicacao {

	public static void main(String[] args) {
		inserirAluno();
        //deletarAluno();
        //atualizarAluno();
        //List<Aluno> alunos = selecionarTudo();
        //List<Aluno> alunos2 = buscarPorNome("Joao");
        //stem.out.println(alunos2);
    }

    public static void inserirAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("Elvis");
        aluno.setCpf("111.111.111-11");
        aluno.setIdade(24);
        AlunoDAO.insert(aluno);
    }

    public static void deletarAluno() {
        Aluno aluno = new Aluno();
        aluno.setId(1);
        AlunoDAO.delete(aluno);
    }

    public static void atualizarAluno() {
        Aluno aluno = new Aluno("João", "111.111.111-11", 32);
        AlunoDAO.update(aluno);
    }

    public static List<Aluno> selecionarTudo() {
        return AlunoDAO.selectAll();
    }

    public static List<Aluno> buscarPorNome(String nome) {
        return AlunoDAO.serchByName(nome);
    }
}
