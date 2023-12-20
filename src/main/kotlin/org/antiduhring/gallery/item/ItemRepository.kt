package org.antiduhring.gallery.item

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.FindAndModifyOptions.options
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component

const val ITEMS_ID_SEQUENCE = "items_id_sequence"

@Document
data class Sequence(
    @Id
    val id: String? = null,
    val seq: Long = 0
)
@Component
class ItemIdGenerator(private val mongoOperations: MongoOperations) {
    fun getNext(): Long {
        var sequence: Sequence? = mongoOperations.findAndModify(
            query(where("_id").`is`(ITEMS_ID_SEQUENCE)),
            Update().inc("seq", 1),
            options().returnNew(true),
            Sequence::class.java
        )
        if (sequence == null) {
            sequence = Sequence(ITEMS_ID_SEQUENCE, 0)
            mongoOperations.insert<Any>(sequence)
        }
        return sequence.seq
    }
}

interface ItemRepository: MongoRepository<ItemDocument, Int>
interface ItemFileRepository: MongoRepository<ItemFileDocument, Int>

@Document("item")
data class ItemDocument (
    @Id
    var id: Long,
    val name: String,
)

@Document("item_file")
data class ItemFileDocument (
    @Id
    var id: Long,
    val file: ByteArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemFileDocument

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
