package com.project.common.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service("mailService")
public class mailServiceImpl implements mailService {

    @Autowired
    JavaMailSender mailSender;

    private boolean lowerCheck;
    private int size;

    @Override
    public String createKey() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        int num = 0;
        do {
            num = random.nextInt(75) + 48;
            if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
                stringBuilder.append((char) num);
            } else {
                continue;
            }
        } while (stringBuilder.length() < size);
        if (!lowerCheck) {
            return stringBuilder.toString().toLowerCase();
        }else{
            return stringBuilder.toString();
        }
    }

    public String getKey(boolean lowerCheck, int size){
        this.lowerCheck = lowerCheck;
        this.size = size;
        return createKey();
    }

    @Async
    @Override
    public void setMail(String authKey, String userId, String userEmail, String context){
        MimeMessage mailMessage = mailSender.createMimeMessage();
        String Message = "<h2>안녕하세요 Remind입니다.</h2><br><br><h3>"+userId+"님 먼저 단어암기 웹 사이트를 이용해주시는 것에 정말 " +
                "감사드리며 아래 보시는 인증하기 버튼을 누르시면 로그인 후 바로 서비스를 이용하실수 있습니다." +
                "<a href='http://localhost:8080"+ context+ "/member/authMember.do?userId="+userId+"&authKey="+authKey+"'>인증하기</a>";
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"UTF-8");
            messageHelper.setFrom("ksj0109188@gmail.com");
            messageHelper.setSubject("Remind 사이트의 회원가입 이메일 인증입니다.");
            messageHelper.setTo(userEmail);
            messageHelper.setText(Message,true);
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Async
    @Override
    public void findPwdMail(String newPwd, String userId, String userName, String userEmail, String context) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        String Message = "<h2>안녕하세요 Remind입니다.</h2><br><br><h3>"+userName+"님 먼저 단어암기 웹 사이트를 이용해주시는 것에 정말 " +
                "감사드리며 아래 임시 비밀번호로 로그인이 가능합니다. <br> 아이디 : "+userId+"<br> 비밀번호 : " + newPwd;
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"UTF-8");
            messageHelper.setFrom("ksj0109188@gmail.com");
            messageHelper.setSubject("Remind 사이트의 비밀번호 찾기 이메일입니다.");
            messageHelper.setTo(userEmail);
            messageHelper.setText(Message,true);
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}