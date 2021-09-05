package com.project.fundoo_notes.controller;

import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.NoteResponseDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NoteController {

    @Autowired
    private INoteService noteService;

    @PostMapping(value = "createnote")
    public ResponseEntity<ResponseDTO> createNote( @RequestBody @Valid NoteDTO noteDTO,
                                                   @RequestHeader String token,
                                                   BindingResult e){
        if(e.hasErrors()){
            List<String> error = e.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }else {
            ResponseDTO res = noteService.create(noteDTO,token);
            return new ResponseEntity(res, HttpStatus.CREATED);
        }
    }

    @GetMapping(value="/get")
    public ResponseEntity<NoteResponseDTO> getNote(@RequestHeader String token){
        NoteResponseDTO res = noteService.getNote(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @PutMapping(value="/update/{noteId}")
    public ResponseEntity<ResponseDTO> updateNote(@RequestHeader String token,
                                                  @RequestBody NoteDTO noteDTO,
                                                  @PathVariable long noteId){
        ResponseDTO res = noteService.updateNote(token,noteDTO,noteId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @DeleteMapping(value="/delete/{noteId}")
    public ResponseEntity<ResponseDTO> deleteNote(@RequestHeader String token,
                                                  @PathVariable long noteId){
        ResponseDTO res = noteService.deleteNote(token,noteId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping(value="/isarchive/{noteId}")
    public ResponseEntity<ResponseDTO> archiveNote(@RequestHeader String token,
                                                   @PathVariable long noteId){
        ResponseDTO res = noteService.archieveNote(token,noteId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping(value="/ispin/{noteId}")
    public ResponseEntity<ResponseDTO> pinNote(@RequestHeader String token,
                                               @PathVariable long noteId){
        ResponseDTO res = noteService.pinNote(token,noteId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping(value = "/getNoteFromTrash/")
    public ResponseEntity<NoteResponseDTO> getAllNoteByTrash(@RequestHeader String token){
        NoteResponseDTO res = noteService.getAllNoteFromTrash(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping(value = "/getPinedNoted/")
    public ResponseEntity<NoteResponseDTO> getAllPinedNote(@RequestHeader String token){
        NoteResponseDTO res = noteService.getAllPinNote(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping(value = "/getFromArchive/")
    public ResponseEntity<NoteResponseDTO> getAllNoteFromArchive(@RequestHeader String token){
        NoteResponseDTO res = noteService.getAllNoteFromArchieve(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping(value = "/getFromTrashAndArchive/")
    public ResponseEntity<NoteResponseDTO> getAllNoteFromTrashAndArchive(@RequestHeader String token){
        NoteResponseDTO res = noteService.getAllNoteFromTrashAndArchieve(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @PutMapping(value = "/setNoteColor/{noteId}")
    public ResponseEntity<ResponseDTO> setNoteColor(@RequestHeader String token,
                                                        @PathVariable Long noteId,
                                                        @RequestBody String color){
        ResponseDTO res = noteService.setNoteColor(token,noteId, color);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
