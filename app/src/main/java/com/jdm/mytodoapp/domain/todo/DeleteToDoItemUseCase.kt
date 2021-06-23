package com.jdm.mytodoapp.domain.todo

import com.jdm.mytodoapp.data.entity.ToDoEntity
import com.jdm.mytodoapp.data.repository.ToDoRepository
import com.jdm.mytodoapp.domain.UseCase

class DeleteToDoItemUseCase(private val toDoRepository: ToDoRepository): UseCase {
    suspend operator fun invoke(id: Long):Boolean   {
        return toDoRepository.deleteToDoItem(id)
    }
}