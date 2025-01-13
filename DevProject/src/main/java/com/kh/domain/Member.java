package com.kh.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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
	@NotBlank (message = "공백이나 빈칸일 수 없습니다.")
	private String password;
	
	@NotBlank
	@Size(max=3, message="3자 미만작성 가능합니다.")
	private String userName;
	
	@Email
	private String email;
	private List<String> hobbyList;
	private int coin;
	
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	
	private String gender;
}
