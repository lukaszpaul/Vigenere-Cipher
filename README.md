# Vigenere-Cipher
Encryption, Decryption, and Letter Analysis 

You are prompted to input what you would like to encrypt and what key you would like to use. The program then runs the inputted text and outputs the repeated key and the encrypted text.

You can input an encrypted text and it will decrypt it based off the key provided previously.

I was finally able to run my code with a file size of over a gigabyte by utilizing a Microsoft Azure Virtual Machine which allowed the letter frequency to be a portion of the time it took before. I ran the letter frequency off of enwik9 which is used for compression tests. Because of the way the Vigenere cipher encrypts the plaintext, running a letter frequency will not give enough information to decrypt the text. 

Trying to decrypt a phrase based on the letter frequency did not prove successful. I tried to replace E,F, and R with E, R, and I respectfully and none of these were correct nor made the phrase any more coherent. 

This encryption method is more secure than using substitution ciphers or Caesar cipher.
