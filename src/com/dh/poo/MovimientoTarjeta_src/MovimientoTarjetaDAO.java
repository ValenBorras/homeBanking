package com.dh.poo.MovimientoTarjeta_src;

import com.dh.poo.DAOException;
import com.dh.poo.MovimientoTarjeta_src.IMovimientoTarjetaDAO;
import com.dh.poo.MovimientoTarjeta_src.MovimientoTarjeta;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovimientoTarjetaDAO implements IMovimientoTarjetaDAO {
    @Override
    public void create(MovimientoTarjeta movimiento) throws DAOException {
        //INSERT INTO
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement = connection.createStatement();
            int cantRegistros = statement.executeUpdate("INSERT INTO MOVIMIENTO (NUMEROTARJETA, MONTO, FECHA) VALUES(" +" '"+ movimiento.getNumeroTarjeta() + "', '" + movimiento.getMonto() + "','" + LocalDate.now() + "')");

            if(cantRegistros > 0)
                System.out.println("Se guardo un nuevo movimiento");
            connection.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new DAOException("MovimientoTarjetaDAO.Create: " + e.getMessage());
        } catch (SQLException throwables){
            throwables.printStackTrace();
            throw new DAOException("MovimientoTarjetaDAO.Create: " + throwables.getMessage());

        }
    }

    @Override
    public List<MovimientoTarjeta> movsDelMes(long numeroTarjeta, int mes) throws DAOException {
        List<MovimientoTarjeta> movimientos = new ArrayList<MovimientoTarjeta>();

        try {
            Class.forName("org.h2.Driver");
            Connection con2 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement2 = con2.createStatement();
            ResultSet resultSet = statement2.executeQuery(" SELECT idMovimientoTarjeta, numeroTarjeta, fecha, monto " +
                    "FROM MOVIMIENTO " +
                    "WHERE numerotarjeta = " + numeroTarjeta +
                    " AND MONTH(fecha) = " + mes +
                    " AND YEAR(fecha) = YEAR(CURDATE())");

            while(resultSet.next())
            {
                MovimientoTarjeta movimiento = new MovimientoTarjeta();
                movimiento.setIdMovimientoTarjeta(resultSet.getInt("IDMOVIMIENTOTARJETA"));
                movimiento.setNumeroTarjeta(resultSet.getLong("NUMEROTARJETA"));
                movimiento.setMonto(resultSet.getDouble("MONTO"));
                movimiento.setFecha(resultSet.getDate("FECHA").toLocalDate());

                movimientos.add(movimiento);
            }
            con2.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("MovimientoTarjetaDAO.RecuperarTodos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("MovimientoTarjetaDAO.RecuperarTodos: " + e.getMessage());
        }

        return movimientos;
    }
}