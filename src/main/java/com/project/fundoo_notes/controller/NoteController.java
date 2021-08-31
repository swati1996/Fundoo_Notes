package com.project.fundoo_notes.controller;

import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping(value="/update/{token}")
    public ResponseEntity<ResponseDTO> updateNote(@RequestHeader String token,
                                                  @RequestBody NoteDTO noteDTO){
        ResponseDTO res = service.updateNote(token,noteDTO);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @DeleteMapping(value="/delete/{token}")
    public ResponseEntity<ResponseDTO> deleteNote(@RequestHeader String token){
        ResponseDTO res = service.deleteNote(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping(value="/isarchive/{token}")
    public ResponseEntity<ResponseDTO> archiveNote(@RequestHeader String token){
        ResponseDTO res = service.archieveNote(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping(value="/ispin/{token}")
    public ResponseEntity<ResponseDTO> pinNote(@RequestHeader String token){
        ResponseDTO res = service.pinNote(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping(value = "/getByTrash/")
    public ResponseEntity<List> getAllNoteByTrash(){
        List res = service.getAllNoteByTrash();
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping(value = "/getByPined/")
    public ResponseEntity<List> getAllNoteByPin(){
        List res = service.getAllNoteByPin();
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping(value = "/getByArchive/")
    public ResponseEntity<List> getAllNoteByArchive(){
        List res = service.getAllNoteByArchive();
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
