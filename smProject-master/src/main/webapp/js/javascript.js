function selectAllCheckBox(allcheck, containerID, checkboxIDMatch){ // (all체크박스,
	// 그룹ID,
	// 체크박스ID)
	var checkboxContainer = document.getElementById(containerID);
	var inputs = checkboxContainer.getElementsByTagName('input') // 그룹에서
	// input태그
	// elements만
	// 가져오는
	// 코드
	for(var i = 1; i < inputs.length; i++){
		var item = inputs.item(i);
		if (item.id.indexOf(checkboxIDMatch) > -1){
			item.checked = allcheck.checked; // input태그 elements 중
			// 체크박스ID를 가진 것만 상태 변경
		}

	}
};

var submitcheck = true; // 중복신청 방지

window.onload = function(){
	var btns = document.getElementsByClassName("submitbtn"); // 신청 버튼의 집합
	for(var i=0; i<btns.length; i++){
		var btn = btns.item(i);
		btn.onclick = function() {
			if(this.value=="신청" && submitcheck){
				alert("신청되었습니다");// 확인용 임시코드
				submitcheck = false;
				this.value="신청취소";
				this.className = "btn btn-cancel btn-lg submitbtn";
			}
			else if(this.value=="신청취소"){
				alert("취소되었습니다");// 확인용 임시코드
				submitcheck = true;
				this.value="신청";
				this.className = "btn btn-primary btn-lg submitbtn";
			}
			else{
				alert("이미 신청했습니다");// 확인용 임시코드
			}
		};
	}
};

function selectAllCheckBox(allcheck, containerID, checkboxIDMatch){ // (all체크박스,
	// 그룹ID,
	// 체크박스ID)
	var checkboxContainer = document.getElementById(containerID);
	var inputs = checkboxContainer.getElementsByTagName('input') // 그룹에서
	// input태그
	// elements만
	// 가져오는
	// 코드
	for(var i = 1; i < inputs.length; i++){
		var item = inputs.item(i);
		if (item.id.indexOf(checkboxIDMatch) > -1){
			item.checked = allcheck.checked; // input태그 elements 중
			// 체크박스ID를 가진 것만 상태 변경
		}

	}
}


document.getElementById('datePicker').valueAsDate = new Date();
document.getElementById('datePicker2').valueAsDate = new Date();
document.getElementById('datePicker3').valueAsDate = new Date();
document.getElementById('datePicker4').valueAsDate = new Date();

function checkMentorApply(){
	if(document.form.group_name.value==""){//이름이 입력하지 않았으면
		alert("그룹 이름을 입력해주세요");
		document.form.group_name.focus();
		return false;
	}else if(document.form.subject.value==""){
		alert("주제를 입력해주세요");
		document.form.subject.focus();
		return false;
	}else if(document.form.year.value==""){
		alert("학년을 입력해주세요");
		document.form.year.focus();
		return false;
	}else if(document.form.grade.value==""){
		alert("해당 과목 성적을 입력해주세요");
		document.form.grade.focus();
		return false;
	}else if(document.form.count.value==""){
		alert("희망 멘티 인원을 입력해주세요");
		document.form.count.focus();
		return false;
	}else if(document.form.study_purpose.value==""){
		alert("스터디 목적을 입력해주세요");
		document.form.study_purpose.focus();
		return false;
	}else if(document.form.study_content.value==""){
		alert("스터디 내용을 입력해주세요");
		document.form.study_content.focus();
		return false;
	}else if(document.form.study_method.value==""){
		alert("스터디 방법을 입력해주세요");
		document.form.study_method.focus();
		return false;
	}else if(document.form.file1.value==""){
		alert("시간표 파일을 등록해주세요");
		document.form.file1.focus();
		return false;
	}else if(document.form.file2.value==""){
		alert("홍보 이미지를 등록해주세요");
		document.form.file2.focus();
		return false;
	}else if(document.form.file3.value==""){
		alert("증빙서류를 등록해주세요");
		document.form.file3.focus();
		return false;
	}else{//
		form.submit();
	}
}



