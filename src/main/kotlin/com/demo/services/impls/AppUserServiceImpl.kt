package com.demo.services.impls

import com.demo.dto.AppUserDto
import com.demo.models.AppUser
import com.demo.repositories.AppUserRepository
import com.demo.services.AppUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class AppUserServiceImpl
@Autowired constructor(private var appUserRepository: AppUserRepository) : AppUserService {

    private val passwordEncoder = BCryptPasswordEncoder();

    override fun create(dto: AppUserDto): AppUser {
        val appUser = AppUser(dto.login, passwordEncoder.encode(dto.password), dto.role)
        return appUserRepository.save(appUser)
    }

    override fun update(id: Long, dto: AppUserDto): AppUser {
        val appUser = read(dto.id!!).get()
        appUser.role = dto.role
        appUser.passwordHash = passwordEncoder.encode(dto.password);
        appUser.passwordSetDate = LocalDateTime.now()
        appUser.login = dto.login

        return appUserRepository.save(appUser);
    }

    override fun read(id: Long): Optional<AppUser> = appUserRepository.findById(id)

    override fun delete(id: Long): Unit = throw UnsupportedOperationException("not implemented")

    override fun findUserByLogin(login: String): AppUser = appUserRepository.findUserByLogin(login)
}