<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<c:set var="req" value="${pageContext.request}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of all K-PACs</title>
        <script src="<c:url value="/resources/dhtmlx/grid.js" />"></script>
        <link href="<c:url value="/resources/dhtmlx/grid.css" />" rel="stylesheet">
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
  		const kpacData = ${knowledgePackagesJsonString};
  		const kpacSetData=${knowledgePackageSetsJsonString};
  		var select = document.getElementById("kpacIds");
		for(index in kpacData) {
		    select.options[select.options.length] = new Option(kpacData[index].title, kpacData[index].id);
		}
  		
  		const kpacSetDataObject=kpacSetData.map(obj=>{
  			return {...obj,id:"<span>"+obj.id+"</span>", action: "<div class='cell__html'><span><a id='kpac-delete-"+obj.id+"'>Delete</a></span></div>"};
  		});
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
        	  id: 'action',
              width: 150,
              htmlEnable: true,
              align: 'center',
              tooltip: false,
              header: [
                { text: 'Action' },
              ],
          }
        ],
        headerRowHeight: 50,
        data: kpacSetDataObject,
        autoWidth: true,
        eventHandlers: { 
            onclick: {
                cell__html: function (event, data) {
                    const span=data.row.id
                    const kpacSetId=span.substring(span.indexOf('>')+1,span.lastIndexOf('<'));
                    const contextPath="${pageContext.request.contextPath}";
                    const anchorTag=document.getElementById('kpac-delete-'+kpacSetId);
                    anchorTag.href=contextPath+"/sets/delete/" + kpacSetId;
                },
            },
        }
      })
      grid.events.on("cellDblClick", function(row,column,e){
          const span=row.id
          const kpacSetId=span.substring(span.indexOf('>')+1,span.lastIndexOf('<'));
          const requestUrl='${req.requestURL}';
          let url=requestUrl.substring(0,requestUrl.indexOf("WEB-INF"))
          url=url+'set/'+kpacSetId
          const strWindowFeatures = "location=yes,scrollbars=yes,status=yes";
          const win = window.open(url, "_blank", strWindowFeatures); 
      });
    </script>
</html>
