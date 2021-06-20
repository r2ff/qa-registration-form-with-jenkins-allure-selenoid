package config;

import org.aeonbits.owner.ConfigFactory;

public class Credentials {
    public static CredentialsConfig cc =
            ConfigFactory.create(CredentialsConfig.class, System.getProperties());
}
