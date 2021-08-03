package com.neconico.neconicojpa.domain.vo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter @Setter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageInfo {

    @Column(nullable = false)
    private String imagePath;

    @Column(nullable = false)
    private String imageFileName;

    public ImageInfo(@NonNull String imagePath, @NonNull String imageFileName) {
        this.imagePath = imagePath;
        this.imageFileName = imageFileName;
    }
}
