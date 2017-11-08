<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:url var="R" value="/" />	

		<div id="fh5co-main">
	
			<div class="container">

				

				<!-- 
				///////////////////////////////////
				Accordions & Progress Bars 
				///////////////////////////////////
				-->

				<div class="row">
					<div class="col-md-12 animate-box">
						<h2 class="fh5co-uppercase-heading-sm text-center">${ board }</h2>
						<div class="fh5co-spacer fh5co-spacer-sm"></div>
					</div>
					<div class="col-md-12 animate-box">
						<div class="panel panel-default ">
							<table class="table board">
								<thead>
									<tr>
										<th>번호</th>
										<th>제목</th>
										<th>작성자</th>
										<th>작성일</th>
										<th>파일</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="article" items="${ article }" varStatus="status">
								        <tr data-url="${R}user/board_detail?type=${ param.type }&id=${article.id}">
								          <td>${ status.index+1 }</td>
								          <td>${ article.title }</td>
								          <td>${ article.userName }</td>
								          <td>${ article.post_date }</td>
								          <td><a href="#"><img src="${R}images\file.png" border="0"></a></td>
								        </tr>
								      </c:forEach>
								</tbody>
							</table>
						</div>
						<c:if test="${ param.type > 1 }">
							<div class="col-md-12 col-r">
								<a href="${R}user/board_create?type=${ param.type }" class="btn btn-primary btn-lg">글쓰기</a>
							</div>
						</c:if>
						<c:if test="${ param.type == 1 }">
							<sec:authorize access="hasRole('MANAGER')">
								<div class="col-md-12 col-r">
									<a href="${R}user/board_create?type=${ param.type }" class="btn btn-primary btn-lg">글쓰기</a>
								</div>
							</sec:authorize>
						</c:if>
				  	</div>
				</div>
			<!-- END row -->

				<div class="fh5co-spacer fh5co-spacer-md"></div>

				



			</div>
			<!-- END container -->

		
		</div>
		<!-- END fhtco-main -->