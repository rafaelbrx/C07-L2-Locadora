package locadora.dao;

import locadora.connection.ConnectionDAO;
import locadora.classes.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectDAO extends ConnectionDAO {

    //-----------------------------
    //JOIN SEM TABELA INTERMEDIARIA
    //-----------------------------


    // (1) Cliente → Reservas
    public List<Reserva> listarReservasPorCliente(int idCliente) {
        connectToDb();
        List<Reserva> lista = new ArrayList<>();

        String sql = """
                SELECT r.* 
                FROM Reserva r
                JOIN Cliente c ON r.idR_cliente = c.id_cliente
                WHERE r.idR_cliente = ?;
                """;

        /* Usando o JOIN apenas para validar os ids (Requisito de Entrega)
         SELECT p.* FROM Reserva r WHERE r.idR_rcliente = ?;
         */

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idCliente);
            rs = pst.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setId_reserva(rs.getInt("id_reserva"));
                r.setIdR_cliente(rs.getInt("idR_cliente"));
                r.setIdR_veiculo(rs.getInt("idR_veiculo"));
                r.setData_inicio(rs.getString("data_inicio"));
                r.setData_fim(rs.getString("data_fim"));
                r.setDuracao_reserva(rs.getInt("duracao_reserva"));
                lista.add(r);
            }

            return lista;

        } catch (SQLException e) {
            System.out.println("Erro ao listar reservas do cliente: " + e.getMessage());
            return null;

        } finally {
            try { if (rs != null) rs.close(); if (pst != null) pst.close(); if (connection != null) connection.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar recursos: " + e.getMessage()); }
        }
    }

    // (2) Reserva → Pagamento
    public Pagamento buscarPagamentoDaReserva(int idReserva) {
        connectToDb();

        String sql = """
                SELECT p.*
                FROM Pagamento p
                JOIN Reserva r ON p.idP_reserva = r.id_reserva
                WHERE p.idP_reserva = ?;
                """;

         /* Usando o JOIN apenas para validar os ids (Requisito de Entrega)
         SELECT p.* FROM Pagamento p WHERE p.idP_reserva = ?;
         */

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idReserva);
            rs = pst.executeQuery();

            if (!rs.next()) return null;

            Pagamento p = new Pagamento();
            p.setId_pagamento(rs.getInt("id_pagamento"));
            p.setIdP_reserva(rs.getInt("idP_reserva"));
            p.setValor(rs.getDouble("valor"));
            p.setForma_pagamento(rs.getString("forma_pagamento"));
            p.setStatus(rs.getBoolean("status"));

            return p;

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pagamento: " + e.getMessage());
            return null;

        } finally {
            try { if (rs != null) rs.close(); if (pst != null) pst.close(); if (connection != null) connection.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar recursos: " + e.getMessage()); }
        }
    }

    // (3) Veículo → Reservas
    public List<Reserva> listarReservasDoVeiculo(int idVeiculo) {
        connectToDb();
        List<Reserva> lista = new ArrayList<>();

        String sql = """
            SELECT r.*
            FROM Reserva r
            JOIN Veiculo v ON r.idR_veiculo = v.id_veiculo
            WHERE r.idR_veiculo = ?;
            """;

        /* Usando o JOIN apenas para validar os ids (Requisito de Entrega)
         SELECT r.* FROM Reserva r WHERE r.idR_veiculo = ?;
        */

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idVeiculo);
            rs = pst.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setId_reserva(rs.getInt("id_reserva"));
                r.setIdR_cliente(rs.getInt("idR_cliente"));
                r.setIdR_veiculo(rs.getInt("idR_veiculo"));
                r.setData_inicio(rs.getString("data_inicio"));
                r.setData_fim(rs.getString("data_fim"));
                r.setDuracao_reserva(rs.getInt("duracao_reserva"));

                lista.add(r);
            }

            return lista;

        } catch (SQLException e) {
            System.out.println("Erro ao listar reservas do veículo: " + e.getMessage());
            return null;

        } finally {
            try { if (rs != null) rs.close(); if (pst != null) pst.close(); if (connection != null) connection.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar recursos: " + e.getMessage()); }
        }
    }

    //-----------------------------
    //JOIN COM TABELA INTERMEDIARIA
    //-----------------------------

    // (1) Categorias de um veículo (N:M)
    public List<Categoria> listarCategoriasDoVeiculo(int idVeiculo) {
        connectToDb();
        List<Categoria> lista = new ArrayList<>();

        String sql = """
                SELECT c.id_categoria, c.nome, c.descricao
                FROM Categoria c
                JOIN Veiculo_Categoria vc ON c.id_categoria = vc.id_categoria
                WHERE vc.id_veiculo = ?;
                """;

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idVeiculo);
            rs = pst.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId_categoria(rs.getInt("id_categoria"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                lista.add(c);
            }

            return lista;

        } catch (SQLException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
            return null;

        } finally {
            try { if (rs != null) rs.close(); if (pst != null) pst.close(); if (connection != null) connection.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar recursos: " + e.getMessage()); }
        }
    }

    // (2) Veículos de uma categoria (N:M)
    public List<Veiculo> listarVeiculosDaCategoria(int idCategoria) {
        connectToDb();
        List<Veiculo> lista = new ArrayList<>();

        String sql = """
                SELECT v.id_veiculo, v.modelo, v.placa, v.ano_fabricacao, v.disponibilidade
                FROM Veiculo v
                JOIN Veiculo_Categoria vc ON v.id_veiculo = vc.id_veiculo
                WHERE vc.id_categoria = ?;
                """;

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idCategoria);
            rs = pst.executeQuery();

            while (rs.next()) {
                Veiculo v = new Veiculo();
                v.setId_veiculo(rs.getInt("id_veiculo"));
                v.setModelo(rs.getString("modelo"));
                v.setPlaca(rs.getString("placa"));
                v.setAno_fabricacao(rs.getInt("ano_fabricacao"));
                v.setDisponibilidade(rs.getBoolean("disponibilidade"));
                lista.add(v);
            }

            return lista;

        } catch (SQLException e) {
            System.out.println("Erro ao listar veículos da categoria: " + e.getMessage());
            return null;

        } finally {
            try { if (rs != null) rs.close(); if (pst != null) pst.close(); if (connection != null) connection.close(); }
            catch (SQLException e) { System.out.println("Erro ao fechar recursos: " + e.getMessage()); }
        }
    }

    // (3) Reserva completa: Cliente + Veículo + Categoria
    public List<String> listarReservasVeiculo_Categoria(int idReserva) {
        connectToDb();
        List<String> lista = new ArrayList<>();

        String sql = """
            SELECT 
                r.id_reserva,
                r.data_inicio,
                r.duracao_reserva,

                c.nome AS cliente_nome,

                v.modelo AS veiculo,
                v.placa AS placa,

                cat.nome AS categoria

            FROM Reserva r
            JOIN Cliente c ON r.idR_cliente = c.id_cliente
            JOIN Veiculo v ON r.idR_veiculo = v.id_veiculo
            JOIN Veiculo_Categoria vc ON v.id_veiculo = vc.id_veiculo
            JOIN Categoria cat ON vc.id_categoria = cat.id_categoria
            WHERE r.id_reserva = ?
            ORDER BY r.id_reserva;
            """;

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idReserva);
            rs = pst.executeQuery();

            while (rs.next()) {
                String linha =
                        "Reserva #" + rs.getInt("id_reserva") +
                                " | Cliente: " + rs.getString("cliente_nome") +
                                " | Início: " + rs.getString("data_inicio") +
                                " | Duração: " + rs.getInt("duracao_reserva") + " dias" +
                                " | Veículo: " + rs.getString("veiculo") +
                                " (" + rs.getString("placa") + ")" +
                                " | Categoria: " + rs.getString("categoria");

                lista.add(linha);
            }

            return lista;

        } catch (SQLException e) {
            System.out.println("Erro ao listar reservas completas: " + e.getMessage());
            return null;

        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}