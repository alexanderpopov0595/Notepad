package com.epam.javacore.jotter.service.note;

import com.epam.javacore.jotter.domain.note.Note;

import java.util.List;

public interface NoteService {

    long addNote(Note note, String login);

    void updateNote(Note note);

    Note selectNote(long id, String login);

    List<Note> selectNoteList(String login);

    List<Note> selectNoteListForSubscriber(String ownerLogin, String subscriberLogin);



    void deleteNote(long id);
}
