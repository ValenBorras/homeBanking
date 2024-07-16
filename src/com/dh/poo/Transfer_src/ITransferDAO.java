package com.dh.poo.Transfer_src;

import com.dh.poo.DAOException;

import java.util.List;

public interface ITransferDAO {
    public void create(Transfer transfer) throws DAOException;
    public Transfer read(int id)throws DAOException;
    public List<Transfer> allTransfersFromUser(int id)throws DAOException;
}
