package com.neconico.neconicojpa.domain.notice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NoticeTest {
    private final String defaultTitle = "제목";
    private final String defaultContent = "내용";
    private final NoticeStatus defaultNoticeStatus = NoticeStatus.ON;

    @DisplayName("notice entity 생성")
    @Test
    void test_create_notice() throws Exception {
        Notice notice = getNotice();

        assertThat(notice.getTitle()).isEqualTo(defaultTitle);
        assertThat(notice.getContent()).isEqualTo(defaultContent);
        assertThat(notice.getNoticeStatus()).isEqualTo(defaultNoticeStatus);
    }

    @DisplayName("notice entity변경")
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

    @DisplayName("변경 시 잘못된 값 주입")
    @Test
    void test_modify_exception() throws Exception {
        String modifyTitle = "";
        String modifyContent = "";

        Notice notice = getNotice();


        assertThatThrownBy(() -> notice.modifyTitle(modifyTitle))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("not insert blank and null on modify title");


        assertThatThrownBy(() -> notice.modifyContent(modifyContent))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("not insert blank and null on modify content");

        assertThatThrownBy(() -> notice.modifyState(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("not insert null on modify state");
    }


    private Notice getNotice() {
        return Notice.builder()
                .title(defaultTitle)
                .content(defaultContent)
                .noticeStatus(defaultNoticeStatus)
                .build();
    }

}
