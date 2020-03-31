package com.jrock.boot.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// JPA Entity클래스들이 BaseTimeEntity을 상속할 경우 필드들도 칼럼으로 인식하도록 한다.
@MappedSuperclass
// BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.
@EntityListeners(AuditingEntityListener.class)
// 모든 Entity의 상위 클래스가 되어 Entity들의 생성, 수정날짜를 자동으로 관리하는 역할
public class BaseTimeEntity {

    // 생성날짜
    @CreatedDate
    private LocalDateTime createdDate;

    // 수정날짜
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
