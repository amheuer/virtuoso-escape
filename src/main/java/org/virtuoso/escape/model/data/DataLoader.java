package org.virtuoso.escape.model.data;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.virtuoso.escape.model.Difficulty;
import org.virtuoso.escape.model.account.Account;
import org.virtuoso.escape.model.account.Score;

public class DataLoader {
	public static JSONObject loadAccounts() {
		JSONObject result = new JSONObject();
		JSONObject root = parseJsonFile(Path.of("json", "accounts.json"));
		if (root == null) return result;
		for (Object key : root.keySet()) {
			String id = String.valueOf(key);
			Object value = root.get(id);
			if (value instanceof JSONObject acct) {
				Object username = acct.get("username");
				Object hashed = acct.get("hashedPassword");
				Object highScore = acct.get("highScore");
				if (username != null && hashed != null && highScore != null)
					result.put(id, new JSONObject(Map.of(
							"username", username,
							"hashedPassword", hashed,
							"highScore", highScore
					)));
			}
		}
		return result;
	}

	public static JSONObject loadGameLanguage(String path) {
		JSONObject result = new JSONObject();
		JSONObject root = parseJsonFile(Path.of(path));
		if (root == null) return result;
		for (Object key : root.keySet()) {
			String id = String.valueOf(key);
			Object inner = root.get(id);
			if (inner instanceof JSONObject innerObj) {
				JSONObject map = new JSONObject();
				for (Object sKey : innerObj.keySet()) {
					String sk = String.valueOf(sKey);
					Object sval = innerObj.get(sk);
					if (sval != null) map.put(sk, String.valueOf(sval));
				}
				result.put(id, map);
			}
		}
		return result;
	}

	public static HashMap<String, Score> loadHighScores() {
		HashMap<String, Score> result = new HashMap<>();
		JSONObject root = parseJsonFile(Path.of("json", "accounts.json"));
		if (root == null) return result;

		for (Object key : root.keySet()) {
			String id = String.valueOf(key);
			Object value = root.get(id);
			if (value instanceof JSONObject acct) {
				Object high = acct.get("highScore");
				if (high instanceof Number) {
					long seconds = ((Number) high).longValue();
					// No difficulty stored in accounts.json; default to TRIVIAL
					Score score = new Score(Duration.ofSeconds(seconds), Difficulty.TRIVIAL);
					result.put(id, score);
				}
			}
		}
		return result;
	}

	public static JSONObject loadGameState(Account account) {
		JSONObject result = new JSONObject();
		JSONObject root = parseJsonFile(Path.of("json", "gamestates.json"));
		if (root == null) return result;

		if (account != null) {
			Object value = root.get(account.id());
			if (value instanceof JSONObject obj) {
				result = obj;
			}
		}
		return result;
	}

	public static JSONObject loadGameStates() {
		JSONObject result = new JSONObject();
		JSONObject root = parseJsonFile(Path.of("json", "gamestates.json"));
		if (root == null) return result;

		for (Object key : root.keySet()) {
			String username = String.valueOf(key);
			Object value = root.get(username);
			if (value instanceof JSONObject) {
				result.put(username, (JSONObject) value);
			}
		}
		return result;
	}

	private static JSONObject parseJsonFile(Path file) {
		if (!Files.exists(file)) {
			System.err.println("File not found");
			return null;
		}
		JSONParser parser = new JSONParser();
		try (FileReader reader = new FileReader(file.toFile())) {
			Object obj = parser.parse(reader);
			if (obj instanceof JSONObject) return (JSONObject) obj;
			System.err.print("Mot viable");
		} catch (IOException | ParseException e) {
			System.err.print("Could not parse file");
		}
		return null;
	}
}
