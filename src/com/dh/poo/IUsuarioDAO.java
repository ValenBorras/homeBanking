package com.dh.poo;
import java.util.List;

public interface IUsuarioDAO {
    public void create(Usuario usuario) throws DAOException;
    public void delete(int id)throws DAOException;
    public void update(Usuario usuario)throws DAOException;
    public Usuario read(int id)throws DAOException;
    public List<Usuario> readAll()throws DAOException;
}
