package se.rest

import io.quarkus.arc.config.ConfigProperties

@ConfigProperties(prefix = "postgres")
interface PostgresConfig {
    fun getHost(): String
    fun getPort(): Int
    fun getUser(): String
    fun getPassword(): String
    fun getDatabase(): String
}
