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
		<h1>Edit Staff</h1>
	</div>
	<div id="pageContent">
		<form:form id="form" method="post" modelAttribute="staff" class="form-horizontal"
			action="update.html">
			<c:if test="${ not empty message }">
				<div class="alert">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<strong id="message">${ message }</strong>
				</div>
			</c:if>
			<div class="control-group">
				<form:label class="control-label" path="staffCode"> 
					Staff Code
				</form:label>
				<div class="controls">
					<form:input path="staffCode" placeholder="Staff Code" cssClass="uneditable-input" readonly="true"/> 
					<form:errors path="staffCode" />
				</div>
			</div>	
			<div class="control-group">
				<form:label class="control-label" path="staffName">
	  				Staff Name 
				</form:label>
				<div class="controls">
					<form:input path="staffName" />
					<form:errors path="staffName" />
				</div>
			</div>
	  		<div class="control-group">
				<form:label class="control-label"  path="sectionCode">
					Section
				</form:label>
				<div class="controls">
					<form:select path="sectionCode">
						<form:option value="CCC">Customer Care Center</form:option>
						<form:option value="BR">Business Retention</form:option>
						<form:option value="POS">Policy Operation Services</form:option>
					</form:select>
				</div>
			</div>
	 		<div class="control-group">
	 			<form:label class="control-label" path="taskRatio">
	  				Task Ratio 
				</form:label>
				<div class="controls">
					<form:input path="taskRatio" />
					<form:errors path="taskRatio" />
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
	
			<button type="submit" class="action blue">
				<span class="icon icon67"></span>
				<span class="label"><spring:message code="btn.lbl.update"></spring:message></span>
			</button>
			<button type="submit" class="action red">
				<span class="icon icon186"></span>
				<span class="label"><spring:message code="btn.lbl.delete"></spring:message></span>
			</button>
			<button class="action" onclick="goBack();">
				<span class="icon icon35"></span>
				<span class="label"><spring:message code="btn.lbl.cancel"></spring:message></span>
			</button>
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