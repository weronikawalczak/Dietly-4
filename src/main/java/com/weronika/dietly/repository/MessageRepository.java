package com.weronika.dietly.repository;

import com.weronika.dietly.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message, Long> {
}
