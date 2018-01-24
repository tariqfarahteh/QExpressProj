const server_url = "http://localhost:9090/rest/";
const api_getFacilities = server_url + "facilities";
const api_getShipment = server_url + "shipments";
const api_utilities = server_url + "utilities";

var rowsCount = 0;
var pageSize = 20;

function getFacilities(){
    $.ajax({
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        type: 'GET',
        url: api_getFacilities,
        success: function(data, result){
            console.log(data);
           let lstOfFacilities = data;
           fillFacilities(lstOfFacilities);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) { 
            console.log("Status: " + textStatus); 
            console.log("Error: " + errorThrown); 
        }   
    });
}

function fillFacilities(listOfFacilities){
   let selectObject = $('#select_list_of_facilities');
   for(var i=0; i <listOfFacilities.length; i++){
       let fac = listOfFacilities[i];
       let option = document.createElement("option");
       option.value = fac.id;
       option.text = fac.name;
       selectObject.append(option);
   }
}

function doSearch(){
    let ulElem = document.getElementById('ul_page');
    $(ulElem).find('li').remove();

    let facilityId =  $('#select_list_of_facilities').val();
    if(facilityId == -1){
        buildPagination();
    } else {
        getOrdersData(1);
    }
}

function getOrdersData(pageNumber){
    let facilityId =  $('#select_list_of_facilities').val();
    $.ajax({
        headers: { 
            'Content-Type': 'application/json' 
        },
        type: 'GET',
        url: api_getShipment + "?facilityId=" + facilityId + "&pageNumber=" + pageNumber,
        dataType: 'json',
        success: function(data,result){
            fillOrdersTable(data);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) { 
            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
        }   
    });
}

function fillOrdersTable(ordersList){
    let table = $('#tbl_Shipments tbody');
    
    $(table).find("tr").remove();

    for(var i=0; i <ordersList.length; i++){
        let order = ordersList[i];
        let row = table[0].insertRow(-1);
       
        let cell0 = row.insertCell(0);
        cell0.innerHTML = order.shipmentID;

        let cell1 = row.insertCell(1);
        cell1.innerHTML = order.createDate;

        let cell2 = row.insertCell(2);
        cell2.innerHTML = order.currentFacilityName;

        let cell3 = row.insertCell(3);
        cell3.innerHTML = order.sellerName;

        let cell4 = row.insertCell(4);
        cell4.innerHTML = order.buyerName;
    }
}



function getRowsCount(){
    let tableName = "ShipmentH";
    $.ajax({
        headers: { 
            'Content-Type': 'application/json' 
        },
        type: 'GET',
        url: api_utilities + "?tableName=" + tableName,
        dataType: 'json',
        success: function(data,result){
            rowsCount = data;
            buildPagination();
        }, error: function(XMLHttpRequest, textStatus, errorThrown) { 
            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
        }   
    });
}


function buildPagination(){
    let ulElem = document.getElementById('ul_page');
    let pages = Math.ceil(rowsCount/pageSize)
    console.log(pages)
    for(var i = 0; i < pages; i++){
        let li = document.createElement("li");
        $(li).text(i+1);
        ulElem.appendChild(li);
    }

    $(ulElem).find('li').click(function(){
        $(ulElem).find('li').removeClass('active');
        $(this).addClass('active');
        let pageNumber = parseInt($(this).text());
        getOrdersData(pageNumber);
    });

    $(ulElem).find('li:first').click();
}



$(document).ready(function() {
    getFacilities();
    getRowsCount();
} );
