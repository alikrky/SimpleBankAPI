function populateRandom() {
	document.getElementById("populateRandom").disabled = true;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "POST", "/populateRandom", false );
    xmlHttp.send( null );
    location.reload();
}
function removeAllData() {
	document.getElementById("removeAll").disabled = true;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "POST", "/removeAll", false );
    xmlHttp.send( null );
    location.reload();
}
function addCustomer() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "POST", "/addCustomer", false );
    xmlHttp.send( null );
    location.reload();
}

function getCustomerDetails(customerID){
    location.replace("/getCustomer?customerID=" + customerID);
}

function isNumeric(str) {
  if (typeof str != "string") return false;
  return !isNaN(str) &&
         !isNaN(parseFloat(str));
}

function addAccount(customerID){
    var credit = document.getElementById("credit");
    if(credit.value == "" || credit.value == undefined ||credit.value == null){
        credit.value = 0;
    }
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "POST", "/addAccount?customerID=" + customerID + "&credit=" + credit.value, false );
    xmlHttp.send( null );
    credit.value = "";
    location.reload();
}

function changeCustomerCreateCheckbox() {
  var checkBox = document.getElementById("createCustomerCheckBox");
  if (checkBox.checked == true){
    document.getElementById("initialCreditLabel").hidden=true;
    document.getElementById("credit").hidden=true;
  } else {
    document.getElementById("initialCreditLabel").hidden=false;
    document.getElementById("credit").hidden=false;
  }
}

