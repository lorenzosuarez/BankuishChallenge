package com.lsuarez.bankuishchallenge.di

import com.lsuarez.domain.repository.ItemRepository
import com.lsuarez.domain.useCase.GetItemsUseCase
import com.lsuarez.domain.useCase.ItemUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Lorenzo on 12/5/2022.
 */


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideItemUseCases(itemRepository: ItemRepository) = ItemUseCases(
        getItemsUseCase = GetItemsUseCase(itemRepository = itemRepository)
    )
}