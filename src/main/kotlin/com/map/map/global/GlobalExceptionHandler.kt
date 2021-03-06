package com.map.map.global

import com.map.map.domain.response.Response
import com.map.map.exception.CustomHttpException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception

@ControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(CustomHttpException::class)
    fun customHttpExceptionHandler(e: CustomHttpException):ResponseEntity<Response>{
        log.error("status: " + e.status.toString() + ", message: " + e.message)
        val response = Response(e.status, e.message)
        return ResponseEntity<Response>(response, e.status)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException):ResponseEntity<Response>{
        val response = Response(HttpStatus.BAD_REQUEST, e.bindingResult.allErrors[0].defaultMessage!!)
        return ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(e: Exception):ResponseEntity<Response>{
        e.printStackTrace()
        val response = Response(HttpStatus.INTERNAL_SERVER_ERROR, "서버에러")
        return ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }


}