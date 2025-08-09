package com.fugisawa.dp4_decorator

// Problema:
// Adicionar funcionalidades a um objeto de forma flexível e dinâmica
// evitando explosão de subclasses
// evitando modificar o código original
// possibilitando compor funcionalidades em tempo de execução.

// Interface base
interface UserRepository {
    fun getFullName(userId: String): String?
    fun setFullName(userId: String, fullName: String)
}

// Implementação concreta
class UserRepositoryImpl : UserRepository {
    private val userList = mutableMapOf<String, String>()
    override fun getFullName(userId: String) = userList[userId]
    override fun setFullName(userId: String, fullName: String) {
        userList[userId] = fullName
    }
}

// Decorator: faz forwarding manual dos métodos
class UsernameValidator(private val repository: UserRepository) : UserRepository {
    override fun getFullName(userId: String): String? = repository.getFullName(userId)

    override fun setFullName(userId: String, fullName: String) {
        require(fullName.length in MIN_NAME_LENGTH..MAX_NAME_LENGTH) { "User full name is not of valid length: $fullName" }
        repository.setFullName(userId, fullName)
    }

    companion object {
        private const val MAX_NAME_LENGTH = 20
        private const val MIN_NAME_LENGTH = 4
    }
}

fun main() {
    val rawRepo = UserRepositoryImpl()
    val repo = UsernameValidator(rawRepo)
    repo.setFullName("lucasfugisawa", "Lucas Fugisawa") // OK
    println(repo.getFullName("lucasfugisawa")) // Lucas Fugisawa

    // repo.setFullName("lucasfugisawa", "Lucas Com Um Sobrenome Muito Comprido") // Lança exceção!
}
