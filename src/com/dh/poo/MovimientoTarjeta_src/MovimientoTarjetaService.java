package com.dh.poo.MovimientoTarjeta_src;
import com.dh.poo.DAOException;
import com.dh.poo.ServiceException;

import java.util.List;

public class MovimientoTarjetaService {
    private IMovimientoTarjetaDAO movimientoDAO;

    public MovimientoTarjetaService()
    {
        movimientoDAO = new MovimientoTarjetaDAO();
    }

    public void create(MovimientoTarjeta transfer)throws ServiceException {

        try {
            movimientoDAO.create(transfer);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("MovimientoTarjetaService.Create: " + e.getMessage());
        }

    }


    public List<MovimientoTarjeta> movsDelMes(long numeroTarjeta, int userId)throws ServiceException{
        try {
            return movimientoDAO.movsDelMes(numeroTarjeta, userId);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("MovService.recuperarTodos: " + e.getMessage());
        }
    }
}
