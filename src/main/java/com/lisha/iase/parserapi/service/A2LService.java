package com.lisha.iase.parserapi.service;

import com.lisha.iase.parserapi.dao.A2LDao;
import com.lisha.iase.parserapi.model.A2L;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class A2LService {
    @Autowired
    private A2LDao a2lDao;

    public void createA2L(String a2lName, byte[] a2lContent){
        a2lDao.save(a2lName, a2lContent);
    }
    public String getJsonByName(String name) throws IOException, DataNotFound {
        List<A2L> selected = a2lDao.selectFileByName(name);
        if(selected.isEmpty()){
            throw new DataNotFound("Requested name is not present on DB");
        }

        String jsonPath = selected.get(0).getJsonPath();
        String jsonString = a2lDao.readFile(jsonPath, StandardCharsets.UTF_8);

        return jsonString;
    }
}
