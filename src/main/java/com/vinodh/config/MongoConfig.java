package com.vinodh.config;

import com.bol.crypt.CryptVault;
import com.bol.secure.CachedEncryptionEventListener;
import com.bol.secure.ReflectionEncryptionEventListener;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Base64;

/**
 * @author thimmv
 * @createdAt 23-06-2019 22:00
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.vinodh.repository")
@PropertySource("classpath:application.yml")
public class MongoConfig extends AbstractMongoConfiguration {

    // normally you would use @Value to wire a property here
    private static final byte[] secretKey = Base64.getDecoder().decode("hqHKBLV83LpCqzKpf8OvutbCs+O5wX5BPu3btWpEvXA=");
    private static final byte[] oldKey = Base64.getDecoder().decode("cUzurmCcL+K252XDJhhWI/A/+wxYXLgIm678bwsE2QM=");

//    @Value("${spring.data.mongodb.secretKey}")
//    private String secretKey;
//
//    @Value("${spring.data.mongodb.oldKey}")
//    private String oldKey;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(new MongoClientURI(uri));
    }

    @Override
    @Bean
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        MappingMongoConverter converter = super.mappingMongoConverter();
        // NB: without overriding defaultMongoTypeMapper, an _class field is put in every document
        // since we know exactly which java class a specific document maps to, this is surplus
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Bean
    public CryptVault cryptVault() {
        return new CryptVault()
                .with256BitAesCbcPkcs5PaddingAnd128BitSaltKey(0, oldKey)
                .with256BitAesCbcPkcs5PaddingAnd128BitSaltKey(1, secretKey)
                // can be omitted if it's the highest version
                .withDefaultKeyVersion(1);
    }

    @Bean
    @Primary
    public ReflectionEncryptionEventListener encryptionEventListener(CryptVault cryptVault) {
        return new ReflectionEncryptionEventListener(cryptVault);
    }

//    @Bean
//    public MongoEncryptionListener encryptionEventListener() {
//        return new MongoEncryptionListener();
//    }


}
