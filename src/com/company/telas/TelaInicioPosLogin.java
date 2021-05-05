package com.company.telas;

import java.io.IOException;
import java.util.Scanner;

public class TelaInicioPosLogin {

    Scanner console = new Scanner(System.in);
    TelaCriacaoDePerguntas criacaoDePerguntas = new TelaCriacaoDePerguntas();

    public TelaInicioPosLogin() throws IOException {
    }

    public void init(int id) throws Exception {
        int option;

        System.out.println("\n\n-------------------------------");
        System.out.println("        PERGUNTAS 1.0");
        System.out.println("-------------------------------");
        System.out.println("\nINICIO");
        System.out.println("\n1) Criação de perguntas");
        System.out.println("2) Consultar/responder perguntas");
        System.out.println("3) Notificações: 0");
        System.out.println("\n0) Sair");
        System.out.print("\nOpção: ");

        try {
            option = Integer.parseInt(console.nextLine());
        } catch (NumberFormatException e) {
            option = -1;
        }

        switch (option) {
            case 1:
                criacaoDePerguntas.init(id);
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida ou não implementada");
        }


    }
}
