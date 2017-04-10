package com.demo.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.function.Supplier

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException : Throwable() {

    companion object sup : Supplier<NotFoundException> {
        override fun get(): NotFoundException {
            return NotFoundException()
        }
    }

}