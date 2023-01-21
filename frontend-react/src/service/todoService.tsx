export interface Todo {
  id: string | null;
  content: string;
}

const BASE_URL = import.meta.env.DEV
  ? 'http://localhost:8080'
  : '/'
const TODO_URL = `${BASE_URL}/api/todos`

export async function getTodos(): Promise<Todo[]> {
  const data = await fetchAPI(TODO_URL)
  return await data.json()
}

export async function addTodo(newTodo: Todo): Promise<Response> {
  return fetchAPI(TODO_URL, 'POST', JSON.stringify(newTodo))
}

export function deleteTodo(todo: Todo): Promise<Response> {
  return fetchAPI(TODO_URL, 'DELETE', JSON.stringify(todo))
}

export function deleteAllTodos(): Promise<Response> {
  return fetchAPI(`${TODO_URL}/all`, 'DELETE')
}

type HttpMethod = 'GET' | 'POST' | 'PUT' | 'DELETE'

async function fetchAPI(url: string, method: HttpMethod = 'GET', body?: string): Promise<Response> {
  return fetch(url, {
    method,
    headers: {
      ...(method !== 'GET' && { 'Content-Type': 'application/json' })
    },
    body
  })
}
