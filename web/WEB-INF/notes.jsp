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
        <form method="POST">
            
<!--        <h1><c:out value="${view}" default="Add" />Note</h1>-->
            <%--<c:if test="${type = edit}">--%>
                <!--<p><h1>Edit Note</h1></p>-->
            <%--</c:if>--%>

            <h1>${view} Note</h1>
            <input type="hidden" name="action" value="${type}"/>
            <input type="text" placeholder="Title" value="${title}" name="Title"/><br>
            <textarea rows="6" cols="35"  name="TextArea">${textArea}</textarea>
            <input type="submit" value="${type}"/>
        </form>
    </body>
</html>
