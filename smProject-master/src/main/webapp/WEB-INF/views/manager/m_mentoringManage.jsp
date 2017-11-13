<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div id="fh5co-main">

	<div class="container">

		<!-- 
				///////////////////////////////////
				검색
				///////////////////////////////////
				-->
		<div class="row animate-box">
			<h2 class="fh5co-uppercase-heading-sm text-center"
				id="#mentoringManage">멘토링 관리</h2>
			<form action="#mentoringManage" method="post" style="float: right"
				class="row" name="fm" onsubmit="return checkSearch();">
				<div class="col-md-8">
					<div class="form-group">
						<label for="search" class="sr-only ">검색어</label> <input
							name="mentoringSearch" placeholder="멘토방 검색" id="mentoringSearch"
							type="text" class="form-control input-lg" value="${keyword}">
					</div>
				</div>
				<div class="col-md-2" style="margin-left: -20px">
					<div class="form-group">
						<input type="submit" class="btn btn-primary btn-lg " value="검색">
						<!--	 <a class="btn btn-primary btn-lg search_btn">검색</a>   -->
					</div>
				</div>
			</form>
		</div>
		<!-- 
					///////////////////////////////////
					검색 결과
					///////////////////////////////////
					-->
		<%
			Object keyword = null;
			if (request.getAttribute("keyword") != null) {
				keyword = request.getAttribute("keyword");
			}
			if (keyword == null) {
		%>
		<div class="animate-box col-md-12" style="display: none">
			<%
				} else {
			%>
			<div class="animate-box col-md-12" style="display: block">
				<%
					}
				%>
				<div class="panel panel-default">
					<table class="table search_user">
						<thead>
							<tr>
								<th>번호</th>
								<th>멘토이름</th>
								<th>멘토링 이름</th>
								<th>신청인원/정원</th>
								<th>개설일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="team" items="${ teams }" varStatus="status">
								<tr>
									<td>${ status.index+1 }</td>
									<td>${ team.name }</td>
									<td>${ team.group_name }</td>
									<td>${ team.apply_mentee_count }/${ team.count }</td>
									<td>${ team.open_date }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="fh5co-spacer fh5co-spacer-sm"></div>

		</div>
		<!-- 
				///////////////////////////////////
				회원 목록
				///////////////////////////////////
				-->
		<div class="row">
			<div class="col-md-12 animate-box">
				<div class="fh5co-spacer fh5co-spacer-sm"></div>
				<form action="#" method="post" class="search_min">
					<div class="col-md-2">
						<div class="form-group">
							<label for="search_term" class="sr-only">학기</label> <select
								class="form-control" id="search_term">
								<option>--학기</option>
								<option>1</option>
								<option>2</option>
							</select>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group">
							<label for="search_year" class="sr-only">년도</label> <input
								placeholder="2017학년도" id="search_year" type="text"
								class="form-control">
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-12 animate-box">
				<div id="fh5co-tab-feature-center" class="fh5co-tab text-center">
					<ul class="resp-tabs-list hor_1">
						<li>멘토링</li>
					</ul>

					<div class="resp-tabs-container hor_1">

						<div>

							<!--멘토링-->
							<div class="row">

								<div class="panel panel-default ">
									<table class="table search_user">
										<thead>
											<tr>
												<th>번호</th>
												<th>멘토이름</th>
												<th>멘토링 이름</th>
												<th>신청인원/정원</th>
												<th>개설일</th>

											</tr>
										</thead>
										<tbody>
											<c:forEach var="team" items="${ teams }" varStatus="status">
												<tr>
													<td>${ status.index+1 }</td>
													<td>${ team.name }</td>
													<td>${ team.group_name }</td>
													<td>${ team.apply_mentee_count }/${ team.count }</td>
													<td>${ team.open_date }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="fh5co-spacer fh5co-spacer-sm"></div>
	</div>
	<!-- END container -->

</div>
<!-- END fhtco-main -->