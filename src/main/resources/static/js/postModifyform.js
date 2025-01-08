$(document).ready(function () {
    // // CSRF 설정: 모든 AJAX 요청에 CSRF 토큰 추가
    // $.ajaxSetup({
    //     beforeSend: function (xhr) {
    //         const csrfToken = $("meta[name='_csrf']").attr("content");
    //         const csrfHeader = $("meta[name='_csrf_header']").attr("content");
    //         if (csrfToken && csrfHeader) {
    //             xhr.setRequestHeader(csrfHeader, csrfToken);
    //         }
    //     }
    // });

// 폼 제출 이벤트 처리
    $("form[name=modifyform]").submit(function (event) {

        //
        // event.preventDefault(); // 기본 폼 제출 중단
        //
        // const formData = new FormData(this); // 폼 데이터 객체 생성
        // $('button[type="submit"]').prop('disabled', true); // 중복 제출 방지
        //
        // // AJAX 요청으로 폼 데이터 전송
        // $.ajax({
        //     url: '/Shoots/post/modifyAction', // 서버 URL (Thymeleaf 경로 확인)
        //     type: 'POST',
        //     data: formData,
        //     contentType: false, // 파일 데이터 전송 시 필요
        //     processData: false, // 자동 처리 비활성화
        //     success: function (response) {
        //         if (response.success) {
        //             alert("게시글이 수정되었습니다.");
        //             location.href = '/Shoots/post/list'; // 성공 시 리스트 페이지로 이동
        //         } else {
        //             alert("게시글 수정에 실패했습니다. 다시 시도해 주세요.");
        //             location.href = '/Shoots/post/modify?num=' + response.post_idx; // 수정 페이지로 이동
        //         }
        //     },
        //     error: function (xhr, status, error) {
        //         alert("서버 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.");
        //         console.error("상태: " + status);
        //         console.error("에러: " + error);
        //     },
        //     complete: function () {
        //         $('button[type="submit"]').prop('disabled', false); // 버튼 다시 활성화
        //     }
        // });

    });



    // 파일 선택 시 파일명을 표시
    $("#upfile").change(function () {
        const inputFile = $(this).val().split('\\'); // 파일 경로 분리
        $('#filevalue').text(inputFile[inputFile.length - 1]); // 파일명만 표시
        show();
        $('input[name="remove_file"]').val('false'); // 삭제 플래그 초기화
    });


    // // 첨부파일이 있을(없을) 경우 remove 이미지가 보이도록 설정
    // function show() {
    //     const fileExists = $('#filevalue').text() ? true : false;
    //     $('.remove').css('display', fileExists ? 'inline-block' : 'none')
    //         .css({ 'position': 'relative', 'top': '-5px' });
    // }

    function show() {
        //파일 이름이 있는 경우 remove 이미지를 보이게 하고
        //파일 이름이 없는 경우 remove 이미지 보이지 않게 합니다.
        $('.remove').css('display', $('#filevalue').text() ? 'inline-block' : 'none')
            .css({'position' : 'relative', 'top': '-5px'});
    }


    // 초기 실행 시 remove 이미지 세팅
    show();

    // 첨부파일 제거 버튼 클릭 시 처리
    $('.remove').click(function () {
        $('#filevalue').text(''); // 첨부파일 표시 내용 제거
        $('#upfile').val('');    // 파일 입력 필드 초기화
        $(this).css('display', 'none'); // 삭제 버튼 숨기기
        $('input[name="remove_file"]').val('true'); // 파일 삭제 플래그 설정
    });



});  //ready end




