package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import services.Post;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PostsServlet", value = "/posts")
public class Posts extends HttpServlet {

    private final Post postService = new Post();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<models.Post> posts = postService.getAllPosts();
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("/WEB-INF/jsp/posts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
