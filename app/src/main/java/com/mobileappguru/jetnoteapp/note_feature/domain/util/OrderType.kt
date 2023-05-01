package com.mobileappguru.jetnoteapp.note_feature.domain.util

sealed class OrderType {

    object Ascending:OrderType()

    object Descending:OrderType()
}