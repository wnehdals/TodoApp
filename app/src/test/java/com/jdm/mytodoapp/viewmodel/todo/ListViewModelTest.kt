package com.jdm.mytodoapp.viewmodel.todo

import com.jdm.mytodoapp.data.entity.ToDoEntity
import com.jdm.mytodoapp.domain.todo.InsertToDoListUseCase
import com.jdm.mytodoapp.presentation.list.ListViewModel
import com.jdm.mytodoapp.viewmodel.ViewModelTest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.koin.test.inject

/**
 * [ListViewModelTest]을 테스트하기 위한 Unit Test Class
 *
 * 1. initData()
 * 2. test viewModel fetch
 * 3. test Item Update
 * 4. test Item Delete All
 *
 */
internal class ListViewModelTest: ViewModelTest(){
    /**
     * 필요한 Usecase를
     * 1. InsertTodoListUsecase
    */
    private val viewModel: ListViewModel by inject()

    private val insertToDoListUseCase: InsertToDoListUseCase by inject()

    private val mockList = (0 until 10).map {
        ToDoEntity(
            id = it.toLong(),
            title = "title $it",
            description = "description $it",
            hasCompleted = false
        )
    }

    @Before
    fun init() {
        initData()
    }

    private fun initData() = runBlockingTest {
        insertToDoListUseCase(mockList)
    }

    // Test : 입력된 데이터를 불러와서 검증한다.

    @Test
    fun `test viewModel fetch`(): Unit = runBlockingTest {
        val testObservable = viewModel.todoListLiveData.test()
        viewModel.fetchData()
        testObservable.assertValueSequence(
            listOf(
                mockList
            )
        )
    }
}