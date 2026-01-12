package DataAccessComponent.Interfaces;

import java.util.List;

public interface IDAO<T> {

    List<T> readBy(String name) throws Exception;

    List<T> readAllstatus(boolean status) throws Exception; // corregir nombre camelcase

    boolean create(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    boolean changestatus(int id, Boolean status) throws Exception;// corregir nombre camelcase

    Integer getMaxReg() throws Exception;
}
