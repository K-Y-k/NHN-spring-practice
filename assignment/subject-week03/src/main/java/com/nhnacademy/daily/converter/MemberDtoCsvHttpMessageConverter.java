package com.nhnacademy.daily.converter;

import com.nhnacademy.daily.model.Locale;
import com.nhnacademy.daily.model.Member;
import com.nhnacademy.daily.model.MemberDto;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;

/**
 * AbstractHttpMessageConverter는
 * 스프링 Ioc 컨테이너에 사용자가 요청한 메시지의 타입에 따라 실행시켜 준다.
 *
 * 스프링의 가장 중요한 핵심!
 * : 구분할 수 있는 설정만 해주면 DI를 해주고 알아서 스프링이 적용해준다!(제어의 역전)
 */
public class MemberDtoCsvHttpMessageConverter extends AbstractHttpMessageConverter<MemberDto> {
    public MemberDtoCsvHttpMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    /**
     * 전략 패턴
     * - 디자인 패턴 중 가장 많이 사용
     */
    /**
     * 제일 중요한 메소드다. (전략 메소드)
     * AbstractHttpMessageConverter가 이 메소드를 보고 사용하지 판단하는 것이다.
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return MemberDto.class.isAssignableFrom(clazz);
    }

    // 역직렬화
    @Override
    protected MemberDto readInternal(Class<? extends MemberDto> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        // 입력 메시지에서 본문을 읽기 위한 Reader 생성
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputMessage.getBody()))) {
            String line;

            // 두 번째 줄부터 데이터 처리
            if ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                String id = values[0];
                String name = values[1];
                int age = Integer.parseInt(values[2]);
                String clazzz = values[3];
                Locale locale = Locale.valueOf(values[4].toUpperCase());

                return new MemberDto(id, name, age, clazzz, locale);
            }
        } catch (IOException e) {
            throw new HttpMessageNotReadableException("Error reading CSV input message", e);
        }

        return null;
    }

    // 직렬화
    @Override
    protected void writeInternal(MemberDto memberDto, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getHeaders().setContentType(MediaType.valueOf("text/csv; charset=UTF-8"));
        try (Writer writer = new OutputStreamWriter(outputMessage.getBody())) {
            // CSV 헤더
            writer.write("id,name,class,locale\n");

            // 데이터
            writer.write(memberDto.getId() + "," + memberDto.getName() + "," + memberDto.getAge() + "," + memberDto.getClazz() + "," + memberDto.getLocale() + "\n");

            // 전송 처리
            writer.flush();
        }
    }
}
