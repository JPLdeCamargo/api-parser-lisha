package com.lisha.iase.parserapi.service;

import com.lisha.iase.parserapi.dao.A2LDao;
import com.lisha.iase.parserapi.model.A2L;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class A2LService {
    @Autowired
    private A2LDao a2lDao;

    public void createA2L(A2L a2l){
        a2lDao.save(a2l);
    }
}
