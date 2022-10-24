package hello.login.domain.member;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Member {
    private Long id;
    @NotBlank
    private String LoginId;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotNull
    private ResidentNumber residentNumber;
}
