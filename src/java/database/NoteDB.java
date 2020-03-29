/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Note;

/**
 *
 * @author 779137
 */
public class NoteDB {

    public int insert(Note note) throws Exception {

        EntityManager em
                = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {

            et.begin();            
            em.persist(note);
            et.commit();
            return 1;
        } catch (Exception e) {
            et.rollback();
            throw e;
        } finally {
            em.close();
        }

    }

    public int update(Note note) throws Exception {

        EntityManager em
                = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(note);
            et.commit();
            return 1;
        } catch (Exception e) {
            et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Note> getAll() throws Exception {
        EntityManager em
                = DBUtil.getEmFactory().createEntityManager();
        try {

            List<Note> note = em.createNamedQuery("Note.findAll", Note.class).getResultList();
            return note;
        } finally {
            em.close();
        }

    }

    /**
     * Get a single user by their username.
     *
     * @param noteId
     *
     * @return A User object if found, null otherwise.
     * @throws java.lang.Exception
     * @throws NotesDBException
     */
    public Note get(int noteId) throws Exception {

        EntityManager em
                = DBUtil.getEmFactory().createEntityManager();
        try {
            Note note = em.find(Note.class, noteId);
            return note;
        } finally {
            em.close();
        }

    }

    public int delete(Note note) throws Exception {

        EntityManager em
                = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.remove(em.merge(note));
            et.commit();
            return 1;
        } catch (Exception e) {
            et.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

}
