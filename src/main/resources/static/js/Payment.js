
    function requestPayment() {
        const IMP = window.IMP; // 생략 가능: CDN으로 불러온 IMP 객체
        IMP.init("imp35523152"); // 포트원 콘솔에서 발급받은 가맹점 식별코드로 변경

        var merchant_uid = 'merchant_' + new Date().getTime();

        // const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        // const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        const matchIdx = document.getElementById('paymentButton').getAttribute('data-matchIdx');
        const sellerIdx = document.getElementById('paymentButton').getAttribute('data-seller');
        const buyerIdx = document.getElementById('paymentButton').getAttribute('data-buyer');
        const price = document.getElementById('paymentButton').getAttribute('data-price');

        if (!buyerIdx) {
            alert("매칭신청은 로그인 후 이용 가능합니다.");
            window.location.href = "/Shoots/login";
            return;
        }

        IMP.request_pay(
            {
                channelKey: "channel-key-7cf4f649-e97f-4e5f-906e-30ec63d4f44c", // 콘솔 내 채널키
                pay_method: "card", // 결제 수단 (예: 카드)
                merchant_uid: merchant_uid, // 고유 주문번호 (동적으로 생성)
                name: matchIdx + ' 번 매치-플레이어 신청',
                amount: price, // 결제 금액
                buyer_email: "shk7357@naver.com", // 구매자 이메일
                buyer_name: "강성현", // 구매자 이름
                buyer_tel: "010-9711-7305", // 구매자 전화번호
                language: "ko", // 결제창 언어 (한국어: 'ko', 영문: 'en')
            },

            async (response) => {
                if (response.success) {
                    try {
                        const notified = await fetch("/Shoots/payment/add", {
                            method: "POST",
                            headers: {
                            "Content-Type": "application/json",
                            // [csrfHeader]: csrfToken
                            },
                            body: JSON.stringify({
                                imp_uid: response.imp_uid,
                                merchant_uid: response.merchant_uid,
                                match_idx: matchIdx,
                                seller_idx: sellerIdx,
                                buyer_idx: buyerIdx,
                                payment_amount: price,
                                }),
                            });

                            if (notified.ok) {
                                const result = await notified.json();
                                console.log("서버 응답 데이터:", result);

                                if (result.success) {
                                        alert("매칭 신청이 완료되었습니다");
                                        console.log("결제 데이터가 성공적으로 서버에 저장되었습니다.");
                                        window.location.reload();
                                } else {
                                    alert("결제에 실패하였습니다. 관리자에게 문의해주세요");
                                    console.log(`서버 처리 중 오류가 발생했습니다: ${result.message}`);
                                }
                            } else {
                                console.error("서버 응답 오류:", notified.status);
                                console.log("결제 데이터 저장 중 오류가 발생했습니다.");
                            }
                        } catch (error) {
                        console.error("결제 데이터 전송 중 오류:", error);
                        alert("결제 처리 중 오류가 발생했습니다.");
                    }

                } else {
                    console.log("Error code:", response.error_code);
                    if (response.error_code === 'F400') {
                        alert("결제를 취소했습니다");
                    } else if (response.error_code === 'F500') {
                        alert("한도 초과로 결제가 거부되었습니다");
                    } else {
                        alert("결제 오류 : " + response.error_msg);
                        console.log("결제 오류:", response.error_msg);
                    }
                }
            }
        );
    }

    function closedMatch(){
        alert("마감된 매칭입니다");
    }

    function limitedMatch(){
        alert("신청인원이 다 찼습니다");
    }


    function requestRefund() {
        var impUid = $(this).data('imp-uid');
        var merchantUid = $(this).data('merchant-uid');
        var refundAmount = $(this).data('amount');

        console.log(">>>> impUid = " + impUid)

        var refundData = {
            imp_uid: impUid,
            merchant_uid: merchantUid,
            amount: refundAmount
        };

        $.ajax({
            url: 'https://api.portone.io/v1/cancel',
            type: 'POST',
            dataType: 'json',
            headers: {
                'Authorization': '4374671001417615'
            },
            contentType: 'application/json',
            data: JSON.stringify(refundData),
            success: function(response) {
                if (response.success) {
                    alert('환불이 성공적으로 처리되었습니다.');
                    console.log(response);
                } else {
                    alert('환불 처리에 실패했습니다.');
                    console.log(response);
                }
            },
            error: function(xhr, status, error) {
                alert('환불 요청 중 오류가 발생했습니다.');
                console.error('Error:', error);
            }
        });
    };

