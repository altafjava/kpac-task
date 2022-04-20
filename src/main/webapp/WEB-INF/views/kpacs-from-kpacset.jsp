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
    		<div>
    			<h2>Knowledge Package List</h2>
    		</div>
    		<div style="height: 50rem; width: 70rem" id="grid_container">
    		</div>
    	</div>
    </body>
    <script>
  		let dataset = ${knowledgePackagesJsonString};
      	const grid = new dhx.Grid('grid_container', {
        columns: [
          {
            id: 'id',
            width: 150,
            tooltip: false,
            header: [
              { text: 'Id' },
            ],
          },
          {
            id: 'title',
            header: [
              { text: 'Title' },
            ],
          },
          {
            id: 'description',
            header: [
              { text: 'Description' },
            ],
          },
        ],
        headerRowHeight: 50,
        data: dataset,
        autoWidth: true,
      })
    </script>
</html>
