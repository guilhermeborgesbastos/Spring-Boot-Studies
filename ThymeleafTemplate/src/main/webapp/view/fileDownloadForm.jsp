<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>File Upload Example</title>
		<spring:url value="/css/main.css" var="mainCss" />
		<script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
		<script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${mainCss}"/>
	</head>
	
	<body>
		
		<div class="row">			
			<div class="col-sm-3"></div>			
			<div class="col-sm-6"><%@ include file="nav.jsp" %></div>
			<div class="col-sm-3"></div>
		</div>
		
		<div class="row">
			
			<div class="col-sm-3"></div>
			
 			<div class="col-sm-6">
 			
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#single-file">Download Single file</a></li>
				</ul>
			
			</div>
			
			<div class="col-sm-3"></div>
		
		</div>
		
		<div class="row">
			
			<div class="col-sm-3"></div>
			
 			<div class="col-sm-6">
 			
				<div class="tab-content">
				
					<div id="single-file" class="tab-pane fade in active">

						<div class="container">
							
							<div class="jumbotron">
								
								<h3>Enter The File to Download <i>(Single file)</i></h3>

								<form:form method="POST" action="/download/file" enctype="multipart/form-data">
									
									    <div class="form-group">
											<label for="fileName">File Name:</label>

											<select class="browser-default custom-select" name="fileName">
												<option selected>Select a file</option>
												<c:forEach items="${files}" var="file">
													<option value="${file}">${file}</option>
												</c:forEach>
											</select>
										</div>
									 
									 <button type="submit" class="btn btn-primary">Download</button>
									
								</form:form>
								
							</div>
							
						</div>
						
					</div>

				</div>

			</div>

			<div class="col-sm-3"></div>

		</div>

		<%@ include file="footer.jsp" %>

	</body>

</html>