package com.olus.olingo4.nnmrls.exception;

/**
 * Dao exception
 *
 * @author Oleksii Usatov
 */
public class DaoException extends RuntimeException {

    public DaoException(String msg) {
        super(msg);
    }

    public DaoException(String msg, Exception e) {
        super(msg, e);
    }
}
