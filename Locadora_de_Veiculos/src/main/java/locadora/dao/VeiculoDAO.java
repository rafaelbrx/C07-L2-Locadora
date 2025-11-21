package locadora.dao;

import locadora.connection.ConnectionDAO;
import locadora.classes.Veiculo;

import java.sql.SQLException;

public class VeiculoDAO extends ConnectionDAO {

    //INSERT - INSERE NOVO VEICULO
    public boolean insertVeiculo(Veiculo v) {
        connectToDb();
        String sql = "INSERT INTO Veiculo(modelo, placa, ano_fabricacao, disponibilidade) VALUES (?, ?, ?, ?)";

        try{
            pst = connection.prepareStatement(sql);

            pst.setString(1, v.getModelo());
            pst.setString(2, v.getPlaca());
            pst.setInt(3, v.getAno_fabricacao());
            pst.setBoolean(4, v.isDisponibilidade());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir veículo: " + e.getMessage());
            return false;

        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    //UPDATE - ATUALIZA TODOS OS DADOS DO VEÍCULO PARA ID X
    public boolean updateVeiculo(Veiculo v) {
        connectToDb();
        String sql = "UPDATE Veiculo SET modelo=?, placa=?, ano_fabricacao=?, disponibilidade=? WHERE id_veiculo=?";

        try{
            pst = connection.prepareStatement(sql);

            pst.setString(1, v.getModelo());
            pst.setString(2, v.getPlaca());
            pst.setInt(3, v.getAno_fabricacao());
            pst.setBoolean(4, v.isDisponibilidade());
            pst.setInt(5, v.getId_veiculo());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar veículo: " + e.getMessage());
            return false;

        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    //DELETE - Remover veículo pela placa
    public boolean deleteVeiculo(Veiculo v) {
        connectToDb();

        try {
            String sqlBusca = "SELECT id_veiculo FROM Veiculo WHERE placa=?";
            pst = connection.prepareStatement(sqlBusca);
            pst.setString(1, v.getPlaca());
            rs = pst.executeQuery();

            if (!rs.next()) {
                System.out.println("Veículo não encontrado.");
                return false;
            }

            int idVeiculo = rs.getInt("id_veiculo");
            pst.close();

            String sqlDelVinculo = "DELETE FROM Veiculo_Categoria WHERE id_veiculo=?";
            pst = connection.prepareStatement(sqlDelVinculo);
            pst.setInt(1, idVeiculo);
            pst.execute();
            pst.close();

            String sqlDelVeiculo = "DELETE FROM Veiculo WHERE id_veiculo=?";
            pst = connection.prepareStatement(sqlDelVeiculo);
            pst.setInt(1, idVeiculo);
            pst.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar veículo: " + e.getMessage());
            return false;

        } finally {
            try {
                if (pst != null) pst.close();
                if (rs != null) rs.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }


    //SELECT - Busca veículo pela placa
    public boolean selectVeiculo(Veiculo v) {
        connectToDb();
        String sql = "SELECT * FROM Veiculo WHERE placa=?";

        try{
            pst = connection.prepareStatement(sql);

            pst.setString(1, v.getPlaca());
            rs = pst.executeQuery();

            if (rs.next()) {
                v.setId_veiculo(rs.getInt("id_veiculo"));
                v.setModelo(rs.getString("modelo"));
                v.setAno_fabricacao(rs.getInt("ano_fabricacao"));
                v.setDisponibilidade(rs.getBoolean("disponibilidade"));
                return true;
            } else return false;

        } catch (SQLException e) {
            System.out.println("Erro ao buscar veículo: " + e.getMessage());
            return false;

        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    //Adicionando categoria ao veículo
    public boolean addCategoriaVeiculo(int idVeiculo, int idCategoria){
        connectToDb();
        String sql = "INSERT INTO Veiculo_Categoria(id_veiculo, id_categoria) VALUES (?, ?)";

        try{
            pst = connection.prepareStatement(sql);

            pst.setInt(1, idVeiculo);
            pst.setInt(2, idCategoria);

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao vincular categoria ao veículo: " + e.getMessage());
            return false;

        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}