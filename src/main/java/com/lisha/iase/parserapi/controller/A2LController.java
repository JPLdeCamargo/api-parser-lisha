package com.lisha.iase.parserapi.controller;

import com.lisha.iase.parserapi.model.A2L;
import com.lisha.iase.parserapi.service.A2LService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/A2Ls")
public class A2LController {
   @Autowired
   private A2LService a2lService;

   @PostMapping("save-a2l")
   public ResponseEntity<?> createA2L(@RequestBody A2L a2l){
       a2lService.createA2L(a2l);
       return new ResponseEntity<>(HttpStatus.OK);
   }

   @GetMapping()
   public ResponseEntity<?> getTest(){
       return new ResponseEntity<>("oioioi", HttpStatus.OK);
   }
}
