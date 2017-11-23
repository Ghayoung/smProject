<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
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
										<th style="width:6%;">번호</th>
										<th>제목</th>
										<th style="width:17%;">작성자</th>
										<th style="width:17%;">작성일</th>
										<th style="width:6%;">파일</th>
										<c:if test="${ param.bd == 3 }">
											<th style="width:10%;">답변</th>
										</c:if>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="article" items="${ article }" varStatus="status">
								        <tr data-url="${R}user/board_detail?id=${article.id}&${ pagination.queryString }">
								          <td>${ (pagination.pg-1)*15+status.count }</td>
								          <td>${ article.title }</td>
								          <td>${ article.userName }</td>
								          <td>${ article.post_date }</td>
								          <td><c:if test="${ article.art_f_id != 0 }"><a href="${R}user/file/download?id=${ article.art_f_id }"><img src="${R}images\file.png" border="0"></a></c:if></td>
								          <c:if test="${ param.bd == 3 }">
											<td><a href="#">답변완료</a></td>
										</c:if>
								        </tr>
								      </c:forEach>
								</tbody>
							</table>
						</div>
						<my:pagination pageSize="${ pagination.sz }" recordCount="${ pagination.recordCount }" />
						<c:if test="${ param.bd > 1 }">
							<div class="col-md-12 col-r">
								<a href="${R}user/board_create?${ pagination.queryString }" class="btn btn-primary btn-lg">글쓰기</a>
							</div>
						</c:if>
						<c:if test="${ param.bd == 1 }">
							<sec:authorize access="hasRole('MANAGER')">
								<div class="col-md-12 col-r">
									<a href="${R}user/board_create?${ pagination.queryString }" class="btn btn-primary btn-lg">글쓰기</a>
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