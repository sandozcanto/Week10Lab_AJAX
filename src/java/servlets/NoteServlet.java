/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Note;
import org.eclipse.jdt.internal.compiler.lookup.InferenceContext18;
import services.NoteService;

/**
 *
 * @author 779137
 */
public class NoteServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        NoteService ns = new NoteService();

        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        if (ajax) {

            List<Note> noteList = null;
            try {
                noteList = ns.getAll();
                request.setAttribute("noteList", noteList);

                return;
            } catch (Exception ex) {
                Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            List<Note> noteList = null;
            try {
                noteList = ns.getAll();
                request.setAttribute("noteList", noteList);

            } catch (Exception ex) {
                Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
            }

            getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        NoteService ns = new NoteService();

        String action = request.getParameter("action");

        try {
            if (action.equals("Add")) {

                String title = request.getParameter("Title");
                String textArea = request.getParameter("TextArea");
                ns.insert(getnewId(ns.getAll()), new Date(), title, textArea);

                request.setAttribute("title", "");
                request.setAttribute("textArea", "");

            } else if (action.equals("Edit")) {

                int noteId = Integer.parseInt(request.getParameter("NoteId"));

                Note idList = null;
                try {
                    idList = ns.get(noteId);
                } catch (Exception ex) {
                    Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(action);
                request.setAttribute("view", "Edit");
                session.setAttribute("updateID", noteId);
                request.setAttribute("title", idList.getTitle());
                request.setAttribute("textArea", idList.getContents());

            } else if (action.equals("Save")) {
                String title = request.getParameter("Title");
                String textArea = request.getParameter("TextArea");

                int uID = (int) session.getAttribute("updateID");

                ns.update(uID, new Date(), title, textArea);
            }

        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }

        List<Note> noteList = null;

        try {
            noteList = ns.getAll();
            request.setAttribute("noteList", noteList);
        } catch (Exception ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext()
                .getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private int getnewId(List<Note> noteList) {

        int id = noteList.size() + 1;
        return id;
    }

}
