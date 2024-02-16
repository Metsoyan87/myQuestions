package com.quiz.myquestions.service.userServiceImpl;

import com.quiz.myquestions.entity.User;
import com.quiz.myquestions.repository.UserRepository;
import com.quiz.myquestions.service.userServiceImpl.UserService;
import com.quiz.myquestions.service.userServiceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.quiz.MockData.getSavedUser;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles({"GULA-YEZ8-9WAK-7FCO"})
@ExtendWith(SpringExtension.class)
class UserServiceTest {

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
    void testDeleteById1() {
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

}

