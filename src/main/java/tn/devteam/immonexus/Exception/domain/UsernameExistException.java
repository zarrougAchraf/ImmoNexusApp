package tn.devteam.immonexus.exception.domain;

public class UsernameExistException {
package tn.devteam.immonexus.Exception.domain;


public class UsernameExistException extends Exception {
    public UsernameExistException(String message) {
        super(message);
    }
}
