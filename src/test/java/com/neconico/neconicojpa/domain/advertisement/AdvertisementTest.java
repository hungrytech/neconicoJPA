package com.neconico.neconicojpa.domain.advertisement;

import com.neconico.neconicojpa.domain.vo.ImageInfo;
import com.neconico.neconicojpa.domain.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class AdvertisementTest {

    @Mock Member member;

    @DisplayName("제목 변경")
    @Test
    void test_modify_title() throws Exception {
        // given
        Advertisement advertisement = getAdvertisement();

        String modifyTitle = "변경된 타이틀";

        // when
        advertisement.modifyTitle(modifyTitle);

        // then
        assertThat(advertisement.getTitle()).isEqualTo(modifyTitle);
    }

    @DisplayName("시작 날짜 변경")
    @Test
    void test_modify_start_date() throws Exception {
        // given
        Advertisement advertisement = getAdvertisement();

        LocalDate modifyStartDate = LocalDate.of(2021, 8, 22);

        // when
        advertisement.modifyStartDate(modifyStartDate);

        // then
        assertThat(advertisement.getStartDate()).isEqualTo(modifyStartDate);
    }

    @DisplayName("종료 날짜 변경")
    @Test
    void test_modify_end_date() throws Exception {
        // given
        Advertisement advertisement = getAdvertisement();

        LocalDate modifyEndDate = LocalDate.of(2021, 9, 10);

        // when
        advertisement.modifyEndDate(modifyEndDate);

        // then
        assertThat(advertisement.getEndDate()).isEqualTo(modifyEndDate);
    }

    @DisplayName("이미지 경로, 이름 변경")
    @Test
    void test_modify_image_path() throws Exception {
        // given
        Advertisement advertisement = getAdvertisement();

        ImageInfo imageInfo = new ImageInfo("modifyPath", "modifyFileName");

        // when
        advertisement.modifyImagePath(imageInfo);

        // then
        assertThat(advertisement.getImageInfo()).isEqualTo(imageInfo);
    }

    @DisplayName("제목 변경 시 잘못된 값 입력")
    @Test
    void test_modify_title_exception() throws Exception {
        Advertisement advertisement = getAdvertisement();

        String modifyTitle = "";

        assertThatThrownBy(() -> advertisement.modifyTitle(modifyTitle))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("wrong value injection on title");

        assertThatThrownBy(() -> advertisement.modifyTitle(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("wrong value injection on title");
    }

    @DisplayName("광고시작 데이터 변경시 잘못된 값 입력")
    @Test
    void test_modify_start_date_exception() throws Exception {
        Advertisement advertisement = getAdvertisement();

        LocalDate startDate = null;

        LocalDate exceedEndDate = LocalDate.of(2021, 9, 28);

        assertThatThrownBy(() -> advertisement.modifyStartDate(startDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("wrong date injection on start date");

        // 종료 날짜보다 큰값 입력
        assertThatThrownBy(() -> advertisement.modifyStartDate(exceedEndDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("exceed date injection than end date");
    }


    @DisplayName("광고종료 데이터 변경시 잘못된 값 입력")
    @Test
    void test_modify_end_date_exception() throws Exception {
        Advertisement advertisement = getAdvertisement();

        LocalDate endDate = null;
        LocalDate belowStartDate = LocalDate.of(2021, 7, 20);

        assertThatThrownBy(() -> advertisement.modifyEndDate(endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("wrong date injection on end date");

        assertThatThrownBy(() -> advertisement.modifyEndDate(belowStartDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("below date injection than start date");
    }



    private Advertisement getAdvertisement() {
        return Advertisement.builder()
                .title("제목")
                .startDate(LocalDate.of(2021, 7, 29))
                .endDate(LocalDate.of(2021, 8, 22))
                .imageInfo(new ImageInfo("defaultImagePath", "defaultImageFileName"))
                .destinationUrl("destinationUrl")
                .advertiseStatus(AdvertiseStatus.ON)
                .member(member)
                .build();
    }


}
