package com.example.homework19.notes;

import com.example.homework19.notes.dto.create.CreateNoteRequest;
import com.example.homework19.notes.dto.create.CreateNoteResponse;
import com.example.homework19.notes.dto.delete.DeleteNoteResponse;
import com.example.homework19.notes.dto.get.GetAllNotesResponse;
import com.example.homework19.notes.dto.update.UpdateNoteRequest;
import com.example.homework19.notes.dto.update.UpdateNoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MAX_CONTENT_LENGTH = 1000;

    private final NoteRepository repository;

    // Метод для створення нової нотатки
    public CreateNoteResponse create(CreateNoteRequest request) {
        // Валідація введених полів, повернення помилки валідації, якщо є
        Optional<CreateNoteResponse.Error> validationError = validateCreateFields(request);

        if (validationError.isPresent()) {
            return CreateNoteResponse.failed(validationError.get());
        }

        // Збереження новоствореної нотатки у репозиторій
        Note createdNote = repository.save(Note.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build());

        // Повернення успішної відповіді із ідентифікатором створеної нотатки
        return CreateNoteResponse.success(createdNote.getId());
    }

    // Метод для отримання нотаток
    public GetAllNotesResponse getAllNotes() {
        // Отримання всіх нотаток з репозиторію
        List<Note> userNotes = repository.findAll();

        // Повернення успішної відповіді із нотатками
        return GetAllNotesResponse.success(userNotes);
    }

    // Метод для оновлення нотатки
    public UpdateNoteResponse update(UpdateNoteRequest request) {
        // Знаходження нотатки за ідентифікатором у репозиторії
        Optional<Note> optionalNote = repository.findById(request.getId());

        // Повернення помилки, якщо нотатка з вказаним ідентифікатором не знайдена
        if (optionalNote.isEmpty()) {
            return UpdateNoteResponse.failed(UpdateNoteResponse.Error.invalidNoteId);
        }

        // Отримання знайденої нотатки
        Note note = optionalNote.get();

        // Валідація полів для оновлення, повернення помилки валідації, якщо є
        Optional<UpdateNoteResponse.Error> validationError = validateUpdateFields(request);

        if (validationError.isPresent()) {
            return UpdateNoteResponse.failed(validationError.get());
        }

        // Оновлення нотатки новим заголовком та змістом
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());

        // Збереження оновленої нотатки у репозиторій
        repository.save(note);

        // Повернення успішної відповіді із оновленою нотаткою
        return UpdateNoteResponse.success(note);
    }

    // Метод для видалення нотатки
    public DeleteNoteResponse delete(long id) {
        // Знаходження нотатки за ідентифікатором у репозиторії
        Optional<Note> optionalNote = repository.findById(id);

        // Повернення помилки, якщо нотатка з вказаним ідентифікатором не знайдена
        if (optionalNote.isEmpty()) {
            return DeleteNoteResponse.failed(DeleteNoteResponse.Error.invalidNoteId);
        }

        // Отримання знайденої нотатки
        Note note = optionalNote.get();

        // Видалення нотатки з репозиторію
        repository.delete(note);

        // Повернення успішної відповіді
        return DeleteNoteResponse.success();
    }

    // Приватний метод для валідації полів при створенні нотатки
    private Optional<CreateNoteResponse.Error> validateCreateFields(CreateNoteRequest request) {
        // Перевірка, чи заголовок не є null або не перевищує максимальну довжину
        if (Objects.isNull(request.getTitle()) || request.getTitle().length() > MAX_TITLE_LENGTH) {
            return Optional.of(CreateNoteResponse.Error.invalidTitle);
        }

        // Перевірка, чи зміст не є null або не перевищує максимальну довжину
        if (Objects.isNull(request.getContent()) || request.getContent().length() > MAX_CONTENT_LENGTH) {
            return Optional.of(CreateNoteResponse.Error.invalidTitle);
        }

        // Повернення пустого значення, якщо валідація пройшла успішно
        return Optional.empty();
    }

    // Приватний метод для валідації полів при оновленні нотатки
    private Optional<UpdateNoteResponse.Error> validateUpdateFields(UpdateNoteRequest request) {
        // Перевірка, чи не перевищує довжина заголовка максимально допустиму
        if (Objects.nonNull(request.getTitle()) && request.getTitle().length() > MAX_TITLE_LENGTH) {
            return Optional.of(UpdateNoteResponse.Error.invalidTitleLength);
        }

        // Перевірка, чи не перевищує довжина змісту максимально допустиму
        if (Objects.nonNull(request.getContent()) && request.getContent().length() > MAX_CONTENT_LENGTH) {
            return Optional.of(UpdateNoteResponse.Error.invalidTitleLength);
        }

        // Повернення пустого значення, якщо валідація пройшла успішно
        return Optional.empty();
    }
}