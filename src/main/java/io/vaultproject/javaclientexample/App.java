package io.vaultproject.javaclientexample;
import com.bettercloud.vault.*;

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

        String vaulttoken = System.getenv("VAULT_TOKEN");
        String vaulthost = System.getenv("VAULT_ADDR");
        System.out.format( "Using Vault Host %s\n", vaulthost);
        System.out.format( "With Vault Token %s\n", vaulttoken );
        /* This should be a separate method called from Main, then
         * again for simplicity...
         */
        final VaultConfig config = new VaultConfig().build();
        final Vault vault = new Vault(config);
        try {
        final String value = vault.logical()
                       .read("secret/hello")
                       .getData().get("value");
        System.out.format( "value key in secret/hello is " + value +"\n");
        } catch(VaultException e) {
          System.out.println("Exception thrown: " + e);
        }
    }
}
