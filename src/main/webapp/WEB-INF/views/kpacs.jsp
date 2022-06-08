<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of all K-PACs</title>
<script src="<c:url value="/resources/codebase/suite.js" />"></script>
<link href="<c:url value="/resources/codebase/suite.css" />" rel="stylesheet">
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>
<body>
	<div class="main-container">
		<div class="form-container">
			<h2>Add Knowledge Package</h2>
			<form action="kpacs" method="post">
				<div class="input-form">
					<label for="title">Title</label> <input type="text" id="title" name="title" placeholder="Title">
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
		<div style="height: 50rem; width: 70rem" id="grid_container"></div>
	</div>
</body>
<script>
    	const contextPath = '${pageContext.request.contextPath}';
		const grid = new dhx.Grid('grid_container', {
		  columns: [
		    {
		      id: 'id', width: 150, tooltip: false,
		      header: [
		        { text: 'Id' },
		        { content: 'comboFilter', customFilter: (value, match) => value === match,},
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
	                    deleteKPac(data.row);
	                }
	            }
	      }
		});
		
		loadGridData();
		
		function loadGridData() {
	        dhx.ajax.get(contextPath + "/api/kpacs").then(function (data) {
	            grid.data.parse(
			      data.map(function (obj) {
			        return {
			          id: obj.id + '',
			          title: obj.title,
			          description: obj.description,
			          creationDate: obj.creationDate,
			        }
			      })
			    )
	        });
		}
		
		function deleteKPac(row) {
	        dhx.ajax
	            .delete(contextPath + "/api/kpacs/" + row.id)
	            .then(function (data) {
	                loadGridData();
	            }).catch(function (error) {
	            	console.log('Error: ',error)
	        });
	    }
    </script>
</html>
