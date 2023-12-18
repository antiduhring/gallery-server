package org.antiduhring.gallery.item

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class ItemService {

    private val items = ConcurrentHashMap<Int, Item>()
    private val files = ConcurrentHashMap<Int, ByteArray>()

    fun getItems(): List<Item> = items.values.toList()

    fun createItem(item: Item) {
        items[item.id] = item
    }

    fun saveImage(id: Int, file: ByteArray) {
        files[id] = file
    }

    fun loadImage(id: Int): ByteArray = files[id] ?: throw IllegalStateException("Not Found")
}

data class Item(val id: Int, val name: String)
