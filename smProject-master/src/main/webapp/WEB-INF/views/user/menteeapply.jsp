<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="R" value="/" />		
		<div id="fh5co-main">
	
			<div class="container">
				
			    <div class="row" id="fh5co-works">
			    	<h2 class="fh5co-uppercase-heading-sm text-center">멘토링 신청</h2>
			        <div class="col-md-8 col-md-offset-2 text-center fh5co-section-heading work-box">
						<h4 class="fh5co-lead">사진을 클릭하면 상세보기로 이동합니다.</h4>
						<div class="fh5co-spacer fh5co-spacer-sm"></div>
					</div>
					
					<c:forEach var="mentor" items="${ mentors }">
					<div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12 text-center fh5co-work-item work-box">
						<figure><a href="${R}user/menteeapply_detail?id=${ mentor.id }"><img class="img-responsive" src="${R}user/getImage?id=${ mentor.apply_f_intro_fk }" alt=""></a></figure>
						<h3>팀명:&nbsp;${ mentor.group_name }</h3>
						<p>주제:&nbsp;${ mentor.subject }</p>
						<p>${ mentor.year }학년&nbsp;${ mentor.name }</p>
						<a href="mentee_update.do?id=${ mentor.id }" class="btn btn-primary btn-lg mt_submit">신청</a>
						<!--
						<c:choose>
						<c:when test="${ mentor.m_condition == 0}">
							<a href="mentor_update.do?id=${ mentor.id }" class="btn btn-primary btm-md m_submitbtn">멘토선정</a>
							<div class="fh5co-spacer fh5co-spacer-md"></div>
						</c:when>
						<c:when test="${ mentor.m_condition == 1 }">
							<a href="mentor_update.do?id=${ mentor.id }" class="btn btn-cancel btm-md m_submitbtn">선정취소</a>
							<div class="fh5co-spacer fh5co-spacer-md"></div>
						</c:when>
						</c:choose>
						-->
						</div>
					</c:forEach>
				</div>
				<!-- END row -->
			    
			</div>
            
        </div>
		
