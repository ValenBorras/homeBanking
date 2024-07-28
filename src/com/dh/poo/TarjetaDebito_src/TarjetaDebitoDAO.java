package com.dh.poo.TarjetaDebito_src;

import com.dh.poo.DAOException;
import com.dh.poo.TarjetaDebito_src.TarjetaDebito;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TarjetaDebitoDAO implements ITarjetaDebitoDAO {
    @Override
    public void create(TarjetaDebito tarjeta) throws DAOException {
        //INSERT INTO
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement = connection.createStatement();

            LocalDate fechaVencimiento = LocalDate.now().plusYears(5);

            int cantRegistros = statement.executeUpdate("INSERT INTO TARJETA_DEBITO (IDCUENTA, IDUSUARIO, FECHAVENCIMIENTO) VALUES(" +" '" + tarjeta.getIdCuenta() + "','" + tarjeta.getIdUsuario() + "','" + fechaVencimiento + "')");

            if(cantRegistros > 0)
                System.out.println("Se guardo un nuevo tarjeta");
            connection.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new DAOException("TarjetaDebitoDAO.Create: " + e.getMessage());
        } catch (SQLException throwables){
            throwables.printStackTrace();
            throw new DAOException("TarjetaDebitoDAO.Create: " + throwables.getMessage());

        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            Class.forName("org.h2.Driver");
            Connection con4 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement4 = con4.createStatement();
            int cantRegistrosEliminados = statement4.executeUpdate("DELETE FROM TARJETA_DEBITO WHERE IDTARJETA=" + id);
            if(cantRegistrosEliminados > 0)
                System.out.println("Se ha eliminado el registro");
            con4.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("TarjetaDebitoDAO.Eliminar: " + e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("TarjetaDebitoDAO.Eliminar: " + throwables.getMessage());
        }
    }

    @Override
    public void update(TarjetaDebito tarjeta) throws DAOException {
        try {
            Class.forName("org.h2.Driver");
            Connection con3 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement3 = con3.createStatement();
            int cantRegistros2 = statement3.executeUpdate("UPDATE TARJETA_DEBITO SET FECHAVENCIMIENTO = '" + tarjeta.getFechaVencimiento() + "' WHERE IDTARJETA=" + tarjeta.getTarjetaID());
            if(cantRegistros2 > 0)
                System.out.println("Se modificó el tarjeta");

            con3.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("TarjetaDebitoDAO.Modificar: " + e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("TarjetaDebitoDAO.Modificar: " + throwables.getMessage());
        }
    }

    @Override
    public TarjetaDebito read(int id) throws DAOException {
        TarjetaDebito tarjeta = new TarjetaDebito();
        try {
            Class.forName("org.h2.Driver");
            Connection con5 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement5 = con5.createStatement();
            ResultSet resultSet2 = statement5.executeQuery("SELECT * FROM TARJETA_DEBITO WHERE IDTARJETA=" + id);


            while(resultSet2.next()) {
                tarjeta.setTarjetaID(resultSet2.getInt("IDTARJETA"));
                tarjeta.setCvv(resultSet2.getInt("CVV"));
                tarjeta.setNumeroTarjeta(resultSet2.getLong("NUMEROTARJETA"));
                tarjeta.setFechaVencimiento(resultSet2.getDate("FECHAVENCIMIENTO").toLocalDate());
                tarjeta.setIdCuenta(resultSet2.getInt("IDCUENTA"));
                tarjeta.setIdUsuario(resultSet2.getInt("IDUSUARIO"));
            }
            con5.close();


        } catch (ClassNotFoundException e) {
            throw new DAOException("TarjetaDebitoDAO.Recuperar: " + e.getMessage());
        } catch (SQLException throwables) {
            throw new DAOException("TarjetaDebito.Recuperar: " + throwables.getMessage());
        }

        return tarjeta;
    }

    @Override
    public List<TarjetaDebito> readAllFromUser(int idUsuario) throws DAOException {
        List<TarjetaDebito> tarjetas = new ArrayList<TarjetaDebito>();

        try {
            Class.forName("org.h2.Driver");
            Connection con2 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement2 = con2.createStatement();
            ResultSet resultSet = statement2.executeQuery("SELECT * FROM TARJETA_DEBITO WHERE IDUSUARIO =" + idUsuario);

            while(resultSet.next())
            {
                TarjetaDebito tarjeta = new TarjetaDebito();
                tarjeta.setTarjetaID(resultSet.getInt("IDTARJETA"));
                tarjeta.setCvv(resultSet.getInt("CVV"));
                tarjeta.setNumeroTarjeta(resultSet.getLong("NUMEROTARJETA"));
                tarjeta.setFechaVencimiento(resultSet.getDate("FECHAVENCIMIENTO").toLocalDate());
                tarjeta.setIdCuenta(resultSet.getInt("IDCUENTA"));
                tarjeta.setIdUsuario(resultSet.getInt("IDUSUARIO"));

                System.out.println("agrego debito");

                tarjetas.add(tarjeta);
            }
            con2.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("TarjetaDebitoDAO.RecuperarTodos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("TarjetaDebitoDAO.RecuperarTodos: " + e.getMessage());
        }

        return tarjetas;
    }
}