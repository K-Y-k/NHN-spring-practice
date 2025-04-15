package com.nhnacademy.daily.converter;

import com.nhnacademy.daily.model.Member;
import com.nhnacademy.daily.model.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;


public class MemberPageCsvHttpMessageConverter extends AbstractHttpMessageConverter<Page<Member>> {
    public MemberPageCsvHttpMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Page.class.isAssignableFrom(clazz);
    }

    // 역직렬화
    @Override
    protected Page<Member> readInternal(Class<? extends Page<Member>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    // 직렬화
    // 멤버 목록 조회 API에서 Accept Header가 text/csv로 들어온 경우 응답
    // GET http://localhost:8080/members
    // Accept: text/csv
    @Override
    protected void writeInternal(Page<Member> members, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getHeaders().setContentType(MediaType.valueOf("text/csv; charset=UTF-8"));

        try (Writer writer = new OutputStreamWriter(outputMessage.getBody())) {
            // CSV 헤더
            writer.write("id,name,class,locale\n");

            // CSV 데이터들 하나씩 가져와서 작성
            for (Member member : members) {
                writer.write(member.getId() + "," + member.getName() + "," + member.getAge() + "," + member.getClazz() + "," + member.getLocale().toJson() + "\n");
            }

            // 전송 처리
            writer.flush();
        }
    }
}
