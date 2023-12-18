package org.antiduhring.gallery.item

import org.springframework.core.io.Resource
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class ItemController(private val itemsService: ItemService) {
    @GetMapping
    fun getItems() = itemsService.getItems()

    @PostMapping
    fun createItem(@RequestBody item: Item) = itemsService.createItem(item)

    @PostMapping("/upload-form/{id}")
    fun uploadFormFile(@PathVariable id: Int, @RequestParam file: MultipartFile) {
        itemsService.saveImage(id, file.bytes)
    }

    @PostMapping("/upload/{id}")
    fun uploadFile(@PathVariable id: Int, @RequestBody resource: Resource) {
        itemsService.saveImage(id, resource.contentAsByteArray)
    }

    @GetMapping("/image/{id}")
    fun loadFile(@PathVariable id: Int) = itemsService.loadImage(id)
}
