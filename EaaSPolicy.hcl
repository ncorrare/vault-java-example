/*
 * to create a token using this policy: 
 *  vault token create -policy=eaas
 */

# Enable transit secrets engine
path "sys/mounts/transit" {
  capabilities = [ "create", "read", "update", "delete", "list" ]
}

# To read enabled secrets engines
path "sys/mounts" {
  capabilities = [ "read" ]
}

# Manage the transit secrets engine
path "transit/*" {
  capabilities = [ "create", "read", "update", "delete", "list" ]
}

#access secrets 
path "secret/*" {
 capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}
