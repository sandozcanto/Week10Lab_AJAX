<%-- 
    Document   : notes
    Created on : Mar 4, 2020, 4:05:48 PM
    Author     : 779137
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/notes.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Notes!</h1>
        <table>
            <tr>
                <td>Date Created</td>
                <td>Title</td>
            </tr>
            <c:forEach var="note" items="${noteList}" varStatus="status">
                <form method="POST">
                    <tr>
                    <input type="hidden" name="action" value="Edit"/>
                    <input type="hidden" name="NoteId" value="${note.noteid}"/>
                    <td>${note.datecreated}</td>
                    <td>${note.title}</td>
                    <td><input type="submit" value="Edit"/></td>
                    </tr>
                </form>    
            </c:forEach>
        </table><br>

        <c:if test="${view == null}">
            <form method="POST" id="noteForm">

                <h1>Add Note</h1>
                <input type="hidden" name="action" value="Add"/>
                <input type="text" placeholder="Title" value="${title}" name="Title" id="Title"/><br>
                <textarea rows="6" cols="35"  name="TextArea" id="TextArea" >${textArea}</textarea>
                <input type="submit" value="Add"/>
            </form>
        </c:if>

        <c:if test="${view != null}">
            <form method="POST" id="noteForm">

                <h1>Edit Note</h1>
                <input type="hidden" name="action" value="Save"/>
                <input type="text" placeholder="Title" value="${title}" name="Title" id="Title" class="editNote"/><br>
                <textarea rows="6" cols="35"  name="TextArea" id="TextArea" class="editNote" >${textArea}</textarea>
                
            </form>
        </c:if>
    </body>
</html>
