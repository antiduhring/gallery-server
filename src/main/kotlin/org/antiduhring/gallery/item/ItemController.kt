package org.antiduhring.gallery.item

import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class ItemController(private val itemsService: ItemService) {
    @GetMapping
    fun getItems() = itemsService.getItems()

    @PostMapping
    fun createItem(@RequestHeader name: String, @RequestBody resource: Resource) =
        itemsService.createItem(name, resource.contentAsByteArray)

    @GetMapping("${IMAGE_URL}/{id}")
    fun loadFile(@PathVariable id: Int): ByteArray =
        itemsService.loadImage(id)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource") }

    @DeleteMapping
    fun clear() {
        itemsService.clearAll()
    }
}
