import React, { useEffect, useRef, useState } from 'react'
import { Todo, addTodo, deleteAllTodos, deleteTodo, getTodos } from './service/todoService'
import './App.css'

function App() {
  const [todos, setTodos] = useState<Todo[]>([])
  const [newTodo, setNewTodo] = useState<string>("")

  const [isAppLoading, setAppLoading] = useState(false)
  const [isNewLoading, setNewLoading] = useState(false)
  const [isRemoveLoading, setRemoveLoading] = useState(false)
  const [isRemoveAllLoading, setRemoveAllLoading] = useState(false)

  const removedTodo = useRef<string | null>(null)

  function resetInput() {
    setNewTodo('')
  }

  async function fetchTodos() {
    setTodos(await getTodos())
  }

  useEffect(() => {
    (async () => {
      setAppLoading(true)

      await fetchTodos()

      setAppLoading(false)
    })()
  }, [])

  async function submitAddTodo() {
    setNewLoading(true)

    await addTodo({ id: null, content: newTodo })
    await fetchTodos()

    setNewLoading(false)
    resetInput()
  }

  async function submitDeleteTodo(todo: Todo) {
    setRemoveLoading(true)
    removedTodo.current = todo.id

    await deleteTodo(todo)
    await fetchTodos()

    setRemoveLoading(false)
    removedTodo.current = null
    resetInput()
  }

  async function submitDeleteAllTodos() {
    setRemoveAllLoading(true)

    await deleteAllTodos()
    await fetchTodos()

    setRemoveAllLoading(false)
    resetInput()
  }

  if (isAppLoading) {
    return <div className="loader"/>
  }

  return (
    <div className="container">
      <div className="app-wrapper">
        <h1>{"TODO App"}</h1>
        <section className="add-todo-section">
          <label htmlFor="new-todo">{"New todo:"}</label>
          <input
            autoFocus
            autoComplete="off"
            id="new-todo"
            type="text"
            value={newTodo}
            onChange={e => setNewTodo(e.target.value)}
            onKeyUp={e => e.key === 'Enter' && submitAddTodo()}
          />
          <button
            type="button"
            className="add-todo-btn"
            onClick={submitAddTodo}
          >
            {isNewLoading
              ? <div className="small-loader"/>
              : "✔"
            }
          </button>
        </section>
        {todos.length > 0 && (
          <>
            <section className="todo-list-section">
              {todos.map(todo => (
                <div className="todo-item-group" key={todo.id}>
                  <div>
                    <input id="todo-item" type={"checkbox"} key={todo.id}/>
                    <label htmlFor="todo-item">{todo.content}</label>
                  </div>
                  <button
                    type="button"
                    className="center-btn remove-todo-btn"
                    onClick={() => submitDeleteTodo(todo)}
                  >
                    {isRemoveLoading && removedTodo.current === todo.id
                      ? <div className="small-loader"/>
                      : "✗"
                    }
                  </button>
                </div>
              ))}
            </section>
            <section className="global-actions-section">
              <button
                type="button"
                className="remove-all-btn"
                onClick={submitDeleteAllTodos}
              >
                {isRemoveAllLoading
                  ? <div className="small-loader"/>
                  : "Remove All"
                }
              </button>
            </section>
          </>
        )}
      </div>
    </div>
  )
}

export default App
