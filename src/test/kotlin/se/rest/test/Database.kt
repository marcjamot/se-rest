package se.rest.test

import org.testcontainers.containers.PostgreSQLContainer
import se.rest.PostgresConfig
import se.rest.repositories.Database
import java.io.File

const val DEFAULT_IMAGE = "postgres:13-alpine"
const val DEFAULT_USER = "test"
const val DEFAULT_PASSWORD = "test"
const val DEFAULT_DATABASE = "test"

data class Config(val _host: String, val _port: Int, val _user: String, val _password: String, val _database: String) : PostgresConfig {

    override fun getHost(): String {
        return _host
    }

    override fun getPort(): Int {
        return _port
    }

    override fun getUser(): String {
        return _user
    }

    override fun getPassword(): String {
        return _password
    }

    override fun getDatabase(): String {
        return _database
    }
}

class PostgreSQL(image: String = DEFAULT_IMAGE) : PostgreSQLContainer<PostgreSQL>(image) {

    lateinit var database: Database

    fun withDefaults(): PostgreSQL {
        withUsername(DEFAULT_USER)
        withPassword(DEFAULT_PASSWORD)
        withDatabaseName(DEFAULT_DATABASE)
        return this
    }

    fun setUp() {
        database = Database(Config(
                _host = containerIpAddress,
                _port = getMappedPort(POSTGRESQL_PORT),
                _user = username,
                _password = password,
                _database = databaseName))

        val url = javaClass.getResource("/sql")
        val files = (File(url.toURI()).listFiles()!!).apply {
            sortWith(compareBy { it.name })
        }

        val pool = database.pool()
        files.forEach {
            pool.query(it.readText()).execute().toCompletableFuture().join()
        }
    }
}
