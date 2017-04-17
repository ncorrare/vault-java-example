package io.vaultproject.javaclientexample;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String vaulttoken = System.getenv("VAULT_TOKEN");
        System.out.format( "Using Vault Token %s", vaulttoken );
    }
}
