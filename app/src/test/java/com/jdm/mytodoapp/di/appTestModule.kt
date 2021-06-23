package com.jdm.mytodoapp.di

import com.jdm.mytodoapp.data.repository.TestToDoRepository
import com.jdm.mytodoapp.data.repository.ToDoRepository
import com.jdm.mytodoapp.domain.todo.*
import com.jdm.mytodoapp.presentation.list.ListViewModel
import com.jdm.mytodoapp.presentation.list.detail.DetailMode
import com.jdm.mytodoapp.presentation.list.detail.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {
    // ViewModel
    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) -> DetailViewModel(detailMode = detailMode, id = id, get(), get(), get(), get()) }

    // UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { InsertToDoItemUseCase(get()) }
    factory { UpdateToDoListUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }
    factory { UpdateToDoItemUseCase(get()) }
    // Repository
    single<ToDoRepository> { TestToDoRepository() }
}