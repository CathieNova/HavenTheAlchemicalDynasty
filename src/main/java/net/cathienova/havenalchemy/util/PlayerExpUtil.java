package net.cathienova.havenalchemy.util;

public class PlayerExpUtil
{
    /**
     * Calculates the amount of experience needed for the next level.
     * @param currentLevel The current level of the player.
     * @return The amount of experience needed for the next level.
     */
    public static int getExperienceForNextLevel(int currentLevel) {
        if (currentLevel >= 30) {
            return 112 + (currentLevel - 30) * 9;
        } else if (currentLevel >= 15) {
            return 37 + (currentLevel - 15) * 5;
        } else {
            return 7 + currentLevel * 2;
        }
    }

    /**
     * Calculates 10% of the experience needed for the next level.
     * @param currentLevel The current level of the player.
     * @return 10% of the experience needed for the next level.
     */
    public static int getPercentExperienceForNextLevel(int currentLevel, float percentage) {
        int experienceForNextLevel = getExperienceForNextLevel(currentLevel);
        return (int) (experienceForNextLevel * percentage);
    }
}
