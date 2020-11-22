<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>

<head>
    <title>Result - StarCoaching</title>
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
                        <li>
                            <a class="dropdown" href="#">Academics <i class="fa fa-caret-down"></i></a>
                            <ul id="sub-menu">
                                <li>
                                    <a href="/courses">Courses <i class="fa fa-caret-right"></i></a>
                                    <ul id="subsub-menu">
                                        <li><a href="#">Class IX</a></li>
                                        <li><a href="#">Class X</a></li>
                                        <li><a href="#">Class XI</a></li>
                                        <li><a href="#">Class XII</a></li>
                                    </ul>
                                </li>
                                <li><a href="/tests">Tests</a></li>
                                <li><a href="/enrollment">Enrollment</a></li>
                            </ul>
                        </li>
                        <li><a href="/attendance">Attendance</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_STUDENT') and isAuthenticated()">
                        <li>
                            <a class="dropdown" href="#">Courses <i class="fa fa-caret-down"></i></a>
                            <ul id="sub-menu">
                                <li><a href="#">Class IX</a></li>
                                <li><a href="#">Class X</a></li>
                                <li><a href="#">Class XI</a></li>
                                <li><a href="#">Class XII</a></li>
                            </ul>
                        </li>
                        <li><a href="/tests">Result</a></li>
                        <li><a href="/enrollment">Enrollment</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_TEACHER') and isAuthenticated()">
                        <li>
                            <a>Attendance <i class="fa fa-caret-down"></i></a>
                            <ul id="sub-menu">
                                <li><a href="#">View Attendance</a></li>
                                <li><a href="/take_attenance">Take Attendance</a></li>
                            </ul>
                        </li>
                        <li class="selected"><a href="/test">Test</a></li>
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
            <div id="content_header"></div>
            <div id="site_content">
                <jsp:include page="sidebar.jsp" />
                <div class="content">
                    <h1>Result</h1>
                    <p>${message}</p>
                    <ul class="ver-table">
                        <li>
                            <label>Student ID:</label>
                            <span>${student.studentID}</span>
                        </li>
                        <li>
                            <label>Student Name:</label>
                            <span>${student.firstName} ${student.middleName} ${student.lastName}</span>
                        </li>
                    </ul>
                    <div style="margin-top: 50px">
                        <table class="hor-table">
                            <thead>
                                <tr>
                                    <th>Test ID</th>
                                    <th>Test Name</th>
                                    <th>Course</th>
                                    <th>Marks</th>
                                    <th>Percentage</th>
                                    <th>Rank</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${resultList}" var="result">
                                    <tr>
                                        <td>${result.testID}</td>
                                        <td>${result.testName}</td>
                                        <td>${result.courseID}</td>
                                        <c:choose>
                                            <c:when test="${result.marks >= 0}">
                                                <td>
                                                    ${result.marks}/${result.totalMarks}
                                                </td>
                                                <td>
                                                    ${result.percent}%
                                                </td>
                                                <td>
                                                    ${result.rank}
                                                </td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>
                                                    N/A
                                                </td>
                                                <td>
                                                    N/A
                                                </td>
                                                <td>
                                                    N/A
                                                </td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <jsp:include page="footer.jsp" />
        </div>
</body>

</html>