<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="fh5co-main">


	<div class="container">
		<div class="col-md-12" id="fh5co-features">
			<div class="row">
				<div class="col-md-12 animate-box">
					<h2 class="fh5co-uppercase-heading-sm text-center" id="save">시간표</h2>
					<div class="fh5co-spacer fh5co-spacer-sm"></div>
				</div>
				<div class="col-md-12 animate-box">
					<form id="form1" name="form1" method="post" action="timetable"
						onsubmit="_submit(this);">
						<div class="panel panel-default ">

							<table class="table table-striped">
								<thead>
									<tr>
										<th class="col-md-3">시간표</th>
										<th>월</th>
										<th>화</th>
										<th>수</th>
										<th>목</th>
										<th>금</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<th scope="row">09:00~10:15</th>
										<td><input type="checkbox" name="mon" value="1"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==1 && timetable.time==1}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="tue" value="1"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==2 && timetable.time==1}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="wed" value="1"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==3 && timetable.time==1}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="thu" value="1"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==4 && timetable.time==1}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="fri" value="1"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==5 && timetable.time==1}">checked</c:if></c:forEach> /></td>
									</tr>
									<tr>
										<th scope="row">10:30~11:45</th>
										<td><input type="checkbox" name="mon" value="2"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==1 && timetable.time==2}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="tue" value="2"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==2 && timetable.time==2}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="wed" value="2"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==3 && timetable.time==2}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="thu" value="2"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==4 && timetable.time==2}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="fri" value="2"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==5 && timetable.time==2}">checked</c:if></c:forEach> /></td>
									</tr>
									<tr>
										<th scope="row">12:00~13:15</th>
										<td><input type="checkbox" name="mon" value="3"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==1 && timetable.time==3}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="tue" value="3"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==2 && timetable.time==3}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="wed" value="3"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==3 && timetable.time==3}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="thu" value="3"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==4 && timetable.time==3}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="fri" value="3"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==5 && timetable.time==3}">checked</c:if></c:forEach> /></td>
									</tr>
									<tr>
										<th scope="row">13:30~14:45</th>
										<td><input type="checkbox" name="mon" value="4"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==1 && timetable.time==4}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="tue" value="4"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==2 && timetable.time==4}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="wed" value="4"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==3 && timetable.time==4}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="thu" value="4"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==4 && timetable.time==4}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="fri" value="4"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==5 && timetable.time==4}">checked</c:if></c:forEach> /></td>
									</tr>
									<tr>
										<th scope="row">15:00~16:15</th>
										<td><input type="checkbox" name="mon" value="5"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==1 && timetable.time==5}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="tue" value="5"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==2 && timetable.time==5}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="wed" value="5"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==3 && timetable.time==5}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="thu" value="5"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==4 && timetable.time==5}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="fri" value="5"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==5 && timetable.time==5}">checked</c:if></c:forEach> /></td>
									</tr>
									<tr>
										<th scope="row">16:30~17:45</th>
										<td><input type="checkbox" name="mon" value="6"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==1 && timetable.time==6}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="tue" value="6"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==2 && timetable.time==6}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="wed" value="6"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==3 && timetable.time==6}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="thu" value="6"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==4 && timetable.time==6}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="fri" value="6"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==5 && timetable.time==6}">checked</c:if></c:forEach> /></td>
									</tr>
									<tr>
										<th scope="row">18:00~19:00</th>
										<td><input type="checkbox" name="mon" value="7"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==1 && timetable.time==7}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="tue" value="7"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==2 && timetable.time==7}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="wed" value="7"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==3 && timetable.time==7}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="thu" value="7"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==4 && timetable.time==7}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="fri" value="7"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==5 && timetable.time==7}">checked</c:if></c:forEach> /></td>
									</tr>
									<tr>
										<th scope="row">19:00~20:00</th>
										<td><input type="checkbox" name="mon" value="8"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==1 && timetable.time==8}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="tue" value="8"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==2 && timetable.time==8}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="wed" value="8"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==3 && timetable.time==8}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="thu" value="8"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==4 && timetable.time==8}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="fri" value="8"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==5 && timetable.time==8}">checked</c:if></c:forEach> /></td>
									</tr>
									<tr>
										<th scope="row">20:00~21:00</th>
										<td><input type="checkbox" name="mon" value="9"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==1 && timetable.time==9}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="tue" value="9"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==2 && timetable.time==9}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="wed" value="9"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==3 && timetable.time==9}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="thu" value="9"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==4 && timetable.time==9}">checked</c:if></c:forEach> /></td>
										<td><input type="checkbox" name="fri" value="9"
											<c:forEach var="timetable" items="${timetable}">
											<c:if test="${timetable.day==5 && timetable.time==9}">checked</c:if></c:forEach> /></td>
									</tr>
								</tbody>

							</table>


						</div>
						<div class="col-md-12">
							<input type="submit"
								class="btn btn-primary btn-lg col-md-offset-11" name="Submit"
								id="button" value="저장" />
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
</div>