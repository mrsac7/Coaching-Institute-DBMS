<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>

<head>
    <title>Home - StarCoaching</title>
    <link rel="stylesheet" type="text/css" href="style/style.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
    <div id="main">
        <div id="header">
            <jsp:include page="logo.jsp" />
            <div id="menubar">
                <ul id="menu">
                    <li><a href="/">Home</a></li>
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <li class="selected"><a href="/users">Users</a></li>
                            <li><a href="/payroll">Payroll</a></li>
                            <li>
                                <a class="dropdown" href="#">Academics <i class="fa fa-caret-down"></i></a>
                                <ul id="sub-menu">
                                    <li><a href="/courses">Courses</a></li>
                                    <li><a href="/tests">Tests</a></li>
                                    <li><a href="/enrollment">Enrollment</a></li>
                                </ul>
                            </li>
                            <li><a href="/attendance">Attendance</a></li>
                            <li><a href="/logout">Log Out ( ${pageContext.request.userPrincipal.name} )</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/login">Log In</a></li>
                            <li><a href="/register">Register</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
        <div id="content_header"></div>
        <div id="site_content">
            <jsp:include page="sidebar.jsp" />
            <div class="content">
                <h1>All Users</h1>
                <p>${message}</p>
                    
                    <table class="hor-table">
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>Email</th>
                                <th>Role</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${allUsers}" var="user">

                                <tr>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <td>${user.role}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </div>
</body>

</html>