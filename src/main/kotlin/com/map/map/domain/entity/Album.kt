package com.map.map.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(indexes = [Index(columnList = "user_idx"), Index(columnList = "building_idx")])
class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    var user: User? = null

    @ManyToOne
    @JoinColumn(name = "building_idx", nullable = false)
    var building: Building? = null

    // 앨범 메모
    @Column(nullable = false)
    var memo: String? = null

    @Column(nullable = false)
    var date: Date? = Date()

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "album_idx")
    var likes: MutableList<AlbumLike> = mutableListOf()

    @OneToMany(mappedBy = "album")
    var viewed: MutableList<Viewed> = mutableListOf()

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "album_idx")
    var comments: MutableList<Comment> = mutableListOf()

    @OneToMany(cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "album_idx")
    var photo: MutableList<Photo> = mutableListOf()

    fun add(user: User){
        this.user = user
        user.albums.add(this)
    }
    fun add(building: Building){
        this.building = building
        building.albums.add(this)
    }
}