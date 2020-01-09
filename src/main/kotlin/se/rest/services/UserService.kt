package se.rest.services

import se.rest.repositories.UserRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class UserService {

    @Inject
    lateinit var userRepository: UserRepository

    fun getUsers(): List<User> = userRepository.getUsers()
            .thenApply { user ->
                user.map {
                    User(
                            id = it.id.toString(),
                            name = it.name
                    )
                }
            }
            .exceptionally { emptyList() }
            .join();
}
