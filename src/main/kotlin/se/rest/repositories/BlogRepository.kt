package se.rest.repositories

import java.util.concurrent.CompletableFuture
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class BlogRepository {

    @Inject
    lateinit var database: Database

    fun getPosts(): CompletableFuture<List<PostEntity>> {
        return database.pool()
                .preparedQuery("SELECT id, user_id, content FROM posts")
                .thenApply { row ->
                    row.map {
                        PostEntity(
                                id = it.getUUID("id"),
                                userId = it.getUUID("user_id"),
                                content = it.getString("content")
                        )
                    }
                }
                .toCompletableFuture()
    }
}
