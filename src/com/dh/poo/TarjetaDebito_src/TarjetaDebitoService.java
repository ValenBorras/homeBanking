package com.dh.poo.TarjetaDebito_src;
import com.dh.poo.DAOException;
import com.dh.poo.ServiceException;
import com.dh.poo.TarjetaDebito_src.ITarjetaDebitoDAO;
import com.dh.poo.TarjetaDebito_src.TarjetaDebito;
import com.dh.poo.TarjetaDebito_src.TarjetaDebitoDAO;

import java.util.List;

public class TarjetaDebitoService {
    private ITarjetaDebitoDAO tarjetaDebitoDAO;

    public TarjetaDebitoService()
    {
        tarjetaDebitoDAO = new TarjetaDebitoDAO();
    }

    public void create(TarjetaDebito tarjeta)throws ServiceException {

        try {
            tarjetaDebitoDAO.create(tarjeta);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("TarjetaDebitoService.Create: " + e.getMessage());
        }

    }

    public void delete(int id)throws ServiceException{

        try {
            tarjetaDebitoDAO.delete(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("TarjetaDebitoService.Delete: " + e.getMessage());
        }

    }

    public void update(TarjetaDebito tarjeta) throws ServiceException{

        try {
            tarjetaDebitoDAO.update(tarjeta);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("TarjetaDebitoService.Update: " + e.getMessage());
        }
    }

    public TarjetaDebito read(int id)throws ServiceException{

        try {
            return tarjetaDebitoDAO.read(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("TarjetaDebitoService.Read: " + e.getMessage());
        }
    }

    public List<TarjetaDebito> readAllFromUser(int idUsuario)throws ServiceException{
        try {
            return tarjetaDebitoDAO.readAllFromUser(idUsuario);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("PersonaService.recuperarTodos: " + e.getMessage());
        }
    }
}
