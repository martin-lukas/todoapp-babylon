import React, { useEffect, useState } from 'react'
import { addTodo, deleteAllTodos, deleteTodo, getTodos } from './service/todo'
import { Todo } from './model/Todo'
import './App.css'

function App() {
  const [todos, setTodos] = useState<Todo[]>([])
  const [newTodo, setNewTodo] = useState<string>("")

  useEffect(() => {
    (async () => {
      const response = await getTodos()
      const data = await response.json()
      setTodos(data)
    })()
  }, []);

  async function submitAddTodo() {
    await addTodo({id: null, content: newTodo})
    const response = await getTodos()
    const data = await response.json() 
    setTodos(data)
    setNewTodo('')
  }

  async function submitDeleteTodo(todo: Todo) {
    await deleteTodo(todo)
    const response = await getTodos()
    const data = await response.json() 
    setTodos(data)
    setNewTodo('')
  }

  async function submitDeleteAllTodos() {
    await deleteAllTodos()
    const response = await getTodos()
    const data = await response.json() 
    setTodos(data)
    setNewTodo('')
  }

  async function handleInputKeyUp(event: React.KeyboardEvent) {
    if (event.key === 'Enter') {
      await submitAddTodo()
    }
  }

  function handleInputChange(event: React.ChangeEvent<HTMLInputElement>) {
    setNewTodo(event.target.value)
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
          onChange={handleInputChange}
          onKeyUp={handleInputKeyUp}
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
