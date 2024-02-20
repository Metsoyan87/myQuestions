package com.quiz.myquestions.service.userServiceImpl;

import com.quiz.myquestions.entity.User;
import com.quiz.myquestions.exception.DuplicateResourceException;
import com.quiz.myquestions.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.quiz.MockData.getSavedUser;
import static com.quiz.MockData.saveUser;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @MockBean
    private UserRepository userRepository;
    @Mock
    private Page page;

    @Test
    void testFindAllUsers() {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteById() {
        int idToDelete = 123;
        Mockito.doNothing().when(userRepository).deleteById(anyInt());
        userServiceImpl.deleteById(idToDelete);
        Mockito.verify(userRepository).deleteById(idToDelete);


    }

    @Test
    void deleteById() {
        User user = getSavedUser();
        userService.deleteById(user.getId());
        Mockito.verify(userRepository).deleteById(user.getId());

    }

    @Test
    void shouldFindByEmail() {
        Optional<User> user = Optional.of(new User());
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        userService.findByEmail(getSavedUser().getEmail());
        verify(userRepository, times(1)).findByEmail(getSavedUser().getEmail());
    }

    @Test
    void shouldReturnByUserRole() {
        User user = getSavedUser();
        when(userRepository.findUsersById(anyInt(), any())).thenReturn(page);
        userService.findByUserRole(user, page.getPageable());
        verify(userRepository, times(1)).findUsersById(getSavedUser().getId(), page.getPageable());

    }

    @Test
    void save_UserNotExists_ShouldSaveUser() throws DuplicateResourceException {
        // Arrange
        User newUser = new User(saveUser());

        Mockito.when(userRepository.findByEmail(newUser.getEmail())).thenReturn(Optional.empty());

        // Act
        userService.save(newUser);

        // Assert
        Mockito.verify(userRepository, Mockito.times(1)).save(newUser);
    }

    @Test
    void save_UserExists_ShouldThrowException() {
        // Arrange
        User existingUser = new User(saveUser());

        Mockito.when(userRepository.findByEmail(existingUser.getEmail())).thenReturn(Optional.of(existingUser));

        // Act and Assert
        assertThrows(DuplicateResourceException.class, () -> userService.save(existingUser));
    }
}

