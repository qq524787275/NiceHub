package com.zhuzichu.mvvm.data.model
import com.google.gson.annotations.SerializedName


data class Event(
    @SerializedName("actor")
    var actor: Actor? = Actor(),
    @SerializedName("created_at")
    var createdAt: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("org")
    var org: Org? = Org(),
    @SerializedName("payload")
    var payload: Payload? = Payload(),
    @SerializedName("public")
    var `public`: Boolean? = false,
    @SerializedName("repo")
    var repo: Repo? = Repo(),
    @SerializedName("type")
    var type: String? = ""
)

data class Payload(
    @SerializedName("action")
    var action: String? = "",
    @SerializedName("number")
    var number: Int? = 0,
    @SerializedName("pull_request")
    var pullRequest: PullRequest? = PullRequest()
)

data class PullRequest(
    @SerializedName("_links")
    var links: Links? = Links(),
    @SerializedName("additions")
    var additions: Int? = 0,
    @SerializedName("assignee")
    var assignee: Any? = Any(),
    @SerializedName("assignees")
    var assignees: List<Any?>? = listOf(),
    @SerializedName("author_association")
    var authorAssociation: String? = "",
    @SerializedName("base")
    var base: Base? = Base(),
    @SerializedName("body")
    var body: String? = "",
    @SerializedName("changed_files")
    var changedFiles: Int? = 0,
    @SerializedName("closed_at")
    var closedAt: String? = "",
    @SerializedName("comments")
    var comments: Int? = 0,
    @SerializedName("comments_url")
    var commentsUrl: String? = "",
    @SerializedName("commits")
    var commits: Int? = 0,
    @SerializedName("commits_url")
    var commitsUrl: String? = "",
    @SerializedName("created_at")
    var createdAt: String? = "",
    @SerializedName("deletions")
    var deletions: Int? = 0,
    @SerializedName("diff_url")
    var diffUrl: String? = "",
    @SerializedName("head")
    var head: Head? = Head(),
    @SerializedName("html_url")
    var htmlUrl: String? = "",
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("issue_url")
    var issueUrl: String? = "",
    @SerializedName("labels")
    var labels: List<Label?>? = listOf(),
    @SerializedName("locked")
    var locked: Boolean? = false,
    @SerializedName("maintainer_can_modify")
    var maintainerCanModify: Boolean? = false,
    @SerializedName("merge_commit_sha")
    var mergeCommitSha: String? = "",
    @SerializedName("mergeable")
    var mergeable: Boolean? = false,
    @SerializedName("mergeable_state")
    var mergeableState: String? = "",
    @SerializedName("merged")
    var merged: Boolean? = false,
    @SerializedName("merged_at")
    var mergedAt: Any? = Any(),
    @SerializedName("merged_by")
    var mergedBy: Any? = Any(),
    @SerializedName("milestone")
    var milestone: Any? = Any(),
    @SerializedName("node_id")
    var nodeId: String? = "",
    @SerializedName("number")
    var number: Int? = 0,
    @SerializedName("patch_url")
    var patchUrl: String? = "",
    @SerializedName("rebaseable")
    var rebaseable: Boolean? = false,
    @SerializedName("requested_reviewers")
    var requestedReviewers: List<Any?>? = listOf(),
    @SerializedName("requested_teams")
    var requestedTeams: List<Any?>? = listOf(),
    @SerializedName("review_comment_url")
    var reviewCommentUrl: String? = "",
    @SerializedName("review_comments")
    var reviewComments: Int? = 0,
    @SerializedName("review_comments_url")
    var reviewCommentsUrl: String? = "",
    @SerializedName("state")
    var state: String? = "",
    @SerializedName("statuses_url")
    var statusesUrl: String? = "",
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("updated_at")
    var updatedAt: String? = "",
    @SerializedName("url")
    var url: String? = "",
    @SerializedName("user")
    var user: User? = User()
)

data class Head(
    @SerializedName("label")
    var label: String? = "",
    @SerializedName("ref")
    var ref: String? = "",
    @SerializedName("repo")
    var repo: Repo? = Repo(),
    @SerializedName("sha")
    var sha: String? = "",
    @SerializedName("user")
    var user: User? = User()
)

