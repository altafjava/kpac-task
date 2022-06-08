<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<c:set var="req" value="${pageContext.request}" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of all K-PACs</title>
        <script src="<c:url value="/resources/codebase/suite.js" />"></script>
		<link href="<c:url value="/resources/codebase/suite.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/select-style.css" />" rel="stylesheet">
    </head>
    <body>
    	<div class="main-container">
			<div class="form-container">
				<h2>Add Knowledge Package Set</h2>
		       	<form action="sets" method="post">
		       		<div class="input-form">
     					<label for="title">Title:</label>
  						<input type="text" id="title" name="title" placeholder="Title">
  					</div>
  					<div class="input-form">
     					<label for="kpacIds">Choose single/multiple K-PACs</label>
     					<div class="select">
     						<select id="kpacIds" name="kpacIds" multiple="multiple">
     							<option value="">--- multi select ---</option>
							</select>
     					</div>
  					</div>
  					<div class="input-form">
				  		<input type="button" value="Add" onclick="this.form.submit()">
				  	</div>
				</form> 
			</div>    		
    		<div style="height: 50rem; width: 70rem" id="grid_container">
    		</div>
    	</div>
    </body>
    <script>
      	const grid = new dhx.Grid('grid_container', {
        columns: [
	          {
	            id: 'id',
	            width: 200,
	            htmlEnable: true,
	            tooltip: false,
	            header: [
	              { text: 'Id' },
	              {
	                content: 'comboFilter',
	                customFilter: (value, match) => value === match,
	              },
	            ],
	          },
	          {
	            id: 'title',
	            header: [
	              { text: 'Title' },
	              {
	                content: 'inputFilter',
	                customFilter: (value, match) => value.toLowerCase().includes(match.toLowerCase()),
	              },
	            ],
	          },
	          {
	              width: 160,
	              header: [{text: "Delete", align: "center"}],
	              sortable: false,
	              align: "center",
	              htmlEnable: true,
	              tooltip: false,
	              template: function () {
	                  return "<div class='delete-button'>Delete</div>"
	              }
	          }
	        ],
	        headerRowHeight: 50,
	        autoWidth: true,
	        resizable: true,
	        eventHandlers: {
	            onclick: {
	                "delete-button": function (event, data) {
	                	deleteKPacSet(data.row);
	                }
	            }
	      	}
	      });
      	
      	const contextPath = '${pageContext.request.contextPath}';
      	
  		( () => {
		    const xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		        if (this.readyState === 4 && this.status === 200) {
		            const responseText=this.responseText;
		            var select = document.getElementById("kpacIds");
		            const kpacData=JSON.parse(responseText)
					for(index in kpacData) {
					    select.options[select.options.length] = new Option(kpacData[index].title, kpacData[index].id);
					}
		        } 
		    };
		    xhttp.open("GET",  contextPath + "/api/kpacs", true);
		    xhttp.send();
		})();
      	
  		loadKpacSetGridData()
  		
		function loadKpacSetGridData() {
	        dhx.ajax.get(contextPath + "/api/sets").then(function (data) {
	            grid.data.parse(
			      data.map(function (obj) {
			        return {
			          id: obj.id + '',
			          title: obj.title,
			        }
			      })
			    )
	        });
		}
      
      	function deleteKPacSet(row) {
	        dhx.ajax
	            .delete(contextPath + "/api/sets/" + row.id)
	            .then(function (data) {
	                loadKpacSetGridData();
	            }).catch(function (error) {
	            	console.log('Error: ',error)
	        });
	    }
      	
      grid.events.on("cellDblClick", function(row,column,e){
          const kpacSetId=row.id
          const requestUrl='${req.requestURL}';
          let url=requestUrl.substring(0,requestUrl.indexOf("WEB-INF"))
          url=url+'set/'+kpacSetId
          const strWindowFeatures = "location=yes,scrollbars=yes,status=yes";
          const win = window.open(url, "_blank", strWindowFeatures);
      });
    </script>
</html>
