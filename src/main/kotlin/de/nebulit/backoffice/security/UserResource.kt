package de.nebulit.backoffice.security

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserResource {
  @GetMapping("/user", produces = ["application/json"])
  fun user(authentication: Authentication): Authentication = authentication
}
