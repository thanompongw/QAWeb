<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!-- *************  LEFT NAV STUFF GOES HERE *********** -->
<div class="span3">
	<div class="well sidebar-nav">
		<ul class="nav nav-list">
			<li class="nav-header">Settings</li>
			<li><a href="<c:url value='/user/index.html' />" id="menuId01">User</a></li>
			<li><a href="<c:url value='/staff/index.html' />" id="menuId02">Staff</a></li>
			<li><a href="<c:url value='/tmChannel/index.html' />" id="menuId03">TM Channel</a></li>
			<li class="nav-header">Case</li>
			<li><a href="#">Link</a></li>
		</ul>
	</div> <!--/.well -->
</div> <!--/span3-->
