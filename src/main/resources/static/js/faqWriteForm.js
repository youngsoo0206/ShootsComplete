$(document).ready(function(){
	
	/*
	헤더의
	<span id="loginid">${pinfo.username}</span>님(로그아웃)
	값을 글쓴이 값으로 설정합니다
	*/
	//$("#board_name").val($("#loginid").text());
	
	$("#upfile").change(function(){
		console.log($(this).val())	//c:\fakepath\upload.png
		const inputfile = $(this).val().split('\\');
		$('#filevalue').text(inputfile[inputfile.length - 1]);
	});
	
	//submit 버튼 클릭할 때 이벤트 부분
	$("form[name=faqform]").submit(function(){
		const $faqTitle = $("#title");
		if($faqTitle.val().trim() == ""){
			alert("제목을 입력하세요");
			$faqTitle.focus();
			return false;
		}
		
		const $faqContent = $("#content");
		if($faqContent.val().trim() == ""){
			alert("내용을 입력하세요");
			$faqContent.focus();
			return false;
		}
		
	})//submit end
	
})//ready end