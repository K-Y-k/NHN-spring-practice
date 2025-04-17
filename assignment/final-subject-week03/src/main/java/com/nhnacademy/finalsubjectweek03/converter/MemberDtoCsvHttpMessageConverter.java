package com.nhnacademy.finalsubjectweek03.converter;

import com.nhnacademy.finalsubjectweek03.domain.MemberDto;
import com.nhnacademy.finalsubjectweek03.domain.Role;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MemberDtoCsvHttpMessageConverter extends AbstractHttpMessageConverter<MemberDto> {
    public MemberDtoCsvHttpMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return MemberDto.class.isAssignableFrom(clazz);
    }

    /**
     * 현재는 Member로 반환할 때만 사용하므로
     * 역직렬화 하는 readInternal만 구현
     */
    @Override
    protected MemberDto readInternal(Class<? extends MemberDto> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        // 입력 메시지에서 본문을 읽기 위한 Reader 생성
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputMessage.getBody()))) {
            String line;

            // 첫 줄 헤더는 형식이므로 건너뛰기
            bufferedReader.readLine();

            if ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                String id = values[0];
                String name = values[1];
                String password = values[2];
                Integer age = Integer.parseInt(values[3]);
                Role role = Role.valueOf(values[4].toUpperCase());

                return new MemberDto(id, name, password, age, role);
            }
        } catch (IOException e) {
            throw new HttpMessageNotReadableException("Error reading CSV input message", e);
        }

        return null;
    }

    @Override
    protected void writeInternal(MemberDto memberDto, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
