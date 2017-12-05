<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="R" value="/" />	
		<div id="fh5co-main">
			<sec:authorize access="authenticated">
			<c:if test="${ period eq 3 }">
			<div class="container">
			    
			    <div class="row">
				<!-- Start Slider -->
	          
	            <h2 class="fh5co-uppercase-heading-sm text-center animate-box fadeInUp animated">멘토 광고</h2>
	            <div class="fh5co-spacer fh5co-spacer-xs"></div>

					  <div class='row'>
    <div class='col-md-12'>
      
          	<c:forEach var="mentor" items="${ mentors }" varStatus="status">
          	<c:choose>
          	<c:when test="${ status.first eq true }">
          		<div class="carousel slide media-carousel fadeInUp animated" id="media">
        		<div class="carousel-inner">
          		<div class="item active">
          		<div class="row">
          	</c:when>
          	<c:when test="${ status.index%3 eq 0 }">
          		<div class="item"><div class="row">
          	</c:when>
          	</c:choose>
              <div class="col-md-4">
                <a class="thumbnail" href="${R}user/menteeapply_detail?id=${ mentor.id }"><img alt="" src="${R}user/getImage?id=${ mentor.apply_f_intro_fk }"></a>
              </div>
            <c:choose>
            <c:when test="${ status.index%3 eq 2 }">
            	</div></div>
            </c:when>
            <c:when test="${ status.last eq true }">
            	</div><div></div></div>
            </c:when>
            </c:choose>
            </c:forEach>
        </div>
        <a data-slide="prev" href="#media" class="left carousel-control">‹</a>
        <a data-slide="next" href="#media" class="right carousel-control">›</a>
      </div>                          
    </div>
  </div>
  
  
	            <!-- End Slider -->
                </div>
                
			<!-- END row -->
            </div>
            </c:if>
            </sec:authorize>
            
            
<c:if test="${ period ne 3 }">

<div class="container">
			    
			    <div class="row">
				<!-- Start Slider -->
	          
	            <h2 class="fh5co-uppercase-heading-sm text-center animate-box fadeInUp animated">SKHU SM</h2>
	            <div class="fh5co-spacer fh5co-spacer-xs"></div>

					  <div class='row'>            
                <div class='col-md-12'>
      <div class="carousel slide media-carousel fadeInUp animated" id="media">
        <div class="carousel-inner">
          <div class="item active">
            <div class="row">
              <div class="col-md-4">
                <a class="thumbnail" href="#"><img alt="" src="${R}images/img_large_1.jpg"></a>
              </div>          
              <div class="col-md-4">
                <a class="thumbnail" href="#"><img alt="" src="${R}images/img_large_2.jpg"></a>
              </div>
              <div class="col-md-4">
                <a class="thumbnail" href="#"><img alt="" src="${R}images/img_large_3.jpg"></a>
              </div>        
            </div>
          </div>
          <div class="item">
            <div class="row">
              <div class="col-md-4">
                <a class="thumbnail" href="#"><img alt="" src="${R}images/img_large_4.jpg"></a>
              </div>          
              <div class="col-md-4">
                <a class="thumbnail" href="#"><img alt="" src="${R}images/img_large_5.jpg"></a>
              </div>
              <div class="col-md-4">
                <a class="thumbnail" href="#"><img alt="" src="${R}images/img_large_6.jpg"></a>
              </div>        
            </div>
          </div>
          <div class="item">
            <div class="row">
              <div class="col-md-4">
                <a class="thumbnail" href="#"><img alt="" src="${R}images/img_large_5.jpg"></a>
              </div>          
              <div class="col-md-4">
                <a class="thumbnail" href="#"><img alt="" src="${R}images/img_large_3.jpg"></a>
              </div>
              <div class="col-md-4">
                <a class="thumbnail" href="#"><img alt="" src="${R}images/img_large_1.jpg"></a>
              </div>      
            </div>
          </div>
        </div>
        <a data-slide="prev" href="#media" class="left carousel-control">‹</a>
        <a data-slide="next" href="#media" class="right carousel-control">›</a>
      </div>                          
    </div>
  </div>
  </div>
  </div>           
            
</c:if>
            
            
  <div style="background-color:#90d7ea;">
  	<div class="container">
    <!--현재 진행일정--> <!-- 화면중앙으로 오게 수정완료-->
		<div class="mentoring_con fadeInUp animated">
		<div class="my_step">
						<ul>
							<li>
								<img src="${R}images/icon_my_step01.png" alt="준비" title="준비">
								<p id="stepA" <c:if test="${ period eq 1 }">class="on"</c:if>>준비</p>
							</li>
							<li>
								<img src="${R}images/icon_my_step02.png" alt="멘토신청" title="멘토신청">
								<p id="stepB" <c:if test="${ period eq 2 }">class="on"</c:if>>멘토신청</p>
							</li>
							<li>
								<img src="${R}images/icon_my_step02.png" alt="멘티신청" title="멘티신청">
								<p id="stepC" <c:if test="${ period eq 3 }">class="on"</c:if>>멘티신청</p>
							</li>
							<li>
								<img src="${R}images/icon_my_step03.png" alt="활동중" title="활동중">
								<p id="stepD" <c:if test="${ period eq 4 }">class="on"</c:if>>활동중</p>
							</li>
							<li>
								<img src="${R}images/icon_my_step04.png" alt="설문" title="설문">
								<p id="stepE">설문</p>
							</li>
							<li>
								<img src="${R}images/icon_my_step05.png" alt="종료" title="종료">
								<p id="stepF" <c:if test="${ period eq 5 }">class="on"</c:if>>종료</p>
							</li>
						</ul>
						<p class="de">
							<span><b>2018학년도&nbsp;&nbsp;1학기&nbsp;&nbsp;멘토링&nbsp;&nbsp;운영기간:&nbsp;&nbsp;${ setting.mentor_start_date }~${ setting.sm_expire_date }</b></span>
						</p>
					</div>
				</div>
		
    </div>
    
    </div>
    <div class="fh5co-spacer fh5co-spacer-md"></div>
      
        </div>