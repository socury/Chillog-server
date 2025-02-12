package com.chatbot.domain.user.service

import com.chatbot.domain.user.dto.GetUserResponse
import com.chatbot.domain.user.entity.UserEntity
import com.chatbot.domain.user.exception.UserErrorCode
import com.chatbot.domain.user.repository.UserRepository
import com.chatbot.global.exception.CustomException
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class UserService (
    val userRepository: UserRepository,
) {
    fun formUser(user: UserEntity): GetUserResponse {
        return GetUserResponse(
            id = user.id,
            username = user.username,
            score = user.score,
            point = user.point
        )
    }

    fun getMe(principal: Principal): GetUserResponse {
        val user = userRepository.findByUsername(principal.name).orElseThrow{ CustomException(UserErrorCode.USER_NOT_FOUND) }
        return formUser(user)
    }
}