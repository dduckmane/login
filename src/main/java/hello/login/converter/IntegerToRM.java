package hello.login.converter;

import hello.login.domain.member.ResidentNumber;
import org.springframework.core.convert.converter.Converter;

public class IntegerToRM implements Converter<String, ResidentNumber> {
    @Override
    public ResidentNumber convert(String source) {
        String[] split = source.split("-");
        Integer birth = Integer.parseInt(split[0]);
        Integer identifier = Integer.parseInt(split[1]);
        return new ResidentNumber(birth,identifier);
    }
}