package se.rest.repositories


import io.vertx.axle.pgclient.PgPool
import io.vertx.pgclient.PgConnectOptions
import io.vertx.sqlclient.PoolOptions
import se.rest.PostgresConfig
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class Database {

    @Inject
    lateinit var postgresConfig: PostgresConfig
    private lateinit var pgPool: PgPool

    fun pool(): PgPool {
        if (!::pgPool.isInitialized) {
            val connectOptions = PgConnectOptions()
                    .setHost(postgresConfig.getHost())
                    .setPort(5432)
                    .setUser(postgresConfig.getUser())
                    .setPassword(postgresConfig.getPassword())
                    .setDatabase(postgresConfig.getDatabase())
            val poolOptions = PoolOptions()
                    .setMaxSize(5)
            pgPool = PgPool.pool(connectOptions, poolOptions)
        }

        return pgPool
    }
}
