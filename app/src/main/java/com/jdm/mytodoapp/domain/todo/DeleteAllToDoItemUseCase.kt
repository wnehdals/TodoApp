package com.jdm.mytodoapp.domain.todo

import com.jdm.mytodoapp.data.entity.ToDoEntity
import com.jdm.mytodoapp.data.repository.ToDoRepository
import com.jdm.mytodoapp.domain.UseCase

class DeleteAllToDoItemUseCase(private val toDoRepository: ToDoRepository): UseCase {
    suspend operator fun invoke() {
        return toDoRepository.deleteAll()
    }
}