package com.dh.poo.MovimientoTarjeta_src;
import com.dh.poo.DAOException;

import java.util.List;

public interface IMovimientoTarjetaDAO {
    public void create(MovimientoTarjeta movimiento) throws DAOException;
    public List<MovimientoTarjeta> movsDelMes(long nroTarjeta, int mes)throws DAOException;
}
