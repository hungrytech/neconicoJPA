package com.neconico.neconicojpa.domain.trade;

public enum RequestStatus {
    PROCESSING("거래 중"), CANCEL("거래 취소"), COMPLETE("거래 완료");

    private String status;

    RequestStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
