package provider;


import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.MultipleDetailMessageSentResponse;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class SmsProvider {

    private final DefaultMessageService messageService;

    @Value("${sms.from-number}") String FROM;

    public SmsProvider(
            @Value("${sms.api-key}") String API_KEY,
            @Value("${sms.api-secret-key}") String API_SECRET_KEY,
            @Value("${sms.domain}") String DOMAIN
    ) {
        this.messageService = NurigoApp.INSTANCE.initialize(API_KEY, API_SECRET_KEY, DOMAIN);
    }


    //단일 메시지 전송
    //http://localhost:1000/Shoots/test/send-sms/수신번호 입력
    public boolean sendSms(String to) {
        Message message = new Message();
        message.setFrom(FROM); //발신번호
        message.setTo(to); //수신번호
        message.setText("문자내용");

        SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));

        String statusCode = response.getStatusCode();
        boolean result = statusCode.equals("2000");

        return result;
    }


    //여러 메시지 전송 >> match에서 동시에 여러명 보낼때 사용
    //http://localhost:1000/Shoots/test/send-many >> 해당 주소로 가면 바로 메시지 전송됨
    public MultipleDetailMessageSentResponse sendMany() {
        ArrayList<Message> messageList = new ArrayList<>();


        /*for (int i = 0; i < 2; i++) {
            Message message = new Message();
            // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
            message.setFrom("발신번호 입력");
            message.setTo("수신번호 입력");
            message.setText("한글 45자, 영자 90자 이하 입력되면 자동으로 SMS타입의 메시지가 추가됩니다." + i);

            // 메시지 건건 마다 사용자가 원하는 커스텀 값(특정 주문/결제 건의 ID를 넣는등)을 map 형태로 기입하여 전송 후 확인 가능
            HashMap<String, String> map = new HashMap<>();

            map.put("키 입력", "값 입력");
            message.setCustomFields(map);

            messageList.add(message);
            messageList.add(message);
        }
        */

        Message test1 = new Message();
        test1.setFrom(FROM);
        test1.setTo("01082204879");
        test1.setText("테스트 메세지 임현빈 1");

        Message test2 = new Message();
        test2.setFrom(FROM);
        test2.setTo("01085289223");
        test2.setText("테스트 메세지 달봉이의 힘찬 아침");

        messageList.add(test1);
        messageList.add(test2);

        try {
            // send 메소드로 단일 Message 객체를 넣어도 동작
            // 세 번째 파라미터인 showMessageList 값을 true로 설정할 경우 MultipleDetailMessageSentResponse에서 MessageList를 리턴
            MultipleDetailMessageSentResponse response = this.messageService.send(messageList, false, true);

            // 중복 수신번호를 허용하고 싶으실 경우 위 코드 대신 아래코드로 대체해 사용
            //MultipleDetailMessageSentResponse response = this.messageService.send(messageList, true);

            System.out.println(response);

            return response;
        } catch (NurigoMessageNotReceivedException exception) {
            System.out.println(exception.getFailedMessageList());
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
