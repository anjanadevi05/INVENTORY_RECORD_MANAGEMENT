package com.records.Inventory.exception;

public class InventoryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InventoryException(String msg) {
        super(msg);
    }
}
