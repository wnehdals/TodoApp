package com.jdm.mytodoapp.viewmodel.detail

import com.jdm.mytodoapp.data.entity.ToDoEntity
import com.jdm.mytodoapp.domain.todo.InsertToDoItemUseCase
import com.jdm.mytodoapp.presentation.list.ListViewModel
import com.jdm.mytodoapp.presentation.list.ToDoListState
import com.jdm.mytodoapp.presentation.list.detail.DetailMode
import com.jdm.mytodoapp.presentation.list.detail.DetailViewModel
import com.jdm.mytodoapp.presentation.list.detail.ToDoDetailState
import com.jdm.mytodoapp.viewmodel.ViewModelTest
import com.jdm.mytodoapp.viewmodel.todo.ListViewModelTest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
import java.lang.Exception

/**
 * [DetailViewModelTest]을 테스트하기 위한 Unit Test Class
 *
 * 1. initData()
 * 2. test viewModel fetch
 * 3. test delete todo
 * 4. test update todo
 *
 */
internal class DetailViewModelTest: ViewModelTest() {
    private val id = 1L

    private val detailViewModel by inject<DetailViewModel> { parametersOf(DetailMode.DETAIL,id) }
    private val listViewModel by inject<ListViewModel>()

    private val insertToDoItemUseCase: InsertToDoItemUseCase by inject()
    private val todo = ToDoEntity(
        id = id,
        title = "title $id",
        description = "description $id",
        hasCompleted = false
    )

    @Before
    fun init() {
        initData()
    }

    private fun initData() = runBlockingTest {
        insertToDoItemUseCase(todo)
    }

    @Test
    fun `test viewmodel fetch`() = runBlockingTest {
        val testObservable = detailViewModel.todoDetailLiveData.test()
        detailViewModel.fetchData()

        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(todo)
            )
        )
    }

    @Test
    fun `test delete todo`() = runBlockingTest {
        val detailTestObservable = detailViewModel.todoDetailLiveData.test()
        detailViewModel.deleteTodo()

        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Delete
            )
        )

        val listTestObservable = listViewModel.todoListLiveData.test()
        listViewModel.fetchData()
        listTestObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Suceess(listOf() )
            )
        )

    }

    @Test
    fun `test update todo`() = runBlockingTest {
        val testObservable = detailViewModel.todoDetailLiveData.test()
        val updateTitle = "title 1 updeate"
        val updateDescription = "description 1 update"

        val updateToDo = todo.copy(
            title = updateTitle,
            description = updateDescription
        )
        detailViewModel.writeToDo(
            title = updateTitle,
            description = updateDescription
        )
        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(updateToDo)
            )
        )


    }
}