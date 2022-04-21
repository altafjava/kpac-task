<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of all K-PACs</title>
        <script src="<c:url value="/resources/dhtmlx/grid.js" />"></script>
        <link href="<c:url value="/resources/dhtmlx/grid.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
    </head>
    <body>
    	<div class="main-container">
			<div class="form-container">
				<h2>Add Knowledge Package</h2>
		       	<form action="kpacs" method="post">
		       		<div class="input-form">
     					<label for="title">Title</label>
  						<input type="text" id="title" name="title" placeholder="Title">
  					</div>
  					<div class="input-form">
     					<label for="description">Description</label>
  						<textarea id="description" name="description" rows="4" cols="50"></textarea>
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
  		let data = ${knowledgePackagesJsonString};
  		const dataset=data.map(el=>{
  			return {...el,id:"<span>"+el.id+"</span>", action: "<div class='cell__html'><span><a id='kpac-delete-"+el.id+"'>Delete</a></span></div>"};
  		});
      	const grid = new dhx.Grid('grid_container', {
        columns: [
          {
            id: 'id',
            width: 150,
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
            id: 'description',
            header: [
              { text: 'Description' },
              {
                content: 'inputFilter',
                customFilter: (value, match) => value.toLowerCase().includes(match.toLowerCase()),
              },
            ],
          },
          {
            id: 'creationDate',
            width: 180,
            header: [
              { text: 'Creation Date' },
              {
                content: 'selectFilter',
                customFilter: (value, match) => value < match,
              },
            ],
            align: "center"
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
        data: dataset,
        autoWidth: true,
        eventHandlers: { 
            onclick: {
                cell__html: function (event, data) {
                    const span=data.row.id
                    const kpacId=span.substring(span.indexOf('>')+1,span.lastIndexOf('<'));
                    const contextPath="${pageContext.request.contextPath}";
                    const anchorTag=document.getElementById('kpac-delete-'+kpacId);
                    anchorTag.href=contextPath+"/kpacs/delete/" + kpacId;
                },
            },
        }
      })
    </script>
</html>
