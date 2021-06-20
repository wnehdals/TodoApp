package com.jdm.mytodoapp.domain.todo

import com.jdm.mytodoapp.data.entity.ToDoEntity
import com.jdm.mytodoapp.data.repository.ToDoRepository
import com.jdm.mytodoapp.domain.UseCase

class GetToDoListUseCase(private val toDoRepository: ToDoRepository): UseCase {
    suspend operator fun invoke(): List<ToDoEntity> {
        return toDoRepository.getToDoList()
    }
}