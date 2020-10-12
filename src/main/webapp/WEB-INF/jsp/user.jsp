<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Simple Bank API</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>

<body>
<div class="wrapper row1">
  <header id="header" class="clear">
    <div id="hgroup">
      <h1><a href="#">SIMPLE BANK API</a></h1>
    </div>
  </header>
</div>
<div class="wrapper row3">
<div id="container">

<div class="full_width clear">
<h1>Customer Info</h1>
      <table>
              <tr class="tableHeader">
                  <td class="customerInfo">Customer ID</td>
                  <td class="customerInfo">Name</td>
                  <td class="customerInfo">Surname</td>
                  <td class="customerInfo">Total Balance</td>
              </tr>
              <tr>
                   <td class="customerInfo">${customerID}</td>
                   <td class="customerInfo">${name}</td>
                   <td class="customerInfo">${surname}</td>
                   <td class="customerInfo">${balance}</td>
              </tr>
      </table>
 </div>

     <div class="full_width clear">

       <div class="one_third first">
        <h1>Add Account with Amount</h1>
        <input type="number" id="credit" step="0.01">
        <br>
        <input type="Button" id="addAccount" style="margin-left: 20px" value="Add Account" onclick="addAccount(${customerID})">
        <br>
       </div>
       <div class="one_third">

       <h1>Account Info</h1>

       <table>
               <tr class="tableHeader">
                   <td class="customerInfo">Account Status</td>
                   <td class="customerInfo">Account ID</td>
               </tr>
               <c:forEach var="account" items="${accounts}">
                <tr>
                    <td class="customerInfo">${account.type}</td>
                    <td class="customerInfo">${account.accountID}</td>
                </tr>
               </c:forEach>
       </table>

       </div>

       <div class="one_third">
              <h1>Transaction History</h1>

              <table>
                      <tr class="tableHeader">
                          <td class="customerInfo">Transaction ID</td>
                          <td class="customerInfo">Amount</td>
                      </tr>
                      <c:forEach var="transaction" items="${transactions}">
                          <tr>
                              <c:forEach var="transactionAmount" items="${transaction.value}">
                                  <td class="customerInfo">${transaction.key}</td>
                                  <td class="customerInfo">${transactionAmount.amount}</td>
                              </c:forEach>
                          </tr>
                      </c:forEach>
              </table>
       </div>

     </div>

<form action="/">
    <input type="submit" value="Go Back" />
</form>



  </div>
</div>
<div class="wrapper row4">
  <footer id="footer" class="clear">
    <p class="fl_left"></p>
  </footer>
</div>
</body>
</html>
