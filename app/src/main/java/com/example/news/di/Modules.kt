package com.example.news.di

import com.example.news.api.NewsApiImpl
import com.example.news.data.contacts.NewsRemoteDataSource
import com.example.news.data.repositories.NewRepositoryImpl
import com.example.news.datasource.api.NewsApi
import com.example.news.datasource.remote.NewsRemoteDataSourceImpl
import com.example.news.domain.contacts.NewsRepository
import com.example.news.domain.usecases.NewsUseCase
import com.example.news.presentaion.viewmodels.HomeVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectFeature() = loadFeature

/**
 * 의존성 주입
 */

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            dataSourceModule,
            networkModule
        )
    )
}

val viewModelModule: Module = module {
    viewModel { HomeVM(newsUseCase = get()) }
}

val useCaseModule: Module = module {
    factory { NewsUseCase(newsRepository = get()) }
}

val repositoryModule: Module = module {
    single { NewRepositoryImpl(newsRemoteDataSource = get()) as NewsRepository }
}

val dataSourceModule: Module = module {
    single { NewsRemoteDataSourceImpl(newsApi = get()) as NewsRemoteDataSource }
}

val networkModule: Module = module {
    single { NewsApiImpl() as NewsApi }
}