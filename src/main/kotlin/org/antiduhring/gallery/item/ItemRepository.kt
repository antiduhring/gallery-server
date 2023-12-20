package org.antiduhring.gallery.item

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository

interface ItemRepository: MongoRepository<ItemDocument, Int>

@Document("item")
data class ItemDocument (
    @Id
    val id: ObjectId = ObjectId(),
    val name: String,
    val imageUrl: String,
)
