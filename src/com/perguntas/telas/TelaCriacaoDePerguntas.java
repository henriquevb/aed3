package com.perguntas.telas;

import com.perguntas.structures.ArvoreBMais;
import com.perguntas.crud.CRUD;
import com.perguntas.models.Pergunta;

import java.io.IOException;
import java.util.Scanner;

public class TelaCriacaoDePerguntas {

    private static final String PERGUNTAS_PATH = "db/perguntas/";
    private ArvoreBMais arvoreBMais = new ArvoreBMais(44, PERGUNTAS_PATH + "arvore_b.db");

    Scanner console = new Scanner(System.in);

    public TelaCriacaoDePerguntas() throws IOException {
    }

    public void init(int id) throws Exception {
        CRUD<Pergunta> perguntaCRUD = new CRUD<Pergunta>(Pergunta.class.getConstructor(),  PERGUNTAS_PATH +"perguntas.db");

        System.out.println("\n\n-------------------------------");
        System.out.println("        PERGUNTAS 1.0");
        System.out.println("-------------------------------");
        System.out.println("\nINICIO > CRIAÇÃO DE PERGUNTAS");
        System.out.println("\n1) Listar");
        System.out.println("2) Incluir");
        System.out.println("3) Alterar");
        System.out.println("4) Arquivar");
        System.out.println("\n0) Retornar ao menu anterior");
        System.out.print("\nOpção: ");

        int option;

        try {
            option = Integer.parseInt(console.nextLine());
        } catch (NumberFormatException e) {
            option = -1;
        }

        switch (option) {
            case 1:
                //Listar
                break;
            case 2:
                System.out.println("\nPergunta: ");
                String pergunta = console.nextLine();

                if(perguntaCRUD != null && !pergunta.isEmpty()) {
                    int idPergunta = perguntaCRUD.create(new Pergunta(id, pergunta));
                    arvoreBMais.create(id, idPergunta);
                }
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida ou não implementada");
        }
    }
}
