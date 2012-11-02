<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<style type="text/css" media="screen">

/* .select {
	width: auto;
}
 */
</style>
<html>
<body>
<div class="span9">
	<div class="page-header">
		<h1><spring:message code="staff.page.create.title"/> </h1>
	</div>
	<div id="pageContent">
		<form:form id="form" method="post" modelAttribute="staff" class="form-horizontal"
			action="save.html">
			<div class="alert alert-error fade in" >
				<p>
					<h4 class="alert-heading">Warning!</h4>
				   	<strong><span id="message-error"></span></strong>
				</p>
			</div>
			<div class="control-group" id="staffCode">
				<form:label class="control-label" path="staffCode"> 
					<spring:message code="staff.page.lbl.code"/>
				</form:label>
				<div class="controls">
					<form:input path="staffCode" placeholder="Staff Code" /> 
					<span class="help-inline"><form:errors path="staffCode" /></span>
				</div>
			</div>	
			<div class="control-group" id="staffName">
				<form:label class="control-label" path="staffName">
					<spring:message code="staff.page.lbl.name"/>
				</form:label>
				<div class="controls">
					<form:input path="staffName" placeholder="Staff Name"/>
					<span class="help-inline"><form:errors path="staffName" /></span>
				</div>
			</div>
	  		<div class="control-group" id="sectionCode">
				<form:label class="control-label" path="sectionCode">
					<spring:message code="staff.page.lbl.section"/>
				</form:label>
				<div class="controls">
					<form:select path="sectionCode">
					   <form:option value="" label="------------ Select ------------"/>
					   <form:options items="${ sections }" itemValue="code" itemLabel="value"/>
					</form:select>
					<span class="help-inline"><form:errors path="sectionCode" /></span>
				</div>
			</div>
	 		<div class="control-group" id="taskRatio">
	 			<form:label class="control-label" path="taskRatio">
					<spring:message code="staff.page.lbl.taskRatio"/>
				</form:label>
				<div class="controls">
					<form:input path="taskRatio" placeholder="Task Ratio"/>
					<span class="help-inline"><form:errors path="taskRatio" /></span>
				</div>
			</div>
			<div class="control-group" id="statusCode">
				<form:label class="control-label" path="statusCode">
					<spring:message code="staff.page.lbl.status"/>
				</form:label>
				<div class="controls">
					<form:select path="statusCode">
					   <form:option value="" label="--- Select ---"/>
					   <form:options items="${ statuses }" itemValue="code" itemLabel="value"/>
					</form:select>
				</div>
			</div>
	
			<button type="submit" class="action blue">
				<span class="label"><spring:message code="btn.lbl.save"/></span>
			</button>
			<button type="button" class="action" id="btnCancel">
				<span class="label"><spring:message code="btn.lbl.cancel"/></span>
			</button>
		</form:form>
		
		<script type="text/javascript">
			function collectFormData(fields) {
				var data = {};
				for (var i = 0; i < fields.length; i++) {
					var $item = $(fields[i]);
					data[$item.attr('name')] = $item.val();
				}
				return data;
			}
				
			$(document).ready(function() {
				var $form = $('#form');
				$('.alert').hide();
				$('.alert').alert();
				$form.bind('submit', function(e) {
					// Ajax validation
					var $inputs = $form.find('input');
					var data = collectFormData($inputs);
					
					$.post('validate.html', data, function(response) {
						$form.find('.control-group').removeClass('error');
						$form.find('.help-inline').empty();
						$form.find('.alert').remove();
						
						if (response.statusCode == "500") {
							var messages = data.messages;
							for (var i = 0; i < response.errorMessages.length; i++) {
								var item = response.errorMessages[i];
								var $controlGroup = $('#' + item.fieldName);
								$controlGroup.addClass('error');
								$controlGroup.find('.help-inline').html(item.message);
							}
						} else {
							$form.unbind('submit');
							$form.submit();
						}
					}, 'json');
					
					e.preventDefault();
					return false;
				});
				
				$('#btnCancel').click(function() {
					$(this).closest('form')
						   .find('input[type=text], textarea, select').val("");
					history.back();
				});
			});
		</script>
	</div>
</div>
</body>
</html>