<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="R" value="/" />	
		<div id="fh5co-main">
	
			<div class="container">

				<!-- 
				///////////////////////////////////
				Forms 
				///////////////////////////////////
				-->

				<div class="row">
					<div class="col-md-12 animate-box">
						<h2 class="fh5co-uppercase-heading-sm text-center">멘토링 신청</h2>
						<div class="fh5co-spacer fh5co-spacer-sm"></div>
					</div>
					<div class="col-md-12 animate-box">
						<form action="#" method="post">
							<div class="col-md-12">
								<img src="${R}user/getImage?id=${ mentor.apply_f_intro_fk }" alt="Images" class="fh5co-align-center img-responsive">
								<div class="fh5co-spacer fh5co-spacer-sm"></div>
								<div class="fh5co-spacer fh5co-spacer-sm"></div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<!-- 나중에 학번도 출력하게 변경 -->
									<label for="name">멘토</label>
									<input placeholder="${ mentor.year }학년&nbsp;${ mentor.name }" id="name" type="text" class="form-control input-lg" disabled>
								</div>	
							</div>
								<div class="col-md-6">
								<div class="form-group">
									<label for="group_name">주제</label>
									<input placeholder="${ mentor.subject }" id="group_name" type="text" class="form-control input-lg" disabled>
								</div>	
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="subject">팀명</label>
									<input placeholder="${ mentor.group_name }" id="subject" type="text" class="form-control input-lg" disabled>
								</div>	
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="year">학년</label>
									<input placeholder="${ mentor.year }" id="year" type="text" class="form-control input-lg" disabled>
								</div>	
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="grade">해당 과목 성적</label>
									<input placeholder="${ mentor.grade }" id="grade" type="text" class="form-control input-lg" disabled>
								</div>	
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="mentee_num">희망 멘티 인원</label>
									<input placeholder="${ mentor.count }" id="mentee_num" type="text" class="form-control input-lg" disabled>
								</div>	
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="study_purpose">스터디 목적</label>
									<textarea placeholder="${ mentor.study_purpose }" id="study_purpose" class="form-control input-lg" rows="3" disabled></textarea>
								</div>	
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="study_content">스터디 내용</label>
									<textarea placeholder="${ mentor.study_content }" id="study_content" class="form-control input-lg" rows="3" disabled></textarea>
								</div>	
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="message">스터디 방법</label>
									<textarea placeholder="${ mentor.study_method }" id="message" class="form-control input-lg" rows="3" disabled></textarea>
								</div>	
							</div>
						    <div class="col-md-12">
								<label for="timetable">시간표</label><br>
								<img src="${R}user/getImage?id=${ mentor.apply_f_time_id }" alt="${R}${ mentor.t_path }"><br><br>
						    </div>
							
							<div class="col-md-12">
									<input type="submit" class="btn btn-primary btn-lg col-md-offset-9" value="신청">
									<input type="button" class="btn btn-outline btn-lg" onclick="location.href='${R}user/menteeapply.do'" value="목록">
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