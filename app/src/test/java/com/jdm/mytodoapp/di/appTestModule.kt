package com.jdm.mytodoapp.di

import com.jdm.mytodoapp.data.repository.TestToDoRepository
import com.jdm.mytodoapp.data.repository.ToDoRepository
import com.jdm.mytodoapp.domain.todo.*
import com.jdm.mytodoapp.presentation.list.ListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {
    // ViewModel
    viewModel { ListViewModel(get(), get(), get()) }


    // UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { UpdateToDoListUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    // Repository
    single<ToDoRepository> { TestToDoRepository() }
}