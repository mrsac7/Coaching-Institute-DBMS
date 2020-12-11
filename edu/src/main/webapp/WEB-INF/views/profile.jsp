<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
                <ul id="menu" style="list-style-type: none;">
                    <li><a href="/">Home</a></li>
                    <sec:authentication var="user" property="principal" />
                    <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
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
                    </sec:authorize>
                    <sec:authorize access="!hasRole('ROLE_ADMIN') and isAuthenticated()">
                        <li class="selected"><a href="/profile">Profile</a></li>
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
            <c:choose>
                <c:when test="${userX.role == 'ROLE_STUDENT'}">
                    <div class="content">
                        <h1>Profile</h1>
                        <p>${message}</p>
                        <div>
                            <h4 style="text-transform:uppercase;display:inline-block; vertical-align: bottom; padding-top: 6px">
                                ${student.firstName} ${student.middleName}
                                ${student.lastName}</h4>
                    
                            <ul id="small-button">
                                <li>
                                    <form action="/profile/${userX.username}/edit">
                                        <input type="submit" value="Edit" />
                                    </form>
                                </li>
                            </ul>
                        </div>
                        <ul class="ver-table">
                            <li style="border-top: black solid 1px;">
                                <label>Username:</label>
                                <span>${userX.username}</span>
                            </li>
                            <li>
                                <label>Student ID:</label>
                                <span>${student.studentID}</span>
                            </li>
                            <li>
                                <label>Email:</label>
                                <span>${userX.email}</span>
                            </li>
                            <li>
                                <label>Date Of Birth:</label>
                                <span>
                                    <fmt:formatDate pattern="dd-MMM-yyyy" value="${student.dateOfBirth}" /></span>
                            </li>
                            <li>
                                <label>Sex:</label>
                                <span>${student.sex}</span>
                            </li>
                            <li>
                                <label>Age:</label>
                                <span>${student.age}</span>
                            </li>
                            <li>
                                <label>Contact:</label>
                                <span>${student.contactNo}</span>
                            </li>
                            <li>
                                <label>Address:</label>
                                <span>${student.houseNo} ${student.street} ${student.city} ${student.pin}</span>
                            </li>
                            <li>
                                <label>Guadian Name:</label>
                                <span>${guardian.gfirstName} ${guardian.gmiddleName} ${guardian.glastName}</span>
                            </li>
                            <li>
                                <label>Guardian Contact:</label>
                                <span>${guardian.gcontactNo}</span>
                            </li>
                        </ul>
                    </div>
                </c:when>
                <c:when test="${userX.role == 'ROLE_TEACHER'}">
                <div class="content">
                    <h1>Profile</h1>
                    <p>${message}</p>
                    <div>
                        <h4 style="text-transform:uppercase;display:inline-block; vertical-align: bottom; padding-top: 6px">
                            ${teacher.firstName} ${teacher.middleName}
                            ${teacher.lastName}</h4>
                
                        <ul id="small-button">
                            <li>
                                <form action="/profile/${userX.username}/edit">
                                    <input type="submit" value="Edit" />
                                </form>
                            </li>
                        </ul>
                    </div>
                    <ul class="ver-table">
                        <li style="border-top: black solid 1px;">
                            <label>Username:</label>
                            <span>${userX.username}</span>
                        </li>
                        <li>
                            <label>Teacher ID:</label>
                            <span>${teacher.teacherID}</span>
                        </li>
                        <li>
                            <label>Email:</label>
                            <span>${userX.email}</span>
                        </li>
                        <li>
                            <label>Date Of Birth:</label>
                            <span>
                                <fmt:formatDate pattern="dd-MMMM-yyyy" value="${teacher.dateOfBirth}" /></span>
                        </li>
                        <li>
                            <label>Sex:</label>
                            <span>${teacher.sex}</span>
                        </li>
                        <li>
                            <label>Age:</label>
                            <span>${teacher.age}</span>
                        </li>
                        <li>
                            <label>Contact:</label>
                            <span>${teacher.contactNo}</span>
                        </li>
                        <li>
                            <label>Address:</label>
                            <span>${teacher.houseNo} ${teacher.street} ${teacher.city} ${teacher.pin}</span>
                        </li>
                        <li>
                            <label>Qualifications:</label>
                            <span>${teacher.qualification}</span>
                        </li>
                        <li>
                            <label>Experience:</label>
                            <span>${teacher.experience}</span>
                        </li>
                    </ul>
                </div>
                </c:when>
            </c:choose>
        </div>
        <jsp:include page="footer.jsp" />
    </div>
</body>

</html>