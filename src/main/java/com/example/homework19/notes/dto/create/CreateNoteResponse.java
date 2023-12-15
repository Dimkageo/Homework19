package com.example.homework19.notes.dto.create;

import lombok.Builder;
import lombok.Data;

// Аннотації Lombok для автоматичної генерації коду
@Builder
@Data
public class CreateNoteResponse {

    private Error error;  // Поле для представлення можливих помилок

    private long createdNoteId;  // Поле для представлення ідентифікатора створеної нотатки

    // Перерахування для визначення можливих помилок
    public enum Error {
        ok,  // Успішна операція
        invalidTitle,  // Помилка: недійсний заголовок
        invalidContent  // Помилка: недійсний зміст
    }

    // Статичний метод для створення відповіді на успішне створення нотатки
    public static CreateNoteResponse success(long createdNoteId) {
        return builder()
                .error(Error.ok)
                .createdNoteId(createdNoteId)
                .build();
    }

    // Статичний метод для створення відповіді на неуспішне створення нотатки
    public static CreateNoteResponse failed(Error error) {
        return builder()
                .error(error)
                .createdNoteId(-1L)
                .build();
    }
}
