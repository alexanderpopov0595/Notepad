package com.epam.javacore.jotter.service;

import com.epam.javacore.jotter.domain.note.Note;
import com.epam.javacore.jotter.domain.note.NoteDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class NoteServiceTest {

    private Note note;
    /*
    @Before
    public void startUp(){
        note=new Note();
        NoteDetails nd1=new NoteDetails();
        Calendar calendar1=Calendar.getInstance();
        calendar1.set(2020, 02, 01, 10, 10, 10);
        Date date1=calendar1.getTime();
        nd1.setDate(date1);

        NoteDetails nd2=new NoteDetails();
        Calendar calendar2=Calendar.getInstance();
        calendar2.set(2020, 02, 01, 10, 23, 10);
        Date date2=calendar2.getTime();
        nd2.setDate(date2);
        List<NoteDetails> noteDetailsList=new ArrayList<NoteDetails>();
        noteDetailsList.add(nd1);
        noteDetailsList.add(nd2);
        note.setNoteDetailsList(noteDetailsList);
    }

    @Test
    public void testReturnListSortedDesc(){

        for(NoteDetails nd: note.getNoteDetailsList()){
            System.out.println(nd.getDate());
        }
        Collections.sort(note.getNoteDetailsList(), new Comparator<NoteDetails>(){

            @Override
            public int compare(NoteDetails nd1, NoteDetails nd2) {
                if(nd1.getDate().before(nd2.getDate())){
                    return 1;
                }
                return -1;
            }
        });
        for(NoteDetails nd: note.getNoteDetailsList()){
            System.out.println(nd.getDate());
        }

    }*/
}
