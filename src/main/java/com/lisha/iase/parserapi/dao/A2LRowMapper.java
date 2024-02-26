package com.lisha.iase.parserapi.dao;

import com.lisha.iase.parserapi.model.A2L;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class A2LRowMapper implements RowMapper<A2L> {

    @Override
    public A2L mapRow(ResultSet rs, int rowNum) throws SQLException{
        A2L a2l = new A2L();

        a2l.setName(rs.getString("name"));
        a2l.setA2lPath(rs.getString("a2l_path"));
        a2l.setJsonPath(rs.getString("json_path"));
        return a2l;
    }

}
