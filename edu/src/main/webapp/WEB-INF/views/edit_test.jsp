<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>

<head>
    <title>Test - StarCoaching</title>
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
                        <li><a href="/payroll">Payroll</a></li>
                        <li class="selected">
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
                        <li class="selected"><a href="/result/${user.username}">Result</a></li>
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
                        <li class="selected"><a href="/test">Test</a></li>
                        <li><a href="/payroll/${user.username}">Payroll</a></li>
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
                <h1>Edit Test</h1>
                <p>${message}</p>
                <form action="/test/${test.testID}/edit" method="POST">
                    <ul class="ver-table">
                        <li>
                            <label>Test ID:</label>
                            <span><input class="read" type="text" name="testID" value="${test.testID}" readonly /></span>
                        </li>
                        <li>
                            <label>Name:</label>
                            <span><input type="text" name="testName" placeholder="Name" value="${test.testName}" /></span>
                        </li>
                        <li>
                            <label>Course:</label>
                            <span>
                                <select id="id" name="courseID" aria-placeholder="Course ID">
                                    <c:forEach items="${allCourses}" var="course">
                                        <c:choose>
                                            <c:when test="${test.courseID == course.courseID}">
                                                <option value="${course.courseID}" selected>${course.courseID}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${course.courseID}">${course.courseID}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </span>
                        </li>
                        <li>
                            <label>Date:</label>
                            <fmt:formatDate pattern="yyyy-MM-dd" value="${test.date}" var="date" />
                            <span><input type="date" name="date" placeholder="Date" value="${date}"></span>
                        </li>
                        <li>
                            <label>Timing:</label>
                            <fmt:formatDate pattern="HH:mm" value="${test.startTime}" var="stime" />
                            <fmt:formatDate pattern="HH:mm" value="${test.endTime}" var="etime" />
                            <span>
                                <input type="time" name="stime" placeholder="Start Time" value="${stime}">
                                &nbsp; To &nbsp;
                                <input type="time" name="etime" placeholder="End Time" value="${etime}">
                            </span>
                        </li>
                        <li>
                            <label>Total Marks:</label>
                            <span><input type="number" name="marks" placeholder="Marks" value="${test.marks}"></span>
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