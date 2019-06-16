<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<spring:url value="/main.css" var="mainCss" />
		<script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
		<script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
		<title>File Upload Example</title>
		<link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${mainCss}"/>
	</head>
	
	<body>
		
		<br /><br />
		
		<div class="row">
			
			<div class="col-sm-3"></div>
			
 			<div class="col-sm-6">
 			
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#single-file">Single file</a></li>
					<li><a data-toggle="tab" href="#multiple-files">Multiple files</a></li>
					<li><a data-toggle="tab" href="#multipart-form-data-request-param">Request Param</a></li>
					<li><a data-toggle="tab" href="#multipart-form-data-model-attribute">Model Attribute</a></li>
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
								
								<h3>Enter The File to Upload <i>(Single file)</i></h3>

								<form:form method="POST" action="/upload/uploadFile" enctype="multipart/form-data">
									
									 <div class="form-group">
									 
										 <label for="file">Select a file to upload</label>
										 <input type="file" name="file" />
									 
									 </div>
									 
									 <button type="submit" class="btn btn-primary">Submit</button>
									
								</form:form>
								
							</div>
							
						</div>
						
					</div>
					
					<div id="multiple-files" class="tab-pane fade">
						
						<div class="container">
							
							<div class="jumbotron">

								<h3>Enter The Files to Upload <i>(Multiple files)</i></h3>

								<form:form method="POST" action="/upload/uploadMultiFile" enctype="multipart/form-data">

									 <div class="form-group">
											<label for="files">Select a file to upload</label>
											<input type="file" name="files" />
									 </div>
									 <div class="form-group">
											<label for="files">Select a file to upload</label>
											<input type="file" name="files" />
									 </div>
									 <div class="form-group">
											<label for="files">Select a file to upload</label>
											<input type="file" name="files" />
									 </div>

									 <button type="submit" class="btn btn-primary">Submit</button>
									 
								</form:form>
							
							</div>
						
						</div>
						
					</div>
					
					<div id="multipart-form-data-request-param" class="tab-pane fade">
						
						<div class="container">
							
							<div class="jumbotron">
								
								<h3>Fill the Form and Select a File (<code>@RequestParam</code>)</h3>

								<form:form method="POST" action="/upload/uploadFileWithAdditionalData" enctype="multipart/form-data">
								
									    <div class="form-group">
											<label for="name">Name:</label>
											<input type="text" name="name" />
										</div>
										<div class="form-group">
											<label for="email">Email:</label>
											<input type="text" name="email" />
										</div>
										<div class="form-group">
											<label for="file">Select a file to upload</label>
											<input type="file" name="file" />
										</div>

									 	<button type="submit" class="btn btn-primary">Submit</button>

								</form:form>
								
							</div>
						
						</div>
						
					</div>
					
					<div id="multipart-form-data-model-attribute" class="tab-pane fade">
				
						<div class="container">
							
							<div class="jumbotron">
								
								<h3>Fill the Form and Select a File (<code>@ModelAttribute</code>)</h3>
								
								<form:form method="POST" action="/upload/uploadFileModelAttribute" enctype="multipart/form-data">
								
									 	<div class="form-group">
											<label for="name">Name:</label>
											<input type="text" name="name" />
										</div>
										 <div class="form-group">
											<label for="email">Email:</label>
											<input type="text" name="email" />
										</div>
										 <div class="form-group">
											<label for="file">Select a file to upload</label>
											<input type="file" name="file" />
										</div>

									 	<button type="submit" class="btn btn-primary">Submit</button>

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