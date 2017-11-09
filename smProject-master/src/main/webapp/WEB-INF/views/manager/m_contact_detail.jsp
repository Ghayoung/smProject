<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="fh5co-main">
	
			<div class="container">

				<!-- 
				///////////////////////////////////
				Forms 
				///////////////////////////////////
				-->

				<div class="row">
					<div class="col-md-12 animate-box">
						<h2 class="fh5co-uppercase-heading-sm text-center">멘토 상세보기</h2>
						<div class="fh5co-spacer fh5co-spacer-sm"></div>
					</div>
					<div class="col-md-12 animate-box">
						<form action="#" method="post">
							<div class="col-md-12">
								<img src="${R}${ mentor.path }" alt="Images" class="fh5co-align-center img-responsive">
								<div class="fh5co-spacer fh5co-spacer-sm"></div>
								<div class="fh5co-spacer fh5co-spacer-sm"></div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="name">멘토</label>
									<input placeholder="${ mentor.name }" id="name" type="text" class="form-control input-lg">
								</div>	
							</div>
								<div class="col-md-6">
								<div class="form-group">
									<label for="group_name">주제</label>
									<input placeholder="${ mentor.subject }" id="group_name" type="text" class="form-control input-lg">
								</div>	
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="subject">팀명</label>
									<input placeholder="${ mentor.group_name }" id="subject" type="text" class="form-control input-lg">
								</div>	
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="year">학년</label>
									<input placeholder="${ mentor.year }" id="year" type="text" class="form-control input-lg">
								</div>	
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="grade">해당 과목 성적</label>
									<input placeholder="${ mentor.grade }" id="grade" type="text" class="form-control input-lg">
								</div>	
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="mentee_num">희망 멘티 인원</label>
									<input placeholder="${ mentor.count }" id="mentee_num" type="text" class="form-control input-lg">
								</div>	
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="study_purpose">스터디 목적</label>
									<textarea placeholder="${ mentor.study_purpose }" id="study_purpose" class="form-control input-lg" rows="3"></textarea>
								</div>	
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="study_content">스터디 내용</label>
									<textarea placeholder="${ mentor.study_content }" id="study_content" class="form-control input-lg" rows="3"></textarea>
								</div>	
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="message">스터디 방법</label>
									<textarea placeholder="${ mentor.study_method }" id="message" class="form-control input-lg" rows="3"></textarea>
								</div>	
							</div>
							<!--
							<div class="col-md-2">
								<a href="#">첨부된 파일 <img src="images\file.png" border="0"></a>
								<div class="fh5co-spacer fh5co-spacer-md"></div>
						    </div>
						    -->
							
							<div class="col-md-12">
									<input type="submit" class="btn btn-primary btn-lg col-md-offset-8" value="멘토선정">
									<input type="button" class="btn btn-outline btn-lg" onclick="location.href='m_contact.do'" value="목록">
							</div>
							
						</form>	
					</div>
					
				</div>
				<!-- END row -->

				<div class="fh5co-spacer fh5co-spacer-sm"></div>



			</div>
			<!-- END container -->

		
		</div>
		<!-- END fhtco-main -->