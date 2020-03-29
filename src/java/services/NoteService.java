/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.NoteDB;
import java.util.Date;
import java.util.List;
import models.Note;

/**
 *
 * @author 779137
 */
public class NoteService {
    
    private NoteDB noteDB;

    public NoteService() {
        noteDB = new NoteDB();
    }

    public Note get(int noteid) throws Exception {
        return noteDB.get(noteid);
    }

    public List<Note> getAll() throws Exception {
        return noteDB.getAll();
    }

    public int update(Integer noteid, Date datecreated, String title, String contents) throws Exception {
        Note note = get(noteid);
        note.setDatecreated(datecreated);
        note.setTitle(title);
        note.setContents(contents);
        return noteDB.update(note);
    }

    public int delete(Integer noteid) throws Exception {
        Note deletedNote = noteDB.get(noteid);

        return noteDB.delete(deletedNote);
    }

    public int insert(Integer noteid, Date datecreated, String title, String contents) throws Exception {
        Note note = new Note(noteid,datecreated, title, contents);
        return noteDB.insert(note);
    }
    
}
