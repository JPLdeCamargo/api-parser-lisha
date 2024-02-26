package com.lisha.iase.parserapi.dao;

import com.lisha.iase.parserapi.model.A2L;
import net.alenzen.a2l.Asap2Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Repository
public class A2LDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String A2LSPATH = "content" + File.separator + "a2ls";
    private final String JSONSPATH = "content" + File.separator + "jsons";
    private final String INSERT_A2L_QUERY = "INSERT INTO a2l(name,a2l_path,json_path) VALUES(?,?,?)";
    private final String GET_A2L_BY_NAME = "SELECT * FROM a2l WHERE name = ?";

    public int save(String a2lName, byte[] a2lContent){
        // Parsing content
        String parsed;
        try {
            parsed = Asap2Parser.parseFromBytes(a2lContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Saving on files
        String a2lPath = A2LSPATH + File.separator + a2lName + ".a2l";
        try (PrintWriter printer = new PrintWriter(a2lPath)){
            printer.println(new String(a2lContent, StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String jsonPath = JSONSPATH + File.separator + a2lName + ".json";
        try (PrintWriter printer = new PrintWriter(jsonPath)){
            printer.println(parsed);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Saving information on DB
        return jdbcTemplate.update(
                INSERT_A2L_QUERY,
                new Object[]{
                        a2lName,
                        a2lPath,
                        jsonPath
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

    public String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
