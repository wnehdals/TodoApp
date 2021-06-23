package com.jdm.mytodoapp.viewmodel.detail

import com.jdm.mytodoapp.data.entity.ToDoEntity
import com.jdm.mytodoapp.presentation.list.BaseViewModel
import com.jdm.mytodoapp.presentation.list.ListViewModel
import com.jdm.mytodoapp.presentation.list.detail.DetailMode
import com.jdm.mytodoapp.presentation.list.detail.DetailViewModel
import com.jdm.mytodoapp.presentation.list.detail.ToDoDetailState
import com.jdm.mytodoapp.viewmodel.ViewModelTest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
/**
 * [DetailViewModelForWriteTest]을 테스트하기 위한 Unit Test Class
 *
 * 1. test viewModel fetch
 * 2. test insert todo
 *
 */
internal class DetailViewModelForWriteTest: ViewModelTest() {
    private val id = 1L

    private val detailViewModel by inject<DetailViewModel> { parametersOf(DetailMode.WRITE,id) }
    private val listViewModel by inject<ListViewModel>()

    private val todo = ToDoEntity(
        id = id,
        title = "title $id",
        description = "description $id",
        hasCompleted = false
    )

    @Test
    fun `test viewModel fetch`() = runBlockingTest {
        val testObservable = detailViewModel.todoDetailLiveData.test()

        detailViewModel.fetchData()
        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Write
            )
        )
    }
    /*
    @Test
    fun `test insert todo`() = runBlockingTest {
        val detailTestObservable = detailViewModel.todoDetailLiveData.test()

        val listTestObservable = listViewModel.todoListLiveData.test()


        detailViewModel.writeToDo(
            title = todo.title,
            description = todo.description
        )
        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
            )
        )

    }
    
     */
}