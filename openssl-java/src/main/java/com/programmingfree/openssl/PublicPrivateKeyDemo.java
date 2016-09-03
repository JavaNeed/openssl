package com.programmingfree.openssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class PublicPrivateKeyDemo {
	private static BigInteger publicKeyModulus;
	private static BigInteger privateKeyModulus;
	private static BigInteger privateKeyExponent;
	private static BigInteger publicKeyExponent;
	private static File privateKeyFile;
	private static File publicKeyFile;

	public static void main(String[] args) {
		privateKeyFile = new File(PublicPrivateKeyDemo.class.getClassLoader().getResource("keys/private.der").getFile());
		publicKeyFile = new File(PublicPrivateKeyDemo.class.getClassLoader().getResource("keys/public.der").getFile());

		try {
            loadKeys();
        } catch (IOException | GeneralSecurityException e) {
            System.out.println(e.getMessage());
        }
	}
	
	private static void loadKeys() throws IOException, GeneralSecurityException {
		// This to load Public Keys
        byte[] publicKeyBytes = new byte[(int) publicKeyFile.length()];
        FileInputStream publicFis = null;
        try {
            publicFis = new FileInputStream(publicKeyFile);
            if (publicFis.read(publicKeyBytes) > 0) {
                X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
                KeyFactory factory = KeyFactory.getInstance("RSA");
                RSAPublicKey pubKey = (RSAPublicKey) factory.generatePublic(publicKeySpec);
                BigInteger pKeyModulus = pubKey.getModulus();
                BigInteger pKeyExponent = pubKey.getPublicExponent();
                System.out.println("PUBLIC KEY EXPO : "+pKeyExponent);
            }
        } finally {
            if (publicFis != null) {
                publicFis.close();
            }
        }

        // This is to load private keys
        byte[] privateKeyBytes = new byte[(int) privateKeyFile.length()];
        FileInputStream privateFis = null;
        try {
            privateFis = new FileInputStream(privateKeyFile);
            if (privateFis.read(privateKeyBytes) > 0) {
                PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                RSAPrivateKey privKey = (RSAPrivateKey) keyFactory.generatePrivate(spec);
                BigInteger pKeyModulus = privKey.getModulus();
                BigInteger pKeyExponent = privKey.getPrivateExponent();
                System.out.println("PRIVATE KEY : "+pKeyExponent);
            }
        } finally {
            if (privateFis != null) {
                privateFis.close();
            }
        }
    }
}
