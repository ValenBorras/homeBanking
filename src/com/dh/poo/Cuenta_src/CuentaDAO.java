package com.dh.poo.Cuenta_src;
import com.dh.poo.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAO implements ICuentaDAO {
    @Override
    public void create(Cuenta cuenta) throws DAOException {
        //INSERT INTO
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement = connection.createStatement();
            int cantRegistros = statement.executeUpdate("INSERT INTO CUENTA (IDUSUARIO, CBU, ALIAS, BALANCE, TIPO) VALUES(" +" '"+ cuenta.getIdUsuario() + "', '" + cuenta.getCbu() + "','" + cuenta.getAlias() + "','"+ cuenta.getBalance()+"','"+ cuenta.getTipo()+ "')");
            if(cantRegistros > 0)
                System.out.println("Se guardo una nueva cuenta");
            connection.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new DAOException("CuentaDAO.Create: " + e.getMessage());
        } catch (SQLException throwables){
            throwables.printStackTrace();
            throw new DAOException("Cuenta.Create: " + throwables.getMessage());

        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            Class.forName("org.h2.Driver");
            Connection con4 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement4 = con4.createStatement();
            int cantRegistrosEliminados = statement4.executeUpdate("DELETE FROM CUENTA WHERE IDCUENTA=" + id);
            if(cantRegistrosEliminados > 0)
                System.out.println("Se elimino la cuenta");
            con4.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("CuentaDAO.Delete: " + e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("CuentaDAO.Delete: " + throwables.getMessage());
        }
    }

    @Override
    public void update(Cuenta cuenta) throws DAOException {
        try {
            Class.forName("org.h2.Driver");
            Connection con3 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement3 = con3.createStatement();
            int cantRegistros2 = statement3.executeUpdate("UPDATE CUENTA SET CBU = '" + cuenta.getCbu() + "', ALIAS = '" + cuenta.getAlias() + "', BALANCE = '" + cuenta.getBalance() + "' WHERE ID=" + cuenta.getIdCuenta());
            if(cantRegistros2 > 0)
                System.out.println("Se modificó la cuenta");

            con3.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("CuentaDAO.Update: " + e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("CuentaDAO.Update: " + throwables.getMessage());
        }
    }

    @Override
    public Cuenta read(int id) throws DAOException {
        Cuenta cuenta = new Cuenta();
        try {
            Class.forName("org.h2.Driver");
            Connection con5 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement5 = con5.createStatement();
            ResultSet resultSet2 = statement5.executeQuery("SELECT * FROM CUENTA WHERE IDCUENTA=" + id);


            while(resultSet2.next()) {
                cuenta.setIdCuenta(resultSet2.getInt("IDCUENTA"));
                cuenta.setIdUsuario(resultSet2.getInt("IDUSUARIO"));
                cuenta.setCbu(resultSet2.getString("CBU"));
                cuenta.setAlias(resultSet2.getString("ALIAS"));
                cuenta.setBalance(resultSet2.getDouble("BALANCE"));
                cuenta.setTipo(resultSet2.getString("TIPO"));
            }
            con5.close();


        } catch (ClassNotFoundException e) {
            throw new DAOException("CuentaDAO.Read: " + e.getMessage());
        } catch (SQLException throwables) {
            throw new DAOException("CuentaDAO.Read: " + throwables.getMessage());
        }

        return cuenta;
    }

    @Override
    public List<Cuenta> readAllFromUserID(int id) throws DAOException {
        List<Cuenta> cuentas = new ArrayList<Cuenta>();

        try {
            Class.forName("org.h2.Driver");
            Connection con2 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement2 = con2.createStatement();
            ResultSet resultSet = statement2.executeQuery("SELECT * FROM CUENTA WHERE IDUSUARIO="+ id);
            int idcuenta = 0;
            int idusuario = 0;
            int cbu = 0;
            String alias = "";
            int balance = 0;
            String tipo ="";

            while(resultSet.next())
            {
                Cuenta cuenta = new Cuenta();
                cuenta.setIdCuenta(resultSet.getInt("IDCUENTA"));
                cuenta.setIdUsuario(resultSet.getInt("IDUSUARIO"));
                cuenta.setCbu(resultSet.getString("CBU"));
                cuenta.setAlias(resultSet.getString("ALIAS"));
                cuenta.setBalance(resultSet.getDouble("BALANCE"));
                cuenta.setTipo(resultSet.getString("TIPO"));

                cuentas.add(cuenta);
            }
            con2.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("CuentasDAO.ReadAllFromUserID: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("CuentasDAO.ReadALlFromUserID: " + e.getMessage());
        }

        return cuentas;
    }
    }

