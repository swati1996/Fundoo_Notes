package com.project.fundoo_notes.controller;

import com.project.fundoo_notes.dto.NoteDTO;
import com.project.fundoo_notes.dto.NoteResponseDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * purpose : Controller for Note
 * @author : Swati
 * @version : 1.0
 * @since : 4-7-21
 **/
@RestController
public class NoteController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private INoteService noteService;

    private static String url ="http://localhost:8080/isUserPresent/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxfQ.Wle-Gvxhe0b_8BJFTucN6yf-aZZCvVCWU0-XHrWcXek";



    @GetMapping(value = "/getUser")
    public ResponseEntity<Boolean> getUser(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Boolean> res = restTemplate.exchange(url, HttpMethod.GET, request, Boolean.class);
        return res;
    }

    /**
     * purpose : Ability to  create Note
     **/

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
    /**
     * purpose : Ability to get all Note for user
     **/


    @GetMapping(value="/get")
    public ResponseEntity<NoteResponseDTO> getNote(@RequestHeader String token){
        NoteResponseDTO res = noteService.getNote(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    /**
     * purpose : Ability to update Note

     **/

    @PutMapping(value="/update/{noteId}")
    public ResponseEntity<ResponseDTO> updateNote(@RequestHeader String token,
                                                  @RequestBody NoteDTO noteDTO,
                                                  @PathVariable long noteId){
        ResponseDTO res = noteService.updateNote(token,noteDTO,noteId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    /**
     * purpose : Ability to delete Note
     **/

    @DeleteMapping(value="/delete/{noteId}")
    public ResponseEntity<ResponseDTO> deleteNote(@RequestHeader String token,
                                                  @PathVariable long noteId){
        ResponseDTO res = noteService.deleteNote(token,noteId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    /**
     * purpose : Ability to archieve Note
     **/

    @GetMapping(value="/archieveNote/{noteId}")
    public ResponseEntity<ResponseDTO> archieveNote(@RequestHeader String token,
                                                    @PathVariable long noteId){
        ResponseDTO res = noteService.archieveNote(token,noteId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    /**
     * purpose : Ability to pinned a note

     **/

    @GetMapping(value="/pinNote/{noteId}")
    public ResponseEntity<ResponseDTO> pinNote(@RequestHeader String token,
                                               @PathVariable long noteId){
        ResponseDTO res = noteService.pinNote(token,noteId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    /**
     * purpose : Ability to get all notes from trash
     **/

    @GetMapping(value = "/getNoteFromTrash/")
    public ResponseEntity<NoteResponseDTO> getAllNoteByTrash(@RequestHeader String token){
        NoteResponseDTO res = noteService.getAllNoteFromTrash(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    /**
     * purpose : Ability to get all note which is pinned

     **/

    @GetMapping(value = "/getPinedNoted/")
    public ResponseEntity<NoteResponseDTO> getAllPinedNote(@RequestHeader String token){
        NoteResponseDTO res = noteService.getAllPinNote(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    /**
     * purpose : Ability to get note from archieve

     **/

    @GetMapping(value = "/getFromArchive/")
    public ResponseEntity<NoteResponseDTO> getAllNoteFromArchive(@RequestHeader String token){
        NoteResponseDTO res = noteService.getAllNoteFromArchieve(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    /**
     * purpose : Ability to get all note from Trash and archieve

     **/

    @GetMapping(value = "/getFromTrashAndArchive/")
    public ResponseEntity<NoteResponseDTO> getAllNoteFromTrashAndArchive(@RequestHeader String token){
        NoteResponseDTO res = noteService.getAllNoteFromTrashAndArchieve(token);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    /**
     * purpose : Ability to set the color of note

     **/

    @PutMapping(value = "/setNoteColor/{noteId}")
    public ResponseEntity<ResponseDTO> setNoteColor(@RequestHeader String token,
                                                        @PathVariable Long noteId,
                                                        @RequestBody String color){
        ResponseDTO res = noteService.setNoteColor(token,noteId, color);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
