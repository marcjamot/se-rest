package se.rest.repositories

import java.util.concurrent.CompletableFuture
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class UserRepository {

    @Inject
    lateinit var database: Database

    fun getUsers(): CompletableFuture<List<UserEntity>> {
        return database.pool()
                .preparedQuery("SELECT id, name FROM users")
                .thenApply { row ->
                    row.map {
                        UserEntity(
                                id = it.getUUID("id"),
                                name = it.getString("name")
                        )
                    }
                }
                .toCompletableFuture()
    }
}
