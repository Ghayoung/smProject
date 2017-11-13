<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="net.skhu.dto.Report"%>
<%@ page import="net.skhu.service.ReportDAO"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%
	List<Report> list = ReportDAO.findAll();
%>

<div id="fh5co-main">
	<div class="container">
		<div class="col-md-12" id="fh5co-features">

			<div class="row">
				<div class="col-md-12 animate-box">
					<h2 class="fh5co-uppercase-heading-sm text-center">보고서 관리</h2>
					<div class="fh5co-spacer fh5co-spacer-sm"></div>
				</div>

				<div class="col-md-12 animate-box">
					<div id="fh5co-tab-feature-center" class="fh5co-tab text-center">
						<ul class="resp-tabs-list hor_1">
							<li>조별</li>
							<li>주별</li>
							<li>전체</li>
							<li>제출현황</li>
						</ul>

						<div class="resp-tabs-container hor_1">

							<div>

								<div class="row">
									<!--조별-->
									<%
										int n = 1;
									%>
									<c:forEach var="teamReports" items="${ teamReports }">

										<h2>${ teamReports.group_name }</h2>
										<!--  
										<label for="report_subject">멘토링 진행률 <span
											class="fh5co-uppercase-heading-sm">&nbsp;&nbsp;8회
												남았습니다.</span></label>
										<div class="progress">
											<div class="progress-bar" role="progressbar"
												style="width: 25%;" aria-valuenow="25" aria-valuemin="0"
												aria-valuemax="100">25%</div>
										</div>
										-->
										<div class="panel panel-default">
											<table class="table board" id="r_table<%=n%>">
												<thead>
													<tr>
														<th style="padding-left: 4px;"><input type="checkbox"
															onclick="selectAllCheckBox(this,'r_table<%=n%>','cb')"></th>
														<th>번호</th>
														<th>스터디주제</th>
														<th>장소</th>
														<th>작성일</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="reports" items="${ teamReports.reports }"
														varStatus="status">
														<tr>
															<td><input type="checkbox" name="checkbox" id="cb_1"></td>
															<th scope="row">${ status.index+1 }</th>
															<td data-url="report_detail?id=${ reports.id }">${ reports.subject }</td>
															<td>${ reports.place }</td>
															<td>${ reports.create_date }</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<div class="col-r">
											<a href="#" class="btn btn-primary btn-lg">다운로드</a>
										</div>
										<div class="fh5co-spacer fh5co-spacer-md"></div>
										<%
											n++;
										%>
									</c:forEach>

									<!--조별 끝-->
								</div>

							</div>

							<div>
								<div class="row">
									<!--주차-->
									<%
										Object startSM = request.getAttribute("startSM");
										String start_sm = startSM.toString();

										Calendar startCalendar = Calendar.getInstance();
										Calendar deadCalendar = Calendar.getInstance();
										Calendar currentCalendar = Calendar.getInstance();
										Calendar reportCalendar = Calendar.getInstance();

										startCalendar.set(Calendar.YEAR, Integer.parseInt(start_sm.substring(0, 4)));
										startCalendar.set(Calendar.MONTH, Integer.parseInt(start_sm.substring(4, 6)) - 1);
										startCalendar.set(Calendar.DATE, Integer.parseInt(start_sm.substring(6)));

										deadCalendar.set(Calendar.YEAR, Integer.parseInt(start_sm.substring(0, 4)));
										deadCalendar.set(Calendar.MONTH, Integer.parseInt(start_sm.substring(4, 6)) - 1);
										deadCalendar.set(Calendar.DATE, Integer.parseInt(start_sm.substring(6)));

										deadCalendar.add(Calendar.DATE, 6);

										int week = 0;
										while (startCalendar.before(currentCalendar) || startCalendar.equals(currentCalendar)) {
									%>

									<h2><%=week + 1%>주차
									</h2>
									<div class="panel panel-default">
										<table class="table board" id="r_table<%=n%>">
											<thead>
												<tr>
													<th style="padding-left: 4px;"><input type="checkbox"
														onclick="selectAllCheckBox(this,'r_table<%=n%>','cb')"></th>
													<th>번호</th>
													<th>팀명</th>
													<th>스터디내용</th>
													<th>장소</th>
													<th>작성일</th>
												</tr>
											</thead>
											<tbody>
												<%
													int i = 0;
														for (Report report : list) {

															String rep_date = (report.getCreate_date()).replaceAll("-", "");

															reportCalendar.set(Calendar.YEAR, Integer.parseInt(rep_date.substring(0, 4)));
															reportCalendar.set(Calendar.MONTH, Integer.parseInt(rep_date.substring(4, 6)) - 1);
															reportCalendar.set(Calendar.DATE, Integer.parseInt(rep_date.substring(6)));

															if ((reportCalendar.equals(startCalendar) || startCalendar.before(reportCalendar))
																	&& ((reportCalendar.equals(deadCalendar) || reportCalendar.before(deadCalendar)))) {
												%>
												<tr>
													<td><input type="checkbox" name="checkbox" id="cb_1"></td>
													<th scope="row"><%=i + 1%></th>
													<td><%=report.getGroup_name()%></td>
													<td data-url="report_detail?id=<%=report.getId()%>"><%=report.getSubject()%></td>
													<td><%=report.getPlace()%></td>
													<td><%=report.getCreate_date()%></td>
												</tr>
												<%
													}
															i++;
														}
														n++;
												%>
											</tbody>
										</table>
									</div>
									<div class="fh5co-spacer fh5co-spacer-md"></div>

									<div class="col-r">
										<div id="check_arr" class="btn btn-primary btn-lg">다운로드</div>
									</div>
									<%
										startCalendar.add(Calendar.DATE, 7);
											deadCalendar.add(Calendar.DATE, 7);
											week++;
										}
									%>

									<!--주차 끝-->
								</div>
							</div>
							<div>
								<div class="row">

									<!--전체-->
									<h2>전체보고서</h2>
									<div class="panel panel-default">
										<table class="table board" id="r_table<%=n%>">
											<thead>
												<tr>
													<th style="padding-left: 4px;"><input type="checkbox"
														onclick="selectAllCheckBox(this,'r_table<%=n%>','cb')"></th>
													<th>번호</th>
													<th>팀명</th>
													<th>스터디내용</th>
													<th>장소</th>
													<th>작성일</th>

												</tr>
											</thead>
											<tbody>
												<%
													int i = 0;
													for (Report report : list) {
												%>
												<tr>
													<td><input type="checkbox" name="checkbox" id="cb_1"></td>
													<th scope="row"><%=i + 1%></th>
													<td><%=report.getGroup_name()%></td>
													<td data-url="report_detail?id=<%=report.getId()%>"><%=report.getSubject()%></td>
													<td><%=report.getPlace()%></td>
													<td><%=report.getCreate_date()%></td>
												</tr>
												<%
													}
													i++;
													n++;
												%>
											</tbody>
										</table>

									</div>
									<div class="fh5co-spacer fh5co-spacer-md"></div>

									<div class="col-r">
										<div id="check_arr" class="btn btn-primary btn-lg">다운로드</div>
									</div>
									<!--전체 끝-->

								</div>
							</div>
							<!--제출현황-->
							<div>
								<div class="row">
									<c:forEach var="conditionReports" items="${ conditionReports }">
										<!--미제출-->
										<h2>${ conditionReports.group_name }</h2>
										<label for="report_subject">멘토링 진행률 <span
											class="fh5co-uppercase-heading-sm">&nbsp;&nbsp;${ totalReport-conditionReports.reportCount }회
												남았습니다.</span></label>
										<div class="progress">

											<div class="progress-bar" role="progressbar"
												style="width: ${ conditionReports.reportCount/totalReport*100 }%;"
												aria-valuenow="${ conditionReports.reportCount/totalReport*100 }%"
												aria-valuemin="0" aria-valuemax="100">
												<fmt:parseNumber integerOnly="true"
													value="${ conditionReports.reportCount/totalReport*100 }" />
												%
											</div>
										</div>

										<div class="fh5co-spacer fh5co-spacer-sm"></div>
									</c:forEach>
								</div>
							</div>
							<!--제출현황 끝-->
							<div class="fh5co-spacer fh5co-spacer-md"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>