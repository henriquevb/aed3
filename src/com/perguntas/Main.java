package com.perguntas;


import com.perguntas.crud.CRUD;
import com.perguntas.models.User;
import com.perguntas.pcv.PCVEmail;
import com.perguntas.structures.HashExtensivel;
import com.perguntas.telas.TelaInicioPosLogin;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static final String USER_PATH = "db/user/";
    private static final String EMAIL_PATH = "db/email/";
    private static final String PERGUNTAS_PATH = "db/perguntas/";

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        try {
            File d = new File("db");
            if (!d.exists()) d.mkdir();

            File userFile = new File(USER_PATH);
            if (!userFile.exists()) userFile.mkdir();

            File emailFile = new File(EMAIL_PATH);
            if (!emailFile.exists()) emailFile.mkdir();

            File perguntasFile = new File(PERGUNTAS_PATH);
            if (!emailFile.exists()) perguntasFile.mkdir();

            new File(USER_PATH + "user.db").delete();
            new File(USER_PATH + "hash_d.db").delete();
            new File(USER_PATH + "hash_c.db").delete();
            new File(EMAIL_PATH + "hash_c.db").delete();
            new File(EMAIL_PATH + "hash_c.db").delete();
            new File(PERGUNTAS_PATH + "perguntas.db").delete();
            new File(PERGUNTAS_PATH + "arvore_b.db").delete();

            CRUD<User> userCRUD = new CRUD<User>(User.class.getConstructor(), USER_PATH + "user.db");
            HashExtensivel<PCVEmail> hashEmail = new HashExtensivel<>(PCVEmail.class.getConstructor(), 4, EMAIL_PATH + "hash_d.db", EMAIL_PATH + "hash_c.db");

            int option;
            do {
                System.out.println("\n\n-------------------------------");
                System.out.println("        PERGUNTAS 1.0");
                System.out.println("-------------------------------");
                System.out.println("\nACESSO");
                System.out.println("\n1) Acesso ao sistema");
                System.out.println("2) Novo usu??rio (primeiro acesso)");
                System.out.println("\n0) Sair");
                System.out.print("\nOp????o: ");

                try {
                    option = Integer.parseInt(console.nextLine());
                } catch (NumberFormatException e) {
                    option = -1;
                }

                switch (option) {
                    case 1: {
                        System.out.println("\nACESSO AO SISTEMA");

                        System.out.print("E-mail: ");
                        String email = console.nextLine();

                        PCVEmail pcvEmail = hashEmail.read(email.hashCode());
                        if (pcvEmail == null) {
                            System.out.println("\nUsu??rio n??o encontrado!");
                            continue;
                        }

                        int id = Integer.parseInt(pcvEmail.toString().split(";")[1]);

                        User read = userCRUD.read(id);
                        System.out.print("Senha: ");
                        String password = console.nextLine();

                        if (password.equals(read.getPassword())) {
                            TelaInicioPosLogin inicio = new TelaInicioPosLogin();
                            inicio.init(id);
                        } else {
                            System.out.println("\nSenha incorreta!");
                            continue;
                        }
                    }
                    break;
                    case 2: {
                        System.out.println("\nNOVO USU??RIO");

                        System.out.print("E-mail: ");
                        String email = console.nextLine();
                        PCVEmail pcvEmail = hashEmail.read(email.hashCode());

                        if (email.isEmpty()) continue;

                        if (pcvEmail != null) {
                            System.out.println("\nEmail j?? cadastrado!");
                            continue;
                        }

                        System.out.print("Nome: ");
                        String name = console.nextLine();
                        System.out.print("Senha: ");
                        String password = console.nextLine();

                        System.out.println("\nRESUMO");
                        System.out.println("Nome: " + name);
                        System.out.println("Email: " + email);
                        System.out.println("Senha: " + password);
                        System.out.print("\nDeseja concluir o cadastro? (1) ");
                        int next;

                        try {
                            next = Integer.parseInt(console.nextLine());
                        } catch (NumberFormatException e) {
                            next = -1;
                        }

                        if (next != 1) continue;

                        int id = userCRUD.create(new User(name, email, password));
                        hashEmail.create(new PCVEmail(email, id));
                        System.out.println("\nUsu??rio cadastrado com sucesso!");
                    }
                    break;
                    case 0:
                        break;
                    default:
                        System.out.println("Op????o inv??lida");
                }
            } while (option != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        console.close();
    }
}