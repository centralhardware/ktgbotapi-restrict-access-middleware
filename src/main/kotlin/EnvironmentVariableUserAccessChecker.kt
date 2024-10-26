package me.centralhardware.telegram

class EnvironmentVariableUserAccessChecker : UserAccessChecker {
    private val allowedUsers =
        (System.getenv("ALLOWED_USERS") ?: "").let { it.split(",").map(String::toLong) }
    override fun checkAccess(userId: Long?) = userId?.let { allowedUsers.contains(userId) } ?: true
}
