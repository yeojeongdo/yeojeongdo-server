package com.map.map.domain.entity

import com.map.map.enum.Gender
import org.hibernate.annotations.Fetch
import java.util.*
import javax.persistence.*

@Entity
@Table(indexes = [Index(columnList = "idx"), Index(columnList = "id")])
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long? = null

    @Column(unique = true)
    var id: String? = null

    @Column(nullable = false)
    var password: String? = null

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false)
    var gender: Gender = Gender.Male

    @Column(nullable = false)
    var birthDate: Date? = null;

    @Column
    var image : String? = null;

    @OneToMany(mappedBy = "user")
    var visited: MutableList<Visited> = mutableListOf()

    @OneToMany(mappedBy = "user")
    var albums: MutableList<Album> = mutableListOf()

    @OneToMany(mappedBy = "user")
    var viewed: MutableList<Viewed> = mutableListOf()


}