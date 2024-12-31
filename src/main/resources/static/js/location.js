document.addEventListener("DOMContentLoaded", function() {

    var infowindow = new kakao.maps.InfoWindow({zIndex: 1});

    var container = document.getElementById('map');
    var options = {
        center: new kakao.maps.LatLng(37.570791, 126.997383),
        level: 8
    };

    var map = new kakao.maps.Map(container, options);

    // HTML5의 geolocation으로 사용할 수 있는지 확인
    if (navigator.geolocation) {

        // GeoLocation을 이용해서 접속 위치 얻기
        navigator.geolocation.getCurrentPosition(function (position) {

            var lat = position.coords.latitude, // 위도
                lon = position.coords.longitude; // 경도

            console.log("lat >>>>>>>>>>>> " + lat);
            console.log("lon >>>>>>>>>>>> " + lon);


            var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성
                message = '<div style="padding:5px; font-size:12px;">내 위치</div>';

            // 마커와 인포윈도우를 표시합니다
            displayMarker(locPosition, message);

        });

    } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정

        var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
            message = '위치 정보 불러오기 실패'

        displayMarker(locPosition, message);
    }

    // 지도에 마커와 인포윈도우를 표시하는 함수
    function displayMarker(locPosition, message) {

        var imageSrc = 'img/mainMarker.png';

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: locPosition,
            image: new kakao.maps.MarkerImage(imageSrc, new kakao.maps.Size(38, 38))
        });

        var iwContent = message, // 인포윈도우에 표시할 내용
            iwRemoveable = true;

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: iwContent,
            removable: iwRemoveable
        });

        // 인포윈도우를 마커위에 표시
        infowindow.open(map, marker);

        // 지도 중심좌표를 접속위치로 변경합니다
        map.setCenter(locPosition);
    }


    // 지도 마커 표시
    // var markerPosition  = new kakao.maps.LatLng(37.570791, 126.997383);
    //
    // var marker = new kakao.maps.Marker({
    //     position: markerPosition
    // });
    //
    // marker.setMap(map);

    var ps = new kakao.maps.services.Places();
    ps.keywordSearch('풋살', placesSearchCB);

    function placesSearchCB(data, status, pagination) {
        if (status === kakao.maps.services.Status.OK) {

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가
            var bounds = new kakao.maps.LatLngBounds();

            for (var i = 0; i < data.length; i++) {
                placesDisplayMarker(data[i]);
                bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
            }

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정
            //map.setBounds(bounds);
            map.setLevel(8);
        }
    }

    function placesDisplayMarker(place) {

        var imageSrc = 'img/subMarker.png';


        // 마커를 생성하고 지도에 표시
        var marker = new kakao.maps.Marker({
            map: map,
            position: new kakao.maps.LatLng(place.y, place.x),
            image: new kakao.maps.MarkerImage(imageSrc, new kakao.maps.Size(35, 35))

        });

        // 마커에 클릭이벤트를 등록
        kakao.maps.event.addListener(marker, 'click', function () {
            // 마커를 클릭하면 장소명이 인포윈도우에 표출
            infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
            infowindow.open(map, marker);
        });
    }

    // 지도 zoom in / out
    window.zoomIn = function() {
        var level = map.getLevel();
        map.setLevel(level - 1);
    };

    window.zoomOut = function() {
        var level = map.getLevel();
        map.setLevel(level + 1);
    }
})
