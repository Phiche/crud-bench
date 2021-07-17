package ru.phicher.crudbench.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.phicher.crudbench.db.Bench
import ru.phicher.crudbench.db.BenchRepository

@RestController
class CrudController(
        private val benchRepository: BenchRepository
) {

    @GetMapping("bench")
    fun getBenches(): ResponseEntity<List<Bench>> {
        val benches = benchRepository.findAll()
        if (benches.isEmpty()) {
            return ResponseEntity<List<Bench>>(HttpStatus.NO_CONTENT)
        }

        return ResponseEntity<List<Bench>>(benches, HttpStatus.OK)
    }

}