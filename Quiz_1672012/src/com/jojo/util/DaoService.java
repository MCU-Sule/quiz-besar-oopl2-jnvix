package com.jojo.util;

import java.sql.SQLException;
import java.util.List;

public interface DaoService<T> {
    List<T> fetchAllList() throws SQLException, ClassNotFoundException;
    int addData(T t) throws  ClassNotFoundException, SQLException;
    int deleteData(T t) throws  ClassNotFoundException, SQLException;
    int updateData(T t) throws  ClassNotFoundException, SQLException;

}
