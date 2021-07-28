package com.neconico.neconicojpa.domain.advertisement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AdvertisementTest {

    @DisplayName("advertisement 생성")
    @Test
    void test_create_advetisement() throws Exception {

        Advertisement.builder()
                .startDate(LocalDate.of(2021, 07, 28))
                .imageFileName("default-image-name")
                .build();



    }

}
