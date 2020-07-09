
path "/transit/encrypt/MyKey"{
  capabilities = ["create", "read", "update", "delete", "list"]
  }


path "/transit/decrypt/MyKey"{
   capabilities = ["create", "read", "update", "delete", "list"]
  }

path "secret/*" {
  capabilities = ["create", "read", "update", "delete", "list"]
}

path "/transit/keys/*"{
  capabilities = ["create", "read", "update", "delete", "list"]
  allowed_parameters = {
    "type" = ["aes256-gcm96", "rsa-2048"]
  }
  }
