package services;

import repositories.Comments;

import java.util.List;

public class Comment {
    private final Comments commentsRepository = new Comments();

    public void saveComment(models.Comment comment) {
        commentsRepository.save(comment);
    }

    public List<models.Comment> getAllComments() {
        return commentsRepository.findAll();
    }

    public void deleteComment(Long id) {
        commentsRepository.delete(id);
    }
}
