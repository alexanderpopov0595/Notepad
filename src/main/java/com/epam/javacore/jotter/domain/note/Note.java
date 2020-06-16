package com.epam.javacore.jotter.domain.note;

import com.epam.javacore.jotter.domain.user.User;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "note",  cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<NoteDetails> noteDetailsList;

    public Note() {
        this.noteDetailsList = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<NoteDetails> getNoteDetailsList() {
        return noteDetailsList;
    }

    public void setNoteDetailsList(List<NoteDetails> noteDetailsList) {
        this.noteDetailsList = noteDetailsList;
    }

}
