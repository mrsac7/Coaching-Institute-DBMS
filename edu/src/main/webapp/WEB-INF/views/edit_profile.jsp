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
            <sec:authorize access="hasRole('ROLE_STUDENT') and isAuthenticated()" >
                <div class="content">
                    <h1>Edit Profile</h1>
                    <p>${message}</p>
                    <form action="/profile/${user.username}/update_student" method="POST">
                        <ul class="ver-table">
                            <li>
                                <label>Username:</label>
                                <span><input class="read" type="text" name="username" value="${user.username}" readonly /></span>
                            </li>
                            <li>
                                <label>StudentID:</label>
                                <span><input class="read" type="text" name="studentID" value="${student.studentID}" readonly /></span>
                            </li>
                            <li>
                                <label>Name:</label>
                                <span>
                                    <input type="text" name="firstName" placeholder="First Name" value="${student.firstName}">
                                    <input type="text" name="middleName" placeholder="Middle Name" value="${student.middleName}">
                                    <input type="text" name="lastName" placeholder="Last Name" value="${student.lastName}">
                                </span>
                            </li>
                            <li>
                                <label>Date Of Birth:</label>
                                <fmt:formatDate pattern="yyyy-MM-dd" value="${student.dateOfBirth}" var="dob" />
                                <span><input type="date" name="dateOfBirth" value="${dob}"></span>
                            </li>
                            <li>
                                <label>Sex:</label>
                                <span>
                                    <select id="id" name="sex" value="${student.sex}" aria-placeholder="Sex">
                                        <option value="Male" selected>Male</option>
                                        <option value="Female">Female</option>
                                        <option value="Other">Other</option>
                                    </select>
                                </span>
                            </li>
                            <li>
                                <label>Contact:</label>
                                <span><input type="number" name="contactNo" placeholder="Contact" value="${student.contactNo}"></span>
                            </li>
                            <li>
                                <label>Address:</label>
                                <span>
                                    <input type="text" name="houseNo" placeholder="House No" value="${student.houseNo}">
                                    <input type="text" name="street" placeholder="Street" value="${student.street}">
                                    <input type="text" name="city" placeholder="City, State" value="${student.city}">
                                    <input type="text" name="pin" placeholder="PIN Code" value="${student.pin}">
                                </span>
                            </li>
                            <li>
                                <label>Guadian Name:</label>
                                <span>
                                    <input type="text" name="gfirstName" placeholder="First Name" value="${guardian.gfirstName}">
                                    <input type="text" name="gmiddleName" placeholder="Middle Name" value="${guardian.gmiddleName}">
                                    <input type="text" name="glastName" placeholder="Last Name" value="${guardian.glastName}">
                                </span>
                            </li>
                            <li>
                                <label>Guardian Contact:</label>
                                <span><input type="number" name="gcontactNo" placeholder="Guardian Contact" value="${guardian.gcontactNo}"></span>
                            </li>
                        </ul>
                        <div class="container">
                            </span><input class="submit button" type="submit" value="Proceed" />
                        </div>
                    </form>
                </div>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_TEACHER') and isAuthenticated()">
                <div class="content">
                    <h1>Edit Profile</h1>
                    <p>${message}</p>
                    <form action="/profile/${user.username}/update_teacher" method="POST">
                        <ul class="ver-table">
                            <li>
                                <label>Username:</label>
                                <span><input class="read" type="text" name="username" value="${user.username}" readonly /></span>
                            </li>
                            <li>
                                <label>TeacherID:</label>
                                <span><input class="read" type="text" name="TeacherID" value="${teacher.teacherID}" readonly /></span>
                            </li>
                            <li>
                                <label>Name:</label>
                                <span>
                                    <input type="text" name="firstName" placeholder="First Name" value="${teacher.firstName}">
                                    <input type="text" name="middleName" placeholder="Middle Name" value="${teacher.middleName}">
                                    <input type="text" name="lastName" placeholder="Last Name" value="${teacher.lastName}">
                                </span>
                            </li>
                            <li>
                                <label>Date Of Birth:</label>
                                <fmt:formatDate pattern="yyyy-MM-dd" value="${teacher.dateOfBirth}" var="dob" />
                                <span><input type="date" name="dateOfBirth" value="${dob}"></span>
                            </li>
                            <li>
                                <label>Sex:</label>
                                <span>
                                    <select id="id" name="sex" value="${teacher.sex}" aria-placeholder="Sex">
                                        <option value="Male" selected>Male</option>
                                        <option value="Female">Female</option>
                                        <option value="Other">Other</option>
                                    </select>
                                </span>
                            </li>
                            <li>
                                <label>Contact:</label>
                                <span><input type="number" name="contactNo" placeholder="Contact" value="${teacher.contactNo}"></span>
                            </li>
                            <li>
                                <label>Address:</label>
                                <span>
                                    <input type="text" name="houseNo" placeholder="House No" value="${teacher.houseNo}">
                                    <input type="text" name="street" placeholder="Street" value="${teacher.street}">
                                    <input type="text" name="city" placeholder="City, State" value="${teacher.city}">
                                    <input type="text" name="pin" placeholder="PIN Code" value="${teacher.pin}">
                                </span>
                            </li>
                            <li>
                                <label>Qualifications:</label>
                                <span>
                                    <input type="text" name="qualification" placeholder="Qualfications (Bachelors, Masters)"
                                        value="${teacher.qualification}">
                                </span>
                            </li>
                            <li>
                                <label>Experience:</label>
                                <span><input type="text" name="experience" placeholder="Experience"
                                        value="${teacher.experience}"></span>
                            </li>
                        </ul>
                        <div class="container">
                            </span><input class="submit button" type="submit" value="Proceed" />
                        </div>
                    </form>
                </div>
            </sec:authorize>
        </div>
        <jsp:include page="footer.jsp" />
    </div>
</body>

</html>