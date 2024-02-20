package com.quiz.myquestions.service.quizServiceImpl;

import com.quiz.myquestions.entity.Quiz;
import com.quiz.myquestions.exception.DuplicateResourceException;
import com.quiz.myquestions.repository.QuizRepository;
import com.quiz.myquestions.service.userServiceImpl.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
@SpringBootTest
class QuizServiceImplTest {
    @Autowired
    private QuizService quizService;
    @MockBean
    private QuizRepository quizRepository;
    @InjectMocks
    private QuizServiceImpl quizServiceImpl;
    @Mock
    private Page page;

    @Test
    void deleteById() {
        int idToDelete = 123;
        Mockito.doNothing().when(quizRepository).deleteById(anyInt());
        quizServiceImpl.deleteById(idToDelete);
        Mockito.verify(quizRepository).deleteById(idToDelete);
    }

    @Test
    void saveQuiz_WithNonExistingQuiz_ShouldSaveSuccessfully() throws DuplicateResourceException {
        Quiz quiz = new Quiz();
        quiz.setTitle("New Quiz");
        when(quizRepository.findByTitle(quiz.getTitle())).thenReturn(Optional.empty());
        quizServiceImpl.saveQuiz(quiz);
        Mockito.verify(quizRepository, Mockito.times(1)).save(quiz);
    }

    @Test
    void saveQuiz_WithExistingQuiz_ShouldThrowDuplicateResourceException() {
        Quiz existingQuiz = new Quiz();
        existingQuiz.setTitle("Existing Quiz");
        when(quizRepository.findByTitle(existingQuiz.getTitle())).thenReturn(Optional.of(existingQuiz));
        assertThrows(DuplicateResourceException.class, () -> quizServiceImpl.saveQuiz(existingQuiz));
    }


    @Test
    void findAllQuiz() {
        List<Quiz> quiz = new ArrayList<>();
        when(quizRepository.findAll()).thenReturn(quiz);
    }

    @Test
    void quizFindById() {
        Quiz quiz = new Quiz(7, "Gugo", new Date());
        when(quizRepository.findQuizById(anyInt(), any())).thenReturn(page);
        quizService.findQuizById(quiz, page.getPageable());
        verify(quizRepository, times(1)).findQuizById(quiz.getId(), page.getPageable());

    }

    @Test
    void findQuizByTitle() {

        Optional<Quiz> quiz = Optional.of(new Quiz());

        when(quizRepository.findByTitle(anyString())).thenReturn(quiz);
        quizService.findQuizByTitle(quiz.get().getTitle());
        verify(quizRepository, times(1)).findByTitle(quiz.get().getTitle());
    }
}