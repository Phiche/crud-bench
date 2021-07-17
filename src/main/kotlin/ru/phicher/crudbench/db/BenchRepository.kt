package ru.phicher.crudbench.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BenchRepository : JpaRepository<Bench, Long>