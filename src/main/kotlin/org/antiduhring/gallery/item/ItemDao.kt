package org.antiduhring.gallery.item

import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

@Repository
class ItemDao {

    private val idSequence = AtomicInteger(0)
    private val items = ConcurrentHashMap<Int, Item>()
    private val files = ConcurrentHashMap<Int, ByteArray>()

    fun getItems() = items.values.toList()

    fun insertItem(name: String): Item {
        val id = idSequence.addAndGet(1)
        val item = Item(id = id, name = name, imageUrl = "/$IMAGE_URL/$id")
        items[id] = item
        return item
    }

    fun insertFile(id: Int, file: ByteArray) {
        files[id] = file
    }

    fun getImage(id: Int) = files[id] ?: throw IllegalStateException("Not Found")
}
