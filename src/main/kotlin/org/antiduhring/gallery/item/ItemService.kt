package org.antiduhring.gallery.item

import org.springframework.stereotype.Service
import java.util.*

@Service
class ItemService(
    private val itemIdGenerator: ItemIdGenerator,
    private val itemRepository: ItemRepository,
    private val itemFileRepository: ItemFileRepository) {

    fun getItems(): List<Item> = itemRepository.findAll().map(this::map)

    //todo: make transactional
    fun createItem(name: String, file: ByteArray): Item {
        val id = itemIdGenerator.getNext()
        val document = ItemDocument(id = id, name = name)
        val saved = itemRepository.save(document)
        itemFileRepository.insert(ItemFileDocument(saved.id, file))
        return map(saved)
    }
    fun loadImage(id: Int): Optional<ByteArray> = itemFileRepository.findById(id).map{it.file}

    private fun map(document: ItemDocument): Item =
        Item(id = document.id, name = document.name, imageUrl = "/$IMAGE_URL/${document.id}")
}

data class Item(val id: Long, val name: String, val imageUrl: String)
