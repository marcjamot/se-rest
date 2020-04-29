package se.rest.controllers

import se.rest.services.BlogService
import se.rest.services.Post
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class PostController {

    @Inject
    lateinit var blogService: BlogService

    data class GetPostsResponse(val posts: List<Post>)

    @GET
    fun index() = GetPostsResponse(posts = blogService.getPosts())
}
