package com.neconico.neconicojpa.repository.advertisement;

import com.neconico.neconicojpa.domain.advertisement.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
