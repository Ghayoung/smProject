<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="R" value="/" />
<div id="fh5co-main">

	<div class="container">

		<div class="row">
			<div class="col-md-12 animate-box">
				<h2 class="fh5co-uppercase-heading-sm text-center" id="com">${ board }</h2>
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
						<a href="${R}user/file/download?id=${ article.art_f_id }">첨부된
							파일 <img src="${R}images\file.png" border="0">
						</a>
					</c:if>
				</div>
				<c:if test="${ article.art_u_id == user }">
					<div class="col-md-12 col-r" style="margin-bottom: 10px;">
						<a
							href="${R}user/board_edit?id=${article.id}&${ pagination.queryString }"
							class="btn btn-primary btn-lg">수정</a> <a
							href="${R}user/board_delete?id=${article.id}&${ pagination.queryString }"
							class="btn btn-primary btn-lg">삭제</a>
					</div>
				</c:if>
				<div class="col-md-12 col-r">
					<a href="${R}user/board?${ pagination.queryString }"
						class="btn btn-primary btn-lg">목록으로</a>
				</div>
			</div>

		</div>
		<!-- END row -->
		<div class="fh5co-spacer fh5co-spacer-sm"></div>

		<!--댓글-->
		<div class="col-md-12">
			<h2 id="comment_btn" class="fh5co-uppercase-heading-sm">댓글 ${ comments.size() }개
				∨</h2>
		</div>
		<div id="comment" style="display: none;">
			<div class="row">
				<div class="col-md-12 animate-box">
					<div class="col-md-12 panel panel-default">
						<c:forEach var="comment" items="${ comments }" varStatus="status">
							<div class="comment">
								<ul>
									<li>${ comment.name }</li>
									<li id="c_content">${ comment.c_post_date }</li>
									<c:if test="${ comment.com_u_id == user }">
										<li><a class="ti-close"
											href="${R}user/comment_delete?id=${article.id}&${ pagination.queryString }&cid=${ comment.id }"></a></li>
										<li style="margin-right: 5px;"><a class="ti-pencil"
											onclick="comment_edit(${ status.count });"></a></li>
									</c:if>
								</ul>
								<div class="c_content_${ status.count }">${ comment.c_content }</div>
								<div class="c_content_edit_${ status.count } col-md-12"
									style="display: none; margin-top: 10px">
									<form method="post"
										action="${R}user/comment_edit?id=${article.id}&${ pagination.queryString }&cid=${ comment.id }">
										<div class="col-md-10 form-group">
											<textarea name="c_content" id="board_comment"
												class="form-control input-lg" row="3">${ comment.c_content }</textarea>
										</div>
										<div class="col-md-2 form-group">
											<!-- -->
											<button class="btn btn-primary btn-lg"
												onclick="editCommentText(${R}user/comment_edit_ajax?id=${article.id}&${ pagination.queryString }&cid=${ comment.id })">등록</button>
										</div>
									</form>

								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="fh5co-spacer fh5co-spacer-sm"></div>
			<div class="row">
				<div class="col-md-12 animate-box">
					<form:form id="newCommentForm" method="post"
						modelAttribute="newComment"
						action="${R}user/comment_create?id=${article.id}&${ pagination.queryString }">
						<div class="col-md-10">
							<div class="form-group">
								<form:textarea path="c_content" placeholder="댓글을 작성해주세요."
									class="form-control input-lg" rows="3"></form:textarea>
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<input type="submit" class="btn btn-primary btn-lg" value="등록">
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>

		<div class="fh5co-spacer fh5co-spacer-sm"></div>



	</div>
	<!-- END container -->


</div>
<!-- END fhtco-main -->

<script>
function editCommentText(url){
	$.ajax({
	    type : "POST",
	    url : url,
	    dataType : "json",
	    error : function() {
	        alert('통신실패!!');
	    },
	    success : function(json) {
	        $('#board_comment').html(json);
	        alert(json);
	    }

	});
}

</script>