package com.bhupen.dailynews.dataType.model

data class Verification (

	val verified : Boolean,
	val reason : String,
	val signature : String,
	val payload : String
)