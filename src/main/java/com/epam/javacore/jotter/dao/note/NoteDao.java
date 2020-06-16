package com.epam.javacore.jotter.dao.note;

import com.epam.javacore.jotter.domain.note.Note;

import java.util.List;

public interface NoteDao {

    void addNote(Note note);

    void updateNote(Note note);

    Note selectNote(long id);

    List<Note> selectNoteList(String login);

    void deleteNote(long id);

    void deleteNoteDetails(long id);
}
