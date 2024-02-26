package com.lisha.iase.parserapi.controller;

import com.lisha.iase.parserapi.model.A2L;
import com.lisha.iase.parserapi.model.RequestA2L;
import com.lisha.iase.parserapi.service.A2LService;
import com.lisha.iase.parserapi.service.DataNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/A2Ls")
public class A2LController {
   @Autowired
   private A2LService a2lService;

   @PostMapping("save-a2l")
   public ResponseEntity<?> createA2L(@RequestBody RequestA2L a2l){
       a2lService.createA2L(a2l.name, a2l.a2lContent);
       return new ResponseEntity<>(HttpStatus.OK);
   }

   @GetMapping("/get-json/{name}")
   public ResponseEntity<?> getJsonByName(@PathVariable("name") String name){

       String json = null;
       try {
           json = a2lService.getJsonByName(name);
       } catch (IOException e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong while fetching your file");
       } catch (DataNotFound e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Name not present on DB");
       }
       return new ResponseEntity<>(json, HttpStatus.OK);

   }
}
