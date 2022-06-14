package com.docubox.di

import com.docubox.util.EncryptionDetails
import com.rockaport.alice.Alice
import com.rockaport.alice.AliceContext
import com.rockaport.alice.AliceContextBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// Module for providing encryption and decryption functions
@Module
@InstallIn(SingletonComponent::class)
object EncryptionModule {

    // Get Alice context for file encryption and decryption
    @Provides
    fun providesAliceContext(): AliceContext =
        AliceContextBuilder().setAlgorithm(EncryptionDetails.algo)
            .setMode(EncryptionDetails.mode)
            .setIvLength(EncryptionDetails.ivmLength)
            .setGcmTagLength(EncryptionDetails.gcmTagLength)
            .build()

    // Get Alice instance
    @Provides
    fun providesAlice(aliceContext: AliceContext): Alice = Alice(aliceContext)
}
