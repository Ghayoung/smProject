<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="R" value="/" />	
		<div id="fh5co-main">
	
			<div class="container">
			
				<div class="row">
					<div class="col-md-12 animate-box">
						<h2 class="fh5co-uppercase-heading-sm text-center">${ board }</h2>
						<div class="fh5co-spacer fh5co-spacer-sm"></div>
					</div>
					<div class="col-md-12 animate-box board">
						<div class="col-md-12">
								<div class="panel panel-default">${ article.title }</div>
						</div>
						
						<div class="col-md-12">
								<div class="panel panel-default">
									<div class="row">
										<div class="col-md-12">
										<p>${ article.content }</p>
										</div>
									</div>
					
								<!-- END row -->
								<div class="fh5co-spacer fh5co-spacer-md"></div>
								</div>
						</div>
						
						<div class="col-md-2">
							<c:if test="${ article.art_f_id != 0 }">
								<a href="${R}user/file/download?id=${ article.art_f_id }">첨부된 파일 <img src="${R}images\file.png" border="0"></a>
							</c:if>
								<div class="fh5co-spacer fh5co-spacer-md"></div>
						</div>
						<c:if test="${ article.art_u_id == user }">
							<div class="col-md-12 col-r" style="margin-bottom:10px;">
								<a href="${R}user/board_edit?id=${article.id}&${ pagination.queryString }" class="btn btn-primary btn-lg">수정</a>
								<a href="${R}user/board_delete?id=${article.id}&${ pagination.queryString }" class="btn btn-primary btn-lg">삭제</a>
							</div>
						</c:if>
						<div class="col-md-12 col-r">
							<a href="${R}user/board?${ pagination.queryString }" class="btn btn-primary btn-lg">목록으로</a>
						</div>
					</div>
					
				</div>
				<!-- END row -->
				<div class="fh5co-spacer fh5co-spacer-sm"></div>
				<!--댓글-->
				<div class="col-md-12 animate-box">
					<h2 id="comment_btn" class="fh5co-uppercase-heading-sm">댓글 2개 ∨</h2>
				</div>
				<div id="comment" style="display:none;">
				<div class="row">
					<div class="col-md-12 animate-box">
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="comment">
									<ul>
										<li>작성자</li>
										<li>2017-09-19</li>
										<li><a class="ti-close"></a></li>
										<li style="margin-right:5px;"><a class="ti-pencil"></a></li>
									</ul>
									<p>댓글 내용입니다.</p>
								</div>
								<div class="comment">
									<ul>
										<li>작성자</li>
										<li>2017-09-19</li>
									</ul>
									<p>댓글 내용입니다.</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="fh5co-spacer fh5co-spacer-sm"></div>
				<div class="row">
					<div class="col-md-12 animate-box">
						<form action="#" method="post">
							<div class="col-md-10">
								<div class="form-group">
									<textarea placeholder="댓글을 작성해주세요." id="board_comment" class="form-control input-lg" rows="3"></textarea>
								</div>	
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<input type="submit" class="btn btn-primary btn-lg" value="등록">
								</div>	
							</div>
						</form>	
					</div>
				</div>
				</div>

				<div class="fh5co-spacer fh5co-spacer-sm"></div>



			</div>
			<!-- END container -->

		
		</div>
		<!-- END fhtco-main -->