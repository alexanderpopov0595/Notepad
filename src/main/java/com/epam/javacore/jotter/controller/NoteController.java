package com.epam.javacore.jotter.controller;

import com.epam.javacore.jotter.domain.note.Note;
import com.epam.javacore.jotter.service.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.security.Principal;

/**
 * Controller is responsible for note's requests
 */
@Controller
@RequestMapping("/notes")
public class NoteController {

    /**
     * Injected note service
     */
    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService){
        this.noteService=noteService;
    }

    @GetMapping
    public String showMyNoteList(Principal principal, Model model){
        model.addAttribute("noteList", noteService.selectNoteList(principal.getName()));
        return "notes/list";
    }

    @GetMapping("/user/{ownerLogin}")
    public String showNoteListToSubscriber(@PathVariable String ownerLogin, Principal principal,Model model){
        model.addAttribute("noteList", noteService.selectNoteListForSubscriber(ownerLogin, principal.getName()));
        return "notes/list";
    }

    /**
     * Method puts note in model and shows note form
     * @param model
     * @return note form page
     */
    @GetMapping("/addNote")
    public String showNoteForm(Model model){
        model.addAttribute("note", new Note());
        return "notes/form";
    }

    /**
     * Method gets note from form
     * If form has errors - method redirects back to form page
     * Then method gets user login from principal object and adds note to database
     * Then method redirects to created note's page
     * @param note
     * @param model
     * @param principal
     * @return note's page
     */
    @PostMapping("/addNote")
    public String addNoteFromForm(@ModelAttribute Note note, Model model, Principal principal){
        long id=noteService.addNote(note, principal.getName());
        return "redirect:/notes/"+id;
    }

    /**
     * Method gets user login from principle object and loads note by id form database
     * Then method puts note in model and redirects to note page
     * @param id
     * @param model
     * @param principal
     * @return note view page
     */
    @GetMapping("/{id}")
    public String showNote(@PathVariable long id, Model model, Principal principal){
        model.addAttribute("note", noteService.selectNote(id, principal.getName()));
        return "notes/view";
    }

    @PostMapping("/{id}")
    public String updateNoteFromForm(@ModelAttribute Note note){
        noteService.updateNote(note);
        return "redirect:/notes/"+note.getId();

    }

    @PostMapping("/{id}/delete")
    public String deleteNote(@PathVariable long id){
        noteService.deleteNote(id);
        return "redirect:/notes";
    }


}
