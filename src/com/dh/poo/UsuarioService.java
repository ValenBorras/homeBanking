package com.dh.poo;
import java.util.List;

public class UsuarioService {
    private IUsuarioDAO usuarioDAO;

    public UsuarioService()
    {
        usuarioDAO = new UsuarioDAO();
    }

    public void create(Usuario persona)throws ServiceException{

        try {
            usuarioDAO.create(persona);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("UsuarioService.Create: " + e.getMessage());
        }

    }

    public void delete(int id)throws ServiceException{

        try {
            usuarioDAO.delete(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("UsuarioService.Delete: " + e.getMessage());
        }

    }

    public void update(Usuario persona) throws ServiceException{

        try {
            usuarioDAO.update(persona);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("UsuarioService.Update: " + e.getMessage());
        }
    }

    public Usuario read(int id)throws ServiceException{

        try {
            return usuarioDAO.read(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("UsuarioService.Read: " + e.getMessage());
        }
    }

    public List<Usuario> readAll()throws ServiceException{
        try {
            return usuarioDAO.readAll();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("PersonaService.recuperarTodos: " + e.getMessage());
        }
    }
}
