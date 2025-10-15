package org.virtuoso.escape.model;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;


public class TerminalDriver {

    String validateInput(Scanner scanner, String prompt, Predicate<String> predicate) {
        String scanAttempt;
        do {
            System.out.print(prompt);
            scanAttempt = scanner.nextLine().strip().toLowerCase();
        }
        while (!predicate.test(scanAttempt));
        return scanAttempt;
    }

    Integer validateInt(Scanner scanner, String prompt, Predicate<Integer> predicate) {
        Predicate<String> adjustedPredicate =
                (str) -> {
                    try {
                        return predicate.test(Integer.valueOf(str));
                    } catch (NumberFormatException e) {
                        return false;
                    }
                };
        return Integer.valueOf(validateInput(scanner, prompt, adjustedPredicate));
    }

    void tryLogin(Scanner scanner, GameProjection projection, int choice) {
        String username, password;
        do {
            System.out.print("Enter your username: ");
            username = scanner.nextLine();
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
        } while (!(
                choice == 1 && projection.login(username, password) ||
                        choice == 2 && projection.createAccount(username, password)
        ));
        // logical negation:
        // If the choice is 0 and login
        // or the choice is 1 and createAccount, continue
    }

    void display(String display, Object... args) {
        // wrapper function allows for flair
        System.out.printf(display + "\n", args);
    }

    void changeRoom(Scanner scanner, GameProjection projection) {
        // TODO(gabri) allow for room changes
        throw new RuntimeException("unimplemented!");
    }

    void roomActions(Scanner scanner, GameProjection projection) {
        display(projection.currentRoom().introMessage());
        StringBuilder prompt = new StringBuilder("""
                                                         (1) Change room
                                                         Pick Entity:
                                                         """);
        ArrayList<Entity> entities = projection.currentRoom().entities();
        for (int i = 0; i < entities.size(); i++) {
            prompt.append(String.format("(%d) %s", i, entities.get(i).name()));
        }
        int response = validateInt(scanner,
                                   prompt.toString(),
                                   i -> 1 <= i && i <= projection.currentRoom().entities().size());
        if (response == 1) changeRoom(scanner, projection);
        else {
            projection.pickEntity(entities.get(response));
        }
    }

    void pickEntityAction(Scanner scanner, GameProjection projection) {
        display("You're speaking with %s", projection.currentEntity().name());
        String prompt = """
                (1) Interact
                (2) Inspect
                (3) Attack
                (4) Speak
                (5) Leave (rudely).
                """;
        int response = validateInt(scanner, prompt, i -> 1 <= i && i <= 5);
        switch (response) {
            case 1 -> projection.interact();
            case 2 -> projection.inspect();
            case 3 -> projection.attack();
            case 4 -> projection.input(validateInput(scanner, "What would you like to say?", _ -> true));
            case 5 -> projection.leaveEntity();
        }
    }


    void gameLoop(Scanner scanner, GameProjection projection) {
        while (true) { // TODO(gabri) come up with an end condition
            if (projection.currentEntity().isPresent()) pickEntityAction(scanner, projection);
            else roomActions(scanner, projection);
        }
    }

    // See JEP 495
    void main() {
        Scanner scanner = new Scanner(System.in);
        GameProjection projection = new GameProjection();
        // TODO(gabri) add modus for difficulty selection.
        int choice = validateInt(scanner, "Login (1) or create new account (2): ", i -> i == 1 || i == 2);
        tryLogin(scanner, projection, choice);
        gameLoop(scanner, projection);
    }
}
