<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
        <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
		<title>Spring MVC File Upload</title>
        <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />
	</head>
	<body>
	
		<br /><br />
		
		<div class="row">
			
			<div class="col-sm-3"></div>
			
				<div class="col-sm-6">
		
				<h2>Submitted File (Single)</h2>
				<table>
					<tr>
						<td>OriginalFileName :</td>
						<td>${file.originalFilename}</td>
					</tr>
					<tr>
						<td>Type :</td>
						<td>${file.contentType}</td>
					</tr>
				</table>
				<br />
				
				<h2>Submitted Files (Multiple)</h2>
				<table>
					<c:forEach items="${files}" var="file">	
						<tr>
							<td>OriginalFileName :</td>
							<td>${file.originalFilename}</td>
						</tr>
						<tr>
							<td>Type :</td>
							<td>${file.contentType}</td>
						</tr>
					</c:forEach>
				</table>
				<br />
				
				<h2>Submitted File with Data (<code>@RequestParam</code>)</h2>
				<table>
					<tr>
						<td>Name :</td>
						<td>${name}</td>
					</tr>
					<tr>
						<td>Email :</td>
						<td>${email}</td>
					</tr>
					<tr>
						<td>OriginalFileName :</td>
						<td>${file.originalFilename}</td>
					</tr>
					<tr>
						<td>Type :</td>
						<td>${file.contentType}</td>
					</tr>
				</table>
				
				<br />
				
				<h2>Submitted File with Data (<code>@ModelAttribute</code>)</h2>
				<table>
					<tr>
						<td>Name :</td>
						<td>${formDataWithFile.name}</td>
					</tr>
					<tr>
						<td>Email :</td>
						<td>${formDataWithFile.email}</td>
					</tr>
					<tr>
						<td>OriginalFileName :</td>
						<td>${formDataWithFile.file.originalFilename}</td>
					</tr>
					<tr>
						<td>Type :</td>
						<td>${formDataWithFile.file.contentType}</td>
					</tr>
				</table>
			
			</div>
		
		</div>
			
	</body>
</html>