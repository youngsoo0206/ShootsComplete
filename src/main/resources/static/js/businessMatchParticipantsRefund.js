$(function () {

    function BrequestRefund() {

        const paymentIdx = document.getElementById('refundButton').getAttribute('data-payment_idx');
        const impUid = document.getElementById('refundButton').getAttribute('data-imp_uid');
        const merchantUid = document.getElementById('refundButton').getAttribute('data-merchant_uid');
        const refundAmount = document.getElementById('refundButton').getAttribute('data-amount');

        console.log(">>>> paymentIdx = " + paymentIdx)
        console.log(">>>> impUid = " + impUid)
        console.log(">>>> merchantUid = " + merchantUid)
        console.log(">>>> refundAmount = " + refundAmount)

        // var refundData = {
        //     payment_idx: paymentIdx,
        //     imp_uid: impUid,
        //     merchant_uid: merchantUid,
        //     refund_amount: refundAmount,
        //     refund_reason: '기업 환불',
        //     refund_method: 'card',
        //     refund_status: 'SUCCESS',
        // };
        //
        // $.ajax({
        //     url: '/Shoots/refund/refundProcess',
        //     type: 'POST',
        //     dataType: 'json',
        //     contentType: 'application/json',
        //     data: JSON.stringify(refundData),
        //     success: function(response) {
        //         if (response.success) {
        //             alert('환불이 성공적으로 처리되었습니다.');
        //             console.log(response);
        //             console.log("Error code:", response.error_code);
        //         } else {
        //             alert('환불 처리에 실패했습니다.');
        //             console.log(response);
        //             console.log("Error code:", response.error_code);
        //         }
        //     },
        //     error: function(xhr, status, error) {
        //         alert('환불 요청 중 오류가 발생했습니다.');
        //         console.error('Error:', error);
        //         console.log("Error code:", xhr.status);
        //     }
        // });
    };
});