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
                    <li class="selected"><a href="/">Home</a></li>
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <li><a href="/users">Users</a></li>
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
            <div id="banner"></div>
            <jsp:include page="sidebar.jsp" />
            <div id="content">
                <h1>Welcome to Vision Kids Planet School</h1>
                <h2>The Vision Kids Planet School Experience</h2>
                <p>The Vision Kids Planet Schoo experience will help each child to be knowledgeable, wise, curious and
                    creative, responsible and confident. It is our endeavour to help each child evolve into well-rounded
                    learners with minds that remain receptive to new ideas and changing needs. To accomplish this, we
                    have designed an integrated curriculum that allows students to learn in great depth as well as see
                    connections that naturally exist among subject areas making learning more meaningful and effective.
                    Taking pioneering steps in establishing a boutique educational Institution</p>
                <h2>VKP Bhilwara, Rajasthan</h2>
                <p>The school's role goes beyond imparting only curriculum-based knowledge. It creates and maintains an
                    environment that allows the individual student to develop himself/herself while acquiring the skills
                    and attitude required evolving into an active and responsible citizen.</p>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </div>
</body>

</html>