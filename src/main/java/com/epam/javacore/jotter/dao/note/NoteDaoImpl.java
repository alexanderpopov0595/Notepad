package com.epam.javacore.jotter.dao.note;

import com.epam.javacore.jotter.domain.note.Note;
import com.epam.javacore.jotter.domain.note.NoteDetails;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class NoteDaoImpl implements NoteDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void addNote(Note note) {
        entityManager.persist(note);
    }

    @Override
    public void updateNote(Note note) {
        entityManager.persist(note.getNoteDetailsList().get(0));
    }

    @Override
    public Note selectNote(long id) {
        return (Note)entityManager.createQuery("SELECT n FROM Note n JOIN NoteDetails nd ON n.id=nd.note.id WHERE n.id=:id ORDER BY nd.date DESC ").setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Note> selectNoteList(String login) {
        return entityManager.createQuery("SELECT n FROM Note n JOIN User u ON n.user.id=u.id WHERE u.login=:login")
                .setParameter("login", login)
                .getResultList();
    }

    @Override
    public void deleteNote(long id) {
        entityManager.createQuery("DELETE Note n WHERE n.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void deleteNoteDetails(long id) {
        entityManager.createQuery("DELETE NoteDetails nd WHERE nd.note.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
