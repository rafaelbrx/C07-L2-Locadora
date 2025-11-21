package locadora.dao;

import locadora.connection.ConnectionDAO;
import locadora.classes.Cliente;
import java.sql.SQLException;

public class ClienteDAO extends ConnectionDAO {

    //INSERT - INSERE NOVO CLIENTE
    public boolean insertCliente(Cliente c) {
        connectToDb();
        String sql = "INSERT INTO Cliente(nome, cpf, telefone, email) VALUES (?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, c.getNome());
            pst.setString(2, c.getCpf());
            pst.setString(3, c.getTelefone());
            pst.setString(4, c.getEmail());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
            return false;

        } finally { //liberar recursos - fecha statement e conexao
            try{
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch(SQLException e){
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    //UPDATE - ALTERA TODOS OS DADOS PARA ID X
    public boolean updateCliente(Cliente c) {
        connectToDb();
        String sql = "UPDATE Cliente SET nome=?, cpf=?, telefone=?, email=? WHERE id_cliente=?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, c.getNome());
            pst.setString(2, c.getCpf());
            pst.setString(3, c.getTelefone());
            pst.setString(4, c.getEmail());
            pst.setInt(5, c.getId());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            return false;

        } finally { //liberar recursos - fecha statement e conexao
            try{
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch(SQLException e){
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    //DELETE - APAGA CLIENTE DA TABELA
    public boolean deleteCliente(Cliente c) {
        connectToDb();
        String sql = "DELETE FROM Cliente WHERE nome=?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, c.getNome());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar cliente: " + e.getMessage());
            return false;

        } finally { //liberar recursos - fecha statement e conexao
            try{
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch(SQLException e){
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    //SELECT - BUSCA POR NOME
    public boolean selectCliente(Cliente c) {
        connectToDb();
        String sql = "SELECT * FROM Cliente WHERE nome=?";

        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, c.getNome());
            rs = pst.executeQuery();

            if(rs.next()){
                c.setId(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                return true;
            } else return false;

        } catch (SQLException e) {
            System.out.println("Erro ao selecionar cliente: " + e.getMessage());
            return false;

        } finally { //libera recursos
            try{
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch(SQLException e){
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}