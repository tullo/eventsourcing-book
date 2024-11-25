#!/bin/sh
# Configuration
KEYCLOAK_URL="http://localhost:8080"
REALM="dev"
ADMIN_USER="admin"
CLERK_USER="clerk"
PASSWORD="password"
ADMIN_ROLE="admin"
CLERK_ROLE="clerk"

# Login to Keycloak
/opt/keycloak/bin/kcadm.sh config credentials --server "$KEYCLOAK_URL" --realm master --user admin --password admin

# Create the realm
/opt/keycloak/bin/kcadm.sh create realms -s realm="$REALM" -s enabled=true

# Create the admin role
/opt/keycloak/bin/kcadm.sh create roles -r "$REALM" -s name="$ADMIN_ROLE"

# Create the clerk role
/opt/keycloak/bin/kcadm.sh create roles -r "$REALM" -s name="$CLERK_ROLE"

# Create the admin user
/opt/keycloak/bin/kcadm.sh create users -r "$REALM" -s username="$ADMIN_USER" -s enabled=true
/opt/keycloak/bin/kcadm.sh set-password -r "$REALM" --username "$ADMIN_USER" --new-password "$PASSWORD"

# Assign the admin role to the admin user
/opt/keycloak/bin/kcadm.sh add-roles -r "$REALM" --uusername "$ADMIN_USER" --rolename "$ADMIN_ROLE"

# Create the clerk user
/opt/keycloak/bin/kcadm.sh create users -r "$REALM" -s username="$CLERK_USER" -s enabled=true
/opt/keycloak/bin/kcadm.sh set-password -r "$REALM" --username "$CLERK_USER" --new-password "$PASSWORD"

# Assign the clerk role to the clerk user
/opt/keycloak/bin/kcadm.sh add-roles -r "$REALM" --uusername "$CLERK_USER" --rolename "$CLERK_ROLE"

echo "Setup complete."
echo "Realm: $REALM"
echo "Admin user: $ADMIN_USER / $PASSWORD (Role: $ADMIN_ROLE)"
echo "Clerk user: $CLERK_USER / $PASSWORD (Role: $CLERK_ROLE)"

CLIENT_ID="spring-boot-app"
REDIRECT_URI="http://localhost:8080/*"

# Create the client
/opt/keycloak/bin/kcadm.sh create clients -r "$REALM" \
    -s clientId="$CLIENT_ID" \
    -s enabled=true \
    -s protocol="openid-connect" \
    -s publicClient=false \
    -s directAccessGrantsEnabled=true \
    -s redirectUris=["$REDIRECT_URI"]

echo "Clienet created $CLIENT_ID"