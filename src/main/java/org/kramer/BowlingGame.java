package org.kramer;

public class BowlingGame {

  private final int[] rolls = new int[21];
  private int currentRoll = 0;

  public void roll(int pins) {
    rolls[currentRoll++] = pins;
  }

  public int score() {
    int totalScore = 0;
    int rollIdx = 0;
    for (int frame = 0; frame < 10; frame++) {
      if (isStrike(rollIdx)) {
        totalScore += 10 + nextTwoRollsForStrike(rollIdx);
        rollIdx += 1;
      } else if (isSpare(rollIdx)) {
        totalScore += 10 + nextRollForSpare(rollIdx);
        rollIdx += 2;
      } else {
        totalScore += sumOfRollsInFrame(rollIdx);
        rollIdx += 2;
      }
    }
    return totalScore;
  }

  private int sumOfRollsInFrame(int rollIdx) {
    return rolls[rollIdx] + rolls[rollIdx + 1];
  }

  private int nextTwoRollsForStrike(int rollIdx) {
    return rolls[rollIdx + 1] + rolls[rollIdx + 2];
  }

  private int nextRollForSpare(int rollIdx) {
    return rolls[rollIdx + 2];
  }

  private boolean isStrike(int rollIdx) {
    return rolls[rollIdx] == 10;
  }

  private boolean isSpare(int rollIdx) {
    return rolls[rollIdx] + rolls[rollIdx + 1] == 10;
  }
}