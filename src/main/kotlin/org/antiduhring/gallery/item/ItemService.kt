package org.antiduhring.gallery.item

import org.springframework.stereotype.Service

@Service
class ItemService(private val itemDao: ItemDao) {

    fun getItems(): List<Item> = itemDao.getItems()

    //todo: make transactional
    fun createItem(name: String, file: ByteArray): Item {
        val item = itemDao.insertItem(name)
        itemDao.insertFile(item.id, file)
        return item
    }
    fun loadImage(id: Int): ByteArray = itemDao.getImage(id)
}

data class Item(val id: Int, val name: String, val imageUrl: String)
