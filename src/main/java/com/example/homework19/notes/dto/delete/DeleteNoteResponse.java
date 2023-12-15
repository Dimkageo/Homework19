package com.example.homework19.notes.dto.delete;

import lombok.Builder;
import lombok.Data;

// Аннотації Lombok для автоматичної генерації коду
@Builder
@Data
public class DeleteNoteResponse {

    private Error error;  // Поле для представлення можливих помилок

    // Перерахування для визначення можливих помилок
    public enum Error {
        ok,  // Успішна операція
        insufficientPrivileges,  // Помилка: недостатньо привілеїв для видалення
        invalidNoteId  // Помилка: недійсний ідентифікатор нотатки
    }

    // Статичний метод для створення відповіді на успішне видалення нотатки
    public static DeleteNoteResponse success() {
        return builder()
                .error(Error.ok)
                .build();
    }

    // Статичний метод для створення відповіді на неуспішне видалення нотатки
    public static DeleteNoteResponse failed(Error error) {
        return builder()
                .error(error)
                .build();
    }
}
