<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<style type="text/css">

.row-fluid .span3 {
	width: auto;
}
.well {
	padding: 50px;
}

body {
	padding: 60px 0;
	overflow: visible;
}
</style>
<div class="container">
	<div class="hero-unit clearfix">
		<div class="pull-left span8">
			<h2>Welcome to <font color="#942727">Generali Thailand</font> Web Application</h2>
			<p>A one stop service for QA voice file Tele marketing</p>
		</div>
		<div class="pull-right">
			<form class="well" id="loginForm" method='post' action=''>
				<strong>User Name</strong>
				<div class="input-prepend">
				<span class="add-on"> <i class="icon-user"></i> </span>
				<input type="text" class="span3" placeholder="Type your username" id="userName">
				</div>
				<strong>Password</strong>
				<div class="input-prepend">
				<span class="add-on"> <i class="icon-lock"></i> </span>
				<input type="password" class="span3" placeholder="Type your password" id="password">
				</div>
				<label class="checkbox"><input type="checkbox">Remember me </label>
				<button class="action blue"><span class="label">Log in</span></button>
			</form>
		</div>
	</div>
</div>

