package com.dh.poo.TarjetaDebito_src;
import com.dh.poo.DAOException;

import java.util.List;

public interface ITarjetaDebitoDAO {
    public void create(TarjetaDebito tarjeta) throws DAOException;
    public void delete(int id)throws DAOException;
    public void update(TarjetaDebito tarjeta)throws DAOException;
    public TarjetaDebito read(int id)throws DAOException;
    public List<TarjetaDebito> readAllFromUser(int idUsuario)throws DAOException;
}

