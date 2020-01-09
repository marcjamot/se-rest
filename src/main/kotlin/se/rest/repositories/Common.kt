package se.rest.repositories

import java.util.*

data class PostEntity(val id: UUID, val userId: UUID, val content: String)
data class UserEntity(val id: UUID, val name: String)
