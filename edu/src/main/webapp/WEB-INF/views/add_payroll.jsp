<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>

<head>
    <title>Payroll - StarCoaching</title>
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
                    <sec:authentication var="user" property="principal" />
                    <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                        <li><a href="/users">Users</a></li>
                        <li class="selected"><a href="/payroll">Payroll</a></li>
                        <li>
                            <a class="dropdown" href="#">Academics <i class="fa fa-caret-down"></i></a>
                            <ul id="sub-menu">
                                <li>
                                    <a href="/courses">Courses <i class="fa fa-caret-right"></i></a>
                                    <ul id="subsub-menu">
                                        <li><a href="/course/ix">Class IX</a></li>
                                        <li><a href="/course/x">Class X</a></li>
                                        <li><a href="/course/xi">Class XI</a></li>
                                        <li><a href="/course/xii">Class XII</a></li>
                                    </ul>
                                </li>
                                <li><a href="/test">Tests</a></li>
                                <li><a href="/view_enrollment">Enrollment</a></li>
                            </ul>
                        </li>
                        <li>
                            <a>Attendance <i class="fa fa-caret-down"></i></a>
                            <ul id="sub-menu">
                                <li><a href="/teacher_attendance">Teacher Attendance</a></li>
                                <li><a href="/student_attendance">Student Attendance</a></li>
                            </ul>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_STUDENT') and isAuthenticated()">
                        <li>
                            <a class="dropdown" href="#">Courses <i class="fa fa-caret-down"></i></a>
                            <ul id="sub-menu">
                                <li><a href="/course/ix">Class IX</a></li>
                                <li><a href="/course/x">Class X</a></li>
                                <li><a href="/course/xi">Class XI</a></li>
                                <li><a href="/course/xii">Class XII</a></li>
                            </ul>
                        </li>
                        <li><a href="/attendance/${user.username}">Attendance</a></li>
                        <li><a href="/result/${user.username}">Result</a></li>
                        <li><a href="/enrollment/${user.username}">Enrollment</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_TEACHER') and isAuthenticated()">
                        <li>
                            <a>Attendance <i class="fa fa-caret-down"></i></a>
                            <ul id="sub-menu">
                                <li><a href="/attendance/${user.username}">View Attendance</a></li>
                                <li><a href="/student_attendance">Student Attendance</a></li>
                            </ul>
                        </li>
                        <li><a href="/test">Test</a></li>
                        <li class="selected"><a href="/payroll/${user.username}">Payroll</a></li>
                    </sec:authorize>
                    <sec:authorize access="!hasRole('ROLE_ADMIN') and isAuthenticated()">
                        <li><a href="/profile/${user.username}">Profile</a></li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li><a href="/logout">Log Out ( ${user.username} )</a></li>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                        <li><a href="/login">Log In</a></li>
                        <li><a href="/register">Register</a></li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
        <div id="content_header"></div>
        <div id="site_content">
            <jsp:include page="sidebar.jsp" />
            <div class="content">
                <h1>Add Payroll</h1>
                <p>${message}</p>
                <form action="/payroll/add" method="POST">
                    <ul class="ver-table">
                        <li>
                            <label>Reference No:</label>
                            <span><input type="text" name="refNo" placeholder="Reference No" /></span>
                        </li>
                        <li>
                            <label>Teacher:</label>
                            <span>
                                <select id="id" name="teacherID" aria-placeholder="Teacher ID">
                                    <c:forEach items="${allTeacher}" var="teacher">
                                        <option value="${teacher.teacherID}">${teacher.teacherID} - ${teacher.firstName} ${teacher.middleName} ${teacher.lastName}</option>
                                    </c:forEach>
                                </select>
                            </span>
                        </li>
                        <li>
                            <label>Month:</label>
                            <span><input type="month" name="sdate" placeholder="Service Date"></span>
                        </li>
                        <li>
                            <label>Date Credited:</label>
                            <span><input type="date" name="creditDate" placeholder="Credit Date"></span>
                        </li>
                        <li>
                            <label>Amount:</label>
                            <span><input type="number" name="amount" placeholder="Amount"></span>
                        </li>
                    </ul>
                    <div class="container">
                        </span><input class="submit button" type="submit" value="Proceed" />
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </div>
</body>

</html>