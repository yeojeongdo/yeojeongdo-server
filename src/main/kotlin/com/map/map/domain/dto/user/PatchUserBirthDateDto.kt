package com.map.map.domain.dto.user

import java.util.*
import javax.validation.constraints.NotNull

class PatchUserBirthDateDto {
    @NotNull(message = "name null 불가능")
    var birthDate : Date? = null
}