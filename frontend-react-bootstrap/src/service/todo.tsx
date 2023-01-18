import { Todo } from "../model/Todo"

const BASE_URL = 'http://localhost:8080'
const TODO_URL = `${BASE_URL}/api/todos`

export function getTodos(): Promise<Response> {
  return fetch(TODO_URL)
}

export async function addTodo(newTodo: Todo): Promise<Response> {
  return fetch(TODO_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(newTodo)
  })
}

export function deleteTodo(todo: Todo): Promise<Response> {
  return fetch(TODO_URL, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(todo)
  })
}

export function deleteAllTodos(): Promise<Response> {
  return fetch(`${TODO_URL}/all`, { method: 'DELETE' })
}
