package com.electusplus.reindex.utils;

import com.sun.crypto.provider.JceKeyStore;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public class KeyStoreProvider {

    static KeyStore keyStore;
    public static void main1(String args[]) throws Exception {
        //Creating the KeyStore object
        KeyStore keyStore = KeyStore.getInstance("JCEKS");

        //Loading the KeyStore object
        char[] keyStorePassword = "psw".toCharArray();
//            InputStream keyStoreData = new FileInputStream("keystore.ks");
        keyStore.load(new FileInputStream("keystore.jks"), keyStorePassword);

//        char[] password = "changeit".toCharArray();
//        String path = "C:/Program Files/Java/jre1.8.0_101/lib/security/cacerts";
//        java.io.FileInputStream fis = new FileInputStream(path);
//        keyStore.load(fis, password);

        //Creating the KeyStore.ProtectionParameter object
        KeyStore.ProtectionParameter protectionParam = new KeyStore.PasswordProtection(keyStorePassword);

        //Creating SecretKey object
        SecretKey mySecretKey = new SecretKeySpec("myPassword".getBytes(), "DSA");

        //Creating SecretKeyEntry object
        KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(mySecretKey);
        keyStore.setEntry("secretKeyAlias", secretKeyEntry, protectionParam);

        //Storing the KeyStore object
        java.io.FileOutputStream fos = null;
        fos = new java.io.FileOutputStream("keystore.jks");
        keyStore.store(fos, keyStorePassword);
        System.out.println("data stored");
    }


    public static void main(String[] args) {
        try {
            keyStore = KeyStore.getInstance("JCEKS");
            char[] keyStorePassword = "psw".toCharArray();
//            InputStream keyStoreData = new FileInputStream("keystore.ks");
            keyStore.load(new FileInputStream("keystore.jks"), keyStorePassword);
//            saveKeyToKeyStore(keyStorePassword, "testKey", "testValue");
            getKeyFromKeyStore(keyStorePassword);
//            createEmptyKeyStore("keystore.jks","psw");
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException | UnrecoverableEntryException e) {
            e.printStackTrace();
        }
    }

    public static String getKeyFromKeyStore(final char[] password) throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException {
        System.out.println(keyStore.aliases());
        return new String(keyStore.getKey("secretKeyAlias", password).getEncoded(), StandardCharsets.UTF_8);
    }

    public static void createEmptyKeyStore(final String keyStorePath, final String password)
            throws IOException,
            CertificateException,
            NoSuchAlgorithmException,
            KeyStoreException {
        keyStore.load(null, password.toCharArray());
        try (FileOutputStream fos = new FileOutputStream(keyStorePath)) {
            keyStore.store(fos, password.toCharArray());
        }
    }

    public static void loadKeyStore(final String keyStorePath, final String password) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream("newKeyStoreFileName.jks"), password.toCharArray());
    }

    public static void saveKeyToKeyStore(final char[] password, final String key, final String value) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        byte[] byteValue = value.getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = new SecretKeySpec(byteValue, "DSA");
        KeyStore.SecretKeyEntry secret
                = new KeyStore.SecretKeyEntry(secretKey);
        KeyStore.ProtectionParameter passwordProtection
                = new KeyStore.PasswordProtection(password);
        keyStore.setEntry(key, secret, passwordProtection);
    }

    public boolean isContainsKey(final String key) throws KeyStoreException {
        return keyStore.containsAlias(key);
    }

    public void deleteKey(final String key) throws KeyStoreException {
        keyStore.deleteEntry(key);
    }
}
