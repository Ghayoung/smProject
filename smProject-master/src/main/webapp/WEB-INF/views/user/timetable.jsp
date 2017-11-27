<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<<<<<<< HEAD
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="fh5co-main">


	<div class="container">
		<div class="col-md-12" id="fh5co-features">
			<div class="row">
				<div class="col-md-12 animate-box">
					<h2 class="fh5co-uppercase-heading-sm text-center">시간표</h2>
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
										<td><input type="checkbox" name="mon" value="1" /></td>
										<td><input type="checkbox" name="tue" value="1" /></td>
										<td><input type="checkbox" name="wed" value="1" /></td>
										<td><input type="checkbox" name="thu" value="1" /></td>
										<td><input type="checkbox" name="fri" value="1" /></td>
									</tr>
									<tr>
										<th scope="row">10:30~11:45</th>
										<td><input type="checkbox" name="mon" value="2" /></td>
										<td><input type="checkbox" name="tue" value="2" /></td>
										<td><input type="checkbox" name="wed" value="2" /></td>
										<td><input type="checkbox" name="thu" value="2" /></td>
										<td><input type="checkbox" name="fri" value="2" /></td>
									</tr>
									<tr>
										<th scope="row">12:00~13:15</th>
										<td><input type="checkbox" name="mon" value="3" /></td>
										<td><input type="checkbox" name="tue" value="3" /></td>
										<td><input type="checkbox" name="wed" value="3" /></td>
										<td><input type="checkbox" name="thu" value="3" /></td>
										<td><input type="checkbox" name="fri" value="3" /></td>
									</tr>
									<tr>
										<th scope="row">13:30~14:45</th>
										<td><input type="checkbox" name="mon" value="4" /></td>
										<td><input type="checkbox" name="tue" value="4" /></td>
										<td><input type="checkbox" name="wed" value="4" /></td>
										<td><input type="checkbox" name="thu" value="4" /></td>
										<td><input type="checkbox" name="fri" value="4" /></td>
									</tr>
									<tr>
										<th scope="row">15:00~16:15</th>
										<td><input type="checkbox" name="mon" value="5" /></td>
										<td><input type="checkbox" name="tue" value="5" /></td>
										<td><input type="checkbox" name="wed" value="5" /></td>
										<td><input type="checkbox" name="thu" value="5" /></td>
										<td><input type="checkbox" name="fri" value="5" /></td>
									</tr>
									<tr>
										<th scope="row">16:30~17:45</th>
										<td><input type="checkbox" name="mon" value="6" /></td>
										<td><input type="checkbox" name="tue" value="6" /></td>
										<td><input type="checkbox" name="wed" value="6" /></td>
										<td><input type="checkbox" name="thu" value="6" /></td>
										<td><input type="checkbox" name="fri" value="6" /></td>
									</tr>
									<tr>
										<th scope="row">18:00~19:00</th>
										<td><input type="checkbox" name="mon" value="7" /></td>
										<td><input type="checkbox" name="tue" value="7" /></td>
										<td><input type="checkbox" name="wed" value="7" /></td>
										<td><input type="checkbox" name="thu" value="7" /></td>
										<td><input type="checkbox" name="fri" value="7" /></td>
									</tr>
									<tr>
										<th scope="row">19:00~20:00</th>
										<td><input type="checkbox" name="mon" value="8" /></td>
										<td><input type="checkbox" name="tue" value="8" /></td>
										<td><input type="checkbox" name="wed" value="8" /></td>
										<td><input type="checkbox" name="thu" value="8" /></td>
										<td><input type="checkbox" name="fri" value="8" /></td>
									</tr>
									<tr>
										<th scope="row">20:00~21:00</th>
										<td><input type="checkbox" name="mon" value="9" /></td>
										<td><input type="checkbox" name="tue" value="9" /></td>
										<td><input type="checkbox" name="wed" value="9" /></td>
										<td><input type="checkbox" name="thu" value="9" /></td>
										<td><input type="checkbox" name="fri" value="9" /></td>
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
=======
<script>
	function checkboxArr(){
		var checkArr=[];
		$("input[name='timetable']:checked").each(function(i)){
			checkArr.push($(this).val());
		}
		
		$.ajax({
			url:'timetable'
			,type:'post'
			,data Type:'text'
			,data:{
				valueArrTest: checkArr
			}
		});
	}
