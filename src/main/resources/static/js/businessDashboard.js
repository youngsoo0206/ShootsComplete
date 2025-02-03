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

    $('#Inquiry').on('click', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/Shoots/business/inquiry',
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



    const urlParams = new URLSearchParams(window.location.search);
    const selectedTab = urlParams.get('tab');

    if (selectedTab) {
        if (selectedTab === "blackList") {
            $("#BlackList").trigger("click");
        }
    }

    if (selectedTab) {
        if (selectedTab === "customerList") {
            $("#CustomerList").trigger("click");
        }
    }

    if (selectedTab) {
        if (selectedTab === "Settings") {
            $("#Settings").trigger("click");
        }
    }

    if (selectedTab) {
        if (selectedTab === "matchPost") {
            $("#MatchPost").trigger("click");
        }
    }

    if (selectedTab) {
        if (selectedTab === "inquiry") {
            $("#Inquiry").trigger("click");
        }
    }


    var ctx = document.getElementById("myAreaChart").getContext("2d");
    var myChart = new Chart(ctx, {
        type: "line",
        data: {
            labels: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월",],
            datasets: [{
                label: "신청 인원",
                data: window.monthlyData,
                borderColor: "rgba(75, 192, 192, 1)",
                backgroundColor: "rgba(75, 192, 192, 0.2)",
                borderWidth: 2,
                fill: true
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
});

