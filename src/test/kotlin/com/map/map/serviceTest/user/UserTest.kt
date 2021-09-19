package com.map.map.serviceTest.user

import com.map.map.domain.dto.auth.RegisterDto
import com.map.map.domain.dto.user.PatchUserNameDto
import com.map.map.domain.repository.UserRepo
import com.map.map.enum.Gender
import com.map.map.service.auth.AuthServiceImpl
import com.map.map.service.user.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.util.*

@SpringBootTest
@Transactional
class UserTest {
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var authServiceImpl: AuthServiceImpl
    @Autowired
    private lateinit var userRepo: UserRepo


    @Test
    fun changeUserName(){
        var registerDto: RegisterDto = RegisterDto()
        setUser1(registerDto)

        authServiceImpl.register(registerDto)

        val user = userRepo.findById(registerDto.id!!)
        val beforeUserName = user!!.name!!

        var patchUserNameDto = PatchUserNameDto()
        patchUserNameDto.name = beforeUserName + "123"
        userService.changeUserName(patchUserNameDto, user)

        val changedUser = userRepo.findById(registerDto.id!!)
        val afterUserName = changedUser!!.name!!

        assert(beforeUserName+"123" == afterUserName)
    }


    fun setUser1(registerDto: RegisterDto){
        registerDto.id= "qwe"
        registerDto.password = "qwerqwer"
        registerDto.gender = Gender.Male
        registerDto.name = "qqqq"
        registerDto.birthDate = Date()

    }
}