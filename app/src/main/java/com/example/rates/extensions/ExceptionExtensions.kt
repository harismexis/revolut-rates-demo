package com.example.rates.extensions


fun Exception.getErrorMessage(): String {
    var errorMsg = this.toString()
    this.message?.let {
        errorMsg = it
    }
    return errorMsg
}
