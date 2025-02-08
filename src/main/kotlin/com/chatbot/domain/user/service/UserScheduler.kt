package com.chatbot.domain.user.service

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class UserScheduler (
    private val entityManager: EntityManager
) {
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    fun resetUserPoints() {
        entityManager.createQuery("UPDATE UserEntity u SET u.point = 0").executeUpdate()
    }
}