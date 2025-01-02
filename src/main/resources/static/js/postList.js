let isRequestInProgress = false;

// 현재 선택된 카테고리 (기본값: A)
let currentCategory = 'A';

// 페이지 이동 요청 함수
function go(page) {
    if (isRequestInProgress) return;

    const limit = 10;
    const data = {
        limit: limit,
        state: "ajax",
        page: page,
        category: currentCategory // 현재 선택된 카테고리 추가
    };
    ajax(data);
}

// 페이징 버튼 생성 함수
function setPaging(href, digit, isActive = false) {
    const gray = (href === "" && isNaN(digit)) ? "gray" : "";
    const active = isActive ? "active" : "";
    const anchor = `<a class="page-link ${gray}" ${href}>${digit}</a>`;
    return `<li class="page-item ${active}">${anchor}</li>`;
}

// 페이지네이션 생성 함수
function generatePagination(data) {
    let output = "";

    // 이전 버튼
    let prevHref = data.page > 1 ? `href=javascript:go(${data.page - 1})` : "";
    output += setPaging(prevHref, '&lt;&lt;');

    // 중간 페이지 번호
    for (let i = data.startpage; i <= data.endpage; i++) {
        const isActive = (i === data.page);
        let pageHref = !isActive ? `href=javascript:go(${i})` : "";
        output += setPaging(pageHref, i, isActive);
    }

    // 다음 버튼
    let nextHref = data.page < data.maxpage ? `href=javascript:go(${data.page + 1})` : "";
    output += setPaging(nextHref, '&gt;&gt;');

    $(".pagination").empty().append(output);
}

// 게시글 목록 요청 및 업데이트 함수
function ajax(sdata) {
    console.log(sdata);

    isRequestInProgress = true;
    // let token = $("meta[name='_csrf']").attr("content");
    // let header = $("meta[name='_csrf_header']").attr("content");

    $.ajax({

        data: sdata,
        url: "/Shoots/post/list/ajax",  // URL은 Spring Boot 컨텍스트 경로에 맞게 수정 필요
        dataType: "json",
        cache: false,
        // beforeSend : function(xhr)
        // {   //데이터를 전송하기 전에 헤더에 csrf값을 설정합니다.
        //     xhr.setRequestHeader(header, token);
        // },
        success: function(data) {
            console.log(data);


            // 기존 tbody 내용 제거
            $("table tbody").remove();

            if (data.listcount > 0) {
                $("thead").show();
                updatePostList(data); // 게시글 목록 업데이트
                generatePagination(data); // 페이지네이션 업데이트
            } else {
                $("thead").hide();
                $(".pagination").empty();
                $("table").append("<tbody><tr><td colspan='5' style='text-align: center;'>게시글이 존재하지 않습니다</td></tr></tbody>");
            }
        },
        error: function(xhr, status, error) {
            console.log("에러", error);
            console.log("응답 상태:", xhr.status);  // HTTP 상태 코드 확인
            console.log("응답 내용:", xhr.responseText);  // 응답 내용 확인
            $("thead").hide();
            $(".pagination").empty();
            $("table").append("<tbody><tr><td colspan='5' style='text-align: center;'>데이터를 불러오는 중 오류가 발생했습니다. 다시 시도해주세요.</td></tr></tbody>");
        },
        complete: function() {
            isRequestInProgress = false;
        }
    });
}

// 게시글 목록 업데이트 함수
function updatePostList(data) {
    var category = data.category;
    var postList = data.postlist;

    //num 추가 @@@@
    let num = data.listcount - (data.page - 1) * data.limit;

    var tableBody = $('#' + (category === 'A' ? 'postListA' : 'postListB'));
    tableBody.empty();  // 기존 내용 비우기

    // 새로 받은 게시글 목록을 테이블에 추가
    postList.forEach(function(post) {
        var row = $('<tr>');
        //row.append('<td>' + post.post_idx + '</td>');
        row.append('<td>' + num-- + '</td>'); // num으로 대체 @@@@

        // 중고게시판(카테고리B)의 경우 -> 파일첨부(미리보기),가격 추가
        if (category === 'B') {
            // 게시글에 첨부파일이 있을 경우 이미지 미리보기 표시
            if (post.post_file) {
                const pathname = "/" + window.location.pathname.split("/")[1] + "/";
                const origin = window.location.origin;
                const contextPath = origin + pathname;

                var imageUrl = contextPath + 'postupload/' + post.post_file;  // 파일이 저장된 경로
                imageUrl = encodeURIComponent(imageUrl);

                var imgPreview = $('<img>').attr('src', decodeURIComponent(imageUrl))
                    .attr('alt', '.')
                    .css({'min-width':'150px','min-height': '150px','max-width':'150px','max-height': '150px', 'object-fit': 'cover'});
                row.append('<td class = "jtdI">' + imgPreview[0].outerHTML + '</td>');
            } else {
                row.append('<td></td>'); // 첨부파일이 없으면 빈 칸
            }

            row.append('<td class = "jtdt"><a href="detail?num=' + post.post_idx + '">'
                + (post.title.length > 20 ? post.title.substring(0, 20) + '...' : post.title)
                + '&nbsp;&nbsp;'  + '<span style="color: orange;">[' + post.commentCount + ']</span>' + '</a>' + '<br>' + '<br>' + post.price + '원</td>');
            row.append('<td class = "jtdw">' + post.user_idx + '</td>');
            row.append('<td class = "jtdr">' + post.register_date + '</td>');
            row.append('<td class = "jtdc">' + post.readcount + '</td>');
        } else {
            row.append('<td><a href="detail?num=' + post.post_idx + '">'
                + (post.title.length > 20 ? post.title.substring(0, 20) + '...' : post.title)
                + '&nbsp;&nbsp;'  + '<span style="color: orange;">[' + post.commentCount + ']</span>' + '</a></td>');
            row.append('<td>' + post.user_idx + '</td>');
            row.append('<td>' + post.register_date + '</td>');
            row.append('<td>' + post.readcount + '</td>');
        }

        row.append('</tr>');
        tableBody.append(row);
    });
}

// 페이지가 로드될 때 자유게시판(A)의 게시글을 불러오는 부분
$(document).ready(function() {
    switchCategory('A'); // 자유게시판(A)로 초기 로딩
});

// 카테고리 변경 시 게시글 목록을 비동기적으로 불러오는 함수
function switchCategory(category) {

    // 기존 활성화 상태 초기화
    $(".nav-link").removeClass("active");

    // 클릭된 탭에 활성화 상태 추가
    $(`#tab${category}`).addClass("active");

    $.ajax({
        url: '/Shoots/post/list/ajax',
        type: 'GET',
        data: {
            category: category,
            state: 'ajax',
            page: 1,
            limit: 10
        },
        dataType : 'json',
        success: function(response) {
            // 서버로부터 받은 JSON 응답 처리
            updatePostList(response);
            currentCategory = category; // 선택된 카테고리 저장
            go(1); // 첫 페이지로 이동하여 게시글 목록 요청
        },
        error: function(xhr, status, error) {
            console.error("AJAX 요청 실패: " + error);
            alert("게시글 목록을 불러오는 데 실패했습니다. 다시 시도해주세요.");
        }
    });
}

// 글쓰기 버튼 클릭 시 '글쓰기' 페이지로 이동
function postWrite() {
    location.href = "write";  // 카테고리 파라미터를 함께 전달
}

// 로그인이 필요한 경우 글쓰기 버튼 클릭 시
function postWriteN() {
    alert("로그인 후 이용 가능합니다.");
    location.href = "../user/login";
}
