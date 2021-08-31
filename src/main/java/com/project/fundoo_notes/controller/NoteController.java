package com.project.fundoo_notes.controller;

import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {

    @Autowired
    private INoteService service;

    @PostMapping(value = "createnote")
    public ResponseEntity<ResponseDTO> createNote(@RequestBody NoteDTO noteDTO){
        ResponseDTO res = service.create(noteDTO);
        return new ResponseEntity(res, HttpStatus.CREATED);
    }
    @GetMapping(value="/get/{token}")
    public ResponseEntity<ResponseDTO> getNote(@RequestHeader String token){
        ResponseDTO res = service.getNote(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
