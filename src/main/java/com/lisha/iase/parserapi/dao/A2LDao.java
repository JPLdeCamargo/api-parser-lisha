package com.lisha.iase.parserapi.dao;

import com.lisha.iase.parserapi.model.A2L;
import net.alenzen.a2l.Asap2Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class A2LDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_A2L_QUERY = "INSERT INTO a2l(name, file, parsed) VALUES(?,?,?)";
    private final String GET_A2L_BY_NAME = "SELECT * FROM a2l WHERE name = ?";

    public int save(A2L a2l){
        String parsed;

        try {
            parsed = Asap2Parser.parseFromBytes(a2l.getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jdbcTemplate.update(
                INSERT_A2L_QUERY,
                new Object[]{
                        a2l.getName(),
                        a2l.getFile(),
                        parsed
                }
        );
    }
    public List<A2L> selectFileByName(String name){
        return jdbcTemplate.query(
                GET_A2L_BY_NAME,
                new A2LRowMapper(),
                new Object[] {
                        name
                }
        );
    }

}
