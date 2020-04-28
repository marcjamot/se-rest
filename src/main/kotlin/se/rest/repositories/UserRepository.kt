package se.rest.repositories

import java.util.concurrent.CompletableFuture
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserRepository(private val database: Database) {

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
