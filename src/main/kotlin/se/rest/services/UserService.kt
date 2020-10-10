package se.rest.services

import se.rest.repositories.UserRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserService(private val userRepository: UserRepository) {

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
