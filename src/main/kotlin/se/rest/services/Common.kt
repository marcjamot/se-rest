package se.rest.services

data class Post(val id: String, val user_id: String, val content: String)
data class User(val id: String, val name: String)
