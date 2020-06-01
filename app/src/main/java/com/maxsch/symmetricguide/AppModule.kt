package com.maxsch.symmetricguide

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.maxsch.symmetricguide.data.datasource.session.SessionDataSource
import com.maxsch.symmetricguide.data.datasource.user.UserDatabase
import com.maxsch.symmetricguide.data.repository.session.SessionRepositoryImpl
import com.maxsch.symmetricguide.data.repository.user.UserRepositoryImpl
import com.maxsch.symmetricguide.entity.session.repository.SessionRepository
import com.maxsch.symmetricguide.entity.user.repository.UserRepository
import com.maxsch.symmetricguide.presentation.login.LoginPresenter
import com.maxsch.symmetricguide.presentation.register.RegisterPresenter
import com.maxsch.symmetricguide.presentation.settings.SettingsPresenter
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            androidContext().getString(R.string.preferences_name),
            Context.MODE_PRIVATE
        )
    }
    single {
        Room.databaseBuilder(
            androidApplication(),
            UserDatabase::class.java,
            androidContext().getString(R.string.user_database_name)
        ).fallbackToDestructiveMigration().build()
    }
    single { get<UserDatabase>().userDao() }
    single { SessionDataSource(get()) }
    single<SessionRepository> { SessionRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single { LoginPresenter(get(), get()) }
    single { SettingsPresenter(get()) }
    single { RegisterPresenter(get(), get()) }
}