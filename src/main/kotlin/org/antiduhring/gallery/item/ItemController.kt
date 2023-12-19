package org.antiduhring.gallery.item

import org.springframework.core.io.Resource
import org.springframework.web.bind.annotation.*

@RestController
class ItemController(private val itemsService: ItemService) {
    @GetMapping
    fun getItems() = itemsService.getItems()

    @PostMapping
    fun createItem(@RequestHeader name: String, @RequestBody resource: Resource) =
        itemsService.createItem(name, resource.contentAsByteArray)

    @GetMapping("${IMAGE_URL}/{id}")
    fun loadFile(@PathVariable id: Int) = itemsService.loadImage(id)
}
