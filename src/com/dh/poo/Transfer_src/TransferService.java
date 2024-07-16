package com.dh.poo.Transfer_src;
import com.dh.poo.DAOException;
import com.dh.poo.ServiceException;
import com.dh.poo.Transfer_src.ITransferDAO;
import com.dh.poo.Transfer_src.TransferDAO;

import java.util.List;

public class TransferService {
    private ITransferDAO usuarioDAO;

    public TransferService()
    {
        usuarioDAO = new TransferDAO();
    }

    public void create(Transfer transfer)throws ServiceException {

        try {
            usuarioDAO.create(transfer);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("TransferService.Create: " + e.getMessage());
        }

    }

    public Transfer read(int id)throws ServiceException{

        try {
            return usuarioDAO.read(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("TransferService.Read: " + e.getMessage());
        }
    }

    public List<Transfer> allTransfersFromUser(int userId)throws ServiceException{
        try {
            return usuarioDAO.allTransfersFromUser(userId);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("PersonaService.recuperarTodos: " + e.getMessage());
        }
    }
}
