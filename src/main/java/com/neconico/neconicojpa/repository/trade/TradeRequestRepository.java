package com.neconico.neconicojpa.repository.trade;

import com.neconico.neconicojpa.domain.trade.TradeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRequestRepository extends JpaRepository<TradeRequest, Long> {
}
