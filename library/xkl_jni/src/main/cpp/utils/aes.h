//
// Created by SkySeraph on 2016/1/28.
//

#ifndef MTDEMO_AES_H
#define MTDEMO_AES_H


#ifndef SECUREKEYS_AES_H
#define SECUREKEYS_AES_H

/*************************** HEADER FILES ***************************/
#include <stddef.h>
#include <string>

/****************************** MACROS ******************************/
#define AES_BLOCK_SIZE 16               // AES operates on 16 bytes at a time

/**************************** DATA TYPES ****************************/
typedef unsigned char BYTE;            // 8-bit byte
typedef unsigned int WORD;             // 32-bit word, change to "long" for 16-bit machines

/*********************** FUNCTION DECLARATIONS **********************/
///////////////////
// AES
///////////////////
// Key setup must be done before any AES decryption function can be used.
void aes_key_setup(const BYTE key[],          // The key, must be 128, 192, or 256 bits
                   WORD w[],                  // Output key schedule to be used later
                   int keysize);              // Bit length of the key, 128, 192, or 256

void aes_decrypt(const BYTE in[],             // 16 bytes of ciphertext
                 BYTE out[],                  // 16 bytes of plaintext
                 const WORD key[],            // From the key setup
                 int keysize);                // Bit length of the key, 128, 192, or 256

///////////////////
// AES - CBC
///////////////////
int aes_decrypt_cbc(const BYTE in[],          // Plaintext
                    size_t in_len,            // Must be a multiple of AES_BLOCK_SIZE
                    BYTE out[],               // Ciphertext, same length as plaintext!
                    const WORD key[],         // From the key setup
                    int keysize,              // Bit length of the key, 128, 192 or 256
                    const BYTE iv[]);         // IV, must be AES_BLOCK_SIZE bytes long

///////////////////
// AES - UTILS
///////////////////
std::string &aes_remove_padding(std::string &str); // Removes padding from the decoded string

#endif //SECUREKEYS_AES_H


#endif //MTDEMO_AES_H
