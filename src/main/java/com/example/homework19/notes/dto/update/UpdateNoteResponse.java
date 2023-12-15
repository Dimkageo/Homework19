package com.example.homework19.notes.dto.update;

import com.example.homework19.notes.Note;
import lombok.Builder;
import lombok.Data;

// Аннотації Lombok для автоматичної генерації коду
@Builder
@Data
public class UpdateNoteResponse {

    private Error error;  // Поле для представлення можливих помилок

    private Note updatedNote;  // Поле для представлення оновленої нотатки

    // Перерахування для визначення можливих помилок
    public enum Error {
        ok,  // Успішна операція
        insufficientPrivileges,  // Помилка: недостатньо привілеїв для оновлення
        invalidNoteId,  // Помилка: недійсний ідентифікатор нотатки
        invalidTitleLength,  // Помилка: недійсна довжина заголовку
        invalidContentLength  // Помилка: недійсна довжина змісту
    }

    // Статичний метод для створення відповіді на успішне оновлення нотатки
    public static UpdateNoteResponse success(Note updatedNote) {
        return builder().error(Error.ok).updatedNote(updatedNote).build();
    }

    // Статичний метод для створення відповіді на неуспішне оновлення нотатки
    public static UpdateNoteResponse failed(Error error) {
        return builder()
                .error(error)
                .updatedNote(null)
                .build();
    }
}
