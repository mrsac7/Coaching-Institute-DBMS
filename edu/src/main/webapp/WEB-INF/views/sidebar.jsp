<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  


<div id="sidebar_container">
  <div class="sidebar_item">
    <h3>Quick Links</h3>
    <ul>
      <li>&#8212;&nbsp;&nbsp;<a href="/students">All Teachers</a></li>
      <sec:authorize access="isAuthenticated()">
        <li>&#8212;&nbsp;&nbsp;<a href="/change_password">Change Password</a></li>
      </sec:authorize>
      <li>&#8212;&nbsp;&nbsp;<a href="/faq">FAQs</a></li>
    </ul>
  </div>
</div>