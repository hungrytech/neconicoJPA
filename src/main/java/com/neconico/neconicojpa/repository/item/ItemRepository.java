package com.neconico.neconicojpa.repository.item;

import com.neconico.neconicojpa.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
