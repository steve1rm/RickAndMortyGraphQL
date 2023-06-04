package me.androidbox.rickandmorty.di

import com.apollographql.apollo3.ApolloClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.androidbox.rickandmorty.data.ApolloCharacterClientImp
import me.androidbox.rickandmorty.domain.CharacterClient
import me.androidbox.rickandmorty.domain.usecase.GetCharacterUseCase
import me.androidbox.rickandmorty.domain.usecase.GetCharactersUseCase
import me.androidbox.rickandmorty.domain.usecase.imp.GetCharacterUseCaseImp
import me.androidbox.rickandmorty.domain.usecase.imp.GetCharactersUseCaseImp
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface AppModule {

    companion object {
        @Singleton
        @Provides
        fun providesApolloClient(): ApolloClient {
            return ApolloClient.Builder()
                .serverUrl("https://rickandmortyapi.com/graphql")
                .build()
        }

        @Singleton
        @Provides
        fun providesCharacterClient(apolloClient: ApolloClient): CharacterClient {
            return ApolloCharacterClientImp(apolloClient)
        }
    }

    @Singleton
    @Binds
    fun bindsGetCharacterUseCase(getCharacterUseCaseImp: GetCharacterUseCaseImp): GetCharacterUseCase

    @Singleton
    @Binds
    fun bindsGetCharactersUseCase(getCharactersUseCaseImp: GetCharactersUseCaseImp): GetCharactersUseCase
}