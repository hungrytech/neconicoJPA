package com.neconico.neconicojpa.domain.notice;

import com.neconico.neconicojpa.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static org.springframework.util.StringUtils.hasText;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private NoticeStatus noticeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Notice(String title, String content, NoticeStatus noticeStatus, Member member) {
        this.title = title;
        this.content = content;
        this.noticeStatus = noticeStatus;
        setMember(member);
    }

    public void modifyTitle(String modifyTitle) {
        if(!hasText(modifyTitle)) {
            throw new IllegalArgumentException("not insert blank and null on modify title");
        }

        title = modifyTitle;
    }

    public void modifyContent(String modifyContent) {
        if(!hasText(modifyContent)) {
            throw new IllegalArgumentException("not insert blank and null on modify content");
        }

        content = modifyContent;
    }

    public void modifyState(NoticeStatus modifyNoticeStatus) {
        if(modifyNoticeStatus == null) {
            throw new IllegalArgumentException("not insert null on modify state");
        }

        noticeStatus = modifyNoticeStatus;
    }

    private void setMember(Member member) {
        this.member = member;
    }
}
