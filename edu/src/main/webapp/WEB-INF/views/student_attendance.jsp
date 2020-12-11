<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>

<head>
    <title>Attendance - StarCoaching</title>
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
                                <li class="selected"><a href="/take_attenance">Take Attendance</a></li>
                            </ul>
                        </li>
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
                    <h1>Student Attendance</h1>
                    <p>${message}</p>
                    <div>
                        <form action="/student_attendance" method="POST">
                            <div>
                                <ul id="small-button2">
                                    <li>
                                        <span><input type="date" name="date" placeholder="Choose Date" value="${date}"></span>
                                    </li>
                                </ul>
                                <ul id="small-button2">
                                    <li>
                                        <span>
                                            <select style="padding: 3px" name="batchID" value="${batchID}" aria-placeholder="Choose Batch">
                                                <c:choose>
                                                    <c:when test="${batchID == 'CLASS_IX'}">
                                                        <option value="CLASS_IX" selected>Class IX</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="CLASS_IX">Class IX</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${batchID == 'CLASS_X'}">
                                                        <option value="CLASS_X" selected>Class X</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="CLASS_X">Class X</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${batchID == 'CLASS_XI'}">
                                                        <option value="CLASS_XI" selected>Class XI</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="CLASS_XI">Class XI</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${batchID == 'CLASS_XII'}">
                                                        <option value="CLASS_XII" selected>Class XII</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="CLASS_XII">Class XII</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </span>
                                    </li>
                                </ul>
                            </div>
                            <div>
                                <ul id="small-button">
                                    <li>
                                        <input style="padding: 4px 15px" type="submit" value="Proceed" />
                                    </li>
                                </ul>
                            </div>
                        </form>
                    </div>
                    <div style="margin-top: 50px">
                        <table class="hor-table">
                            <thead>
                                <tr>
                                    <th>Student ID</th>
                                    <th>Student Name</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${allStudents}" var="student">
                                    <tr>
                                        <td>${student.studentID}</td>
                                        <td>${student.firstName} ${student.middleName} ${student.lastName}</td>
                                        <c:choose>
                                            <c:when test="${student.status == 'Present'}">
                                                <td style="color:#228B22;">${student.status}</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td style="color:red">${student.status}</td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td>
                                            <span>
                                                <form action="/student_attendance/${student.studentID}" method="POST" class="form-button">
                                                    <input name="date" value="${date}" style="display: none">
                                                    <input name="studentID" value="${student.studentID}" style="display: none">
                                                    <input name="batchID" value="${batchID}" style="display: none">
                                                    <input type="submit" name="status" value="Present" />
                                                </form> 
                                                &nbsp;
                                                <form action="/student_attendance/${student.studentID}" method="POST" class="form-button">
                                                    <input name="date" value="${date}" style="display: none">
                                                    <input name="studentID" value="${student.studentID}" style="display: none">
                                                    <input name="batchID" value="${batchID}" style="display: none">
                                                    <input style="color: red; border: 1px solid red;" type="submit" name="status" value="Absent" />
                                                </form>
                                            </span>
                                        </td>
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