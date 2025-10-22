package dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> {

    List<T> listar() throws SQLException;

}
