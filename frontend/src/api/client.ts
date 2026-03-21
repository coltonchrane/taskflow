const BASE_URL = '/api';

export const api = {
  get: async <T>(path: string): Promise<T> => {
    const response = await fetch(`${BASE_URL}${path}`);
    if (!response.ok) throw new Error(`API GET failed: ${response.statusText}`);
    return response.json();
  },
  post: async <T>(path: string, body: any): Promise<T> => {
    const response = await fetch(`${BASE_URL}${path}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(body),
    });
    if (!response.ok) throw new Error(`API POST failed: ${response.statusText}`);
    return response.json();
  },
  delete: async (path: string): Promise<void> => {
    const response = await fetch(`${BASE_URL}${path}`, { method: 'DELETE' });
    if (!response.ok) throw new Error(`API DELETE failed: ${response.statusText}`);
  }
};
