package com.example.test.common

import java.util.*

data class Data(
        val id: Long,
        val name: String,
        val full_name: String,
        val owner: User,
        val html_url: String,
        val description: String,
        val url: String,
        val created_at: Date,
        val updated_at: Date,
        val pushed_at: Date,
        val git_url: String,
        val ssh_url: String,
        val clone_url: String,
        val svn_url: String,
        val homepage: String,
        val stargazers_count: Int,
        val watchers_count: Int,
        val language: String,
        val has_issues: Boolean,
        val has_downloads: Boolean,
        val has_wiki: Boolean,
        val has_pages: Boolean,
        val forks_count: Int,
        val open_issues_count: Int,
        val forks: Int,
        val open_issues: Int,
        val watchers: Int,
        val default_branch: String
)

data class User(
        val login: String,
        val id: Long,
        val node_id: String,
        val avatar_url: String,
        val gravatar_id: String,
        val url: String,
        val html_url: String,
        val followers_url: String,
        val following_url: String,
        val gists_url: String,
        val starred_url: String,
        val subscriptions_url: String,
        val organizations_url: String,
        val repos_url: String,
        val events_url: String,
        val received_events_url: String,
        val type: String,
        val site_admin: Boolean
)

data class Test(
        val name: String,
        val id: String
)
