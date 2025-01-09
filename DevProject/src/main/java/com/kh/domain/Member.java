package com.kh.domain;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//@Getter // lombok 사용하여 getter 생성
//@Setter // lombok 사용하여 setter 생성
//@ToString(exclude = "content") // 해당 객체 배제
//@AllArgsConstructor // 모든 매개변수를 받을 수 있게 됨.
//@NoArgsConstructor // lombok 사용하여 디생 생성.
//@RequiredArgsConstructor // 매개변수 생성자 생성
//@EqualsAndHashCode(of = "boardNo") // equals 생성 (기준은 boardNo)


@Data // 위의 lombok기반 어노테이션 통합본
public class Member {
	@NotBlank (message = "공백이나 빈칸일 수 없습니다.")
	private String userId;
	private String password;
	private List<String> hobbyList;
	private int coin;
	private Date dateOfBirth;
	private String email;
	private String userName;
}
