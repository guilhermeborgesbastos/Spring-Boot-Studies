<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<title>Spring MVC File Upload</title>
		<spring:url value="/css/main.css" var="mainCss" />
        <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${mainCss}"/>
	</head>
	<body>
		
		<c:set var="requestURI" value="${requestURI}" />

		<div class="row">			
			<div class="col-sm-3"></div>			
			<div class="col-sm-6"><%@ include file="nav.jsp" %></div>
			<div class="col-sm-3"></div>
		</div>
		
		<div class="row">
			
			<div class="col-sm-3"></div>
			
				<div class="col-sm-6">
				
					<div class="container">

						<c:if test="${requestURI == 'uploadFile'}">
							<div class="jumbotron uploadFile">				
								<h2>Submitted File <i>(Single)</i></h2>
								<div class="well well-sm">
									<p class="property">Original File Name:</p>
									<p>${file.originalFilename}</p>
								</div>
								<div class="well well-sm">
									<p class="property">Type:</p>
									<p>${file.contentType}</p>
								</div>
							</div>
						</c:if>

						<c:if test="${requestURI == 'uploadMultiFile'}">
							<div class="jumbotron uploadMultiFile">
								<h2>Submitted Files <i>(Multiple)</i></h2>
								<c:forEach items="${files}" var="file">	
									<div class="well well-sm">
										<p class="property">Original File Name:</p>
										<p>${file.originalFilename}</p>
										<p class="property">Type:</p>
										<p>${file.contentType}</p>
									</div>
								</c:forEach>
							</div>
						</c:if>

						<c:if test="${requestURI == 'uploadFileWithAdditionalData'}">
							<div class="jumbotron uploadFileWithAdditionalData">	
								<h2>Submitted File with Data (<code>@RequestParam</code>)</h2>
								<div class="well well-sm">
									<p class="property">Name:</p>
									<p>${name}</p>
								</div>
								<div class="well well-sm">
									<p class="property">Email:</p>
									<p>${email}</p>
								</div>
								<div class="well well-sm">
									<p class="property">Original File Name:</p>
									<p>${file.originalFilename}</p>
								</div>
								<div class="well well-sm">
									<p class="property">Type:</p>
									<p>${file.contentType}</p>
								</div>
							</div>
						</c:if>
						
						<c:if test="${requestURI == 'uploadFileModelAttribute'}">
							<div class="jumbotron uploadFileModelAttribute">
								<h2>Submitted File with Data (<code>@ModelAttribute</code>)</h2>
								<div class="well well-sm">
									<p class="property">Name:</p>
									<p>${formDataWithFile.name}</p>
								</div>
								<div class="well well-sm">
									<p class="property">Email:</p>
									<p>${formDataWithFile.email}</p>
								</div>
								<div class="well well-sm">
									<p class="property">Original File Name:</p>
									<p>${formDataWithFile.file.originalFilename}</p>
								</div>
								<div class="well well-sm">
									<p class="property">Type:</p>
									<p>${formDataWithFile.file.contentType}</p>
								</div>
							</div>
						</c:if>
					
					</div>
				</div>

			</div>
		
		</div>
			
	</body>
</html>