package locadora.dao;

import locadora.connection.ConnectionDAO;
import locadora.classes.Categoria;

import java.sql.SQLException;

public class CategoriaDAO extends ConnectionDAO {

    //INSERT - INSERE NOVA CATEGORIA
    public boolean insertCategoria(Categoria ct) {
        connectToDb();
        String sql = "INSERT INTO categoria(nome, descricao) VALUES (?, ?)";

        try{
            pst = connection.prepareStatement(sql);

            pst.setString(1, ct.getNome());
            pst.setString(2, ct.getDescricao());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir categoria: " + e.getMessage());
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

    //UPDATE - Atualiza categoria pelo nome
    public boolean updateCategoria(Categoria ct) {
        connectToDb();
        String sql = "UPDATE categoria SET descricao = ? WHERE nome = ?";

        try{
            pst = connection.prepareStatement(sql);

            pst.setString(1, ct.getDescricao());
            pst.setString(2, ct.getNome());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar categoria: " + e.getMessage());
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

    //DELETE - Remove categoria pelo nome
    public boolean deleteCategoria(Categoria ct) {
        connectToDb();
        String sql = "DELETE FROM categoria WHERE nome = ?";

        try{
            pst = connection.prepareStatement(sql);

            pst.setString(1, ct.getNome());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar categoria: " + e.getMessage());
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

    //SELECT - Busca categoria por nome
    public boolean selectCategoria(Categoria ct) {
        connectToDb();
        String sql = "SELECT * FROM categoria WHERE nome = ?";

        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, ct.getNome());
            rs = pst.executeQuery();

            if(rs.next()){
                ct.setId_categoria(rs.getInt("id_categoria"));
                ct.setNome(rs.getString("nome"));
                ct.setDescricao(rs.getString("descricao"));

                return true;
            } else return false;

        } catch (SQLException e) {
            System.out.println("Erro ao selecionar categoria: " + e.getMessage());
            return false;

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
