window.addEventListener('DOMContentLoaded', event => {

    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }


    $('#Users').on('click', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/Shoots/testAdmin/userList',
            method: 'GET',
            success: function (data) {
                $('#layoutSidenav_content').html(data);
            },
            error: function (xhr, status, error) {
                console.log('Error loading content: ', error);
            }
        });
    });

    $('#BusinessStatus').on('click', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/Shoots/testAdmin/businessList',
            method: 'GET',
            success: function (data) {
                $('#layoutSidenav_content').html(data);
            },
            error: function (xhr, status, error) {
                console.log('Error loading content: ', error);
            }
        });
    });

    $('#BusinessApprove').on('click', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/Shoots/testAdmin/businessApprovedList',
            method: 'GET',
            success: function (data) {
                $('#layoutSidenav_content').html(data);
            },
            error: function (xhr, status, error) {
                console.log('Error loading content: ', error);
            }
        });
    });

    $('#Post').on('click', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/Shoots/testAdmin/postList',
            method: 'GET',
            success: function (data) {
                $('#layoutSidenav_content').html(data);
            },
            error: function (xhr, status, error) {
                console.log('Error loading content: ', error);
            }
        });
    });

    $('#Faq').on('click', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/Shoots/testAdmin/faqList',
            method: 'GET',
            success: function (data) {
                $('#layoutSidenav_content').html(data);
            },
            error: function (xhr, status, error) {
                console.log('Error loading content: ', error);
            }
        });
    });

    $('#Notice').on('click', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/Shoots/testAdmin/noticeList',
            method: 'GET',
            success: function (data) {
                $('#layoutSidenav_content').html(data);
            },
            error: function (xhr, status, error) {
                console.log('Error loading content: ', error);
            }
        });
    });


});

