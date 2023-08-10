package com.myshopapp.testapp.arch.modules

import com.myshopapp.testapp.arch.repos.ItemRepo
import com.myshopapp.testapp.arch.repos.ItemRepoPattern
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class Binder {
    @Binds
    abstract fun bindRepo(itemRepo: ItemRepo): ItemRepoPattern

}