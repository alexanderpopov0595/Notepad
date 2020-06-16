package com.epam.javacore.jotter.service.note;

import com.epam.javacore.jotter.dao.note.NoteDao;
import com.epam.javacore.jotter.dao.subscribe.SubscribeDao;
import com.epam.javacore.jotter.dao.user.UserDao;
import com.epam.javacore.jotter.domain.note.Note;
import com.epam.javacore.jotter.domain.note.NoteDetails;
import com.epam.javacore.jotter.domain.user.Subscriber;
import com.epam.javacore.jotter.domain.user.User;
import com.epam.javacore.jotter.enums.Status;
import com.epam.javacore.jotter.exceptions.AccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Service is responsible for buiseness logic of notes
 */
@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    /**
     * Injected note dao
     */
    private NoteDao noteDao;

    private SubscribeDao subscribeDao;

    /**
     * Injected user dao
     */
    private UserDao userDao;

    @Autowired
    public NoteServiceImpl(NoteDao noteDao, UserDao userDao, SubscribeDao subscribeDao){
        this.noteDao=noteDao;
        this.userDao=userDao;
        this.subscribeDao=subscribeDao;
    }

    public boolean checkAccess(String noteOwner, String login){

        if(noteOwner.equals(login)){

            return true;
        }
        List<Subscriber> list=subscribeDao.selectSubscriberList(noteOwner);
        for(Subscriber s: list){

            if(s.getSubscriber().getLogin().equals(login) && s.getStatus()== Status.SUBSCRIBED){

                return true;
            }
        }

        return false;
    }

    /**
     * Method adds note to database
     * First method loads user from database by login
     * Second method sets user and date to note
     * Third method adds note to database
     * At last method returns created note id
     * @param note
     * @param login
     * @return note id
     */
    @Override
    public long addNote(Note note, String login) {
        User user=userDao.selectUser(login);
        note.setUser(user);
        for(NoteDetails nd: note.getNoteDetailsList()) {
            nd.setDate(Calendar.getInstance());
            nd.setNote(note);
        }
        noteDao.addNote(note);
        return note.getId();
    }

    @Override
    public void updateNote(Note note) {
        for(NoteDetails nd: note.getNoteDetailsList()) {
            nd.setDate(Calendar.getInstance());
            nd.setNote(note);
        }
        noteDao.updateNote(note);
    }

    @Override
    public Note selectNote(long id, String login) {
        Note note=noteDao.selectNote(id);

        if(!checkAccess(note.getUser().getLogin(), login)){
            throw new AccessDeniedException();
        }
        Collections.sort(note.getNoteDetailsList(), new Comparator<NoteDetails>() {
            @Override
            public int compare(NoteDetails o1, NoteDetails o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
        return note;
    }



    @Override
    public List<Note> selectNoteList(String login) {
        return noteDao.selectNoteList(login);
    }

    @Override
    public List<Note> selectNoteListForSubscriber(String ownerLogin, String subscriberLogin){
        if(!checkAccess(ownerLogin, subscriberLogin)){
            throw new AccessDeniedException();
        }
        return noteDao.selectNoteList(ownerLogin);
    }

    @Override
    public void deleteNote(long id) {
        noteDao.deleteNote(id);
        noteDao.deleteNoteDetails(id);
    }
}
