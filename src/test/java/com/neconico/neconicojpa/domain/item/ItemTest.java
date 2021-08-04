package com.neconico.neconicojpa.domain.item;

import com.neconico.neconicojpa.domain.member.Member;
import com.neconico.neconicojpa.domain.vo.ImageInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ItemTest {

    @Mock Member member;

    @DisplayName("아이템 제목 변경")
    @Test
    void test_modify_title() throws Exception {
        Item item = getItem();


        String modifyTitle = "변경된 제목";
        item.modifyTitle(modifyTitle);

        assertThat(item.getTitle()).isEqualTo(modifyTitle);

    }

    @DisplayName("아이템 제목변경 시 공백 주입")
    @Test
    void test_modify_title_exception() throws Exception {
        Item item = getItem();

        String modifyTitle = "";

        assertThatThrownBy(() -> item.modifyTitle(modifyTitle))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("wrong value injection on item title");

    }

    @DisplayName("아이템 내용 변경")
    @Test
    void test_modify_content() throws Exception {
        Item item = getItem();

        String modifyContent = "변경된 내용";
        item.modifyContent(modifyContent);


        assertThat(item.getContent()).isEqualTo(modifyContent);
    }

    @DisplayName("아이템 내용변경 시 공백 주입")
    @Test
    void test_modify_content_exception() throws Exception {
        Item item = getItem();

        String modifyContent = "";

        assertThatThrownBy(() -> item.modifyContent(modifyContent))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("wrong value injection on item content");
    }

    @DisplayName("이미지 파일 정보 변경")
    @Test
    void test_modify_image_info() throws Exception {
        Item item = getItem();
        ImageInfo modifyInfo = new ImageInfo("modify path", "modify name");

        item.modifyImageInfo(modifyInfo);

        assertThat(item.getImageInfo().getImagePath()).isEqualTo(modifyInfo.getImagePath());
        assertThat(item.getImageInfo().getImageFileName()).isEqualTo(modifyInfo.getImageFileName());
    }

    @DisplayName("거래지역 변경")
    @Test
    void test_modify_trade_area() throws Exception {
        Item item = getItem();
        String modifyTradeArea = "modify area";

        item.modifyTradeArea(modifyTradeArea);

        assertThat(item.getTradeArea()).isEqualTo(modifyTradeArea);
    }

    @DisplayName("거래지역 변경 시 공백 주입")
    @Test
    void test_modify_trade_area_exception() throws Exception {
        Item item = getItem();

        String modifyTradeArea = "";

        assertThatThrownBy(() -> item.modifyTradeArea(modifyTradeArea))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("wrong value injection on trade area");
    }

    @DisplayName("조회수 변경")
    @Test
    void test_modify_hits() throws Exception {
        Item item = getItem();

        long hits = item.getHits();

        item.increaseHits();

        assertThat(item.getHits()).isEqualTo(hits + 1);
    }


    private Item getItem() {
        ShippingPrice shippingPrice = ShippingPrice.convertShippingPrice("yes");

        return Item.builder()
                .member(member)
                .title("기본 제목")
                .content("기본 내용")
                .imageInfo(new ImageInfo("image path", "image name"))
                .price(10000)
                .hits(0)
                .shippingPrice(shippingPrice)
                .tradeArea("서울시 강남구")
                .build();
    }


}
