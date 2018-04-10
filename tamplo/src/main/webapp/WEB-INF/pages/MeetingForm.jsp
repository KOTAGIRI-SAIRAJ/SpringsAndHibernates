<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Contact</title>
</head>
<body>
    <div class="container">
    <%--<div class="container">
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <a class="navbar-brand">${FormName} Meeting</a>
            <ul class="navbar-nav">
                <li ><a href="/"><input type="button" class="btn" style="background-color: #343a40" value="Home"></a></li>
                <li ><a href="allMeetings"><input type="button" class="btn" style="background-color: #343a40" value="Meetings"></a></li>
                <li class="active"><a href="newMeeting"><input type="button" class="btn" style="background-color: #343a40" value="New Meeting"></a></li>
            </ul>
        </nav>
    </div>--%>
    <jsp:include page="Header.jsp"></jsp:include>
    <form:form action="saveMeeting" method="post" modelAttribute="meeting">
                <table>
                    <form:hidden path="id"/>
                    <tr>
                        <td>Meeting Title:</td>
                        <td><form:input path="meeting_title" /></td>
                    </tr>
                    <tr>
                         <td>Meeting Duration(in hrs):</td>
                         <td><form:input path="meeting_dur" /></td>
                    </tr>
                    <tr>
                         <td>Client Name</td>
                         <td><form:input path="client_name" /></td>
                    </tr>

                    <tr>
                        <td colspan="2" align="center"><input type="submit" class="btn btn-primary" value="Save"></td>
                    </tr>
                </table>
                </form:form>
        </div>
</body>
</html>