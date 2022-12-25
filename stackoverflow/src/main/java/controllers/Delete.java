package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import services.Comment;
import services.Post;

import java.io.IOException;

@WebServlet(name = "DeletePostServlet", value = "/posts/delete")
public class Delete extends HttpServlet {

    private final Comment commentService = new Comment();
    private final Post postService = new Post();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postID = Long.parseLong(request.getParameter("postID"));

        commentService.getAllComments().forEach(comment -> {
            if (comment.getPostID().equals(postID)) {
                commentService.deleteComment(comment.getId());
            }
        });

        postService.deletePost(postID);

        response.sendRedirect("/posts");
    }
}
