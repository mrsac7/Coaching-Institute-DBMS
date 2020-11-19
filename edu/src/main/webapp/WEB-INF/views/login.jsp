<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  


<!DOCTYPE HTML>
<html>

<head>
  <title>Login - StarCoaching</title>
  <link rel="stylesheet" type="text/css" href="/style/style.css" />
</head>

<body>
  <div id="main">
    <div id="header">
        <jsp:include page="logo.jsp"/>
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
              <li class="selected"><a href="/login">Log In</a></li>
              <li><a href="/register">Register</a></li>
            </c:otherwise>
          </c:choose>
        </ul>
      </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
      <jsp:include page="sidebar.jsp"/>
      <div class="content">
          <h1>Login</h1>
          <p>${message}</p>  
          <form action="/login" method="POST">
            <ul class="ver-table">
              <li>
                <label>Username:</label>
                <span><input type="text" name="username" placeholder="Username" /></span>
              </li>
              <li>
                <label>Password:</label>
                <span><input type="password" name="password" placeholder="Password" /></span>
              </li>
            </ul>
            <div class="container">
              </span><input class="submit button" type="submit" value="Login" />
            </div>
          </form>
      </div>
    </div>
    <jsp:include page="footer.jsp"/>
  </div>
</body>
</html>