</script>	
<div id="fh5co-main">


	<div class="container">
		<div class="col-md-12" id="fh5co-features">
			<div class="row">
				<div class="col-md-12 animate-box">
					<h2 class="fh5co-uppercase-heading-sm text-center">시간표</h2>
					<div class="fh5co-spacer fh5co-spacer-sm"></div>
				</div>
				<div class="col-md-12 animate-box">
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
									<td><input type="checkbox" name="timetable" value="mon1"></td>
									<td><input type="checkbox" name="timetable" value="tue1"></td>
									<td><input type="checkbox" name="timetable" value="wed1"></td>
									<td><input type="checkbox" name="timetable" value="thu1"></td>
									<td><input type="checkbox" name="timetable" value="fri1"></td>
								</tr>
								<tr>
									<th scope="row">10:30~11:45</th>
									<td><input type="checkbox" name="timetable" value="mon2"></td>
									<td><input type="checkbox" name="timetable" value="tue2"></td>
									<td><input type="checkbox" name="timetable" value="wed2"></td>
									<td><input type="checkbox" name="timetable" value="thu2"></td>
									<td><input type="checkbox" name="timetable" value="fri2"></td>
								</tr>
								<tr>
									<th scope="row">12:00~13:15</th>
									<td><input type="checkbox" name="timetable" value="mon3"></td>
									<td><input type="checkbox" name="timetable" value="tue3"></td>
									<td><input type="checkbox" name="timetable" value="wed3"></td>
									<td><input type="checkbox" name="timetable" value="thu3"></td>
									<td><input type="checkbox" name="timetable" value="fri3"></td>
								</tr>
								<tr>
									<th scope="row">13:30~14:45</th>
									<td><input type="checkbox" name="timetable" value="mon4"></td>
									<td><input type="checkbox" name="timetable" value="tue4"></td>
									<td><input type="checkbox" name="timetable" value="wed4"></td>
									<td><input type="checkbox" name="timetable" value="thu4"></td>
									<td><input type="checkbox" name="timetable" value="fri4"></td>
								</tr>
								<tr>
									<th scope="row">15:00~16:15</th>
									<td><input type="checkbox" name="timetable" value="mon5"></td>
									<td><input type="checkbox" name="timetable" value="tue5"></td>
									<td><input type="checkbox" name="timetable" value="wed5"></td>
									<td><input type="checkbox" name="timetable" value="thu5"></td>
									<td><input type="checkbox" name="timetable" value="fri5"></td>
								</tr>
								<tr>
									<th scope="row">16:30~17:45</th>
									<td><input type="checkbox" name="timetable" value="mon6"></td>
									<td><input type="checkbox" name="timetable" value="tue6"></td>
									<td><input type="checkbox" name="timetable" value="wed6"></td>
									<td><input type="checkbox" name="timetable" value="thu6"></td>
									<td><input type="checkbox" name="timetable" value="fri6"></td>
								</tr>
								<tr>
									<th scope="row">18:00~19:00</th>
									<td><input type="checkbox" name="timetable" value="mon7"></td>
									<td><input type="checkbox" name="timetable" value="tue7"></td>
									<td><input type="checkbox" name="timetable" value="wed7"></td>
									<td><input type="checkbox" name="timetable" value="thu7"></td>
									<td><input type="checkbox" name="timetable" value="fri7"></td>
								</tr>
								<tr>
									<th scope="row">19:00~20:00</th>
									<td><input type="checkbox" name="timetable" value="mon8"></td>
									<td><input type="checkbox" name="timetable" value="tue8"></td>
									<td><input type="checkbox" name="timetable" value="wed8"></td>
									<td><input type="checkbox" name="timetable" value="thu8"></td>
									<td><input type="checkbox" name="timetable" value="fri8"></td>
								</tr>
								<tr>
									<th scope="row">20:00~21:00</th>
									<td><input type="checkbox" name="timetable" value="mon9"></td>
									<td><input type="checkbox" name="timetable" value="tue9"></td>
									<td><input type="checkbox" name="timetable" value="wed9"></td>
									<td><input type="checkbox" name="timetable" value="thu9"></td>
									<td><input type="checkbox" name="timetable" value="fri9"></td>
								</tr>


							</tbody>
						</table>
					</div>
					<div class="col-md-12">
						<button id="submitform"
							class="btn btn-primary btn-lg col-md-offset-11">저장</button>
					</div>

>>>>>>> branch 'master' of https://github.com/choiyk/smProject.git
				</div>
			</div>

			<!-- END row -->

			<div class="fh5co-spacer fh5co-spacer-md"></div>
			<!-- End Spacer -->
			<div class="fh5co-spacer fh5co-spacer-md"></div>

		</div>
		<!-- END container -->


	</div>
	<!-- END fhtco-main -->
</div>