$(function () {
    $("#matchForm").click(function () {
        location.href = "../match/write";
    })

    $(".today").click(function () {

        $("#player_gender").val('');
        $("#match_level").val('');

        fetch('/Shoots/business/post?filter=today')
            .then(response => response.text())
            .then(data => {

                // 서버에서 반환된 HTML에서 테이블만 추출하여 matchListContainer 갱신
                const parser = new DOMParser();
                const doc = parser.parseFromString(data, 'text/html');
                const newTable = doc.querySelector('#matchListContainer table');
                const matchListContainer = document.getElementById('matchListContainer');

                matchListContainer.innerHTML = '';

                if (newTable && newTable.querySelector('tbody').children.length > 0) {
                    matchListContainer.appendChild(newTable); // 테이블이 있으면 추가
                    document.querySelector('caption').textContent = "오늘의 매치"; // 캡션 수정
                } else {
                    console.log(">>>>>>>>>>>>>>>>>> 데이터 없음")
                    matchListContainer.innerHTML = '<div style="text-align: center; margin: 100px 0 100px 0"><p> 오늘 날짜에 해당하는 매치가 없습니다 </p></div>';
                }

            })
            .catch(error => {
                console.error('Error loading today matches:', error);
            });
    });

    $("#allMatchesBtn").click(function () {

        $("#player_gender").val('');
        $("#match_level").val('');

        fetch('/Shoots/business/post')  // 전체 리스트를 가져오는 요청
            .then(response => response.text())
            .then(data => {
                const parser = new DOMParser();
                const doc = parser.parseFromString(data, 'text/html');
                const newTable = doc.querySelector('#matchListContainer table');
                const matchListContainer = document.getElementById('matchListContainer');

                matchListContainer.innerHTML = '';

                if (newTable && newTable.querySelector('tbody').children.length > 0) {
                    matchListContainer.appendChild(newTable);
                    document.querySelector('caption').textContent = "전체 매치";
                } else {
                    matchListContainer.innerHTML = '<div style="text-align: center; margin: 100px 0 100px 0"><p> 매칭글이 존재하지 않습니다 </p></div>';
                }
            })
            .catch(error => {
                console.error('Error loading all matches:', error);
            });
    });

    $("#player_gender").change(function () {
        const gender = $("#player_gender").val()
        $("#match_level").val('');

        fetch(`/Shoots/business/post?gender=${gender}`)
            .then(response => response.text())
            .then(data => {
                const parser = new DOMParser();
                const doc = parser.parseFromString(data, 'text/html');
                const newTable = doc.querySelector('#matchListContainer table');
                const matchListContainer = document.getElementById('matchListContainer');

                matchListContainer.innerHTML = '';

                if (newTable && newTable.querySelector('tbody').children.length > 0) {
                    matchListContainer.appendChild(newTable);

                    if (gender === 'm') {
                        document.querySelector('caption').textContent = "남성 매치";
                    } else if (gender === 'f') {
                        document.querySelector('caption').textContent = "여성 매치";
                    } else if (gender === 'a') {
                        document.querySelector('caption').textContent = "혼성 매치";
                    }
                } else {
                    matchListContainer.innerHTML = '<div style="text-align: center; margin: 100px 0 100px 0"><p> 해당 성별에 맞는 매치가 없습니다 </p></div>';
                }
            })
            .catch(error => {
                console.error('Error loading filtered matches:', error);
            });
    });

    $("#match_level").change(function () {
        const level = $("#match_level").val()
        $("#player_gender").val('');

        fetch(`/Shoots/business/post?level=${level}`)
            .then(response => response.text())
            .then(data => {
                const parser = new DOMParser();
                const doc = parser.parseFromString(data, 'text/html');
                const newTable = doc.querySelector('#matchListContainer table');
                const matchListContainer = document.getElementById('matchListContainer');

                matchListContainer.innerHTML = '';

                if (newTable && newTable.querySelector('tbody').children.length > 0) {
                    matchListContainer.appendChild(newTable);

                    if (level === '초급') {
                        document.querySelector('caption').textContent = "초급 매치";
                    } else if (level === '중급') {
                        document.querySelector('caption').textContent = "중급 매치";
                    } else if (level === '고급') {
                        document.querySelector('caption').textContent = "고급 매치";
                    } else if (level === '전체') {
                        document.querySelector('caption').textContent = "난이도 무관 매치";
                    }
                } else {
                    matchListContainer.innerHTML = '<div style="text-align: center; margin: 100px 0 100px 0"><p> 해당 난이도에 맞는 매치가 없습니다 </p></div>';
                }
            })
            .catch(error => {
                console.error('Error loading filtered matches:', error);
            });
    });

    $('.btn-delete').click(function(event) {
        event.preventDefault();
        const userConfirmed = confirm("매칭글을 삭제하시겠습니까?");

        if (userConfirmed) {
            alert("매칭글이 삭제되었습니다");
            $(this).closest("form").submit();
        }
    });

});