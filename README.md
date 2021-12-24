# Image Steganography Cryptography

Image steganography is a very secure way of communication of secret information as the message is hidden within the image. However, it is not difficult to extract information from this method if the attacker knows where to look. Therefore it is vulnerable to many attacks. 

* We want to create a more secure image communication system which is less susceptible to middle man attacks and eavesdropping. We plan on achieving this by encrypting the message before hiding it inside the image.
* We will use multiple symmetric and asymmetric encryption algorithms on the secret message before LSB image steganography.
* We want to make the cryptography system faster by using Chinese Remainder Theorem along with RSA. 
* For symmetric encryption we have gone through various journal papers and used Blowfish algorithm for the project which as mentioned, turns out to be a better fit for our application.
* The Least Significant Bit Image steganography method that we have used hides the data in RGB plane. This is a better way of embedding information in an image since it results in an image which is very similar to the original cover image.

### Blowfish Algorithm:
![image](https://user-images.githubusercontent.com/37493439/147320846-7c8e4b84-c6df-436e-bed7-0020ca689fd1.png)
