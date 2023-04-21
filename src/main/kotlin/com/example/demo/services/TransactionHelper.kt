package com.example.demo.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.Callable

@Service
class TransactionHelper {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun doInNewTransaction(fn: Callable<*>) {
        fn.call()
    }
}
