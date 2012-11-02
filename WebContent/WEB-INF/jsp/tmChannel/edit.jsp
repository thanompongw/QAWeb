<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<style type="text/css" media="screen">

.select {
	width: auto;
}

</style>
<html>
<body>
<div class="span9">
	<div class="page-header">
		<h1>Edit TM Channel</h1>
	</div>
	<div id="pageContent">
		<form:form id="form" method="post" modelAttribute="tmChannel" class="form-horizontal"
			action="save.html">
			<c:if test="${ not empty message }">
				<div class="alert">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<strong id="message">${ message }</strong>
				</div>
			</c:if>
			<div class="control-group tiptip">
				<button type="submit" class="on" title="Save">
					<span class="icon icon67"></span>
				</button>
				<button type="submit" class="on" title="Delete">
					<span class="icon icon186"></span>
				</button>
				<button class="on" onclick="goBack();" title="Cacel">
					<span class="icon icon35"></span>
				</button>
			</div>
			<div class="control-group">
				<form:label class="control-label" path="tmChannelCode"> 
					TM Channel Code
				</form:label>
				<div class="controls">
					<form:input path="tmChannelCode" placeholder="TM Channel Code" cssClass="uneditable-input" readonly="true"/> 
					<form:errors path="tmChannelCode" />
				</div>
			</div>
			<div class="control-group">
				<form:label class="control-label" path="tmChannelDesc">
	  				TM Channel Description
				</form:label>
				<div class="controls">
					<form:input path="tmChannelDesc" placeholder="TM Channel Description"/>
					<form:errors path="tmChannelDesc" />
				</div>
			</div>
			<div class="control-group">
				<form:label class="control-label"  path="activeFlag">
					Status
				</form:label>
				<div class="controls">
					<form:select path="activeFlag">
						<form:option value="Y">Active</form:option>
						<form:option value="N">Inactive</form:option>
					</form:select>
				</div>
			</div>
		</form:form>
		
		<script type="text/javascript">
		
			function goBack() {
			    $(this).delay(5000, function() {
			      	history.back(-1);
			    })
			    return false;
		    }
			
			$(document).ready(function() {
				$("#form").submit(function() {  
					$.post($(this).attr("action"), $(this).serialize(), function(html) {
						var messages = html.messages;
						var message = "";
						for (var i = 0; i < messages.length; i++) {
							message = message + messages[i] + "/n";
						}
						$("#pageContent").replaceWith(message);
						$('html, body').animate({ scrollTop: 60 }, 500);
					});
					return false;
				});
			});
		</script>
	</div>
</div>
</body>
</html>