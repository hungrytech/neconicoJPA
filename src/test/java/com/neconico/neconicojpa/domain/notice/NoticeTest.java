package com.neconico.neconicojpa.domain.notice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NoticeTest {

    @DisplayName("notice 정보 변경")
    @Test
    void test_modify_notice() throws Exception {
        // given
        Notice notice = getNotice();

        String modifyTitle = "변경제목";
        String modifyContent = "변경내용";

        // when
        notice.modifyTitle(modifyTitle);
        notice.modifyContent(modifyContent);
        notice.modifyState(NoticeStatus.CLOSE);

        // then
        assertThat(notice.getTitle()).isEqualTo(modifyTitle);
        assertThat(notice.getContent()).isEqualTo(modifyContent);
        assertThat(notice.getNoticeStatus()).isEqualTo(NoticeStatus.CLOSE);
    }

    @DisplayName("title 변경 시 잘못된 값 주입")
    @Test
    void test_modify_title_exception() throws Exception {
        String modifyTitle = "";


        Notice notice = getNotice();


        assertThatThrownBy(() -> notice.modifyTitle(modifyTitle))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("not insert blank and null on modify title");

    }

    @DisplayName("content 변경 시 잘못된 값 주입")
    @Test
    void test_modify_content_exception() throws Exception {
        String modifyContent = "";

        Notice notice = getNotice();

        assertThatThrownBy(() -> notice.modifyContent(modifyContent))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("not insert blank and null on modify content");
    }

    @DisplayName("status 변경 시 잘못된 값 주입")
    @Test
    void test_modify_status_exception() throws Exception {
        Notice notice = getNotice();

        assertThatThrownBy(() -> notice.modifyState(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("not insert null on modify state");
    }

    private Notice getNotice() {
        return Notice.builder()
                .title("제목")
                .content("내용")
                .noticeStatus(NoticeStatus.ON)
                .build();
    }

}
