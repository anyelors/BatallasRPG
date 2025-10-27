package dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> {

    List<T> listar() throws SQLException;

    T obtener(Long id) throws SQLException;

    void insertar(T elemento) throws SQLException;

    void eliminar( T obj ) throws SQLException;

    void actualizar( T obj ) throws SQLException;

}