data class User(
    @SerializedName("avatar_url")
    var avatarUrl: String? = "",
    @SerializedName("events_url")
    var eventsUrl: String? = "",
    @SerializedName("followers_url")
    var followersUrl: String? = "",
    @SerializedName("following_url")
    var followingUrl: String? = "",
    @SerializedName("gists_url")
    var gistsUrl: String? = "",
    @SerializedName("gravatar_id")
    var gravatarId: String? = "",
    @SerializedName("html_url")
    var htmlUrl: String? = "",
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("login")
    var login: String? = "",
    @SerializedName("node_id")
    var nodeId: String? = "",
    @SerializedName("organizations_url")
    var organizationsUrl: String? = "",
    @SerializedName("received_events_url")
    var receivedEventsUrl: String? = "",
    @SerializedName("repos_url")
    var reposUrl: String? = "",
    @SerializedName("site_admin")
    var siteAdmin: Boolean? = false,
    @SerializedName("starred_url")
    var starredUrl: String? = "",
    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String? = "",
    @SerializedName("type")
    var type: String? = "",
    @SerializedName("url")
    var url: String? = ""
)

data class Repo(
    @SerializedName("archive_url")
    var archiveUrl: String? = "",
    @SerializedName("archived")
    var archived: Boolean? = false,
    @SerializedName("assignees_url")
    var assigneesUrl: String? = "",
    @SerializedName("blobs_url")
    var blobsUrl: String? = "",
    @SerializedName("branches_url")
    var branchesUrl: String? = "",
    @SerializedName("clone_url")
    var cloneUrl: String? = "",
    @SerializedName("collaborators_url")
    var collaboratorsUrl: String? = "",
    @SerializedName("comments_url")
    var commentsUrl: String? = "",
    @SerializedName("commits_url")
    var commitsUrl: String? = "",
    @SerializedName("compare_url")
    var compareUrl: String? = "",
    @SerializedName("contents_url")
    var contentsUrl: String? = "",
    @SerializedName("contributors_url")
    var contributorsUrl: String? = "",
    @SerializedName("created_at")
    var createdAt: String? = "",
    @SerializedName("default_branch")
    var defaultBranch: String? = "",
    @SerializedName("deployments_url")
    var deploymentsUrl: String? = "",
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("downloads_url")
    var downloadsUrl: String? = "",
    @SerializedName("events_url")
    var eventsUrl: String? = "",
    @SerializedName("fork")
    var fork: Boolean? = false,
    @SerializedName("forks")
    var forks: Int? = 0,
    @SerializedName("forks_count")
    var forksCount: Int? = 0,
    @SerializedName("forks_url")
    var forksUrl: String? = "",
    @SerializedName("full_name")
    var fullName: String? = "",
    @SerializedName("git_commits_url")
    var gitCommitsUrl: String? = "",
    @SerializedName("git_refs_url")
    var gitRefsUrl: String? = "",
    @SerializedName("git_tags_url")
    var gitTagsUrl: String? = "",
    @SerializedName("git_url")
    var gitUrl: String? = "",
    @SerializedName("has_downloads")
    var hasDownloads: Boolean? = false,
    @SerializedName("has_issues")
    var hasIssues: Boolean? = false,
    @SerializedName("has_pages")
    var hasPages: Boolean? = false,
    @SerializedName("has_projects")
    var hasProjects: Boolean? = false,
    @SerializedName("has_wiki")
    var hasWiki: Boolean? = false,
    @SerializedName("homepage")
    var homepage: String? = "",
    @SerializedName("hooks_url")
    var hooksUrl: String? = "",
    @SerializedName("html_url")
    var htmlUrl: String? = "",
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("issue_comment_url")
    var issueCommentUrl: String? = "",
    @SerializedName("issue_events_url")
    var issueEventsUrl: String? = "",
    @SerializedName("issues_url")
    var issuesUrl: String? = "",
    @SerializedName("keys_url")
    var keysUrl: String? = "",
    @SerializedName("labels_url")
    var labelsUrl: String? = "",
    @SerializedName("language")
    var language: String? = "",
    @SerializedName("languages_url")
    var languagesUrl: String? = "",
    @SerializedName("license")
    var license: License? = License(),
    @SerializedName("merges_url")
    var mergesUrl: String? = "",
    @SerializedName("milestones_url")
    var milestonesUrl: String? = "",
    @SerializedName("mirror_url")
    var mirrorUrl: Any? = Any(),
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("node_id")
    var nodeId: String? = "",
    @SerializedName("notifications_url")
    var notificationsUrl: String? = "",
    @SerializedName("open_issues")
    var openIssues: Int? = 0,
    @SerializedName("open_issues_count")
    var openIssuesCount: Int? = 0,
    @SerializedName("owner")
    var owner: Owner? = Owner(),
    @SerializedName("private")
    var `private`: Boolean? = false,
    @SerializedName("pulls_url")
    var pullsUrl: String? = "",
    @SerializedName("pushed_at")
    var pushedAt: String? = "",
    @SerializedName("releases_url")
    var releasesUrl: String? = "",
    @SerializedName("size")
    var size: Int? = 0,
    @SerializedName("ssh_url")
    var sshUrl: String? = "",
    @SerializedName("stargazers_count")
    var stargazersCount: Int? = 0,
    @SerializedName("stargazers_url")
    var stargazersUrl: String? = "",
    @SerializedName("statuses_url")
    var statusesUrl: String? = "",
    @SerializedName("subscribers_url")
    var subscribersUrl: String? = "",
    @SerializedName("subscription_url")
    var subscriptionUrl: String? = "",
    @SerializedName("svn_url")
    var svnUrl: String? = "",
    @SerializedName("tags_url")
    var tagsUrl: String? = "",
    @SerializedName("teams_url")
    var teamsUrl: String? = "",
    @SerializedName("trees_url")
    var treesUrl: String? = "",
    @SerializedName("updated_at")
    var updatedAt: String? = "",
    @SerializedName("url")
    var url: String? = "",
    @SerializedName("watchers")
    var watchers: Int? = 0,
    @SerializedName("watchers_count")
    var watchersCount: Int? = 0
)

