package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import services.Comment;
import services.User;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "PostServlet", urlPatterns = "/posts/*")
public class Post extends HttpServlet {

    private final services.Post postService = new services.Post();
    private final Comment commentService = new Comment();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long postID = Long.valueOf(request.getPathInfo().substring(1));
            models.Post post = postService.findPost(postID);

            request.setAttribute("post", post);
            request.setAttribute("comments", commentService.getAllComments().stream()
                    .filter(comment -> comment.getPostID().equals(postID)).collect(Collectors.toList())
            );
            request.getRequestDispatcher("/WEB-INF/jsp/post.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postID = Long.valueOf(request.getPathInfo().substring(1));
        models.User user = User.getAuthUser();
        String text = request.getParameter("text");
        models.Comment comment = models.Comment.builder()
                .text(text)
                .userID(user.getId())
                .postID(postID)
                .build();
        commentService.saveComment(comment);
        response.sendRedirect("/posts/" + postID);
    }
}
