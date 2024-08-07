package com.dh.poo.TarjetaCredito_src;

import com.dh.poo.DAOException;
import com.dh.poo.TarjetaCredito_src.TarjetaCredito;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TarjetaCreditoDAO implements ITarjetaCreditoDAO{
    public void create(TarjetaCredito tarjeta) throws DAOException {
        //INSERT INTO
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement = connection.createStatement();

            LocalDate fechaCierre = LocalDate.now().withDayOfMonth(27);
            LocalDate fechaVencimiento = fechaCierre.plusDays(7);

            System.out.println(fechaCierre);
            System.out.println(fechaVencimiento);

            int cantRegistros = statement.executeUpdate("INSERT INTO TARJETA_CREDITO (IDUSUARIO,FECHAVENCIMIENTO,DISPONIBLE, APAGAR, FECHACIERRE) VALUES(" +" '" + tarjeta.getIdUsuario() + "','" + fechaVencimiento + "','" + tarjeta.getDisponible() + "','" + 0 + "','" + fechaCierre + "')");

            if(cantRegistros > 0)
                System.out.println("Se guardo un nuevo tarjeta");
            connection.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new DAOException("TarjetaCreditoDAO.Create: " + e.getMessage());
        } catch (SQLException throwables){
            throwables.printStackTrace();
            throw new DAOException("TarjetaCreditoDAO.Create: " + throwables.getMessage());

        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            Class.forName("org.h2.Driver");
            Connection con4 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement4 = con4.createStatement();
            int cantRegistrosEliminados = statement4.executeUpdate("DELETE FROM TARJETA_CREDITO WHERE IDTARJETA=" + id);
            if(cantRegistrosEliminados > 0)
                System.out.println("Se ha eliminado el registro");
            con4.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("TarjetaCreditoDAO.Eliminar: " + e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("TarjetaCreditoDAO.Eliminar: " + throwables.getMessage());
        }
    }

    @Override
    public void update(TarjetaCredito tarjeta) throws DAOException {
        try {
            Class.forName("org.h2.Driver");
            Connection con3 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement3 = con3.createStatement();
            int cantRegistros2 = statement3.executeUpdate("UPDATE TARJETA_CREDITO SET FECHAVENCIMIENTO = '" + tarjeta.getFechaVencimiento() + "', DISPONIBLE = '" + tarjeta.getDisponible() + "',APAGAR = '" + tarjeta.getaPagar() + "' WHERE IDTARJETA=" + tarjeta.getTarjetaID());
            if(cantRegistros2 > 0)
                System.out.println("Se modificó el tarjeta");

            con3.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("TarjetaCreditoDAO.Modificar: " + e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("TarjetaCreditoDAO.Modificar: " + throwables.getMessage());
        }
    }

    @Override
    public TarjetaCredito read(int id) throws DAOException {
        TarjetaCredito tarjeta = new TarjetaCredito();
        try {
            Class.forName("org.h2.Driver");
            Connection con5 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement5 = con5.createStatement();
            ResultSet resultSet2 = statement5.executeQuery("SELECT * FROM TARJETA_CREDITO WHERE IDTARJETA=" + id);


            while(resultSet2.next()) {
                tarjeta.setTarjetaID(resultSet2.getInt("IDTARJETA"));
                tarjeta.setCvv(resultSet2.getInt("CVV"));
                tarjeta.setNumeroTarjeta(resultSet2.getLong("NUMEROTARJETA"));
                tarjeta.setFechaVencimiento(resultSet2.getDate("FECHAVENCIMIENTO").toLocalDate());
                tarjeta.setIdUsuario(resultSet2.getInt("IDUSUARIO"));
                tarjeta.setFechaCierre(resultSet2.getDate("FECHACIERRE").toLocalDate());
                tarjeta.setDisponible(resultSet2.getInt("DISPONIBLE"));
                tarjeta.setaPagar(resultSet2.getInt("APAGAR"));
            }
            con5.close();


        } catch (ClassNotFoundException e) {
            throw new DAOException("TarjetaCreditoDAO.Recuperar: " + e.getMessage());
        } catch (SQLException throwables) {
            throw new DAOException("TarjetaCredito.Recuperar: " + throwables.getMessage());
        }

        return tarjeta;
    }

    @Override
    public List<TarjetaCredito> readAllFromUser(int idUsuario) throws DAOException {
        List<TarjetaCredito> tarjetas = new ArrayList<TarjetaCredito>();

        try {
            Class.forName("org.h2.Driver");
            Connection con2 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement2 = con2.createStatement();
            ResultSet resultSet = statement2.executeQuery("SELECT * FROM TARJETA_CREDITO WHERE IDUSUARIO =" + idUsuario);

            while(resultSet.next())
            {
                TarjetaCredito tarjeta = new TarjetaCredito();
                tarjeta.setTarjetaID(resultSet.getInt("IDTARJETA"));
                tarjeta.setCvv(resultSet.getInt("CVV"));
                tarjeta.setNumeroTarjeta(resultSet.getLong("NUMEROTARJETA"));
                tarjeta.setFechaVencimiento(resultSet.getDate("FECHAVENCIMIENTO").toLocalDate());
                tarjeta.setIdUsuario(resultSet.getInt("IDUSUARIO"));
                tarjeta.setFechaCierre(resultSet.getDate("FECHACIERRE").toLocalDate());
                tarjeta.setDisponible(resultSet.getInt("DISPONIBLE"));
                tarjeta.setaPagar(resultSet.getInt("APAGAR"));

                System.out.println("agrego credito");

                tarjetas.add(tarjeta);
            }
            con2.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("TarjetaCreditoDAO.RecuperarTodos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("TarjetaCreditoDAO.RecuperarTodos: " + e.getMessage());
        }

        return tarjetas;
    }
}
