package com.demo.controllers.validators

import com.demo.dto.AppUserDto
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
open class AppUserDtoValidator : Validator {

    override fun supports(clazz: Class<*>?): Boolean = clazz?.equals(AppUserDto::class.java)!!

    override fun validate(target: Any?, errors: Errors?) {
        validatePasswords(errors, target as AppUserDto)
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun validatePasswords(errors: Errors?, form: AppUserDto) {
        if (form.password != form.passwordRepeated) {
            errors?.reject("password.no_match", "Passwords do not match")
        }
    }

//    private fun validateEmail(errors: Errors, form: UserCreateForm) {
//        if (userService.getUser(form.login).isPresent()) {
//            errors.reject("login.exists", "AppUser with this login already exists")
//        }
//    }

}
