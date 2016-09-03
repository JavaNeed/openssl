openssl-java
---------------

- Down the OpenSSL from https://code.google.com/archive/p/openssl-for-windows/downloads
- Extract the zip and go to the /bin location
- execute the following command (This will create the RSA key)
```
openssl genrsa -out key.pem 2048
```

- To create the private key, execute following (Rename key.der to private.der)  
``` 
openssl pkcs8 -topk8 -nocrypt -in key.pem -inform PEM -out key.der -outform DER
```

- To create the public key, execute the following command
```
openssl rsa -in key.pem -pubout -outform DER -out public.der
```


@ref:
----
- http://stackoverflow.com/questions/11410770/load-rsa-public-key-from-file  (This works great !!!)
- http://codeartisan.blogspot.in/2009/05/public-key-cryptography-in-java.html
- http://stackoverflow.com/questions/39311157/only-rsaprivate-crt-keyspec-and-pkcs8encodedkeyspec-supported-for-rsa-private

This is just for info:
-------------------
- https://rietta.com/blog/2012/01/27/openssl-generating-rsa-key-from-command/
- https://docs.typo3.org/typo3cms/extensions/naw_single_signon/ExtSingleSign-on/Configuration/CreatingRsaPublicPrivateKeyPairWithOpenssl/Index.html
- http://stackoverflow.com/questions/5244129/use-rsa-private-key-to-generate-public-key