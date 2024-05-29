package idusw.springboot.ckjmall.model;

import lombok.*;

// lombok annotation - boilerplate code 를 다루기 위한 라이브러리
// POJO, Beans
@Getter
@Setter
//@NoArgsConstructor // 빈 생성자 초기화
//@AllArgsConstructor // 모든 생성자 초기화
@EqualsAndHashCode
@Builder // 명확하게 생성자를 생성

public class Member { // POJO : plain old java object = beans
    private Long idx;
    private String id;
    private String pw;
    private String name;
    private String email;
    // 접근 메소드 : getter / setter'

}