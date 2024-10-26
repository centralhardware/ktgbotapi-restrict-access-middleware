package me.centralhardware.telegram

interface UserAccessChecker {

    fun checkAccess(userId: Long?): Boolean
}
