package com.hatchlabs.kfms.controllers

import com.hatchlabs.kfms.dtos.LoginDTO
import com.hatchlabs.kfms.dtos.Message
import com.hatchlabs.kfms.dtos.RegisterDTO
import com.hatchlabs.kfms.models.User
import com.hatchlabs.kfms.repositories.UserRepository
import com.hatchlabs.kfms.services.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.sql.Date
import java.util.*

@RestController
@RequestMapping("api/")
class AuthController(
        private val userService: UserService,
        private val usersRepo: UserRepository
) {

    @PostMapping("register")
    fun register(@RequestBody body: RegisterDTO): ResponseEntity<User>{
        val user = User()
        user.username = body.username
        user.firstName = body.firstName
        user.lastName = body.lastName
        user.dateCreated = Date(System.currentTimeMillis())
        user.lastLogin = body.lastLogin
        user.createdBy = body.createdBy
        user.email = body.email
        user.profilePicture = body.profilePicture
        user.role = body.role
        user.password = body.password
        user.status = body.status

        return ResponseEntity.ok(this.userService.save(user))

    }

    @PostMapping("login")
    fun login(@RequestBody body: LoginDTO, response: HttpServletResponse): ResponseEntity<Any>{

        val randomString = "kfms"
        val user = this.userService.findByEmail(body.email)
                ?: return ResponseEntity.badRequest().body(Message("User with email "+ body.email +" not found!"))

        if (!user.comparePasswords(body.password)){
            return ResponseEntity.badRequest().body(Message("Invalid Password!"))
        }

        val issuer = user.id.toString()
        val jwt = Jwts.builder()
                .setIssuer(issuer)
                .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) //1 Day
                .signWith(SignatureAlgorithm.HS256, randomString.toByteArray())
                .compact()

        val cookie = Cookie("jwt",jwt)
        cookie.isHttpOnly = true //This ensures the frontend doesn't access the cookie, the cookie is sent to the backend for validation.

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Success!"))
    }

    @GetMapping("getUser")
    fun getUser(@CookieValue("jwt") jwt: String?): ResponseEntity<Any>{
        return try {
            if (jwt == null){
                ResponseEntity.status(401).body(Message("Unauthenticated! JWT is null!"))
            }else{
                val body = Jwts.parser().setSigningKey("kfms").parseClaimsJws(jwt).body
                ResponseEntity.ok(this.userService.getUserById(body.issuer.toLong()))
            }
        }catch (e: Exception){
            ResponseEntity.status(401).body(Message("Unauthenticated! JWT is not null but something is wrong!"))
        }


    }

    @PostMapping("logout")
    fun logout(response: HttpServletResponse):ResponseEntity<Any>{
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0
        response.addCookie(cookie)
        return ResponseEntity.ok(Message("Logout Successful"))
    }

    @GetMapping("/getLoggedOnUser")
    fun getLoggedOnUser(): User? {
//        val authentication: org.springframework.security.core.Authentication? = SecurityContextHolder.getContext().authentication
//
//        if (authentication?.principal is User) {
//            return authentication.principal as User
//        }
//
//        return null

        SecurityContextHolder.getContext().authentication?.name
                ?.let { username ->
                    usersRepo.findByUsername(username)
                            ?.let { loggedInUser ->
                                return loggedInUser
                            }
                            ?: throw Exception("No userName with the following userName=$username, Exist in the users table")
                }
                ?: throw Exception("No user has logged in")
    }


}