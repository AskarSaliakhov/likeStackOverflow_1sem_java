import models.Comment;
import models.Post;
import repositories.Comments;
import repositories.Posts;
import repositories.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepositoriesTests {
    private final User userRepository = new User();
    private final Posts postsRepository = new Posts();

    private final Comments commentsRepository = new Comments();

    @Test
    void findAllUsers() {
        List<models.User> userList = userRepository.findAll();
        assertEquals("test", userList.get(0).getLogin());
    }

    @Test
    void saveUser() {
        models.User user = models.User.builder().login("askar").password("askar").build();
        userRepository.save(user);
        List<models.User> list = userRepository.findAll();

        assertEquals("askar", list.get(list.size()-1).getLogin());
    }

    @Test
    void findAllPosts() {
        List<Post> posts = postsRepository.findAll();
        assertEquals(5, posts.get(0).getUserID());
    }

    @Test
    void findByID() {
        Optional<Post> optional = postsRepository.findById(1L);
        assertEquals(5, optional.get().getUserID());
    }

    @Test
    void savePost() {
        Post post = Post.builder().title("test").text("test").userID(5L).build();
        postsRepository.save(post);
        List<Post> allPosts = postsRepository.findAll();
        assertEquals("test", allPosts.get(allPosts.size()-1).getTitle());
    }

    @Test
    void deletePost() {
        postsRepository.delete(2L);
        assertTrue(postsRepository.findById(2L).isEmpty());
    }

    @Test
    void updatePost() {
        Post post = postsRepository.findById(1L).get();
        post.setTitle("test");

        postsRepository.update(post);
        post = postsRepository.findById(1L).get();

        assertEquals("test", post.getTitle());
    }

    

    @Test
    void saveComment() {
        Comment comment = Comment.builder().text("test").userID(5L).postID(1L).build();
        commentsRepository.save(comment);

        List<Comment> comments = commentsRepository.findAll();
        assertEquals("test", comments.get(1).getText());
    }

}
