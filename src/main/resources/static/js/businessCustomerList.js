$(function () {

    $("#allCustomer").click(function () {

        $("#user_gender").val('');
        $("#user_age").val('');

        fetch('/Shoots/business/customerList')  // 전체 리스트를 가져오는 요청
            .then(response => response.text())
            .then(data => {
                const parser = new DOMParser();
                const doc = parser.parseFromString(data, 'text/html');
                const newTable = doc.querySelector('#customerListContainer table');
                const customerListContainer = document.getElementById('customerListContainer');

                customerListContainer.innerHTML = '';

                if (newTable && newTable.querySelector('tbody').children.length > 0) {
                    customerListContainer.appendChild(newTable);
                    document.querySelector('caption').textContent = "전체 고객 리스트";
                } else {
                    customerListContainer.innerHTML = '<div style="text-align: center; margin: 100px 0 100px 0"><p> 고객리스트가 존재하지 않습니다 </p></div>';
                }
            })
            .catch(error => {
                console.error('Error loading all matches:', error);
            });
    });

    $("#vipBtn").click(function () {

        $("#user_gender").val('');
        $("#user_age").val('');

        fetch('/Shoots/business/customerList?vip=true')
            .then(response => response.text())
            .then(data => {
                const parser = new DOMParser();
                const doc = parser.parseFromString(data, 'text/html');
                const newTable = doc.querySelector('#customerListContainer table');
                const customerListContainer = document.getElementById('customerListContainer');

                customerListContainer.innerHTML = '';

                if (newTable && newTable.querySelector('tbody').children.length > 0) {
                    customerListContainer.appendChild(newTable);
                    document.querySelector('caption').textContent = "참여 빈도 순";
                } else {
                    customerListContainer.innerHTML = '<div style="text-align: center; margin: 100px 0 100px 0"><p> 고객리스트가 존재하지 않습니다 </p></div>';
                }
            })
            .catch(error => {
                console.error('Error loading all matches:', error);
            });
    });


    function fetchUserData() {
        const selectGender = $("#user_gender").val();
        const age = $("#user_age").val();

        if (selectGender === 'm') {
            var gender = 1;
        } else if (selectGender === 'f') {
            gender = 2;
        }

        let queryParams = [];
        if (gender) queryParams.push(`gender=${gender}`);
        if (age) queryParams.push(`age=${age}`);

        const queryString = queryParams.length > 0 ? `?${queryParams.join('&')}` : '';

        fetch(`/Shoots/business/customerList${queryString}`)
            .then(response => response.text())
            .then(data => {
                const parser = new DOMParser();
                const doc = parser.parseFromString(data, 'text/html');
                const newTable = doc.querySelector('#customerListContainer table');
                const customerListContainer = document.getElementById('customerListContainer');

                customerListContainer.innerHTML = '';

                if (newTable && newTable.querySelector('tbody').children.length > 0) {
                    customerListContainer.appendChild(newTable);

                    let captionText = '';
                    if (selectGender && age) {
                        if (selectGender === 'm' && age === 'asc') {
                            captionText = `남성 고객, 나이 오름차순`;
                        } else if (selectGender === 'm' && age === 'desc') {
                            captionText = `남성 고객, 나이 내림차순`;
                        } else if (selectGender === 'f' && age === 'asc') {
                            captionText = `여성 고객, 나이 오름차순`;
                        } else if (selectGender === 'f' && age === 'desc') {
                            captionText = `여성 고객, 나이 내림차순`;
                        }
                    } else if (selectGender) {
                        if (selectGender === 'm') {
                            captionText = `남성 고객`;
                        } else if (selectGender === 'f'){
                            captionText = `여성 고객`;
                        }
                    } else if (age) {
                        if (age === 'asc') {
                            captionText = `나이 오름차순`;
                        } else {
                            captionText = `나이 내림차순`;
                        }
                    } else {
                        captionText = "전체 고객 리스트";
                    }

                    document.querySelector('caption').textContent = captionText;

                    const newPopoverTriggerList = document.querySelectorAll('[data-bs-toggle="popover"]');
                    popoverList = [...newPopoverTriggerList].map(popoverTriggerEl => new bootstrap.Popover(popoverTriggerEl));

                } else {
                    if (selectGender === 'm' && age == null) {
                        customerListContainer.innerHTML = '<div style="text-align: center; margin: 100px 0 100px 0"><p> 남성 회원이 존재하지 않습니다. </p></div>';
                    } else if (selectGender === 'f' && age == null) {
                        customerListContainer.innerHTML = '<div style="text-align: center; margin: 100px 0 100px 0"><p> 여성 회원이 존재하지 않습니다. </p></div>';
                    } else {
                        customerListContainer.innerHTML = '<div style="text-align: center; margin: 100px 0 100px 0"><p> 고객리스트가 존재하지 않습니다 </p></div>';
                    }
                }
            })
            .catch(error => {
                console.error('Error loading sales data:', error);
            });
    }

    $("#user_gender").change(fetchUserData);
    $("#user_age").change(fetchUserData);



    const blockButtons = document.querySelectorAll("[data-bs-target='#insertBlacklist']");

    blockButtons.forEach(button => {
        button.addEventListener("click", function () {

            const userIdx = this.getAttribute("data-target");
            const userName = this.getAttribute("data-targetName");

            document.getElementById("target_idx").value = userIdx;
            document.getElementById("target_name").value = userName;

        });
    });

    const popoverTriggerList = document.querySelectorAll('[data-bs-toggle="popover"]')
    let popoverList = [...popoverTriggerList].map(popoverTriggerEl => new bootstrap.Popover(popoverTriggerEl))

})