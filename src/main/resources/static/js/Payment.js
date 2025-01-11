    function requestPayment() {
        const IMP = window.IMP;
        IMP.init("imp35523152");

        var merchant_uid = 'merchant_' + new Date().getTime();

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
                channelKey: "channel-key-7a6c345a-a185-4466-865b-b2cca8f302d3",
                pay_method: "card",
                merchant_uid: merchant_uid,
                name: matchIdx + ' 번 매치-플레이어 신청',
                amount: price,
                buyer_email: "shk7357@naver.com",
                buyer_name: "강성현",
                buyer_tel: "010-9711-7305",
                language: "ko",
            },

            function (response) {
                console.log(response);

                if (response.imp_uid) {
                    try {
                        // 서버에 결제 정보를 전송하는 부분
                        fetch("/Shoots/payment/add", {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json"
                            },
                            body: JSON.stringify({
                                imp_uid: response.imp_uid,
                                merchant_uid: response.merchant_uid,
                                match_idx: matchIdx,
                                seller_idx: sellerIdx,
                                buyer_idx: buyerIdx,
                                payment_amount: price,
                            }),
                        })
                            .then((notified) => {
                                if (notified.ok) {
                                    return notified.json();
                                } else {
                                    throw new Error('서버 응답 오류');
                                }
                            })
                            .then((result) => {
                                console.log("서버 응답 데이터:", result);

                                if (result.success) {
                                    alert("매칭 신청이 완료되었습니다");
                                    console.log("결제 데이터가 성공적으로 서버에 저장되었습니다.");
                                    window.location.reload();
                                } else {
                                    alert("결제에 실패하였습니다. 관리자에게 문의해주세요");
                                    console.log(`서버 처리 중 오류가 발생했습니다: ${result.message}`);
                                }
                            })
                            .catch((error) => {
                                console.error("결제 데이터 전송 중 오류:", error);
                                alert("결제 처리 중 오류가 발생했습니다.");
                            });
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

        const paymentIdx = document.getElementById('refundButton').getAttribute('data-payment_idx');
        const impUid = document.getElementById('refundButton').getAttribute('data-imp_uid');
        const merchantUid = document.getElementById('refundButton').getAttribute('data-merchant_uid');
        const refundAmount = document.getElementById('refundButton').getAttribute('data-amount');

        console.log(">>>> impUid = " + impUid)
        console.log(">>>> merchantUid = " + merchantUid)
        console.log(">>>> refundAmount = " + refundAmount)

        var refundData = {
            payment_idx: paymentIdx,
            imp_uid: impUid,
            merchant_uid: merchantUid,
            refund_amount: refundAmount,
            payment_status: 'refunded',
        };

        $.ajax({
            url: '/Shoots/refund/refundProcess',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(refundData),
            success: function(response) {
                if (response.success) {
                    alert('환불이 성공적으로 처리되었습니다.');
                    console.log(response);
                    window.location.reload();
                } else {
                    alert('환불 처리에 실패했습니다.');
                    console.log(response);
                    console.log("Error code:", response.error_code);
                    window.location.reload();
                }
            },
            error: function(xhr, status, error) {
                alert('환불 요청 중 오류가 발생했습니다.');
                console.error('Error:', error);
                console.log("Error code:", xhr.status);
            }
        });
    };

