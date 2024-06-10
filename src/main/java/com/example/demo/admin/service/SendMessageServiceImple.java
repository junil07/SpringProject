package com.example.demo.admin.service;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Service;

@Service
public class SendMessageServiceImple implements SendMessageService {
    private DefaultMessageService messageService;

    public SendMessageServiceImple() {
        this.messageService = NurigoApp.INSTANCE.initialize("NCSAUEU6PZNYQFHA", "Y5MELR1IYEZILCGRSOTHZVM5RKJYHQ83", "https://api.coolsms.co.kr");
    }

    @Override
    public void sendMessage(String sellerPhoneNum, String text) {
        Message message = new Message();
        message.setFrom("01064109376");    // 보내는 사람 전화번호 하이푼 있으면 안됨
        message.setTo("01064109376");      // 받는 사람 전화번호  하이푼 있으면 안됨
        message.setText(text);    // 보낼 메세지
        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println("오예 테스트 : " + response);
    }
}
