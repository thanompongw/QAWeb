<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:url value="QAWeb" var="baseUrl" />
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link rel="shortcut icon" href="<c:url value='/images/gt.ico' />" />
<link rel="icon" href="<c:url value='/images/gt.gif' />" type="image/gif" />

<style type="text/css" media="screen">
	@IMPORT url("<c:url value='/bootstrap/css/bootstrap.css' />");
	@IMPORT url("<c:url value='/bootstrap/css/bootstrap-responsive.css' />");
	@IMPORT url("<c:url value='/css/css3-buttons.css' />");
	@IMPORT url("<c:url value='/css/tiptip.css' />");
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

button span.label, .button span.label {
	background-color: inherit;
}
</style>

<!-- Le javascript ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<script type="text/javascript" src="<c:url value='/js/jquery-1.8.2.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.tiptip.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.dataTables.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/DT_bootstrap.js' />"></script>
<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jqBootstrapValidation.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/qaApp.js' />"></script>

<script type="text/javascript">
$(document).ready(function() {
	// Launch TipTip tooltip
	$('.tiptip a.button, .tiptip button').tipTip();
});

$(function () { $("input,select,textarea").not("[type=submit]").jqBootstrapValidation(); } );

</script>

<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--
[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]
-->

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><tiles:getAsString name="title" /></title>
</head>
<body style="background-image: url(<c:url value="/images/bg.jpg"/>);">
	<tiles:insertAttribute name="header" />
	<div id="main" class="container-fluid">
		<div class="row-fluid">
			<tiles:insertAttribute name="menu" />
			<tiles:insertAttribute name="body" />
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>
