$(function () {

    const popoverTriggerList = document.querySelectorAll('[data-bs-toggle="popover"]')
    let popoverList = [...popoverTriggerList].map(popoverTriggerEl => new bootstrap.Popover(popoverTriggerEl))

    populateTimeOptions();

    function populateTimeOptions() {
        const openingSelect = document.getElementById('open_time');
        const updateOpeningSelect = document.getElementById('updateOpen_time');
        const closingSelect = document.getElementById('close_time');
        const updateClosingSelect = document.getElementById('updateClose_time');

        var openSavedTime = updateOpeningSelect.getAttribute('data-saved-open-time');
        var closeSavedTime = updateClosingSelect.getAttribute('data-saved-close-time');

        for (let hour = 9; hour < 24; hour++) {
            for (let minute of [0, 30]) {
                const hourFormatted = hour.toString().padStart(2, '0');
                const minuteFormatted = minute.toString().padStart(2, '0');
                const time = `${hourFormatted}:${minuteFormatted}`;

                // 각 select 요소마다 새로운 option 객체를 생성
                const openingOption1 = document.createElement('option');
                openingOption1.value = time;
                openingOption1.textContent = time;
                openingSelect.appendChild(openingOption1);

                const openingOption2 = document.createElement('option');
                openingOption2.value = time;
                openingOption2.textContent = time;
                updateOpeningSelect.appendChild(openingOption2);

                const closingOption1 = document.createElement('option');
                closingOption1.value = time;
                closingOption1.textContent = time;
                closingSelect.appendChild(closingOption1);

                const closingOption2 = document.createElement('option');
                closingOption2.value = time;
                closingOption2.textContent = time;
                updateClosingSelect.appendChild(closingOption2);

                // 저장된 시간이 있다면 선택
                if (time === openSavedTime) {
                    openingOption1.selected = true;
                    openingOption2.selected = true;
                }

                if (time === closeSavedTime) {
                    closingOption1.selected = true;
                    closingOption2.selected = true;
                }
            }
        }
    }


    $('form[id=insertBusinessInfoForm]').submit(function (event) {
        const checkboxes = $('input[name="field_type"]');

        const isChecked = checkboxes.is(':checked');

        if (!isChecked) {
            event.preventDefault();
            alert('잔디 종류를 하나 이상 선택해 주세요.');
        } else {
            const confirmSave = confirm('구장 정보를 저장하시겠습니까?');
            if (!confirmSave) {
                event.preventDefault();
            } else {
                alert('저장되었습니다.');
            }
        }
    });

    $('form[id=updateBusinessInfoForm]').submit(function (event) {
        const checkboxes = $('input[name="field_type"]');

        const isChecked = checkboxes.is(':checked');

        if (!isChecked) {
            event.preventDefault();
            alert('잔디 종류를 하나 이상 선택해 주세요.');
        } else {
            const confirmSave = confirm('구장 정보를 수정하시겠습니까?');
            if (!confirmSave) {
                event.preventDefault();
            } else {
                alert('수정되었습니다.');
            }
        }
    });
})