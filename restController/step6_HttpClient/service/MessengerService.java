package com.nhnacademy.restcontrollerpractice.step6.service;

import com.nhnacademy.restcontrollerpractice.step6.domain.DoorayMessengerRequest;
import com.nhnacademy.restcontrollerpractice.step6.domain.MyServerSendRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class MessengerService {

    public void sendMessage(MyServerSendRequest sendRequest) {
        System.out.println("sendMessage 실행");
        
        /**
         * HTTP 클라이언트
         * - HTTP API를 사용하기 위한 모듈
         * 
         * RestTemplate
         * - 예전부터 많이 써오던 라이브러리
         * - 다양한 형식 지원
         * - 비동기
         * - 다양한 HTTP 메소드 지원
         */
        RestTemplate restTemplate = new RestTemplate();

        // url 설정
        String url = sendRequest.getChannel().getUrl();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.put("content-type", Arrays.asList("application/json"));


        // 바디
        DoorayMessengerRequest body = new DoorayMessengerRequest("봇 k", sendRequest.getText(), sendRequest.getAttachments());

        HttpEntity<DoorayMessengerRequest> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<HashMap> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, HashMap.class);
        System.out.println("전송 완료 : " + exchange.getStatusCode());
    }
}
