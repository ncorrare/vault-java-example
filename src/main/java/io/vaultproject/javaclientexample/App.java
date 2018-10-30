package io.vaultproject.javaclientexample;
import java.util.HashMap;
import java.util.Map;

import com.bettercloud.vault.*;
import com.bettercloud.vault.response.LogicalResponse;

/**
 * Hello world from Vault!
 *
 */
public class App 
{
    public static void main( String[] args ) throws VaultException
    {
         /* The com.bettercloud.vault driver automatically reads a
          * a number of Environment Variables like VAULT_TOKEN or
          * VAULT_ADDR, you should ensure those are set properly
          * These are displayed just to ensure you have the
          * right ones for demo purposes.
          */
    	 Map<String, String> secrets = new HashMap<String, String>();
  	  	 secrets.put("value", "world");
  	  							  

  	  
       
        try {
        VaultSecret vault = new VaultSecret();
        
        //Write KV Secret
        LogicalResponse writeResponse = vault.setKvSecret("secret/hello", secrets);
        System.out.format( "Write request response : " + writeResponse.getRestResponse().getStatus() +"\n");
       
        //read KV Secret
        System.out.format( "value key in secret/hello is " + vault.getKvSecret() +"\n");
        
        //Encrypt plaintext
        String usingKey = "test";
        String plainText = "test input";
        
        Map<String, String> ciphertext = vault.encryptSecret(usingKey, plainText);
        
        System.out.format( "the encrypted Value is " + ciphertext.get("ciphertext") +"\n");
        
        
        //Decrypt ciphertext
        String plainTextResponse = vault.decryptSecret(usingKey, ciphertext.get("ciphertext"));
        System.out.format( "the decrypted Value is " + plainTextResponse +"\n");
        
        
        } catch(VaultException e) {
          System.out.println("Exception thrown: " + e);
        }
    }
}
