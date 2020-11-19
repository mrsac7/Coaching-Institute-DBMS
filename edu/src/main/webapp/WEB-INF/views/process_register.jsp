<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE HTML>
<html>

<head>
    <title>Register - StarCoaching</title>
    <meta name="description" content="website description" />
    <meta name="keywords" content="website keywords, website keywords" />
    <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
    <link rel="stylesheet" type="text/css" href="/style/style.css" />
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
                            <li><a href="/logout">Log Out ( ${pageContext.request.userPrincipal.name} )</a></li>
                            <li><a>Users</a></li>
                            <li><a>Payroll</a></li>
                            <li><a>Academics</a></li>
                            <li><a>Attendance</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/login">Log In</a></li>
                            <li class="selected"><a href="/register">Register</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
        <div id="content_header"></div>
        <div id="site_content">
            <jsp:include page="sidebar.jsp" />
            <div class="content">
                <h1>Register User</h1>
                <p>${message}</p>
                <form action="/process_register" method="POST">
                    <div class="form_settings">
                        <p><span>Username</span><input type="text" name="username" /></p>
                        <p><span>Email</span><input type="text" name="email"></p>
                        <p><span>Role</span>
                            <select id="id" name="role">
                                <option value="student" selected>Student</option>
                                <option value="teacher">Teacher</option>
                            </select>
                        </p>
                        <p><span>password</span><input type="text" name="password" /></p>
                        <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit"
                                value="Register" /></p>
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </div>
</body>

</html>