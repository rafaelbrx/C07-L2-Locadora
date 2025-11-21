package locadora;

import locadora.dao.*;
import locadora.classes.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ClienteDAO clienteDAO = new ClienteDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ReservaDAO reservaDAO = new ReservaDAO();
        PagamentoDAO pagamentoDAO = new PagamentoDAO();
        SelectDAO selectDAO = new SelectDAO();

        int op = -1;

        while (op != 0) {
            System.out.println("\n\uD83D\uDE97 Locadora de Veículos (\uD83D\uDEA7 Acesso Funcionário)");
            System.out.println("\n1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Veículos");
            System.out.println("3 - Gerenciar Categorias");
            System.out.println("4 - Gerenciar Reservas");
            System.out.println("5 - Gerenciar Pagamentos");
            System.out.println("6 - Relatórios");
            System.out.println("0 - Sair");
            System.out.print("\nEscolha: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> clienteMenu(sc, clienteDAO);

                case 2 -> veiculoMenu(sc, veiculoDAO);

                case 3 -> categoriaMenu(sc, categoriaDAO);

                case 4 -> reservaMenu(sc, reservaDAO);

                case 5 -> pagamentoMenu(sc, pagamentoDAO);

                case 6 -> selectMenu(sc, selectDAO);

                case 0 -> System.out.println("Desligando o programa...");
                default -> System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }

    // Menu de Gerenciamento de Clientes
    private static void clienteMenu(Scanner sc, ClienteDAO dao) {
        System.out.println("\n\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67\u200D\uD83D\uDC66 Clientes");
        System.out.println("\n1 - Inserir");
        System.out.println("2 - Atualizar");
        System.out.println("3 - Deletar");
        System.out.println("4 - Buscar");
        System.out.print("\nEscolha: ");
        int op = sc.nextInt(); sc.nextLine();

        Cliente c = new Cliente();

        switch(op){
            case 1 -> {
                System.out.print("Nome: ");
                c.setNome(sc.nextLine());
                System.out.print("CPF: ");
                c.setCpf(sc.nextLine());
                System.out.print("Telefone: ");
                c.setTelefone(sc.nextLine());
                System.out.print("Email: ");
                c.setEmail(sc.nextLine());
                System.out.println(dao.insertCliente(c) ? "Inserido!" : "Erro!");
            }

            case 2 -> {
                System.out.print("Nome atual do cliente: ");
                c.setNome(sc.nextLine());
                if (!dao.selectCliente(c)) {
                    System.out.println("Cliente não encontrado!");
                    break;
                }
                System.out.print("Novo CPF: ");
                c.setCpf(sc.nextLine());
                System.out.print("Novo Telefone: ");
                c.setTelefone(sc.nextLine());
                System.out.print("Novo Email: ");
                c.setEmail(sc.nextLine());
                System.out.println(dao.updateCliente(c) ? "Atualizado!" : "Erro!");
            }

            case 3 -> {
                System.out.print("Nome do cliente para deletar: ");
                c.setNome(sc.nextLine());
                System.out.println(dao.deleteCliente(c) ? "Deletado!" : "Erro!");
            }

            case 4 -> {
                System.out.print("Nome do cliente: ");
                c.setNome(sc.nextLine());
                if(dao.selectCliente(c)){
                    System.out.println("\n✅ Cliente encontrado:");
                    System.out.println(c);
                }else{
                    System.out.println("Não encontrado.");
                }
            }
        }
    }

    // Menu de Gerenciamento de Veículos
    private static void veiculoMenu(Scanner sc, VeiculoDAO dao) {
        System.out.println("\n\uD83D\uDE97 Veículos");
        System.out.println("\n1 - Inserir");
        System.out.println("2 - Atualizar");
        System.out.println("3 - Deletar");
        System.out.println("4 - Buscar");
        System.out.println("5 - Vincular Categoria a Veículo");
        System.out.print("\nEscolha: ");
        int op = sc.nextInt(); sc.nextLine();

        Veiculo v = new Veiculo();

        switch(op){

            case 1 -> {
                System.out.print("Modelo: ");
                v.setModelo(sc.nextLine());
                System.out.print("Placa: ");
                v.setPlaca(sc.nextLine());
                System.out.print("Ano: ");
                v.setAno_fabricacao(sc.nextInt()); sc.nextLine();
                System.out.print("Disponível: (true / false) ");
                v.setDisponibilidade(sc.nextBoolean()); sc.nextLine();

                System.out.println(dao.insertVeiculo(v) ? "Inserido!" : "Erro!");
            }

            case 2 -> {
                System.out.print("ID do veículo: ");
                v.setId_veiculo(sc.nextInt()); sc.nextLine();

                System.out.print("Novo Modelo: ");
                v.setModelo(sc.nextLine());
                System.out.print("Nova Placa: ");
                v.setPlaca(sc.nextLine());
                System.out.print("Novo Ano: ");
                v.setAno_fabricacao(sc.nextInt()); sc.nextLine();
                System.out.print("Disponível: (true / false)");
                v.setDisponibilidade(sc.nextBoolean()); sc.nextLine();

                System.out.println(dao.updateVeiculo(v) ? "Atualizado!" : "Erro!");
            }

            case 3 -> {
                System.out.print("Placa do veículo para deletar: ");
                v.setPlaca(sc.nextLine());
                System.out.println(dao.deleteVeiculo(v) ? "Deletado!" : "Erro!");
            }

            case 4 -> {
                System.out.print("Placa do veículo: ");
                v.setPlaca(sc.nextLine());

                if(dao.selectVeiculo(v)){
                    System.out.println("\n✅ Encontrado:");
                    System.out.println(v);
                } else {
                    System.out.println("Não encontrado.");
                }
            }

            case 5 -> {
                System.out.print("ID do veículo: ");
                int idV = sc.nextInt();
                System.out.print("ID da categoria: ");
                int idC = sc.nextInt();
                sc.nextLine();
                boolean ok = dao.addCategoriaVeiculo(idV, idC);
                System.out.println(ok ? "Vinculado!" : "Erro ao vincular.");
            }
        }
    }

    // Menu de Gerenciamento de Categorias
    private static void categoriaMenu(Scanner sc, CategoriaDAO dao) {
        System.out.println("\n\uD83D\uDCDA Categorias");
        System.out.println("\n1 - Inserir");
        System.out.println("2 - Atualizar por nome");
        System.out.println("3 - Deletar");
        System.out.println("4 - Buscar");
        System.out.print("\nEscolha: ");
        int op = sc.nextInt(); sc.nextLine();

        Categoria c = new Categoria();

        switch(op){
            case 1 -> {
                System.out.print("Nome: ");
                c.setNome(sc.nextLine());
                System.out.print("Descrição: ");
                c.setDescricao(sc.nextLine());
                System.out.println(dao.insertCategoria(c) ? "Inserido!" : "Erro!");
            }

            case 2 -> {
                System.out.print("Nome atual: ");
                c.setNome(sc.nextLine());
                System.out.print("Nova descrição: ");
                c.setDescricao(sc.nextLine());
                System.out.println(dao.updateCategoria(c) ? "Atualizado!" : "Erro!");
            }

            case 3 -> {
                System.out.print("Nome da categoria: ");
                c.setNome(sc.nextLine());
                System.out.println(dao.deleteCategoria(c) ? "Deletado!" : "Erro!");
            }

            case 4 -> {
                System.out.print("Nome da categoria: ");
                c.setNome(sc.nextLine());
                if(dao.selectCategoria(c)){
                    System.out.println("\n✅ Encontrada:");
                    System.out.println(c);
                } else {
                    System.out.println("Não encontrada.");
                }
            }
        }
    }

    // Menu de Gerenciamento de Reservas
    private static void reservaMenu(Scanner sc, ReservaDAO dao) {
        System.out.println("\n\uD83D\uDCDD Reservas");
        System.out.println("\n1 - Inserir");
        System.out.println("2 - Deletar");
        System.out.println("3 - Buscar");
        System.out.print("\nEscolha: ");
        int op = sc.nextInt(); sc.nextLine();

        Reserva r = new Reserva();

        switch(op){
            case 1 -> {
                LocalDate hoje = LocalDate.now();
                r.setData_inicio(hoje.toString());
                System.out.println("Duração da reserva (dias): ");
                int duracao = sc.nextInt(); sc.nextLine();
                r.setDuracao_reserva(duracao);
                LocalDate fim = hoje.plusDays(duracao);
                r.setData_fim(fim.toString());
                System.out.print("ID Cliente: ");
                r.setIdR_cliente(sc.nextInt());
                System.out.print("ID Veículo: ");
                r.setIdR_veiculo(sc.nextInt());
                sc.nextLine();

                System.out.println(dao.insertReserva(r) ? "Inserido!" : "Erro!");
            }

            case 2 -> {
                System.out.print("ID da reserva: ");
                r.setId_reserva(sc.nextInt());
                System.out.println(dao.deleteReserva(r) ? "Deletado!" : "Erro!");
            }

            case 3 -> {
                System.out.print("ID da reserva: ");
                r.setId_reserva(sc.nextInt());
                if(dao.selectReserva(r)){
                    System.out.println("\n✅ Encontrada:");
                    System.out.println(r);
                } else {
                    System.out.println("Não encontrada.");
                }
            }
        }
    }

    // Menu de Gerenciamento de Pagamentos
    private static void pagamentoMenu(Scanner sc, PagamentoDAO dao) {
        System.out.println("\n\uD83D\uDCB8 Pagamentos");
        System.out.println("\n1 - Inserir");
        System.out.println("2 - Atualizar");
        System.out.println("3 - Deletar");
        System.out.println("4 - Buscar");
        System.out.print("\nEscolha: ");
        int op = sc.nextInt(); sc.nextLine();

        Pagamento p = new Pagamento();

        switch(op){
            case 1 -> {
                System.out.print("ID Reserva: ");
                p.setIdP_reserva(sc.nextInt());
                sc.nextLine();
                System.out.print("Valor: ");
                p.setValor(sc.nextDouble());
                sc.nextLine();
                System.out.print("Forma de pagamento: (Cartão de Crédito / Cartão de Débito / Depósito Bancário)");
                p.setForma_pagamento(sc.nextLine());
                System.out.print("Status: (true = pago / false = pendente) ");
                p.setStatus(sc.nextBoolean()); sc.nextLine();
                System.out.println(dao.insertPagamento(p) ? "Inserido!" : "Erro!");
            }

            case 2 -> {
                System.out.print("ID Pagamento: ");
                p.setId_pagamento(sc.nextInt());
                sc.nextLine();
                System.out.print("Novo Valor: ");
                p.setValor(sc.nextDouble());
                sc.nextLine();
                System.out.print("Nova Forma de pagamento: (Cartão de Crédito / Cartão de Débito / Depósito Bancário)");
                p.setForma_pagamento(sc.nextLine());
                System.out.print("Novo Status: (true = pago / false = pendente) ");
                p.setStatus(sc.nextBoolean());
                System.out.println("Novo ID da Reserva vinculada: ");
                p.setIdP_reserva(sc.nextInt());
                System.out.println(dao.updatePagamento(p) ? "Atualizado!" : "Erro!");
            }

            case 3 -> {
                System.out.print("ID Pagamento: ");
                p.setId_pagamento(sc.nextInt());
                System.out.println(dao.deletePagamento(p) ? "Deletado!" : "Erro!");
            }

            case 4 -> {
                System.out.print("ID Pagamento: ");
                p.setId_pagamento(sc.nextInt());
                if(dao.selectPagamento(p)){
                    System.out.println("\n✅ Encontrado:");
                    System.out.println(p);
                } else {
                    System.out.println("Não encontrado.");
                }
            }
        }
    }


    // Menu de Relatórios (6 Selects obrigatórios)
    private static void selectMenu(Scanner sc, SelectDAO dao) {
        System.out.println("\n\uD83D\uDCCB Relatórios");
        System.out.println("\n1 - Reservas por Cliente");
        System.out.println("2 - Pagamento de uma Reserva");
        System.out.println("3 - Reservas de um Veículo");
        System.out.println("4 - Categorias de um Veículo");
        System.out.println("5 - Veículos de uma Categoria");
        System.out.println("6 - Reserva Completa");
        System.out.print("\nEscolha: ");
        int op = sc.nextInt(); sc.nextLine();

        switch(op){

            case 1 -> {
                System.out.print("ID do cliente: ");
                int id = sc.nextInt();
                List<Reserva> lista = dao.listarReservasPorCliente(id);
                lista.forEach(System.out::println);
            }

            case 2 -> {
                System.out.print("ID da reserva: ");
                int id = sc.nextInt();
                Pagamento p = dao.buscarPagamentoDaReserva(id);
                System.out.println(p);
            }

            case 3 -> {
                System.out.print("ID do veículo: ");
                int id = sc.nextInt();
                List<Reserva> lista = dao.listarReservasDoVeiculo(id);
                lista.forEach(System.out::println);
            }

            case 4 -> {
                System.out.print("ID do veículo: ");
                int id = sc.nextInt();
                List<Categoria> lista = dao.listarCategoriasDoVeiculo(id);
                lista.forEach(System.out::println);
            }

            case 5 -> {
                System.out.print("ID da categoria: ");
                int id = sc.nextInt();
                List<Veiculo> lista = dao.listarVeiculosDaCategoria(id);
                lista.forEach(System.out::println);
            }

            case 6 -> {
                System.out.print("ID da reserva: ");
                int id = sc.nextInt();
                List<String> lista = dao.listarReservasVeiculo_Categoria(id);
                if(lista != null) lista.forEach(System.out::println);
                else System.out.println("Erro ou sem registros.");
            }
        }
    }
}
