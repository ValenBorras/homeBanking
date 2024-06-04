package com.dh.poo.User_src;
import com.dh.poo.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO {
    @Override
    public void create(Usuario usuario) throws DAOException {
        //INSERT INTO
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement = connection.createStatement();
            int cantRegistros = statement.executeUpdate("INSERT INTO USUARIO (NOMBRE, EMAIL, PASSWORD) VALUES(" +" '"+ usuario.getNombre() + "', '" + usuario.getEmail() + "','" + usuario.getPassword() + "')");

            if(cantRegistros > 0)
                System.out.println("Se guardo un nuevo usuario");
            connection.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new DAOException("UsuarioDAO.Create: " + e.getMessage());
        } catch (SQLException throwables){
            throwables.printStackTrace();
            throw new DAOException("UsuarioDAO.Create: " + throwables.getMessage());

        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            Class.forName("org.h2.Driver");
            Connection con4 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement4 = con4.createStatement();
            int cantRegistrosEliminados = statement4.executeUpdate("DELETE FROM USUARIO WHERE IDUSUARIO=" + id);
            if(cantRegistrosEliminados > 0)
                System.out.println("Se ha eliminado el registro");
            con4.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("UsuarioDAO.Eliminar: " + e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("UsuarioDAO.Eliminar: " + throwables.getMessage());
        }
    }

    @Override
    public void update(Usuario usuario) throws DAOException {
        try {
            Class.forName("org.h2.Driver");
            Connection con3 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement3 = con3.createStatement();
            int cantRegistros2 = statement3.executeUpdate("UPDATE USUARIO SET NOMBRE = '" + usuario.getNombre() + "', EMAIL = '" + usuario.getEmail() + "', PASSWORD = '" + usuario.getPassword() + "' WHERE ID=" + usuario.getIdUsuario());
            if(cantRegistros2 > 0)
                System.out.println("Se modificó el usuario");

            con3.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("UsuarioDAO.Modificar: " + e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("UsuarioDAO.Modificar: " + throwables.getMessage());
        }
    }

    @Override
    public Usuario read(int id) throws DAOException {
        Usuario usuario = new Usuario();
        try {
            Class.forName("org.h2.Driver");
            Connection con5 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement5 = con5.createStatement();
            ResultSet resultSet2 = statement5.executeQuery("SELECT * FROM USUARIO WHERE IDUSUARIO=" + id);


            while(resultSet2.next()) {
                usuario.setIdUsuario(resultSet2.getInt("IDUSUARIO"));
                usuario.setNombre(resultSet2.getString("NOMBRE"));
                usuario.setEmail(resultSet2.getString("EMAIL"));
                usuario.setPassword(resultSet2.getString("PASSWORD"));
            }
            con5.close();


        } catch (ClassNotFoundException e) {
            throw new DAOException("UsuarioDAO.Recuperar: " + e.getMessage());
        } catch (SQLException throwables) {
            throw new DAOException("Usuario.Recuperar: " + throwables.getMessage());
        }

        return usuario;
    }

    @Override
    public List<Usuario> readAll() throws DAOException {
        List<Usuario> personas = new ArrayList<Usuario>();

        try {
            Class.forName("org.h2.Driver");
            Connection con2 = DriverManager.getConnection("jdbc:h2:/Users/valentinaborras/Desktop/Lic.EnIA/LabI(java)/DB", "valen", "123");
            Statement statement2 = con2.createStatement();
            ResultSet resultSet = statement2.executeQuery("SELECT * FROM USUARIO");
            int id = 0;
            String nombre = "";
            String email = "";
            String password = "";

            while(resultSet.next())
            {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getInt("IDUSUARIO"));
                usuario.setNombre(resultSet.getString("NOMBRE"));
                usuario.setEmail(resultSet.getString("EMAIL"));
                usuario.setPassword(resultSet.getString("PASSWORD"));

                personas.add(usuario);
            }
            con2.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("UsuarioDAO.RecuperarTodos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("UsuarioDAO.RecuperarTodos: " + e.getMessage());
        }

        return personas;
    }
    }

