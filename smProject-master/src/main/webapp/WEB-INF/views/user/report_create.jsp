<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="fh5co-main">

	<div class="container">

		<!-- 
				///////////////////////////////////
				Forms 
				///////////////////////////////////
				-->

		<div class="row">
			<div class="col-md-12 animate-box">
				<h2 class="fh5co-uppercase-heading-sm text-center">보고서 작성</h2>
				<div class="fh5co-spacer fh5co-spacer-sm"></div>
			</div>
			<div class="col-md-12 animate-box">
				<form name="form" action="report_create" method="post" enctype="multipart/form-data" onsubmit="return checkMentorApply();">
					<div class="col-md-12">
						<div class="form-group">
							<label for="subject">스터디 주제</label> <input
								placeholder="스터디 주제" id="subject" type="text" name="subject"
								class="form-control input-lg">
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label for="place">장소</label> <input placeholder="장소" name="place"
								id="place" type="text" class="form-control input-lg">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="study_day">이번 주 모임 요일</label>
							<select id="day" class="form-control" name="day">
								<option value=0>--</option>
								<option value=월요일>월요일</option>
								<option value=화요일>화요일</option>
								<option value=수요일>수요일</option>
								<option value=목요일>목요일</option>
								<option value=금요일>금요일</option>
								<option value=토요일>토요일</option>
								<option value=일요일>일요일</option>
							</select>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="start_time">시작 시간</label> <input
								id="start_time" type="time" class="form-control input-lg" name="start_time">
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="end_time">종료 시간</label> <input id="end_time" name="end_time"
								type="time" class="form-control input-lg">
						</div>
					</div>

					<div class="col-md-12">
						<div class="form-group">
							<label for="study_content">스터디 내용</label>
							<textarea placeholder="스터디 내용" id="study_content" name="study_content"
								class="form-control input-lg" rows="3"></textarea>
						</div>
					</div>

					<div class="col-md-2">
						<div class="form-group">
							<label>인증샷</label> 
							<input type="file" class="btn btn-lg " value="파일첨부" style="margin-top: -5px;" name="file1"> 
							<label style="margin-top: 20px;">활동 증명 자료</label> 
							<input type="file" class="btn btn-lg " value="파일첨부" style="margin-top: -5px;" name="file2">
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<input type="submit"
								class="btn btn-primary btn-lg col-md-offset-10" value="저장">
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