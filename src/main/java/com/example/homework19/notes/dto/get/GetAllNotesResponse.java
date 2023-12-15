package com.example.homework19.notes.dto.get;

import com.example.homework19.notes.Note;
import lombok.Builder;
import lombok.Data;

import java.util.List;

// Аннотації Lombok для автоматичної генерації коду
@Builder
@Data
public class GetAllNotesResponse {

    private Error error;  // Поле для представлення можливих помилок

    private List<Note> userNotes;  // Поле для представлення нотаток користувача

    // Перерахування для визначення можливих помилок
    public enum Error {
        ok  // Успішна операція
    }

    // Статичний метод для створення відповіді на успішне отримання нотаток користувача
    public static GetAllNotesResponse success(List<Note> allNotes) {
        return builder()
                .error(Error.ok)
                .userNotes(allNotes)
                .build();
    }

    // Статичний метод для створення відповіді на неуспішне отримання нотаток користувача
    public static GetAllNotesResponse failed(Error error) {
        return builder()
                .error(error)
                .userNotes(null)
                .build();
    }
}
