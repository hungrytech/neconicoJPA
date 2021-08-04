package com.neconico.neconicojpa.domain.item;

import com.neconico.neconicojpa.domain.member.Member;
import com.neconico.neconicojpa.domain.vo.ImageInfo;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;

import static org.springframework.util.StringUtils.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 2000)
    private String content;

    private long price;

    @Embedded
    private ImageInfo imageInfo;

    private String tradeArea;

    @Enumerated(EnumType.STRING)
    private ShippingPrice shippingPrice;

    private long hits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Item(@NonNull String title, @NonNull String content, @NonNull long price,
                @NonNull ImageInfo imageInfo, @NonNull String tradeArea, @NonNull ShippingPrice shippingPrice,
                @NonNull Member member, long hits) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.imageInfo = imageInfo;
        this.tradeArea = tradeArea;
        this.shippingPrice = shippingPrice;
        this.hits = hits;
        setMember(member);
    }

    public void modifyTitle(String modifyTitle) {
        if (!hasText(modifyTitle)) {
            throw new IllegalArgumentException("wrong value injection on item title");
        }
        title = modifyTitle;
    }

    public void modifyContent(String modifyContent) {
        if(!hasText(modifyContent)) {
            throw new IllegalArgumentException("wrong value injection on item content");
        }

        content = modifyContent;
    }

    public void modifyImageInfo(@NonNull ImageInfo modifyInfo) {
        imageInfo = modifyInfo;
    }

    public void modifyTradeArea(String modifyTradeArea) {
        if(!hasText(modifyTradeArea)) {
            throw new IllegalArgumentException("wrong value injection on trade area");
        }

        tradeArea = modifyTradeArea;
    }

    public void increaseHits() {
        hits += 1;
    }

    private void setMember(Member member) {
        this.member = member;
        member.getItems().add(this);
    }


}
