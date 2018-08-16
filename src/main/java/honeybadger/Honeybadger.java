package honeybadger;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class Honeybadger {

    public static void main(String[] args) {
    	// Adding the encryption password at runtime.
    	System.setProperty("jasypt.encryptor.password", "honey");
        SpringApplication.run(Honeybadger.class, args);
    }

}