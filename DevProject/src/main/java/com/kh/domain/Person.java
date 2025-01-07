package com.kh.domain;

import lombok.Builder;

@Builder // Builder란 생성자를 통해 값을 입력 할 때 선언한 객체들의 값을 지정 할 떄 모두 지정 할 수 있도록 선언.
		 // 즉 빠트리지 말라고 하는 것.
public class Person {
	private final String name;
	private final int age;
	private final int phone;
}
