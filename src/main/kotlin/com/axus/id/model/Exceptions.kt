package com.axus.id.model

class InsufficientPermissionsException : RuntimeException("Not enough permissions.")
class PermissionsListIsTooLongException(override val message: String) : RuntimeException()