<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
	<head>
        <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
		<title>File Upload Example</title>
        <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />
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
					
						<h3>Enter The File to Upload (Single file)</h3>

						<div class="container">
				
				  			<div class="jumbotron">
							
								<form:form method="POST" action="/upload/uploadFile" enctype="multipart/form-data">
									
									 <div class="form-group">
									 
										 <label for="file">Select a file to upload</label>
										 <input type="file" name="file" />
									 
									 </div>
									 
									 <button type="submit" class="btn btn-default">Submit</button>
									
								</form:form>
								
							</div>
							
						</div>
						
					</div>
					
					<div id="multiple-files" class="tab-pane fade">
						<h3>Enter The Files to Upload (Multiple files)</h3>
						<p>Some content in menu 1.</p>
						
						<div class="container">
						
				  			<div class="jumbotron">
				  			
								<form:form method="POST" action="/upload/uploadMultiFile" enctype="multipart/form-data">
									
									 <div class="form-group">
											<label for="files">Select a file to upload</label>
											<input type="file" name="files" />
									 </div>
									 <div class="form-group">
											<label for="files">Select a file to upload</label>
											<input type="files" name="files" />
									 </div>
									 <div class="form-group">
											<label for="files">Select a file to upload</label>
											<input type="files" name="files" />
									 </div>
									 
									 <button type="submit" class="btn btn-default">Submit</button>
									 
								</form:form>
							
							</div>
						
						</div>
						
					</div>
					
					<div id="multipart-form-data-request-param" class="tab-pane fade">
						<h3>Fill the Form and Select a File (<code>@RequestParam</code>)</h3>
						<p>Some content in menu 2.</p>
		
						<div class="container">
						
				  			<div class="jumbotron">
						
								<form:form method="POST" action="/upload/uploadFileWithAdditionalData" enctype="multipart/form-data">
								
									<table>
										<tr>
											<td>Name</td>
											<td><input type="text" name="name" /></td>
										</tr>
										<tr>
											<td>Email</td>
											<td><input type="text" name="email" /></td>
										</tr>
										<tr>
											<td>Select a file to upload</td>
											<td><input type="file" name="file" /></td>
										</tr>
										<tr>
											<td><input type="submit" value="Submit" /></td>
										</tr>
									</table>
								
								</form:form>
								
							</div>
						
						</div>
						
					</div>
					
					<div id="multipart-form-data-model-attribute" class="tab-pane fade">
				
						<h3>Fill the Form and Select a File (<code>@ModelAttribute</code>)</h3>
						
						<p>Some content in menu 2.</p>
						
						<div class="container">
						
				  			<div class="jumbotron">
						
								<form:form method="POST" action="/upload/uploadFileModelAttribute" enctype="multipart/form-data">
								
									<table>
										<tr>
											<td>Name</td>
											<td><input type="text" name="name" /></td>
										</tr>
										<tr>
											<td>Email</td>
											<td><input type="text" name="email" /></td>
										</tr>
										<tr>
											<td>Select a file to upload</td>
											<td><input type="file" name="file" /></td>
										</tr>
										<tr>
											<td><input type="submit" value="Submit" /></td>
										</tr>
									</table>
								
								</form:form>
							
							</div>
							
						</div>
						
					</div>
					
				</div>
			
			</div>
			
			<div class="col-sm-3"></div>
		
		</div>
	
	</body>

</html>