<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<style type="text/css">

fieldset { padding:0; border:0; margin-top:0px; }

</style>
<script type="text/javascript">

	$().ready(function() {		
		$("#accordion").accordion({
			autoHeight: false,
			navigation: true
		});
	});
</script>
</head>
<body>
<div id="accordion" style="margin-left: 350px; margin-top: 50px; width: 440px; height: 250px">
<h1><a href="#">Access Denied!!</a></h1>
	<div id="accessDeniedDialog">
		<fieldset>
			<div id="form_err" style="width: 100%;">
		    <div class="ui-state-error ui-corner-all" style="padding:0.7em 0; float:left;">
		            <span class="ui-icon ui-icon-alert" style="float:left; margin-right: 0.3em 0;"></span>
		            <div id="error" style="font-weight: bold;">Please contact to information technology department for add your information in system.</div>
		    </div>
		    <div style="clear:left"/></div>
		    </div>
			<br>
			<br>
		</fieldset>
	</div>
</div>
</body>
</html>