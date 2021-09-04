package com.project.fundoo_notes.controller;

import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.NoteResponseDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NoteController {

    @Autowired
    private INoteService service;

    @PostMapping(value = "createnote")
    public ResponseEntity<ResponseDTO> createNote( @RequestBody @Valid NoteDTO noteDTO
            ,@RequestHeader String token,
            BindingResult e){
        if(e.hasErrors()){
            List<String> error = e.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }else {
            ResponseDTO res = service.create(noteDTO,token);
            return new ResponseEntity(res, HttpStatus.CREATED);
        }
    }

    @GetMapping(value="/get")
    public ResponseEntity<NoteResponseDTO> getNote(@RequestHeader String token){
        NoteResponseDTO res = service.getNote(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @PutMapping(value="/update")
    public ResponseEntity<ResponseDTO> updateNote(@RequestHeader String token,
                                                  @RequestBody NoteDTO noteDTO){
        ResponseDTO res = service.updateNote(token,noteDTO);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @DeleteMapping(value="/delete")
    public ResponseEntity<ResponseDTO> deleteNote(@RequestHeader String token){
        ResponseDTO res = service.deleteNote(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping(value="/isarchive")
    public ResponseEntity<ResponseDTO> archiveNote(@RequestHeader String token){
        ResponseDTO res = service.archieveNote(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping(value="/ispin")
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
        List res = service.getAllNoteByArchieve();
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping(value = "/getByTrashAndArchive/")
    public ResponseEntity<List> getAllNoteByTrashAndArchive(){
        List res = service.getAllNoteByTrashAndArchieve();
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
