package com.neconico.neconicojpa.domain.advertisement;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
    private String imagePath;

    @Column(nullable = false)
    private String imageFileName;

    private String destinationUrl;

    @Enumerated(EnumType.STRING)
    private AdvertiseStatus advertiseStatus;

    @Builder
    private Advertisement(final String title, LocalDate startDate, LocalDate endDate, String imagePath,
                         String imageFileName, String destinationUrl, AdvertiseStatus advertiseStatus) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imagePath = imagePath;
        this.imageFileName = imageFileName;
        this.destinationUrl = destinationUrl;
        this.advertiseStatus = advertiseStatus;
    }
}
