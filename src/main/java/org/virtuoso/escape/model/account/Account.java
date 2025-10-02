package org.virtuoso.escape.model.account;

/**
 * @author gabri
 */
public class Account {
    //TODO(gabri) add logic
    private String hashedPasword;
    private String username;
    private Score highScore;

    public Account(String username, String password) {

    }
    public Account(String serializedData){} // TODO(gabri) use JSON types instead of serializedData
    public void setHighScore(){}
    public String getUsername(){
        return "";
    }
    public Score getHighScore() {
        return this.highScore;
    }
}
