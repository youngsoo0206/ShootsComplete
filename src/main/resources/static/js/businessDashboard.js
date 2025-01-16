window.addEventListener('DOMContentLoaded', event => {

    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }


    $('#MatchPost').on('click', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/Shoots/business/post',
            method: 'GET',
            success: function (data) {
                $('#layoutSidenav_content').html(data);
            },
            error: function (xhr, status, error) {
                console.log('Error loading content: ', error);
            }
        });
    });

    $('#Sales').on('click', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/Shoots/business/sales',
            method: 'GET',
            success: function (data) {
                $('#layoutSidenav_content').html(data);
            },
            error: function (xhr, status, error) {
                console.log('Error loading content: ', error);
            }
        });
    });

    $('#MatchParticipants').on('click', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/Shoots/business/MatchParticipants',
            method: 'GET',
            success: function (data) {
                $('#layoutSidenav_content').html(data);
            },
            error: function (xhr, status, error) {
                console.log('Error loading content: ', error);
            }
        });
    });

    $('#CustomerList').on('click', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/Shoots/business/customerList',
            method: 'GET',
            success: function (data) {
                $('#layoutSidenav_content').html(data);
            },
            error: function (xhr, status, error) {
                console.log('Error loading content: ', error);
            }
        });
    });

    $('#BlackList').on('click', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/Shoots/business/blacklist',
            method: 'GET',
            success: function (data) {
                $('#layoutSidenav_content').html(data);
            },
            error: function (xhr, status, error) {
                console.log('Error loading content: ', error);
            }
        });
    });

    $('#Settings').on('click', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/Shoots/business/Settings',
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

