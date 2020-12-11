<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html>

<head>
    <title>FAQs - StarCoaching</title>
    <meta name="description" content="website description" />
    <meta name="keywords" content="website keywords, website keywords" />
    <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
    <link rel="stylesheet" type="text/css" href="/style/style.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                        <li class="selected">
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
            <div id="banner"></div>
            <div id="sidebar_container">
                <div class="sidebar_item">
                    <h3>Quick Links</h3>
                    <ul>
                        <li>&#8212;&nbsp;&nbsp;<a href="/teacher">All Teachers</a></li>
                        <sec:authorize access="isAuthenticated()">
                            <li>&#8212;&nbsp;&nbsp;<a href="#">Study Materials</a></li>
                            <li>&#8212;&nbsp;&nbsp;<a href="/change_password">Change Password</a></li>
                        </sec:authorize>
                        <li>&#8212;&nbsp;&nbsp;<a href="#">FAQs</a></li>
                    </ul>
                </div>
                <div class="sidebar_item" style="padding-bottom: 50px;">
                    <h3>Notifications</h3>
                    <div>
                        <ul id="small-button">
                            <li>
                                <a href="/notification/add">
                                    <input type="submit" value="Add" />
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div style="clear:both;">
                        <c:forEach items="${list}" var="notification">
                            <span style="display:block; margin-bottom: 5px;"><span
                                    style="color:#1a73e8">${notification.emailID}</span> &#8212; <a
                                    href="notification/${notification.notificationID}"
                                    style="text-decoration: underline;">${notification.header}</a>
                            </span>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <!-- <include src="sidebar.jsp"></include> -->
            <div class="content">
                <h1>Course - X</h1>
                <h4>CHEMISTRY</h4>
                <p>Syllabus of Chemistry Course</p>
                <h4>PHYSICS</h4>
                <p>Syllabus of Physics Course</p>
                <h4>MATHS</h4>
                <p>Syllabus of Maths Course</p>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </div>
</body>

</html>