<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="fh5co-main">
	<div class="container">
		<div class="row">
			<div class="col-md-12 animate-box">
				<h2 class="fh5co-uppercase-heading-sm text-center">회원가입</h2>
				<div class="fh5co-spacer fh5co-spacer-sm"></div>
			</div>	
			<div class="col-md-8 col-md-offset-2 animate-box">
				<form:form method="post" modelAttribute="user">
					<div class="col-md-12">
						<div class="form-group">
							<label for="id">ID</label>
							<form:input path="user_id" placeholder="id" id="id" type="text" class="form-control input-lg" />
						</div>	
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label for="password">Password</label>
							<form:input path="pw" placeholder="Password" id="text" type="password" class="form-control input-lg" />
						</div>	
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label for="name">name</label>
							<form:input path="name" id="name" type="text" class="form-control input-lg" />
						</div>	
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label for="type">type</label>
							<form:input path="type" id="type" type="text" class="form-control input-lg" />
						</div>	
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label for="major_id">major_id</label>
							<form:input path="major_id" id="major_id" type="text" class="form-control input-lg" />
						</div>	
					</div>
					<div class="col-md-12 col-r">
						<input type="submit" class="btn btn-primary btn-lg " value="join">
					</div>
				</form:form>	
			</div>
		</div>
	<!-- END row -->
	</div>
</div>

<script>
	if(${param.error!=null}){alert("로그인 실패");};
</script>
        