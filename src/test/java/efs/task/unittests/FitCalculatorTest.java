package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void should_ReturnFalse_whenDietNotRecommended() {
        //given
        double height = 1.72;
        double weight = 69.5;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void should_ThrowsIllegalArgumentException_whenHeightIs0AndWeightIsArbitrary() {
        //given
        double height = 0.0;
        double weight = 50.7;

        //when, then
        assertThrows(IllegalArgumentException.class, () -> FitCalculator.isBMICorrect(weight, height));

    }

    @ParameterizedTest(name = "{index}: Weight: {0}")
    @ValueSource(doubles = {85.7, 79.3, 77.1})
    void isBMICorrect_True_ForAllWeights(double weight) {
        //given
        double height = 1.75;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "{index}: Weight: {0}, Height: {1}")
    @CsvSource({"85.7, 1.90",
            "79.3, 1.83",
            "72.1, 1.75"
    })
    void isBMICorrect_False_ForAllPairs(double weight, double height) {
        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @ParameterizedTest(name = "{index}: Weight: {0}, Height: {1}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void isBMICorrect_False_ForAllPairsFromFile(double weight, double height) {
        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void findUserWithTheWorstBMI_ShouldReturnUserWithTheWorstBmi() {
        //given
        var worst_user = TestConstants.TEST_USERS_LIST.get(2);

        //when
        var user = FitCalculator.findUserWithTheWorstBMI(TestConstants.TEST_USERS_LIST);

        //then
        assertEquals(user, worst_user);
    }

    @Test
    void findUserWithTheWorstBMI_Null_EmptyList() {
        //given
        List<User> empty = new ArrayList<>();

        //when
        var users = FitCalculator.findUserWithTheWorstBMI(empty);

        //then
        assertNull(users);
    }

    @Test
    void calculateBMIScor_ShouldReturnCorrectUsersBmi() {
        //given
        var users = FitCalculator.calculateBMIScore(TestConstants.TEST_USERS_LIST);

        //when, then
        for(int i = 0; i < users.length; i++){
            assertEquals(TestConstants.TEST_USERS_BMI_SCORE[i], users[i]);
        }
    }
}

