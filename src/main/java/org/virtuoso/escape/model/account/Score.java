package org.virtuoso.escape.model.account;

import org.virtuoso.escape.model.Difficulty;

import java.time.Duration;

/**
 * @author gabri
 * @param timeRemaining
 * @param difficulty
 */
public record Score(Duration timeRemaining, Difficulty difficulty) {}