<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div id="fh5co-main">
	
			<div class="container">

				

				<!-- 
				///////////////////////////////////
				Accordions & Progress Bars 
				///////////////////////////////////
				-->

				<div class="row">
					<div class="col-md-12 animate-box">
						<h2 class="fh5co-uppercase-heading-sm text-center">메일쓰기</h2>
						<div class="fh5co-spacer fh5co-spacer-sm"></div>
					</div>
						<form:form id="form" method="post" modelAttribute="email" enctype="multipart/form-data" onsubmit="return checkInputEmail();">
										<div class="col-md-12">
												<div class="form-group">
											<label for="tab">받는사람</label>
											<form:input path="to" name="to" placeholder="받는사람" id="tab" type="email" class="form-control input-lg" />
										</div>
						
										<div class="form-group">
											<label for="tab">제목</label>
											<form:input path="subject" name="subject" placeholder="제목" id="tab" type="text" class="form-control input-lg" />
										</div>	
									
										<div class="form-group">
											<label for="introduce_content">내용</label>
											<form:textarea path="text" name="text" placeholder="내용" id="introduce_content" class="form-control input-lg" rows="10"></form:textarea>
										</div>
										
										<div class="form-group">
											<label>파일 첨부</label>
											<input name="file" type="file" class="btn btn-lg " value="파일첨부">
										</div> 

										<div class="form-group">
									<input type="submit" class="btn btn-primary btn-lg col-md-offset-11" value="보내기">
								</div>	
									</div>
						</form:form>
									
				</div>
			<!-- END row -->

				<div class="fh5co-spacer fh5co-spacer-md"></div>

				



			</div>
			<!-- END container -->

		
		</div>
		<!-- END fhtco-main -->

<script>
	if( ${ param.success!=null } ) { alert("메일이 발송되었습니다.");};
</script>

