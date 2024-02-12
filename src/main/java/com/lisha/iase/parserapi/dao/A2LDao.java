package com.lisha.iase.parserapi.dao;

import com.lisha.iase.parserapi.model.A2L;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class A2LDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_A2L_QUERY = "INSERT INTO a2l(name, file) VALUES(?,?)";

    public int save(A2L a2l){
        return jdbcTemplate.update(
                INSERT_A2L_QUERY,
                new Object[]{
                        a2l.getName(),
                        a2l.getFile()
                }
        );
    }

}
