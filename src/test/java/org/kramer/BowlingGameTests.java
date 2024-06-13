package org.kramer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BowlingGameTests {

  BowlingGame game;
  @BeforeEach
  void setupBowlingGame() {
    game = new BowlingGame();
  }

  @Test
  void roll_GivenNoPinsKnockedDown_StoresZero() {
    game.roll(1);
    assertEquals(1, game.score());
  }

  @Test
  void roll_GivenTwoRolls_StoresSumOfBothRolls() {
    game.roll(4);
    game.roll(3);
    assertEquals(7, game.score());
  }

  @Test
  void roll_GivenAllZeros_ScoresZero() {
    rollMany(20, 0);
    assertEquals(0, game.score());
  }


  @Test
  void roll_GivenAllOnes_ScoresTwenty() {
    rollMany(20, 1);
    assertEquals(20, game.score());
  }

  @Test
  void testRollingSpare() {
    rollSpare(3, 7);
    game.roll(5);
    rollMany(17, 0);
    assertEquals(20, game.score());
  }

  @Test
  void testRollingTwoSpares() {
    rollSpare(3, 7);
    game.roll(5);
    rollMany(9, 0);
    rollSpare(5, 5);
    game.roll(2);
    rollMany(5, 0);

    assertEquals(34, game.score());
  }

  @Test
  void testRollingStrike() {
    rollStrike();
    game.roll(3);
    game.roll(4);
    rollMany(16, 0);
    assertEquals(24, game.score());
  }

  @Test
  void testRollingAllSpares() {
    rollMany(21, 5);
    assertEquals(150, game.score());
  }

  @Test
  void testRollingAllStrikes() {
    for (int i = 0; i < 12; i++)
      rollStrike();
    assertEquals(300, game.score());
  }

  private void rollSpare(int roll_1, int roll_2) {
    game.roll(roll_1);
    game.roll(roll_2);
  }

  private void rollStrike() {
    game.roll(10);
  }

  private void rollMany(int numberOfRolls, int pinsForEachRoll) {
    IntStream.range(0, numberOfRolls).forEach(i -> game.roll(pinsForEachRoll));
  }
}