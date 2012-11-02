<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="span9">
	<div class="page-header">
		<h1><spring:message code="staff.page.create.title"/> </h1>
	</div>
	<div id="pageContent">
		<form:form id="form" method="post" commandName="staff" class="form-horizontal"
			action="create.html">
			<div class="alert alert-error fade in" data-alert="alert" style="top:0">
			   	<button type="button" class="close" data-dismiss="alert">×</button>
				<h4 class="alert-heading">Error!</h4>
			   	<strong><span id="message-error"></span></strong>
			</div>
			<div class="alert alert-block fade in" data-alert="alert" style="top:0">
			   	<button type="button" class="close" data-dismiss="alert">×</button>
				<h4 class="alert-heading">Warning!</h4>
			   	<strong><span id="message-warning"></span></strong>
			</div>
			<div class="alert alert-success fade in" data-alert="alert" style="top:0">
			   	<button type="button" class="close" data-dismiss="alert">×</button>
				<h4 class="alert-heading">Well Done!</h4>
			   	<strong><span id="message-success"></span></strong>
			</div>
			<div class="control-group" id="staffCode">
				<form:label class="control-label" path="staffCode"> 
					<spring:message code="staff.page.lbl.code"/>
				</form:label>
				<div class="controls">
					<input type="text" id="staffCode" name="staffCode" placeholder="Staff Code" maxlength="6" required>
				</div>
			</div>	
			<div class="control-group" id="staffName">
				<form:label class="control-label" path="staffName">
					<spring:message code="staff.page.lbl.name"/>
				</form:label>
				<div class="controls">
					<input type="text" id="staffName" name="staffName" placeholder="Staff Name" maxlength="100" required>
					<span class="help-inline"><form:errors path="staffName" /></span>
				</div>
			</div>
	  		<div class="control-group" id="sectionCode">
				<form:label class="control-label" path="sectionCode">
					<spring:message code="staff.page.lbl.section"/>
				</form:label>
				<div class="controls">
					<select id="sectionCode" name="sectionCode" required>
						<option value="">------------ Select ------------</option>
					    <c:forEach items="${ sections }" var="section">
					        <option value="${ section.code }">${ section.value }</option>
					    </c:forEach>
					</select>
				</div>
			</div>
	 		<div class="control-group" id="taskRatio">
	 			<form:label class="control-label" path="taskRatio">
					<spring:message code="staff.page.lbl.taskRatio"/>
				</form:label>
				<div class="controls">
					<input type="number" id="taskRatio" name="taskRatio" placeholder="Task Ratio" max="100" required/>
				</div>
			</div>
			<div class="control-group" id="statusCode">
				<form:label class="control-label" path="statusCode">
					<spring:message code="staff.page.lbl.status"/>
				</form:label>
				<div class="controls">
					<select id="statusCode" name="statusCode" required>
						<option value="">--- Select ---</option>
					    <c:forEach items="${ statuses }" var="status">
					        <option value="${ status.code }">${ status.value }</option>
					    </c:forEach>
					</select>
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
				
			$(document).ready(function() {
				
				$('.alert').fadeIn(400);
				$('.alert').hide();
				
				$(':input').blur(function() {
					window.setTimeout(function() { $(".alert").slideUp(); }, 1000);
				});
				
				var $form = $('#form');
				$('#form').submit(function() {
					$.post($(this).attr("action"), $(this).serialize(), function(response) {		
						if (response.statusCode == '500') {
							for (var i = 0; i < response.errorMessages.length; i++) {
								var item = response.errorMessages[i];
								$('#message-error').html(item.message);
								$('.alert-error').show();
							}
						} else if (response.statusCode == '400') {
							for (var i = 0; i < response.errorMessages.length; i++) {
								var item = response.errorMessages[i];
								$('#message-warning').html(item.message);
								$('.alert-block').show();
							}
						} else {
							for (var i = 0; i < response.errorMessages.length; i++) {
								var item = response.errorMessages[i];
								$('#message-success').html(item.message);
								$('.alert-success').show();
								
								window.setTimeout(function() { $(".alert").slideUp(); }, 5000);
							}
						}
					});
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
