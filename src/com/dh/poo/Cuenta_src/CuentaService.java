package com.dh.poo.Cuenta_src;
import com.dh.poo.*;
import com.dh.poo.User_src.IUsuarioDAO;
import com.dh.poo.User_src.Usuario;
import com.dh.poo.User_src.UsuarioDAO;

import java.util.List;

public class CuentaService {
    private ICuentaDAO cuentaDAO;

    public CuentaService()
    {
        cuentaDAO = new CuentaDAO();
    }

    public void create(Cuenta cuenta)throws ServiceException {

        try {
            cuentaDAO.create(cuenta);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("CuentaService.Create: " + e.getMessage());
        }

    }

    public void delete(int id)throws ServiceException{

        try {
            cuentaDAO.delete(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("CuentaService.Delete: " + e.getMessage());
        }

    }

    public void update(Cuenta cuenta) throws ServiceException{

        try {
            cuentaDAO.update(cuenta);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("CuentaService.Update: " + e.getMessage());
        }
    }

    public Cuenta read(int id)throws ServiceException{

        try {
            return cuentaDAO.read(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("UsuarioService.Read: " + e.getMessage());
        }
    }

    public Cuenta readFromAlias(String alias)throws ServiceException{

        try {
            return cuentaDAO.readFromAlias(alias);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("UsuarioService.Read: " + e.getMessage());
        }
    }

    public List<Cuenta> readAllFromUserID(int id)throws ServiceException{
        try {
            return cuentaDAO.readAllFromUserID(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("PersonaService.recuperarTodos: " + e.getMessage());
        }
    }

    public void depositar(Cuenta cuenta, double monto) throws ServiceException{

        try {
            cuentaDAO.depositar(cuenta, monto);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("CuentaService.Update: " + e.getMessage());
        }
    }

    public void debitar(Cuenta cuenta, double monto) throws ServiceException{

        try {
            cuentaDAO.debitar(cuenta, monto);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("CuentaService.Update: " + e.getMessage());
        }
    }
}
