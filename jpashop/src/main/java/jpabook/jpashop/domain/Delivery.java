package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //EnumType.ORDINAL은 enum값들이 0, 1순서대로 매핑됨 -> enum 필드 값들 중간에 값이 추가되면 DB에서 enum필드 값들이 갱신되지 않으면서 에러가 발생함
    private DeliveryStatus status;  //ENUM [READ(준비), COMP(배송)]
}
