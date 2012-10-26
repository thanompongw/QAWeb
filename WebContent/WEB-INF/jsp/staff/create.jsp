<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style type="text/css" media="screen">

.select {
	width: auto;
}

</style>
<div class="span9">
	<div class="page-header">
		<h1>Create Staff</h1>
	</div>
	<div id="pageContent">
		<form:form id="form" method="post" modelAttribute="staff" class="form-horizontal">

			<div class="control-group">
				<form:label class="control-label" path="staffCode"> 
					Staff Code
				</form:label>
				<div class="controls">
					<form:input path="staffCode" placeholder="Staff Code" /> 
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
						<form:option value="BR">BR</form:option>
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
	
			<button type="submit" class="action blue"><span class="label">Save</span></button>
			<button class="action"><span class="label">Cancel</span></button>
		</form:form>
	</div>
</div>