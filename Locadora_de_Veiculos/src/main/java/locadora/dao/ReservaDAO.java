package locadora.dao;

import locadora.connection.ConnectionDAO;
import locadora.classes.Reserva;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO extends ConnectionDAO {

    //INSERT - Insere nova reserva
    public boolean insertReserva(Reserva r) {
        connectToDb();
        String sql = "INSERT INTO Reserva (data_inicio, data_fim, duracao_reserva, idR_cliente, idR_veiculo) VALUES (?, ?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, r.getData_inicio());
            pst.setString(2, r.getData_fim());
            pst.setInt(3, r.getDuracao_reserva());
            pst.setInt(4, r.getIdR_cliente());
            pst.setInt(5, r.getIdR_veiculo());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir reserva: " + e.getMessage());
            return false;

        } finally {
            try { if(pst != null) pst.close(); if(connection != null) connection.close(); }
            catch(SQLException e){ System.out.println("Erro ao fechar recursos: " + e.getMessage()); }
        }
    }

    //SELECT - Busca reserva pelo ID
    public boolean selectReserva(Reserva r) {
        connectToDb();
        String sql = "SELECT * FROM Reserva WHERE id_reserva=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, r.getId_reserva());
            rs = pst.executeQuery();

            if(rs.next()){
                r.setData_inicio(rs.getString("data_inicio"));
                r.setData_fim(rs.getString("data_fim"));
                r.setDuracao_reserva(rs.getInt("duracao_reserva"));
                r.setIdR_cliente(rs.getInt("idR_cliente"));
                r.setIdR_veiculo(rs.getInt("idR_veiculo"));
                return true;
            } else return false;

        } catch (SQLException e) {
            System.out.println("Erro ao buscar reserva: " + e.getMessage());
            return false;
        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    //UPDATE - Atualiza todos os dados da reserva pelo id
    public boolean updateReserva(Reserva r) {
        connectToDb();
        String sql = "UPDATE Reserva SET data_inicio=?, data_fim=?, duracao_reserva=?, idR_cliente=?, idR_veiculo=? WHERE id_reserva=?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, r.getData_inicio());
            pst.setString(2, r.getData_fim());
            pst.setInt(3, r.getDuracao_reserva());
            pst.setInt(4, r.getIdR_cliente());
            pst.setInt(5, r.getIdR_veiculo());
            pst.setInt(6, r.getId_reserva());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar reserva: " + e.getMessage());
            return false;

        } finally {
            try { if(pst != null) pst.close(); if(connection != null) connection.close(); }
            catch(SQLException e){ System.out.println("Erro ao fechar recursos: " + e.getMessage()); }
        }
    }

    //DELETE - Deletar reserva pelo ID
    public boolean deleteReserva(Reserva r) {
        connectToDb();
        String sql = "DELETE FROM Reserva WHERE id_reserva=?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setInt(1, r.getId_reserva());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar reserva: " + e.getMessage());
            return false;

        } finally {
            try { if(pst != null) pst.close(); if(connection != null) connection.close(); }
            catch(SQLException e){ System.out.println("Erro ao fechar recursos: " + e.getMessage()); }
        }
    }

    //LISTA DE RESERVAS DO CLIENTE
    public List<Reserva> listarReservasPorCliente(int idCliente) {
        connectToDb();
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM Reserva WHERE idR_cliente=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idCliente);
            rs = pst.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setId_reserva(rs.getInt("id_reserva"));
                r.setData_inicio(rs.getString("data_inicio"));
                r.setData_fim(rs.getString("data_fim"));
                r.setDuracao_reserva(rs.getInt("duracao_reserva"));
                r.setIdR_cliente(rs.getInt("idR_cliente"));
                r.setIdR_veiculo(rs.getInt("idR_veiculo"));
                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar reservas do cliente: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return lista;
    }
}