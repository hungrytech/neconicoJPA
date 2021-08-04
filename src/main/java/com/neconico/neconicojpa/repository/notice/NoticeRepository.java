package com.neconico.neconicojpa.repository.notice;

import com.neconico.neconicojpa.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
