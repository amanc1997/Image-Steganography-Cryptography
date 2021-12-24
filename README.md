# Secure Image Steganography with Blowfish, RSA and Chinese Remainder Theorem

Image steganography is a very secure way of communication of secret information as the message is hidden within the image. However, it is not difficult to extract information from this method if the attacker knows where to look. Therefore it is vulnerable to many attacks. 

* We want to create a more secure image communication system which is less susceptible to middle man attacks and eavesdropping. We plan on achieving this by encrypting the message before hiding it inside the image.
* We will use multiple symmetric and asymmetric encryption algorithms on the secret message before LSB image steganography.
* We want to make the cryptography system faster by using Chinese Remainder Theorem along with RSA. 
* For symmetric encryption we have gone through various journal papers and used Blowfish algorithm for the project which as mentioned, turns out to be a better fit for our application.
* The Least Significant Bit Image steganography method that we have used hides the data in RGB plane. This is a better way of embedding information in an image since it results in an image which is very similar to the original cover image.

### Blowfish Algorithm:
![image](https://user-images.githubusercontent.com/37493439/147320846-7c8e4b84-c6df-436e-bed7-0020ca689fd1.png)

### F function in Blowfish:
![image](https://user-images.githubusercontent.com/37493439/147321002-91ef9163-7fe4-4c7a-b90d-95da996462ad.png)

### RSA using CRT:
![image](https://user-images.githubusercontent.com/37493439/147321023-7753d984-0cbf-413c-8b4b-29d9701d5525.png)

The idea is to encrypt the secret message using symmetric and asymmetric encryption algorithms. This encrypted message cannot be decoded by attackers easily as it has been encrypted twice using strong algorithms. The encrypted data is then embedded inside a cover image. This image looks normal to any attacker’s eye. The receiver can then extract the message from the image and decrypt the encrypted data to get the original message.
