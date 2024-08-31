package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery(); // traz o dado em forma de tabela. Retorna 0 ou 1

            // se retornar 1, é pq tem o objeto
            if (rs.next()) {
                // criando um objeto associado
                Department dep = instantiateDepartment(rs);
                Seller seller = instantiateSeller(rs);
                seller.setDepartment(dep);  // associando a classe department
                return seller;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    // Não precisa capturar a excecoes, pois onde o método e chamando ja captura
    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }

    private Seller instantiateSeller(ResultSet rs) throws SQLException {
        Seller seller = new Seller();
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBithDate(rs.getDate("BirthDate"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setDepartment(instantiateDepartment(rs)); // outro jeito de fazer isso, é instanciar o department.
        return seller;
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
