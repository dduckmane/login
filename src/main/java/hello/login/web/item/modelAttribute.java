package hello.login.web.item;
import hello.login.domain.item.DeliveryCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Getter@Setter
@Component
@RequiredArgsConstructor
public class modelAttribute {
    private final Map<String, String> regions;
    private final List<DeliveryCode> deliveryCodes;

    @PostConstruct
    public void init(){
        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주");
        deliveryCodes.add(new DeliveryCode("FAST", "빠른 배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL", "일반 배송"));
        deliveryCodes.add(new DeliveryCode("SLOW", "느린 배송"));
    }

}
