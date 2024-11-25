package de.nebulit.backoffice.security

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Component

@Component
class CustomOIDCUserService : OidcUserService() {
  override fun loadUser(userRequest: OidcUserRequest): OidcUser {
    val oidcUser = super.loadUser(userRequest) as OidcUser

    // Extract claims (e.g., roles or groups) from the token

    // Map roles to GrantedAuthority
    val claims =
        oidcUser.idToken.getClaimAsMap("realm_access")["roles"] as List<String>? ?: emptyList()

    val authorities = claims.map { role -> SimpleGrantedAuthority("ROLE_" + role.uppercase()) }

    // Return a new user with mapped authorities
    return DefaultOidcUser(authorities, oidcUser.idToken, oidcUser.userInfo)
  }
}
