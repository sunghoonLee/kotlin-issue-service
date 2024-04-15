package com.fastcampus.issueservice.web

import com.fastcampus.issueservice.config.AuthUser
import com.fastcampus.issueservice.model.CommentRequest
import com.fastcampus.issueservice.model.CommentResponse
import com.fastcampus.issueservice.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/issues/{issueId}/comment")
class CommentController (
    private val commentService: CommentService
) {

    @PostMapping
    fun create(
        authUser: AuthUser,
        @PathVariable issueId: Long,
        @RequestBody request: CommentRequest,
    ): CommentResponse {
        return commentService.create(issueId, authUser.userId, authUser.username, request)
    }

    @PutMapping("/{commentId}")
    fun edit(
        authUser: AuthUser,
        @PathVariable commentId: Long,
        @RequestBody request: CommentRequest,
    ): CommentResponse? {
        return commentService.edit(commentId, authUser.userId, request)
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        authUser: AuthUser,
        @PathVariable issueId: Long,
        @PathVariable commentId: Long,
    ) = commentService.delete(issueId, commentId, authUser.userId)

}
