<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  


<div id="sidebar_container">
  <div class="sidebar_item">
    <h3>Useful Links</h3>
    <ul>
      <li><a href="/students">All Students</a></li>
      <li><a href="/employees">All Employees</a></li>
      <li><a href="/credentials/change">Change Password</a></li>
    </ul>
  </div>
  <div class="sidebar_item">
    <h3>Student Search</h3>
    <form method="post" action="/students/search" id="search_form">
      <p>
        <input class="search" type="text" name="serialNumber" onfocus="this.value=''" value="Enter Serial Number....." />
        <input name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;" src="/style/search.png" alt="Search"
          title="Search" />
      </p>
    </form>
  </div>
  <div class="sidebar_item">
    <h3>Employee Search</h3>
    <form method="post" action="/employees/search" id="search_form">
      <p>
        <input class="search" type="text" name="employeeId" onfocus="this.value=''" value="Enter Employee Id....." />
        <input name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;" src="/style/search.png" alt="Search"
          title="Search" />
      </p>
    </form>
  </div>
</div>