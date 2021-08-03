package com.neconico.neconicojpa.domain.advertisement;

import com.neconico.neconicojpa.domain.vo.ImageInfo;
import com.neconico.neconicojpa.domain.member.Member;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDate;

import static org.springframework.util.StringUtils.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Advertisement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adver_id")
    private Long id;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String destinationUrl;

    @Embedded
    private ImageInfo imageInfo;

    @Enumerated(EnumType.STRING)
    private AdvertiseStatus advertiseStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Advertisement(@NonNull String title, @NonNull LocalDate startDate, @NonNull LocalDate endDate,
                          @NonNull String destinationUrl, @NonNull ImageInfo imageInfo,
                          @NonNull AdvertiseStatus advertiseStatus, @NonNull Member member) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.destinationUrl = destinationUrl;
        this.imageInfo = imageInfo;
        this.advertiseStatus = advertiseStatus;
        setMember(member);
    }

    public void modifyTitle(String modifyTitle) {
        if(!hasText(modifyTitle)) {
            throw new IllegalArgumentException("wrong value injection on title");
        }

        this.title = modifyTitle;
    }

    public void modifyStartDate(LocalDate modifyStartDate) {
        if(modifyStartDate == null) {
            throw new IllegalArgumentException("wrong date injection on start date");
        }

        if(modifyStartDate.isAfter(endDate)) {
            throw new IllegalArgumentException("exceed date injection than end date");
        }

        this.startDate = modifyStartDate;
    }

    public void modifyImagePath(@NonNull ImageInfo modifyImageInfo) {
        imageInfo = modifyImageInfo;
    }

    public void modifyEndDate(LocalDate modifyEndDate) {
        if(modifyEndDate == null) {
            throw new IllegalArgumentException("wrong date injection on end date");
        }

        if(modifyEndDate.isBefore(startDate)) {
            throw new IllegalArgumentException("below date injection than start date");
        }

        this.endDate = modifyEndDate;
    }

    private void setMember(Member member) {
        this.member = member;
    }


}
