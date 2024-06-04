package com.dh.poo.Cuenta_src;
import com.dh.poo.DAOException;

import java.util.List;

public interface ICuentaDAO {
    public void create(Cuenta cuenta) throws DAOException;
    public void delete(int id)throws DAOException;
    public void update(Cuenta cuenta)throws DAOException;
    public Cuenta read(int id)throws DAOException;
    public List<Cuenta> readAllFromUserID(int id)throws DAOException;
}
