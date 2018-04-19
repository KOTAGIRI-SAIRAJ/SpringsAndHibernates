<%@page isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<html>
<head>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employee Management Screen</title>
    <style type="text/css">
        .table { border: 1px solid #2980B9; }
        .table thead > tr > th { border-bottom: none; }
        .table thead > tr > th, .table tbody > tr > th, .table tfoot > tr > th, .table thead > tr > td, .table tbody > tr > td, .table tfoot > tr > td { border: 1px solid #2980B9; }
    </style>
</head>
<body>
    <div class="container">
    <jsp:include page="Header.jsp"></jsp:include>
            <table border="1" style="margin-top: 25px">
                <th>Meeting Title</th>
    <th>Meeting Duration</th>
    <th>Meeting Name</th>
    <th>Operations</th>
               
     
                <c:forEach var="meeting" items="${meetingList}">
                    <tr>
                        <td>${meeting.meeting_title}</td>
                        <td>${meeting.meeting_dur}</td>
                        <td>${meeting.client_name}</td>
                        <td>
    <a href="editMeeting?id=${meeting.id}"><input type="button" class="btn btn-success" value="Edit"></a>
    <a href="deleteMeeting?id=${meeting.id}"><input type="button" class="btn btn-danger" value="Delete"></a>
    <a href="enrollEmployees?id=${meeting.id}"><input type="button" class="btn btn-info" value="Enroll Employees"></a>
    </td>
                    </tr>
                    <%--<c:forEach var="meeting" items="${meetingList}">

                    </c:forEach>--%>
                </c:forEach>
            </table>
        </div>
</body>
</html>