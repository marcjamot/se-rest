package se.rest.controllers

import se.rest.services.User
import se.rest.services.UserService
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class UserController {

    @Inject
    lateinit var userService: UserService

    data class GetUsersResponse(val users: List<User>)

    @GET
    fun getUsers() = GetUsersResponse(users = userService.getUsers())
}
