package ru.phicher.crudbench.db

import javax.persistence.*

@Entity
@Table(name = "BENCH")
@SequenceGenerator(
        name = "BENCH_AuditingEntityListener_generator",
        sequenceName = "BENCH_SEQ",
        allocationSize = 1
)
data class Bench(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val benchId: Long,
        val benchName: String,
        val benchResult: String
)
