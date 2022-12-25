package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import services.Post;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "UpdateServlet", value = "/posts/update")
@MultipartConfig
public class Update extends HttpServlet {

    private final Post postService = new Post();
    private Long postID;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long postID = Long.parseLong(request.getParameter("postID"));
        this.postID = postID;
        models.Post post = postService.findPost(postID);

        request.setAttribute("post", post);
        request.getRequestDispatcher("/WEB-INF/jsp/update_post.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        Part part = request.getPart("img");

        models.Post post = postService.findPost(postID);
        post.setTitle(title);
        post.setText(text);

        if (!part.getSubmittedFileName().equals("")) {
            String imgName = UUID.randomUUID() + "_" + part.getSubmittedFileName();
            post.setImgName(imgName);
            post.setImg(part.getInputStream().readAllBytes());
        }

        postService.updatePost(post);
        response.sendRedirect("/posts");
    }
}
