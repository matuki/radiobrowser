package br.com.matuki.radiobrowser.di

import br.com.matuki.radiobrowser.shared.RadioRepository
import br.com.matuki.radiobrowser.shared.api.RadioBrowserApi
import br.com.matuki.radiobrowser.shared.api.RadioRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindRadioRepository(radioRepository: RadioRepositoryImpl): RadioRepository

    companion object {
        @Singleton
        @Provides
        fun radioBrowserApi() = RadioBrowserApi()
    }
}