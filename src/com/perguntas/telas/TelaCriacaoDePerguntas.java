package com.perguntas.telas;

import com.perguntas.structures.ArvoreBMais;
import com.perguntas.crud.CRUD;
import com.perguntas.models.Pergunta;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TelaCriacaoDePerguntas {

    private static final String PERGUNTAS_PATH = "db/perguntas/";
    private final ArvoreBMais arvoreBMais = new ArvoreBMais(44, "arvore_b.db");

    Scanner console = new Scanner(System.in);
    private final TelaInicioPosLogin telaInicioPosLogin;

    public TelaCriacaoDePerguntas(final TelaInicioPosLogin telaInicioPosLogin) throws IOException {
        this.telaInicioPosLogin = telaInicioPosLogin;
    }

    public void init(int userId) throws Exception {
        CRUD<Pergunta> perguntaCRUD = new CRUD<Pergunta>(Pergunta.class.getConstructor(),  "perguntas.db");

        int option;
        do {
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


            try {
                option = Integer.parseInt(console.nextLine());
            } catch (NumberFormatException e) {
                option = -1;
            }

            switch (option) {
                case 1:
                    int [] idsPerguntas = arvoreBMais.read(userId);

                    if(idsPerguntas.length > 0) {
                        for(int i = 0; i < idsPerguntas.length; i++) {
                            Pergunta pergunta = perguntaCRUD.read(idsPerguntas[i]);

                            if(pergunta != null) {
                                SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
                                Date date = new Date(pergunta.getCriacao());

                                System.out.println("\n" + (i+1) + ".");
                                System.out.println(formatter.format(date));
                                System.out.println(pergunta.getPergunta());
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("\nPergunta: ");
                    String pergunta = console.nextLine();

                    if(!pergunta.isEmpty()) {
                        int idPergunta = perguntaCRUD.create(new Pergunta(userId, pergunta));
                        arvoreBMais.create(userId, idPergunta);
                    }
                    break;
                case 3:
                    //Alterar
                    System.out.println("Alterar: A ser implementado");
                    break;
                case 4:
                    //Arquivar
                    System.out.println("Arquivar: A ser implementado");
                    break;
                case 0:
                    telaInicioPosLogin.init(userId);
                    break;
                default:
                    System.out.println("Opção inválida ou não implementada");
            }
        } while(option != 0);
    }
}
