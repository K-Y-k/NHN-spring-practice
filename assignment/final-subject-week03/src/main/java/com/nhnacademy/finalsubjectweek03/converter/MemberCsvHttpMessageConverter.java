package com.nhnacademy.finalsubjectweek03.converter;

import com.nhnacademy.finalsubjectweek03.domain.Member;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class MemberCsvHttpMessageConverter extends AbstractHttpMessageConverter<Member> {
    public MemberCsvHttpMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        /// 받아온 클래스가 Member 클래스 또는 Member 클래스의 자식 클래스인지 확인
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    protected Member readInternal(Class<? extends Member> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    /**
     * 현재는 Member로 반환할 때만 사용하므로
     * 직렬화 하는 writeInternal만 구현
     */
    @Override
    protected void writeInternal(Member member, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getHeaders().setContentType(MediaType.valueOf("text/csv; charset=UTF-8"));

        try (Writer writer = new OutputStreamWriter(outputMessage.getBody())) {
            writer.write("id,name,password,age,role\n");
            writer.write(member.getId() + "," + member.getName() + "," + member.getPassword() + "," + member.getAge() + "," + member.getRole() + "\n");
            writer.flush();
        }
    }
}
