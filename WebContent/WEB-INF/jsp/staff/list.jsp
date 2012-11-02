<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<style type="text/css" media="screen">
	@IMPORT url("<c:url value='/css/DT_bootstrap.css' />");
<!--
.row {
	margin-left: 0;
}

button, .button {
	margin: 5px 5px 5px 0px;
}
-->
</style>
<script type="text/javascript">

$(document).ready(function() {
	
	/* Table initialisation */
	$(document).ready(function() {
		$('#staffTable').dataTable({
		  	"aoColumns": [{
		  		"sTitle": "<spring:message code='staff.page.lbl.code'/>",
		     	"fnRender": function (oObj, sVal) {
	          	var template = '<a href="<c:url value='/satff/edit.html' />" id="ID">' + sVal + '</a>'
	          	return template;
	       	}
		   	},
			  	{"sTitle": "<spring:message code='staff.page.lbl.name'/>" },
			  	{"sTitle": "<spring:message code='staff.page.lbl.section'/>" },
			  	{"sTitle": "<spring:message code='staff.page.lbl.taskRatio'/>" },
			  	{"sTitle": "<spring:message code='staff.page.lbl.status'/>" }
		  	],
	      	"sDom": "<'row'<'span4'l><'pull-right'f>r>t<'row'<'span4'i><'pull-right'p>><'spacer'>",
	      	"sPaginationType": "bootstrap",
	      	"bStateSave": true,
	      	"bJQueryUI": true,
	        "bAutoWidth": false,
	        "bScrollCollapse": true, 
	        "bSort": false,
	        "bPaginate": true,
	      	"oLanguage": {
	        	"sLengthMenu": "<spring:message code='datatable.rowsPerPage'/>",
	        	"oPaginate": {
	            	"sNext": "<spring:message code='default.paginate.next'/>",
	            	"sPrevious": "<spring:message code='default.paginate.prev'/>"
	        },
	        "sSearch": "<spring:message code='default.filter.label'/>",
	        "sZeroRecords": "<spring:message code='default.search.noResults.message'/>",
	        "sEmptyTable": "<spring:message code='default.search.noResults.message'/>",
	        "sInfo": "<spring:message code='datatable.showing'/>",
	        "sInfoEmpty": "<spring:message code='datatable.showing.empty'/>",
	        "sInfoFiltered": " <spring:message code='datatable.filtered'/>"
	        },
            "bServerSide": true,
            "sAjaxSource": "list.html",
	      	"fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    dataType: 'json',
                    contentType: "application/json;charset=UTF-8",
                    type: 'POST',
                    url: sSource,
                    data: stringify_aoData(aoData),
                    success: fnCallback,
                    error : function (e) {
                        alert (e);
                    }
                });
            }
	    });
	});
});
</script>
<div class="span9">
	<div class="page-header">
		<h1><spring:message code="staff.page.list.title" /></h1>
	</div>
	<div id="pageContent">
		<div class="buttons">
			<a href="<c:url value='/staff/create.html' />" class="button">
				<span class="icon icon3"></span>
				<span class="label"><spring:message code="staff.page.btn.lbl.add" /></span>
			</a>
		</div>
		<table class="table table-striped table-bordered table-hover table-condensed tablesorter" id="staffTable"></table>
	</div>
</div>
