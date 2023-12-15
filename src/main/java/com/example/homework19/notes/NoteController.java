package com.example.homework19.notes;


import com.example.homework19.notes.dto.create.CreateNoteRequest;
import com.example.homework19.notes.dto.create.CreateNoteResponse;
import com.example.homework19.notes.dto.delete.DeleteNoteResponse;
import com.example.homework19.notes.dto.get.GetAllNotesResponse;
import com.example.homework19.notes.dto.update.UpdateNoteRequest;
import com.example.homework19.notes.dto.update.UpdateNoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
private final NoteService noteService;

@PostMapping
public CreateNoteResponse create(@RequestBody CreateNoteRequest request) {
return noteService.create( request);
}

@GetMapping
public GetAllNotesResponse getAllNotes() {
return noteService.getAllNotes();
}

@PatchMapping
public UpdateNoteResponse update( @RequestBody UpdateNoteRequest request) {
return noteService.update(request);
}

@DeleteMapping
public DeleteNoteResponse delete(@RequestParam(name = "id") long id) {
return noteService.delete( id);
}
}