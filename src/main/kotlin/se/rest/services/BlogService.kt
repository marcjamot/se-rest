package se.rest.services

import se.rest.repositories.BlogRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class BlogService {

    @Inject
    lateinit var blogRepository: BlogRepository

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
                .exceptionally { println(it.message); emptyList() }
                .join()
    }
}
