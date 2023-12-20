package org.antiduhring.gallery.item

import org.springframework.stereotype.Service

@Service
class ItemService(private val itemDao: ItemDao, private val itemRepository: ItemRepository) {

    fun getItems(): List<Item> = itemDao.getItems()

    //todo: make transactional
    fun createItem(name: String, file: ByteArray): Item {
        val item = itemDao.insertItem(name)
        itemDao.insertFile(item.id, file)
        return item
    }
    fun loadImage(id: Int): ByteArray = itemDao.getImage(id)

    fun get(): Long {
        itemRepository.save(ItemDocument(name = "Test", imageUrl = "some url"))
        return itemRepository.count()
    }
}

data class Item(val id: Int, val name: String, val imageUrl: String)
