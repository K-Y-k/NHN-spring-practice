package com.nhnacademy.daily.converter;

import com.nhnacademy.daily.model.Member;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class MemberCsvHttpMessageConverter extends AbstractHttpMessageConverter<Member> {
    public MemberCsvHttpMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    protected Member readInternal(Class<? extends Member> clazz, HttpInputMessage inputMessage) {
        // (생략)
        return null;
    }

    @Override
    protected void writeInternal(Member member, HttpOutputMessage outputMessage) throws IOException {
        outputMessage.getHeaders().setContentType(MediaType.valueOf("text/csv; charset=UTF-8"));

        try (Writer writer = new OutputStreamWriter(outputMessage.getBody())) {
            writer.write("id,name,age,class,locale\n");
            writer.write(member.getId() + "," + member.getName() + "," + member.getAge() + "," + member.getClazz() + "," + member.getLocale() + "\n");
            writer.flush();
        }
    }
}
