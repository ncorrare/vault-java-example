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
	
  /* For Reference check out the Hashicorp Vault EaaS learn page:
		  https://learn.hashicorp.com/vault/encryption-as-a-service/eaas-transit
		  */
		
    public static void main( String[] args ) throws VaultException
    {
         /* The com.bettercloud.vault driver automatically reads a
          * a number of Environment Variables like VAULT_TOKEN or
          * VAULT_ADDR, you should ensure those are set properly
          * These are displayed just to ensure you have the
          * right ones for demo purposes.
          */
    	
    	
    	//Create secrets to save
    	 Map<String, Object> secrets = new HashMap<String, Object>();
  	  	 secrets.put("value", "world");
  	    	  
       
        try {
        VaultSecret vault = new VaultSecret();
        
        //Write KV Secret
        LogicalResponse writeResponse = vault.setKvSecret("secret/hello", secrets);
        System.out.format( "Write request response : " + writeResponse.getRestResponse().getStatus() +"\n");
       
        //read KV Secret
        System.out.format( "value secret in secret/hello is " + vault.getKvSecret() +"\n");
        
       //////////////////////////////////////////////////////////////////////////////////////////////////////
        //Create Encryption Keys
        String encryptionKey = "demo";
        vault.createKeys(encryptionKey);
        
        //Encrypt plaintext
        
        String plainText = "test input";
        
        Map<String, String> ciphertext = vault.encryptSecret(encryptionKey, plainText);
        
        System.out.format( "the encrypted Value is " + ciphertext.get("ciphertext") +"\n");
        
        
        //Decrypt ciphertext
        String plainTextResponse = vault.decryptSecret(encryptionKey, ciphertext.get("ciphertext"));
        System.out.format( "the decrypted Value is " + plainTextResponse +"\n");
        
        
        //Rotate Keys
        vault.rotateKeys(encryptionKey);
        
      //Encrypt plaintext after key rotate
        plainText = "test 2";
        
        Map<String, String> ciphertext2 = vault.encryptSecret(encryptionKey, plainText);
        
        System.out.format( "the encrypted Value is " + ciphertext2.get("ciphertext") +"\n");
        
        //Decrypt ciphertext
         plainTextResponse = vault.decryptSecret(encryptionKey, ciphertext.get("ciphertext"));
        System.out.format( "the decrypted Value is " + plainTextResponse +"\n");
        
        
        } catch(VaultException e) {
          System.out.println("Exception thrown: " + e);
        }
        
        
    }
}
