<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="#"><spring:message code='default.title.caption' /></a>
			<ul class="nav pull-right">
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">
					<i class="icon-user"></i>Thanompong W 
					<b class="caret"></b>
					</a>
					
					<ul class="dropdown-menu">
						<li><a href="#">Profile</a></li>
						<li class="divider"></li>
						<li><a href="#">Sign Out</a></li>
					</ul>
				</li>
			</ul>
			<div class="nav-collapse">
				<ul class="nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#task">My Task</a></li>
					<li><a href="#about">About</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>