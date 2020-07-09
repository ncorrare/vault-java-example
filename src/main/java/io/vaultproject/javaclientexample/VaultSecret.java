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
	        final VaultConfig config = new VaultConfig()
                    .address(vaulthost)
                    .token(vaulttoken)
                    .build();
	        vault = new Vault(config,1);
	}

	  public LogicalResponse setKvSecret(String path, Map<String, Object> secrets){
		  LogicalResponse writeResponse = null;		   
		  try {
					    	  
					    	  // Write operation
					    	    writeResponse = vault.logical().write("secret/hello", secrets);
		      	
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
	        
	        Map<String, Object> toEncrypt = new HashMap<String, Object>();
	        toEncrypt.put("plaintext", encodedString);
		  
		  LogicalResponse writeResponse = null;		   
		  try {
					    	  
					    	  // Write operation
					    	    writeResponse = vault.logical().write(transit, toEncrypt);
					    	    
		      } catch(Exception e) {
		        System.out.println("Exception thrown: " + e);
		      }
			return writeResponse.getData();
		  

		  }
	  
	  public String decryptSecret(String key, String cipherText){
		  String transit = "transit/decrypt/"+key;
		  String plainText = null;
		        
	        Map<String, Object> toDecrypt = new HashMap<String, Object>();
	        toDecrypt.put("ciphertext", cipherText);
		  
		  LogicalResponse writeResponse = null;		   
		  try {
					    	  
					    	  // Write operation
					    	    writeResponse = vault.logical().write(transit, toDecrypt);
					    	    
					    	    byte[] decodedBytes = Base64.getDecoder().decode(writeResponse.getData().get("plaintext"));
					    	    plainText = new String(decodedBytes);
					    	    
		      } catch(Exception e) {
		        System.out.println("Exception thrown: " + e);
		      }
			return plainText;
		  

		  }

	public void rotateKeys(String key) {
				 String transit = "transit/keys/"+key+"/rotate";
				 
				 Map<String, Object> empty = new HashMap<String, Object>();
		  
		  LogicalResponse writeResponse = null;		   
		  try {
					    	  
					    	  // Write operation
					    	    writeResponse = vault.logical().write(transit, empty);
					    	    
					    	    System.out.println("keys rotated: " + writeResponse.getRestResponse().getStatus() );
					    	    
		      } catch(VaultException e) {
		        System.out.println("Exception thrown: " + e);
		      }
			
		  
		
		
	}

	public void createKeys(String key) {
		 String transit = "transit/keys/"+key;
		 
		 Map<String, Object> empty = new HashMap<String, Object>();
  
  LogicalResponse writeResponse = null;		   
  try {
			    	  
			    	  // Write operation
			    	    writeResponse = vault.logical().write(transit, empty);
			    	    
			    	    System.out.println("keys created: " + writeResponse.getRestResponse().getStatus() );
			    	    
      } catch(Exception e) {
        System.out.println("Exception thrown: " + e);
      }
	
		
	}
	

}
