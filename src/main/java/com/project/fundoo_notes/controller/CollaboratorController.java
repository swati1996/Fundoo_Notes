package com.project.fundoo_notes.controller;

import com.project.fundoo_notes.dto.CollaboratorDTO;
import com.project.fundoo_notes.dto.ResponseDTO;
import com.project.fundoo_notes.service.ICollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CollaboratorController {
    @Autowired
    private ICollaboratorService collaboratorService;


    @PostMapping("/addCollaborator")
    public ResponseEntity<ResponseDTO> addCollaboratorForNote(@RequestHeader String token,
                                                              @RequestParam Long noteId,
                                                              @RequestBody CollaboratorDTO collDTO){
        collaboratorService.addCollaboratorForNote(token,noteId,collDTO);
        return null;

    }


}
