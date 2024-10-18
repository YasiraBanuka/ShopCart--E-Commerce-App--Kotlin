package com.yasirabanuka.shopcart.utils


sealed class UIEvents {
    object SuccessEvent: UIEvents()
    data class ErrorEvent(val message: String): UIEvents()
}