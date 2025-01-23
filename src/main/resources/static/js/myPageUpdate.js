$(function(){
	 //아이디의 정규식 체크하기 위한 변수로 기본값은 false, 규칙에 맞게 입력하면 true값을 갖습니다.
    let checkemail=false;//이메일의 정규식 체크하기 위한 변수로 기본값은 false, 규칙에 맞게 입력하면 true값을 갖습니다.

    $(function(){
        $(".emailcheck").click(function(){
            const email = $("input[name=email]").val();
            const emailpattern = /^\w+@\w+[.][A-Za-z0-9]{3}$/;
                if(!emailpattern.test(email)){
                    alert("잘못된 형식의 이메일입니다.")
                    checkemail=false;
                }else { //이메일 형식이 올바를 시, 이메일 중복체크

                    checkemail = true;
                }

                    $.ajax({  //이메일 중복검사 : 개인회원
                        url: "emailcheck",
                        data: {"email": email},
                        success: function (resp) {
                            if (resp == "-1") {//db에 해당 id가 없는 경우
                                $("#email-message").css('color', 'green').html("사용 가능한 이메일 입니다.");
                                checkemail = true;
                            } else {//db에 해당 id가 있는 경우(1)
                                $("#email-message").css('color', 'blue').html("사용중인 이메일 입니다.");
                                checkemail = false;
                            }
                        }
                    });//이메일 중복검사 ajax 끝
        })
    })

    //개인회원가입 버튼 제출시 유효성 검사
    $('form[name="updateform"]').off('submit').submit(function(){

        const telPattern = /^[0-9]{8,13}$/;
        if (!telPattern.test($("input[name=tel]").val())) {
            alert("전화번호를 확인해 주세요.");
            $("input[name=tel]").val('').focus();
            return false;
        }


        if(!$.isNumeric($("input[name='jumin']").val())){
            alert("주민등록번호에는 숫자를 입력해 주세요.");
            $("input[name='jumin']").val('').focus();
            return false;
        }

        if(!$.isNumeric($("input[name='gender']").val())){
            alert("주민번호 뒷자리에는 숫자를 입력해 주세요");
            $("input[name='gender']").val('').focus();
            return false;
        }

        if(!$.isNumeric($("input[name='tel']").val())){
            alert("전화번호는 숫자로 입력해 주세요");
            $("input[name='tel']").val('').focus();
            return false;
        }



        if(!checkemail){
            alert("이메일 형식을 확인해 주세요.");
            $("input[name=email]").focus();
            return false;
        }
    })// 개인회원가입 submit 끝

})//ready