package locadora.dao;

import locadora.connection.ConnectionDAO;
import locadora.classes.Pagamento;
import java.sql.SQLException;

public class PagamentoDAO extends ConnectionDAO {

    //INSERT - Insere novo pagamento
    public boolean insertPagamento(Pagamento p) {
        connectToDb();
        String sql = "INSERT INTO Pagamento (valor, forma_pagamento, status, idP_reserva) VALUES (?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);

            pst.setDouble(1, p.getValor());
            pst.setString(2, p.getForma_pagamento());
            pst.setBoolean(3, p.isStatus());
            pst.setInt(4, p.getIdP_reserva());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir pagamento: " + e.getMessage());
            return false;

        } finally {
            try { if (pst != null) pst.close(); if (connection != null) connection.close(); }
            catch (SQLException e){ System.out.println("Erro ao fechar recursos: " + e.getMessage()); }
        }
    }

    //SELECT - Busca pagamento pelo ID
    public boolean selectPagamento(Pagamento p) {
        connectToDb();
        String sql = "SELECT * FROM Pagamento WHERE id_pagamento=?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setInt(1, p.getId_pagamento());
            rs = pst.executeQuery();

            if (rs.next()) {
                p.setValor(rs.getDouble("valor"));
                p.setForma_pagamento(rs.getString("forma_pagamento"));
                p.setStatus(rs.getBoolean("status"));
                p.setIdP_reserva(rs.getInt("idP_reserva"));
                return true;
            } else return false;

        } catch (SQLException e) {
            System.out.println("Erro ao selecionar pagamento: " + e.getMessage());
            return false;
        } finally {
            try { if(pst != null) pst.close(); if(rs != null) rs.close(); if(connection != null) connection.close(); }
            catch(SQLException e){ System.out.println("Erro ao fechar recursos: " + e.getMessage()); }
        }
    }

    //UPDATE - Atualizar pagamento pelo id
    public boolean updatePagamento(Pagamento p) {
        connectToDb();
        String sql = "UPDATE Pagamento SET valor=?, forma_pagamento=?, status=?, idP_reserva=? WHERE id_pagamento=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setDouble(1, p.getValor());
            pst.setString(2, p.getForma_pagamento());
            pst.setBoolean(3, p.isStatus());
            pst.setInt(4, p.getIdP_reserva());
            pst.setInt(5, p.getId_pagamento());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pagamento: " + e.getMessage());
            return false;

        } finally {
            try { if(pst != null) pst.close(); if(connection != null) connection.close(); }
            catch(SQLException e){ System.out.println("Erro ao fechar recursos: " + e.getMessage()); }
        }
    }

    //DELETE - Deletar pagamento pelo ID
    public boolean deletePagamento(Pagamento p) {
        connectToDb();
        String sql = "DELETE FROM Pagamento WHERE id_pagamento=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, p.getId_pagamento());
            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar pagamento: " + e.getMessage());
            return false;

        } finally {
            try { if(pst != null) pst.close(); if(connection != null) connection.close(); }
            catch(SQLException e){ System.out.println("Erro ao fechar recursos: " + e.getMessage()); }
        }
    }

    //SELECT - Busca pagamento pelo ID da Reserva
    public boolean selectPagamentoReserva(Pagamento p, int idReserva) {
        connectToDb();
        String sql = "SELECT * FROM Pagamento WHERE idP_reserva=?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setInt(1, idReserva);
            rs = pst.executeQuery();

            if (rs.next()) {
                p.setId_pagamento(rs.getInt("id_pagamento"));
                p.setValor(rs.getDouble("valor"));
                p.setForma_pagamento(rs.getString("forma_pagamento"));
                p.setStatus(rs.getBoolean("status"));
                p.setIdP_reserva(rs.getInt("idP_reserva"));
                return true;
            } else return false;

        } catch (SQLException e) {
            System.out.println("Erro ao selecionar pagamento: " + e.getMessage());
            return false;
        } finally {
            try { if(pst != null) pst.close(); if(rs != null) rs.close(); if(connection != null) connection.close(); }
            catch(SQLException e){ System.out.println("Erro ao fechar recursos: " + e.getMessage()); }
        }
    }
}