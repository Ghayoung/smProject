<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="R" value="/" />

<div id="fh5co-main">
	
			<div class="container">
				
			    <div class="row" id="fh5co-works">
			    	<h2 class="fh5co-uppercase-heading-sm text-center">멘토 선정</h2>
			        <div class="col-md-8 col-md-offset-2 text-center fh5co-section-heading work-box">
						<h4 class="fh5co-lead">사진을 클릭하면 상세보기로 이동합니다.</h4>
						<div class="fh5co-spacer fh5co-spacer-sm"></div>
					</div>
					
					<c:forEach var="mentor" items="${ mentors }">
					<div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12 text-center fh5co-work-item work-box">
						<figure><a href="${R}manager/m_contact_detail?id=${ mentor.id }"><img class="img-responsive" src="${R}${ mentor.path }" alt="Free HTML5 Template"></a></figure>
						<h3>팀명:&nbsp;${ mentor.group_name }</h3>
						<p>주제:&nbsp;${ mentor.subject }</p>
						<p>${ mentor.year }학년&nbsp;${ mentor.name }</p>
						<input type="submit" class="btn btn-primary btm-md m_submitbtn" value="멘토선정">
						<div class="fh5co-spacer fh5co-spacer-md"></div>
					</div>
					</c:forEach>
					
					<!--
					<div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12 text-center fh5co-work-item work-box">
						<figure><a href="m_contact_detail"><img class="img-responsive" src="../images/work_1.jpg" alt="Free HTML5 Template"></a></figure>
						<h3>주제: 이산수학</h3>
						<p>멘토: 3학년 남하영</p>
						<p>소개: 소개멘트</p>
						<input type="submit" class="btn btn-primary btm-md m_submitbtn" value="멘토선정">
						<div class="fh5co-spacer fh5co-spacer-md"></div>
					</div>
					<div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12 text-center fh5co-work-item work-box"> 
						<figure><a href="m_contact_detail"><img class="img-responsive" src="../images/work_2.jpg" alt="Free HTML5 Template"></a></figure>
						<h3>주제: C언어</h3>
						<p>멘토: 3학년 최윤경</p>
						<p>소개: 소개멘트</p>
						<input type="submit" class="btn btn-primary btn-md m_submitbtn" value="멘토선정">
					</div>

					<div class="clearfix visible-sm-block visible-xs-block"></div>

					<div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12 text-center fh5co-work-item work-box"> 
						<figure><a href="m_contact_detail"><img class="img-responsive" src="../images/work_3.jpg" alt="Free HTML5 Template"></a></figure>
						<h3>주제: 보안시스템</h3>
						<p>멘토: 3학년 이혜민</p>
						<p>소개: 소개멘트</p>
						<input type="submit" class="btn btn-primary btn-md m_submitbtn " value="멘토선정">
					</div>

					<div class="clearfix visible-md-block visible-lg-block"></div>

					<div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12 text-center fh5co-work-item work-box">
						<figure><a href="m_contact_detail"><img class="img-responsive" src="../images/work_4.jpg" alt="Free HTML5 Template"></a></figure>
						<h3>주제: JAVA</h3>
						<p>멘토: 3학년 마재희</p>
						<p>소개: 소개멘트</p>
						<input type="submit" class="btn btn-primary btn-md m_submitbtn " value="멘토선정">
					</div>

					<div class="clearfix visible-sm-block visible-xs-block"></div>

					<div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12 text-center fh5co-work-item work-box"> 
						<figure><a href="m_contact_detail"><img class="img-responsive" src="../images/work_5.jpg" alt="Free HTML5 Template"></a></figure>
						<h3>주제: 데이터베이스</h3>
						<p>멘토: 3학년 남하영</p>
						<p>소개: 소개멘트</p>
						<input type="submit" class="btn btn-primary btn-md m_submitbtn " value="멘토선정">
					</div>
					<div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12 text-center fh5co-work-item work-box"> 
						<figure><a href="m_contact_detail"><img class="img-responsive" src="../images/work_6.jpg" alt="Free HTML5 Template"></a></figure>
						<h3>주제: HTML</h3>
						<p>멘토: 3학년 최윤경</p>
						<p>소개: 소개멘트</p>
						<input type="submit" class="btn btn-primary btn-md m_submitbtn " value="멘토선정">
					</div>
					-->
				</div>
				<!-- END row -->
			    
			</div>
            
        </div>