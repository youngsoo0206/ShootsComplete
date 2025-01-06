window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }


    $('#postButton').on('click', function (e) {
        e.preventDefault(); // 기본 링크 동작 막기

        // AJAX 요청
        $.ajax({
            url: '/business/post', // 서버에서 제공하는 businessPost.html 경로
            method: 'GET',
            success: function (data) {
                // 성공적으로 로드된 HTML을 #layoutSidenav_content 영역에 삽입
                $('#layoutSidenav_content').html(data);
            },
            error: function (xhr, status, error) {
                // 오류 발생 시 처리
                console.log('Error loading content: ', error);
            }
        });
    });
});
