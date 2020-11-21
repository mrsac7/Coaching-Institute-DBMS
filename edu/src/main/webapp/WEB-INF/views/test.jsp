<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


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
                        <li><a href="/attendance/${user.username}">Attendance</a></li>
                        <li><a href="/tests">Result</a></li>
                        <li><a href="/enrollment">Enrollment</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_TEACHER') and isAuthenticated()">
                        <li>
                            <a>Attendance <i class="fa fa-caret-down"></i></a>
                            <ul id="sub-menu">
                                <li><a href="/attendance/${user.username}">View Attendance</a></li>
                                <li><a href="/take_attendance">Take Attendance</a></li>
                            </ul>
                        </li>
                        <li><a href="/test">Test</a></li>
                        <li><a href="#">Payroll</a></li>
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
                <h1>All Tests</h1>
                <p>${message}</p>
                <div>
                    <ul id="small-button">
                        <li>
                            <a href = "/test/add">
                                <input type="submit" value="Add" />
                            </a>
                        </li>
                    </ul>
                </div>
                <table class="hor-table" style="margin-top: 30px;">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Course</th>
                            <th>Date</th>
                            <th>Start</th>
                            <th>End</th>
                            <th>Marks</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${allTest}" var="test">        
                            <tr>
                                <td>${test.testID}</td>
                                <td>${test.testName}</td>
                                <td>${test.courseID}</td>
                                <td><fmt:formatDate pattern="dd-MM-yyyy" value="${test.date}" /></td>
                                <td>${test.startTime}</td>
                                <td>${test.endTime}</td>
                                <td>${test.marks}</td>
                                <td>
                                    <span>
                                        <form action="" class="form-button">
                                            <input type="submit" value="Result">
                                        </form>
                                        <form action="/test/${test.testID}/edit" class="form-button">
                                            <input style= "border: 1px solid  #1a73e8;color: #1a73e8;" type="submit" value="Edit">
                                        </form>
                                        <form action="/test/${test.testID}/delete" method = "POST" class="form-button">
                                            <input style= "border: 1px solid red;color:red;" type="submit" value="Delete">
                                        </form>
                                    </span>
                                </td>
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