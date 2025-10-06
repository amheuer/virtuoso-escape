package org.virtuoso.escape.model;

import java.util.ArrayList;

/**
 * @author gabri
 * NOTE record automatically generates getters.
 */
public record Floor(String id, ArrayList<Room> rooms) {}