package se.rest.services

import se.rest.repositories.BlogRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class BlogService(private val blogRepository: BlogRepository) {

    fun getPosts(): List<Post> {
        return blogRepository.getPosts()
                .thenApply { post ->
                    post.map {
                        Post(
                                id = it.id.toString(),
                                user_id = it.userId.toString(),
                                content = it.content
                        )
                    }
                }
                .exceptionally { emptyList() }
                .join()
    }
}
