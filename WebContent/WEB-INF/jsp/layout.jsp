<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:url value="QAWeb" var="baseUrl" />
<head>
<link rel="shortcut icon" href="<c:url value='/images/gt.ico' />" />
<link rel="icon" href="<c:url value='/images/gt.gif' />" type="image/gif" />

<style type="text/css" media="screen">
	@IMPORT url("<c:url value='/bootstrap/css/bootstrap.min.css' />");
	@IMPORT url("<c:url value='/css/css3-buttons.css' />");
	@IMPORT url("<c:url value='/css/tiptip.css' />");
	@IMPORT url("<c:url value='/css/google-bootstrap.css' />");
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
<script type="text/javascript" src="<c:url value='/js/jquery.dataTables.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/DT_bootstrap.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.tiptip.js' />"></script>
<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.js' />"></script>

<script type="text/javascript" src="<c:url value='/js/bootstrap-popover.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/google-select.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/google-select-dropdown.js' />"></script>

<script type="text/javascript">
	$("select:not([multiple])").gSelect();
</script>


<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--
[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]
-->

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><tiles:getAsString name="title" /></title>
</head>
<html lang="en">
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
