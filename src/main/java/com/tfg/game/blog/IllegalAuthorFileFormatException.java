package com.tfg.game.blog;

import java.util.IllegalFormatException;

public class IllegalAuthorFileFormatException extends IllegalArgumentException {
    public IllegalAuthorFileFormatException(String message) {
        super(message);
    }
}
