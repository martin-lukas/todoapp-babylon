import React, { useEffect, useState } from 'react'
import { Todo, addTodo, deleteAllTodos, deleteTodo, getTodos } from './service/todoService'
import './App.css'

function App() {
  const [todos, setTodos] = useState<Todo[]>([])
  const [newTodo, setNewTodo] = useState<string>("")

  async function fetchTodos() {
    setTodos(await getTodos())
  }

  useEffect(() => {
    fetchTodos()
  }, [])

  async function submitAddTodo() {
    await addTodo({id: null, content: newTodo})
    await fetchTodos()
    setNewTodo('')
  }

  async function submitDeleteTodo(todo: Todo) {
    await deleteTodo(todo)
    await fetchTodos()
    setNewTodo('')
  }

  async function submitDeleteAllTodos() {
    await deleteAllTodos()
    await fetchTodos()
    setNewTodo('')
  }

  return (
    <div>
      <h1>TODO App</h1>
      <section className="add-todo-section">
        <label htmlFor="new-todo">New todo:</label>
        <input
          id="new-todo"
          type="text"
          value={newTodo}
          onChange={e => setNewTodo(e.target.value)}
          onKeyUp={e => e.key === 'Enter' && submitAddTodo()}
        />
        <button type="button" onClick={submitAddTodo}>
          ✔️
        </button>
      </section>
      <section className="todo-list-section">
        {todos.map(todo => (
          <div className="todo-item-group" key={todo.id}>
            <div>
              <input id="todo-item" type={"checkbox"} key={todo.id}/>
              <label htmlFor="todo-item">{todo.content}</label>
            </div>
            <button type="button" onClick={() => submitDeleteTodo(todo)}>
              X
            </button>
          </div>
        ))}
      </section>
      <section className="global-actions-section">
        <button type="button" onClick={submitDeleteAllTodos}>
          Remove All
        </button>
      </section>
    </div>
  )
}

export default App
