package com.quiz.myquestions.service.quizServiceImpl;

import com.quiz.myquestions.entity.Quiz;
import com.quiz.myquestions.exception.DuplicateResourceException;
import com.quiz.myquestions.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class QuizServiceImplTest {

    @Mock
    private QuizRepository quizRepository;

    @InjectMocks
    private QuizServiceImpl quizServiceImpl;

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


}