package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import services.Post;
import services.User;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "AddPostServlet", value = "/posts/add")
@MultipartConfig
public class Add extends HttpServlet {

    private final Post postService = new Post();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        Part part = request.getPart("img");
        models.User user = User.getAuthUser();
        String fileName = UUID.randomUUID() + "_" + part.getSubmittedFileName();


        models.Post post = models.Post.builder()
                .title(title)
                .text(text)
                .img(part.getInputStream().readAllBytes())
                .imgName(fileName)
                .userID(user.getId())
                .build();

        postService.savePost(post);
        response.sendRedirect("/posts");

    }
}
