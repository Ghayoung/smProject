<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div id="fh5co-main">
	
			<div class="container">

				<!-- 
				///////////////////////////////////
				Forms 
				///////////////////////////////////
				-->

				<div class="row">
					<div class="col-md-12 animate-box">
						<h2 class="fh5co-uppercase-heading-sm text-center">멘토 모집</h2>
						<div class="fh5co-spacer fh5co-spacer-sm"></div>
					</div>
					<div class="col-md-12 animate-box">
						<form name="form" action="mentorapply" method="post" enctype="multipart/form-data" onsubmit="return checkMentorApply();">
						
							<div class="col-md-12">
								<div class="form-group">
									<label for="group_name">팀명</label>
									<input name="group_name" placeholder="팀명" id="group_name" type="text" class="form-control input-lg"/>
								</div>	
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="subject">주제</label>
									<input name="subject" placeholder="주제" id="subject" type="text" class="form-control input-lg"/>
								</div>	
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="year">학년</label>
									<input name="year" placeholder="학년" id="year" type="text" class="form-control input-lg"/>
								</div>	
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="grade">해당 과목 성적</label>
									<input name="grade" placeholder="해당 과목 성적" id="grade" type="text" class="form-control input-lg"/>
								</div>	
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="count">희망 멘티 인원</label>
									<input name="count" placeholder="희망 멘티 인원" id="count" type="text" class="form-control input-lg"/>
								</div>	
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="study_purpose">스터디 목적</label>
									<textarea name="study_purpose" placeholder="스터디 목적" id="study_purpose" class="form-control input-lg" rows="3"></textarea>
								</div>	
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="study_content">스터디 내용</label>
									<textarea name="study_content" placeholder="스터디 내용" id="study_content" class="form-control input-lg" rows="3"></textarea>
								</div>	
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="study_method">스터디 방법</label>
									<textarea name="study_method" placeholder="스터디 방법" id="study_method" class="form-control input-lg" rows="3"></textarea>
								</div>	
							</div>
							
							<div class="col-md-2">
								<div class="form-group">
									<p>시간표</p>
									<input type="file" class="btn btn-lg " value="파일첨부" style="margin-top:-15px;" name="file1"/>
									<p style="margin-top: 10px;">홍보 이미지</p>
									<input type="file" class="btn btn-lg" value="파일첨부" style="margin-top:-15px;" name="file2"/>
									<p style="margin-top: 10px;">증빙서류</p>
									<input type="file" class="btn btn-lg " value="파일첨부" style="margin-top:-15px;"  name="file3"/>
								</div>	
							</div>
							 
							<div class="col-md-12">
								<div class="form-group">
									<input type="submit" class="btn btn-primary btn-lg col-md-offset-11" value="등록">
								</div>	
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