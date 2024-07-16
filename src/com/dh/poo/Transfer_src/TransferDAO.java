package com.dh.poo.Transfer_src;

import com.dh.poo.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransferDAO implements ITransferDAO {
    @Override
    public void create(Transfer transfer) throws DAOException {
        //INSERT INTO
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement = connection.createStatement();
            int cantRegistros = statement.executeUpdate("INSERT INTO TRANSFER (ALIASDESDE, ALIASHASTA, MONTO) VALUES(" +" '"+ transfer.getAliasDesde() + "', '" + transfer.getAliasHasta() + "','" + transfer.getMonto() + "')");

            if(cantRegistros > 0)
                System.out.println("Se guardo un nuevo transfer");
            connection.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new DAOException("TransferDAO.Create: " + e.getMessage());
        } catch (SQLException throwables){
            throwables.printStackTrace();
            throw new DAOException("TransferDAO.Create: " + throwables.getMessage());

        }
    }

    @Override
    public Transfer read(int id) throws DAOException {
        Transfer transfer = new Transfer();
        try {
            Class.forName("org.h2.Driver");
            Connection con5 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement5 = con5.createStatement();
            ResultSet resultSet2 = statement5.executeQuery("SELECT * FROM TRANSFER WHERE IDTRANSFER=" + id);


            while(resultSet2.next()) {
                transfer.setIdTransfer(resultSet2.getInt("IDTRANSFER"));
                transfer.setAliasDesde(resultSet2.getString("ALIASDESDE"));
                transfer.setAliasHasta(resultSet2.getString("ALIASHASTA"));
                transfer.setMonto(resultSet2.getDouble("MONTO"));
                transfer.setFecha(resultSet2.getTimestamp("FECHA"));
            }
            con5.close();


        } catch (ClassNotFoundException e) {
            throw new DAOException("TransferDAO.Recuperar: " + e.getMessage());
        } catch (SQLException throwables) {
            throw new DAOException("Transfer.Recuperar: " + throwables.getMessage());
        }

        return transfer;
    }

    @Override
    public List<Transfer> allTransfersFromUser(int userID) throws DAOException {
        List<Transfer> transfers = new ArrayList<Transfer>();

        try {
            Class.forName("org.h2.Driver");
            Connection con2 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement2 = con2.createStatement();
            ResultSet resultSet = statement2.executeQuery(" SELECT t.idTransfer, t.aliasDesde, t.aliasHasta, t.fecha, t.monto" +
                    "FROM TRANSFER t" +
                    "JOIN CUENTA aliasDesde ON t.aliasDesde = aliasDesde.idCuenta" +
                    "JOIN CUENTA aliasHasta ON t.aliasHasta = aliasHasta.idCuenta" +
                    "WHERE aliasDesde.idUsuario ="+ userID + " OR aliasHasta.idUsuario ="+userID);

            while(resultSet.next())
            {
                Transfer transfer = new Transfer();
                transfer.setIdTransfer(resultSet.getInt("IDTRANSFER"));
                transfer.setAliasDesde(resultSet.getString("ALIASDESDE"));
                transfer.setAliasHasta(resultSet.getString("ALIASHASTA"));
                transfer.setMonto(resultSet.getDouble("MONTO"));
                transfer.setFecha(resultSet.getTimestamp("FECHA"));

                transfers.add(transfer);
            }
            con2.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("TransferDAO.RecuperarTodos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("TransferDAO.RecuperarTodos: " + e.getMessage());
        }

        return transfers;
    }
    }

