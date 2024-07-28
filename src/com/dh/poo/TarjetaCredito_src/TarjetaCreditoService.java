package com.dh.poo.TarjetaCredito_src;

import com.dh.poo.DAOException;
import com.dh.poo.ServiceException;
import com.dh.poo.TarjetaCredito_src.ITarjetaCreditoDAO;
import com.dh.poo.TarjetaCredito_src.TarjetaCredito;
import com.dh.poo.TarjetaCredito_src.TarjetaCreditoDAO;

import java.util.List;

public class TarjetaCreditoService {
    private ITarjetaCreditoDAO tarjetaDebitoDAO;

    public TarjetaCreditoService()
    {
        tarjetaDebitoDAO = new TarjetaCreditoDAO();
    }

    public void create(TarjetaCredito tarjeta)throws ServiceException {

        try {
            tarjetaDebitoDAO.create(tarjeta);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("TarjetaCreditoService.Create: " + e.getMessage());
        }

    }

    public void delete(int id)throws ServiceException{

        try {
            tarjetaDebitoDAO.delete(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("TarjetaCreditoService.Delete: " + e.getMessage());
        }

    }

    public void update(TarjetaCredito tarjeta) throws ServiceException{

        try {
            tarjetaDebitoDAO.update(tarjeta);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("TarjetaCreditoService.Update: " + e.getMessage());
        }
    }

    public TarjetaCredito read(int id)throws ServiceException{

        try {
            return tarjetaDebitoDAO.read(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("TarjetaCreditoService.Read: " + e.getMessage());
        }
    }

    public List<TarjetaCredito> readAllFromUser(int idUsuario)throws ServiceException{
        try {
            return tarjetaDebitoDAO.readAllFromUser(idUsuario);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("PersonaService.recuperarTodos: " + e.getMessage());
        }
    }
}
