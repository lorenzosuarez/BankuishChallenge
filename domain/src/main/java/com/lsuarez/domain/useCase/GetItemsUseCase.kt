package com.lsuarez.domain.useCase

import com.lsuarez.domain.repository.ItemRepository


/**
 * Created by Lorenzo on 12/5/2022.
 */

class GetItemsUseCase(private val itemRepository: ItemRepository) {
    operator fun invoke() = itemRepository.getItems()
}