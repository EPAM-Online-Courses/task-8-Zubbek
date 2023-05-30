package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class PlannerTest {
    public static final Planner planner = new Planner();

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void calculateDailyCaloriesDemand_ShouldReturnAppropriateCaloriesDemandForUser(ActivityLevel levels) {
        //given
        var user = TestConstants.TEST_USER;

        //when
        int caloriesDemand = planner.calculateDailyCaloriesDemand(user, levels);

        //then
        assertEquals(TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(levels), caloriesDemand);
    }

    @Test
    void calculateDailyIntake_ShouldReturnAppropriateDailyIntakeOfCaloriesForUser() {
        //given
        var user = TestConstants.TEST_USER;

        //when
        DailyIntake dailyIntake = planner.calculateDailyIntake(user);

        //then
        assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getCalories(), dailyIntake.getCalories());
        assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getProtein(), dailyIntake.getProtein());
        assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getFat(), dailyIntake.getFat());
        assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getCarbohydrate(), dailyIntake.getCarbohydrate());
    }

}
