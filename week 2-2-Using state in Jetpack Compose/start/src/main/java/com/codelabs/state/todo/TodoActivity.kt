/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codelabs.state.todo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.codelabs.state.ui.StateCodelabTheme

class TodoActivity : AppCompatActivity() {

    val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateCodelabTheme {
                Surface {
                    // TODO: build the screen in compose
                    TodoActivityScreen(todoViewModel)
                }
            }
        }
    }

    /**
     * observeAsState변경 될 때마다 업데이트 LiveData되는 State객체를 관찰 하고 반환합니다 LiveData.
    컴포저블이 컴포지션에서 제거되면 자동으로 관찰을 중지합니다.
     */
    @Composable
    private fun TodoActivityScreen(todoViewModel: TodoViewModel) {
        Column {
            TodoItemInput(onItemComplete = todoViewModel::addItem)
            TodoScreen(
                items = todoViewModel.todoItems,
                onAddItem = todoViewModel::addItem,
                onRemoveItem = todoViewModel::removeItem,
                onStartEdit = todoViewModel::onEditItemSelected,
                onEditItemChange = todoViewModel::onEditItemChange,
                onEditDone = todoViewModel::onEditDone
            )
        }

    }

    @Preview
    @Composable
    fun Preview() {
        StateCodelabTheme {
            TodoActivityScreen(todoViewModel)
        }
    }
}


