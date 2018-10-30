/**
 * 
 */
package io.vaultproject.javaclientexample;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;

/**
 * @author phatb
 *
 */
public class VaultSecret {
	 Vault vault;
	 
	  public VaultSecret() throws VaultException {
		  String vaulttoken = System.getenv("VAULT_TOKEN");
	        String vaulthost = System.getenv("VAULT_ADDR");
	        System.out.format( "Using Vault Host %s\n", vaulthost);
	        System.out.format( "With Vault Token %s\n", vaulttoken );
	        /* This should be a separate method called from Main, then
	         * again for simplicity...
	         */
	         VaultConfig config = new VaultConfig().build();
	        vault = new Vault(config);
	}

	  public LogicalResponse setKvSecret(String path, Map<String, String> values){
		  LogicalResponse writeResponse = null;		   
		  try {
					    	  
					    	  // Write operation
					    	    writeResponse = vault.logical().write("secret/hello", values);
		      	
		      } catch(VaultException e) {
		        System.out.println("Exception thrown: " + e);
		      }
			return writeResponse;
		  

		  }
	
	  public String getKvSecret(){
		 String value = "";
	      try {
	      	  value = vault.logical()
	                     .read("secret/hello")
	                     .getData().get("value");
	      	
	      } catch(VaultException e) {
	        System.out.println("Exception thrown: " + e);
	      }
		return value;
	  

	  }
	
	  public Map<String, String> encryptSecret(String key, String plainText){
		  String transit = "transit/encrypt/"+key;
		  String encodedString = Base64.getEncoder().encodeToString(plainText.getBytes());
	        
	        Map<String, String> toEncrypt = new HashMap<String, String>();
	        toEncrypt.put("plaintext", encodedString);
		  
		  LogicalResponse writeResponse = null;		   
		  try {
					    	  
					    	  // Write operation
					    	    writeResponse = vault.logical().write(transit, toEncrypt);
					    	    
		      } catch(VaultException e) {
		        System.out.println("Exception thrown: " + e);
		      }
			return writeResponse.getData();
		  

		  }
	  
	  public String decryptSecret(String key, String cipherText){
		  String transit = "transit/decrypt/"+key;
		  String plainText = null;
		        
	        Map<String, String> toDecrypt = new HashMap<String, String>();
	        toDecrypt.put("ciphertext", cipherText);
		  
		  LogicalResponse writeResponse = null;		   
		  try {
					    	  
					    	  // Write operation
					    	    writeResponse = vault.logical().write(transit, toDecrypt);
					    	    
					    	    byte[] decodedBytes = Base64.getDecoder().decode(writeResponse.getData().get("plaintext"));
					    	    plainText = new String(decodedBytes);
					    	    
		      } catch(VaultException e) {
		        System.out.println("Exception thrown: " + e);
		      }
			return plainText;
		  

		  }
	

}