data class License(
    @SerializedName("key")
    var key: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("node_id")
    var nodeId: String? = "",
    @SerializedName("spdx_id")
    var spdxId: String? = "",
    @SerializedName("url")
    var url: Any? = Any()
)

data class Owner(
    @SerializedName("avatar_url")
    var avatarUrl: String? = "",
    @SerializedName("events_url")
    var eventsUrl: String? = "",
    @SerializedName("followers_url")
    var followersUrl: String? = "",
    @SerializedName("following_url")
    var followingUrl: String? = "",
    @SerializedName("gists_url")
    var gistsUrl: String? = "",
    @SerializedName("gravatar_id")
    var gravatarId: String? = "",
    @SerializedName("html_url")
    var htmlUrl: String? = "",
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("login")
    var login: String? = "",
    @SerializedName("node_id")
    var nodeId: String? = "",
    @SerializedName("organizations_url")
    var organizationsUrl: String? = "",
    @SerializedName("received_events_url")
    var receivedEventsUrl: String? = "",
    @SerializedName("repos_url")
    var reposUrl: String? = "",
    @SerializedName("site_admin")
    var siteAdmin: Boolean? = false,
    @SerializedName("starred_url")
    var starredUrl: String? = "",
    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String? = "",
    @SerializedName("type")
    var type: String? = "",
    @SerializedName("url")
    var url: String? = ""
)

data class Base(
    @SerializedName("label")
    var label: String? = "",
    @SerializedName("ref")
    var ref: String? = "",
    @SerializedName("repo")
    var repo: RepoX? = RepoX(),
    @SerializedName("sha")
    var sha: String? = "",
    @SerializedName("user")
    var user: User? = User()
)

data class Label(
    @SerializedName("color")
    var color: String? = "",
    @SerializedName("default")
    var default: Boolean? = false,
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("node_id")
    var nodeId: String? = "",
    @SerializedName("url")
    var url: String? = ""
)

data class Links(
    @SerializedName("comments")
    var comments: Comments? = Comments(),
    @SerializedName("commits")
    var commits: Commits? = Commits(),
    @SerializedName("html")
    var html: Html? = Html(),
    @SerializedName("issue")
    var issue: Issue? = Issue(),
    @SerializedName("review_comment")
    var reviewComment: ReviewComment? = ReviewComment(),
    @SerializedName("review_comments")
    var reviewComments: ReviewComments? = ReviewComments(),
    @SerializedName("self")
    var self: Self? = Self(),
    @SerializedName("statuses")
    var statuses: Statuses? = Statuses()
)

data class ReviewComment(
    @SerializedName("href")
    var href: String? = ""
)

data class Commits(
    @SerializedName("href")
    var href: String? = ""
)

data class Issue(
    @SerializedName("href")
    var href: String? = ""
)

data class Self(
    @SerializedName("href")
    var href: String? = ""
)

data class Html(
    @SerializedName("href")
    var href: String? = ""
)

data class ReviewComments(
    @SerializedName("href")
    var href: String? = ""
)

data class Statuses(
    @SerializedName("href")
    var href: String? = ""
)

data class Comments(
    @SerializedName("href")
    var href: String? = ""
)

data class Actor(
    @SerializedName("avatar_url")
    var avatarUrl: String? = "",
    @SerializedName("display_login")
    var displayLogin: String? = "",
    @SerializedName("gravatar_id")
    var gravatarId: String? = "",
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("login")
    var login: String? = "",
    @SerializedName("url")
    var url: String? = ""
)

data class Org(
    @SerializedName("avatar_url")
    var avatarUrl: String? = "",
    @SerializedName("gravatar_id")
    var gravatarId: String? = "",
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("login")
    var login: String? = "",
    @SerializedName("url")
    var url: String? = ""
)

data class RepoX(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("url")
    var url: String? = ""
)