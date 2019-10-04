package com.bhupen.dailynews.dataType.model

data class Commit (

	val author : Author_Commit,
	val committer : Committer,
	val message : String,
	val tree : Tree,
	val url : String,
	val comment_count : Int,
	val verification : Verification
)