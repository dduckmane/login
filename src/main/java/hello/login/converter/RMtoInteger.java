package hello.login.converter;

import hello.login.domain.member.ResidentNumber;
import org.springframework.core.convert.converter.Converter;

public class RMtoInteger implements Converter<ResidentNumber,String> {
    @Override
    public String convert(ResidentNumber source) {
        return source.getBirth()+"-"+source.getIdentifier();
    }
}
