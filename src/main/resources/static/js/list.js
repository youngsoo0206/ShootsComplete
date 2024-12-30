	// 페이지가 로드될 때 자유게시판(A)의 게시글을 불러오는 부분을 추가합니다.
	$(document).ready(function() {
	    // 페이지가 로드되면 자동으로 자유게시판(A)로 게시글 목록을 불러오기
	    switchCategory('A');
	});


        // 카테고리 변경 시 게시글 목록을 비동기적으로 불러오는 함수
        function switchCategory(category) {
		            $.ajax({
		                url: 'list',  // 서블릿 URL
		                type: 'GET',
		                data: {
		                    category: category,  // 카테고리 정보 전송
		                    state: 'ajax',  // AJAX 요청임을 알려주는 파라미터
		                    page: 1,  // 기본적으로 첫 페이지
		                    limit: 10  // 한 페이지당 10개씩 게시글을 표시
		                },
		                dataType : 'json',
		                success: function(response) {
							// 응답 데이터 확인
							console.log(response);
							
		                    // 서버로부터 받은 JSON 응답 처리
		                    updatePostList(response);
		                    
		                    
		                },
		                error: function(xhr, status, error) {
		                    console.error("AJAX 요청 실패: " + error);
		                }
		            });
		            currentCategory = category; // 선택된 카테고리 저장
    go(1); // 첫 페이지로 이동하여 게시글 목록 요청
        }

        // 서버에서 받은 응답 데이터를 바탕으로 게시글 목록을 갱신하는 함수
        function updatePostList(data) {
            var category = data.category;
            var postList = data.postlist;
            var page = data.page;
            var maxPage = data.maxpage;
            var startPage = data.startpage;
            var endPage = data.endpage;
            var listCount = data.listcount;

            // 게시글 목록 테이블 갱신
            var tableBody = $('table tbody');
            tableBody.empty();  // 기존 내용 비우기
            
            
            // 새로 받은 게시글 목록을 테이블에 추가
            postList.forEach(function(post) {
                var row = $('<tr>');
                row.append('<td>' + post.post_id + '</td>');
                
                // 중고게시판(카테고리B)의 경우 -> 파일첨부(미리보기),가격 추가
                if (category === 'B') {
		        	// 게시글에 첨부파일이 있을 경우 이미지 미리보기 표시
			        if (post.post_file) {
			            // 이미지를 미리보기로 표시 //uploads/
		            	const pathname = "/" + window.location.pathname.split("/")[1] + "/";
						const origin = window.location.origin;
						const contextPath = origin + pathname;
						
			            var imageUrl = contextPath + 'postupload/' + post.post_file;  // 파일이 저장된 경로
			            //console.log('이미지 경로:', imageUrl); //콘솔로 확인
			            
			            // 파일 경로에 특수문자가 포함되어 있을 경우 인코딩 처리
	           		 	imageUrl = encodeURIComponent(imageUrl);
			            
			            // 이미지를 미리보기로 표시
			            var imgPreview = $('<img>').attr('src', decodeURIComponent(imageUrl))
										           .attr('alt', '.')
										           .css({'min-width':'150px','min-height': '150px','max-width':'150px','max-height': '150px', 'object-fit': 'cover'});
			            // 이미지 태그를 테이블 셀에 추가
			            row.append('<td class = "jtdI">' + imgPreview[0].outerHTML + '</td>');
			        }
		            
		        else 
		            row.append('<td></td>'); // 첨부파일이 없으면 빈 칸
                }
                
               
                
                
                // 중고게시판(카테고리B)의 경우 -> 파일첨부(미리보기),가격 추가
                // 카테고리 B일 경우 추가로 가격 표시
                if (category === 'B') {
		        	 row.append('<td class = "jtdt"><a href="detail?num=' + post.post_id + '">' 
                + (post.title.length > 20 ? post.title.substring(0, 20) + '...' : post.title) 
                + '&nbsp;&nbsp;'  + '<span style="color: orange;">[' + post.commentCount + ']</span>' + '</a>' + '<br>' + '<br>' + post.price + '원</td>');
		        
		        row.append('<td class = "jtdw">' + post.user_id + '</td>');
                row.append('<td class = "jtdr">' + post.register_date + '</td>');
                row.append('<td class = "jtdc">' + post.readcount + '</td>');
		        
                } else {
					 row.append('<td><a href="detail?num=' + post.post_id + '">' 
                + (post.title.length > 20 ? post.title.substring(0, 20) + '...' : post.title) 
                + '&nbsp;&nbsp;'  + '<span style="color: orange;">[' + post.commentCount + ']</span>' + '</a></td>');
                
                row.append('<td>' + post.user_id + '</td>');
                row.append('<td>' + post.register_date + '</td>');
                row.append('<td>' + post.readcount + '</td>');
				}
                
                row.append('</tr>');
                tableBody.append(row);
                
            });
            
            /*
            

            // 페이지 네비게이션 갱신 (예시: 페이지 번호 표시)
            var pagination = $('#pagination');
            pagination.empty();  // 기존 페이지네이션 비우기

            // 페이지 번호 추가 (예시: 페이지 1, 2, 3, ...)
            for (var i = startPage; i <= endPage && i <= maxPage; i++) {
                var pageLink = $('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="switchPage(' + i + ')">' + i + '</a></li>');
                pagination.append(pageLink);
            }
        

        // 게시판 클릭 시 게시판 이동하는 함수 (페이지 이동)
        function switchPage(pageNumber) {
            var category = $('input[name="category"]:checked').val();  // 선택된 카테고리
            switchCategory(category, pageNumber); 
        }
        
       */
        }
		
		/*// 글쓰기 버튼 클릭 시 카테고리와 함께 '글쓰기' 페이지로 이동
		function postWrite() {
		    var category = $('#categoryTab .nav-link.active').attr('id').replace('tab', '');  // 선택된 카테고리
		    location.href = "write?category=" + category;  // 카테고리 파라미터를 함께 전달
		} */
        
        
        // 글쓰기 버튼 클릭 시 카테고리와 함께 '글쓰기' 페이지로 이동
        function postWrite() {
            location.href = "write";  // 카테고리 파라미터를 함께 전달
        }
        
		function postWriteN() {
			alert("로그인 후 이용 가능합니다.")
			location.href = "../user/login";
		} 
        
        
        /* // 글쓰기 버튼 클릭 시 카테고리와 함께 '글쓰기' 페이지로 이동
        function postWrite() {
            var category = $('input[name="category"]:checked').val();  // 선택된 카테고리
            location.href = "write?category=" + category;  // 카테고리 파라미터를 함께 전달
        }*/
        
        
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

    $.ajax({
        data: sdata,
        url: "/Shoots/post/list",
        dataType: "json",
        cache: false,
        success: function(data) {
            console.log(data);
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
        error: function() {
            console.log("에러");
            $("thead").hide();
            $(".pagination").empty();
            $("table").append("<tbody><tr><td colspan='5' style='text-align: center;'>데이터를 불러오는 중 오류가 발생했습니다. 다시 시도해주세요.</td></tr></tbody>");
        },
        complete: function() {
            isRequestInProgress = false;
        }
    });
}

