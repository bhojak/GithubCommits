package com.bhupen.dailynews.dataType.model

data class Git_commit (

	val sha : String,
	val node_id : String,
	val commit : Commit,
	val url : String,
	val html_url : String,
	val comments_url : String,
	val author : Author,
	val committer : Committer,
	val parents : List<Parents>
)