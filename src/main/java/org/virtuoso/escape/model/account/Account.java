package org.virtuoso.escape.model.account;

/**
 * @author gabri, Treasure
 */
public class Account {
    private String hashedPassword;
    private String username;
    private Score highScore;

    public Account(String username, String password) {
		this.username = username;
		this.hashedPassword = hashPassword(password);
    }// End of constructor(String, String)

    public Account(String username, String password, Score highScore){

    }// End of constructor(String, String, Score)

    public void setHighScore(Score score){
		this.highScore = score;
    }// End of setHighScore

    public String getUsername(){
        return this.username;
    }// End of getUsername

    public Score getHighScore() {
        return this.highScore;
    }// End of getHighScore

	private String hashPassword(String password){
		return String.valueOf(password.hashCode());

		//OR

		/*int hash = 0;
		for (char c : password.toCharArray()){
			hash += c;
		}// End of for
		return String.valueOf(hash);*/

	}// End of hashPassword
}// End of Account
