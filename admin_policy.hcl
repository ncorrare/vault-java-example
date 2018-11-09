path "transit/*" {
 capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}


# This section grants all access on "secret/*". Further restrictions can be
# applied to this broad policy, as shown below.

path "pki/*" {
 capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}

path "secret/*" {
 capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}


path "aws/*" {
 capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}

path "aws/creds/*" {
 capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}


# Even though we allowed secret/*, this line explicitly denies
# secret/super-secret. This takes precedence.
path "secret/super-secret" {
  capabilities = ["deny"]
}

# Policies can also specify allowed, disallowed, and required parameters. Here
# the key "secret/restricted" can only contain "foo" (any value) and "bar" (one
# of "zip" or "zap").
path "secret/restricted" {
  capabilities = ["create"]
  allowed_parameters = {
    "foo" = []
    "bar" = ["zip", "zap"]
  }
  
}

path "auth/*"
{
  capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}

# List, create, update, and delete auth methods
path "sys/auth/*"
{
  capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}

# policies
path "sys/policy"
{
  capabilities = ["create", "update", "delete"]
  control_group = {
        factor "ops_manager" {
            identity {
                group_names = ["managers"]
                approvals = 1
            }
        }
    }
}


# List, create, update, and delete key/value secrets
path "secret/*"
{
  capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}

# Manage and manage secret engines broadly across Vault.
path "sys/mounts/*"
{
  capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}

# Read health checks
path "sys/health"
{
  capabilities = ["read", "sudo"]
}

# To perform Step 4
path "sys/capabilities-self"
{
  capabilities = ["create", "update"]
}

path "sys/*"
{
   capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}

path "sys/control-group"
{
   capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}


path "identity/*"
{
   capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}

path "ssh/*"
{
   capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}

path "ssh/my-role/*"
{
   capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}

path "sys/capabilities-self/*"
{
   capabilities = ["create", "read", "update", "delete", "list", "sudo"]
}
