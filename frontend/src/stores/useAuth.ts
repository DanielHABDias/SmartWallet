import { defineStore } from 'pinia';
import { httpClient } from '../services/httpClient';

export const useAuth = defineStore('auth', () => {
    const token = localStorage.getItem('token') || '';
    const isAuthenticated = !!token;

    async function login(email: string, password: string){
        try{
            const response = await httpClient.post('/auth/login', { login: email, password });
            if (response.data.token) {
                localStorage.setItem('token', response.data.token);
            }
        } catch(error: any) {
            logout();      
        }
    }

    function logout() {
        localStorage.removeItem('token');
    }

    async function register(name: string, email: string, password: string) {
        try{
            const response = await httpClient.post('/auth/register', { name, email, password });
            if (response.data.token) {
                localStorage.setItem('token', response.data.token);
            }
        } catch(error) {
            logout();       
        }
    }

    return {
        token,
        isAuthenticated,
        login,
        register
    };
});