<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Simple Bank API</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>

</head>
<body>
<div class="wrapper row1">
  <header id="header" class="clear">
    <div id="hgroup">
      <h1><a href="#">SIMPLE BANK API</a></h1>
    </div>
  </header>
</div>
<!-- ################################################################################################ -->
<!-- content -->
<div class="wrapper row3">
  <div id="container">

    <div class="full_width clear">
      <h2>CREATE OR REMOVE DATA</h1>
      <div class="one_third first">
      <input type="Button" name="populateRandom" id="populateRandom" value="Populate Customer" onclick="populateRandom();" <c:if test="${not empty customerList}">disabled = disabled</c:if>>
      <input type="Button" name="removeAll" id="removeAll" value="Remove All Data" onclick="removeAllData();">
      </div>
    </div>
    <br>
    <div class="full_width clear">
      <div class="one_third first">
      <h2>CUSTOMER FORM</h2>
      <label><h5>Only Create User <input type="checkbox" id="createCustomerCheckBox" onclick="changeCustomerCreateCheckbox()"></h5></label>

      <form method="post" action="saveDetails">
        <label><h5>Customer Number</h5></label>
        <input type="number" id="customerID" name="customerID" >
        <label><h5>Name</h5></label>
        <input type="text" id="name" name="name">
        <label><h5>Surname</h5></label>
        <input type="text" id="surname" name="surname">
        <label for="initialCredit" id="initialCreditLabel"><h5>Initial Credit</h5></label>
        <input type="number" id="credit" name="credit" step="0.01"><br><br>
        <input type="submit" style="margin-left:50px" value="Save Customer">
       </form>
      </div>
      <div class="two_third">
      <c:if test="${not empty customerList}">
          <div class="customerList">
              <h2>CUSTOMERS</h2>
              <table>
              <tr class="tableHeader">
                  <td class="customerInfo">Customer ID</td>
                  <td class="customerInfo">Name</td>
                  <td class="customerInfo">Surname</td>
              </tr>
              <c:forEach var="entry" items="${customerList}">
              <tr id ="customerInfo-${entry.customerID}" class="customerInfoContainer" onclick="getCustomerDetails(${entry.customerID})">
                      <td class="customerInfo">${entry.customerID}</td>
                      <td class="customerInfo">${entry.name}</td>
                      <td class="customerInfo">${entry.surname}</td>
              </tr>
              </c:forEach>
              </table>
          </div>
      </c:if>
      </div>
    </div>
  </div>
</div>
<div class="wrapper row4">
  <footer id="footer" class="clear">
    <p class="fl_left"></p>
  </footer>
</div>
</body>
</html>
