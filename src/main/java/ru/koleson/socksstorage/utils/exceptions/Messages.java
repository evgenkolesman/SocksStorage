package ru.koleson.socksstorage.utils.exceptions;

public enum Messages {
    SUCCESS("HTTP 200 — удалось выполнить операцию"),
    NOTFOUND("HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат"),
    SERVERERROR("HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
