package com.quiz.myquestions.service.answerServiceImpl;

import com.quiz.myquestions.entity.Answer;
import com.quiz.myquestions.repository.AnswerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AnswerServiceImplTest {
    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private AnswerServiceImpl answerService;

    public AnswerServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void findByAnswerById() {
    }

    @Test
    void saveAnswer() {
    }

    @Test
    void deleteAnswerById() {
    }

    @Test
    void editAnswerById() {
    }

    @Test
    public void testFindAll() {
        List<Answer> expectedAnswers = new ArrayList<>();
        Answer answer1 = Mockito.mock(Answer.class);
        Answer answer2 = Mockito.mock(Answer.class);

        expectedAnswers.add(answer1);
        expectedAnswers.add(answer2);

        when(answerRepository.findAll()).thenReturn(expectedAnswers);

        List<Answer> actualAnswers = answerService.findAll();

        assertEquals(expectedAnswers, actualAnswers);
    }
}