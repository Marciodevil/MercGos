/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produto;

/**
 *
 * @author DEVIL NOTE
 */
public class ProdutoDAO {
    public void create (Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO dbmercadinho.produto(descricao,qtd,preco)VALUES(?,?,?)");
            stmt.setString(1, p.getDescricao());
            stmt.setInt(2, p.getQtd());
            stmt.setDouble(3, p.getPreco());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Gravado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!\n"+ex);
            
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public List<Produto> read() {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM dbmercadinho.produto");
            rs = stmt.executeQuery();
            while(rs.next()){
                Produto produto = new Produto();
                produto.setIdP(rs.getInt("idP"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQtd(rs.getInt("qtd"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;
    }
    
    
        public void update (Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE dbmercadinho.produto SET descricao = ?, qtd = ?, preco = ? WHERE idP = ?");
            stmt.setString(2, p.getDescricao());
            stmt.setInt(3, p.getQtd());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(1, p.getIdP());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!\n"+ex);
            
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
}
