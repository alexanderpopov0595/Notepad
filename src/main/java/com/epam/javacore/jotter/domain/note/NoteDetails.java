package com.epam.javacore.jotter.domain.note;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="note_details")
public class NoteDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_note", referencedColumnName = "id")
    private Note note;

    @Column(name = "header")
    private String header;

    @Column(name="text")
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date")
    //private Date date;
    private Calendar date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNote(Note note){
        this.note=note;
    }

    public Note getNote(){
        return note;
    }

    public void setHeader(String header){
        this.header=header;
    }
    public String getHeader(){
        return header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
