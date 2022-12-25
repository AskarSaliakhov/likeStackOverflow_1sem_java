package services;

import repositories.Posts;

import java.util.List;
import java.util.Optional;

public class Post {
    private static final Posts repository = new Posts();

    public void savePost(models.Post post) {
        repository.save(post);
    }

    public List<models.Post> getAllPosts() {
        return repository.findAll();
    }

    public models.Post findPost(Long id) {
        Optional<models.Post> post = repository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new IllegalArgumentException("No such post");
        }
    }

    public void deletePost(Long id) {
        repository.delete(id);
    }

    public void updatePost(models.Post post) {
        repository.update(post);
    }
}
