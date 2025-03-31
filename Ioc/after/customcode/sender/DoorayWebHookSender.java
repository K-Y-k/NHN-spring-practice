package org.example.customcode.sender;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.example.framework.annotations.First;
import org.example.framework.annotations.Part;

import java.nio.charset.StandardCharsets;

//TODO-2 프레임워크 규칙을 참고해서 DoorayWebHookSender 를 사용하도록 한다.
/**
 * 초기화를 원하는 클래스는 사용자 정의한 @Part 를 붙여주면 프레임워크가 객체를 생성해준다. (기본 생성자로 초기화)
 */
@Part
public class DoorayWebHookSender implements Sender {

    //TODO-1 dooray web hook url 을 입력한다.
    String hookUrl = "https://nhnacademy.dooray.com/services/3204376758577275363/4035225265635412382/S32eovZRRweMoppGijdBEA";

    @Override
    public void sendMessage(Request request) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(hookUrl);
            httpPost.addHeader("Content-Type", "application/json; charset=UTF-8");
            HookBody hookBody = new HookBody(request.getName(), request.getMessage());
            Gson gson = new Gson();
            StringEntity stringEntity = new StringEntity(gson.toJson(hookBody), StandardCharsets.UTF_8);
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
            if (closeableHttpResponse.getStatusLine().getStatusCode() != 200) {
                System.out.println("something is wrong");
            }
        } catch (Exception e) {
            System.out.println("something is wrong. " + e);
        }

    }

    public static class HookBody {
        String botName;
        String botIconImage;
        String text;

        public HookBody(String botName, String text) {
            this.botName = botName;
            this.botIconImage = "https://static.dooray.com/static_images/dooray-bot.png";
            this.text = text;
        }
    }
}
