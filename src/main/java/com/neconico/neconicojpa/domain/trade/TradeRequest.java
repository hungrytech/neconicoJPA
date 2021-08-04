package com.neconico.neconicojpa.domain.trade;

import com.neconico.neconicojpa.domain.item.Item;
import com.neconico.neconicojpa.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TradeRequest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "tradeRequest", fetch = FetchType.LAZY)
    private Item item;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @Builder
    public TradeRequest(Member member, RequestStatus requestStatus) {
        this.member = member;
        this.requestStatus = requestStatus;
    }

    public void insertTargetItem(Item item) {
        this.item = item;
    }

    public void modifyRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
