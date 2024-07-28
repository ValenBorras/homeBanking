package com.dh.poo.TarjetaCredito_src;

import com.dh.poo.DAOException;
import com.dh.poo.TarjetaCredito_src.TarjetaCredito;

import java.util.List;

public interface ITarjetaCreditoDAO {
    public void create(TarjetaCredito tarjeta) throws DAOException;
    public void delete(int id)throws DAOException;
    public void update(TarjetaCredito tarjeta)throws DAOException;
    public TarjetaCredito read(int id)throws DAOException;
    public List<TarjetaCredito> readAllFromUser(int idUsuario)throws DAOException;
}
