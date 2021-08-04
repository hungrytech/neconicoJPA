package com.neconico.neconicojpa.domain.trade;

import com.neconico.neconicojpa.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TradeRequestTest {

    @Mock Member member;


    @DisplayName("거래 상태 변경")
    @Test
    void test_trade_request() throws Exception {
        TradeRequest tradeRequest = TradeRequest.builder()
                .member(member)
                .requestStatus(RequestStatus.PROCESSING)
                .build();

        tradeRequest.modifyRequestStatus(RequestStatus.COMPLETE);

        assertThat(tradeRequest.getRequestStatus()).isEqualTo(RequestStatus.COMPLETE);
    }

}
