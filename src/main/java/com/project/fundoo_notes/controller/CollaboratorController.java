package com.project.fundoo_notes.controller;

import com.project.fundoo_notes.dto.CollaboratorDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.model.CollaboratorModel;
import com.project.fundoo_notes.service.ICollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * purpose :Controller for Collaborator
 * @author : Swati
 * @version : 1.0
 * @since : 7-7-21
 **/
@RestController
public class CollaboratorController {
    @Autowired
    private ICollaboratorService collaboratorService;

    /**
     *Add collaborator
     **/


    @PostMapping("/addCollaborator")
    public ResponseEntity<ResponseDTO> addCollaboratorForNote(@RequestHeader String token,
                                                              @RequestParam Long noteId,
                                                              @RequestBody String email){
        ResponseDTO res= collaboratorService.addCollaboratorForNote(token,noteId,email);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    /**
     * remove collaborator from note
     **/
    @PostMapping("/removeCollaborator")
    public ResponseEntity<ResponseDTO> removeCollaborator(@RequestHeader String token,
                                                              @RequestParam Long noteId,
                                                              @RequestBody String email){
        ResponseDTO res= collaboratorService.removeCollaborator(token,noteId,email);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    /**
     *  get all collaborator of note
     **/
    @PostMapping("/getCollaborator")
    public ResponseEntity<List> getCollaborator(@RequestHeader String token,
                                                       @RequestParam Long noteId){
        List res= (List) collaboratorService.getCollaborator(token,noteId);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

}
