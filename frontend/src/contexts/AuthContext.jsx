import React, { createContext, useContext, useEffect, useState } from 'react'
import api from '../api/axios'

const AuthContext = createContext(null)

function normalizeUser(raw) {
    if (!raw) return null
    return {
        ...raw,
        first_name: raw.first_name ?? raw.name ?? '',
        last_name: raw.last_name ?? raw.surname ?? '',
    }
}

export function AuthProvider({ children }) {
    const [user, setUser] = useState(() => {
        const raw = localStorage.getItem('user')
        if (!raw) return null
        try {
            const parsed = JSON.parse(raw)
            return normalizeUser(parsed)
        } catch {
            return null
        }
    })
    const [token, setToken] = useState(() => localStorage.getItem('access_token'))
    const isAuthenticated = !!token

    const login = async (email, password) => {
        const { data } = await api.post('/api/auth/login', { email, password })
        localStorage.setItem('access_token', data.access_token)
        setToken(data.access_token)

        const me = await api.get('/api/user/token')
        const norm = normalizeUser(me.data)
        localStorage.setItem('user', JSON.stringify(norm))
        setUser(norm)
    }

    const register = async ({ email, first_name, last_name, password }) => {
        await api.post('/api/auth/register', {
            name: first_name,
            surname: last_name,
            email,
            password,
        })
    }

    const refreshMe = async () => {
        const me = await api.get('/api/user/token')
        const norm = normalizeUser(me.data)
        localStorage.setItem('user', JSON.stringify(norm))
        setUser(norm)
    }

    const logout = () => {
        localStorage.removeItem('access_token')
        localStorage.removeItem('user')
        setToken(null)
        setUser(null)
    }

    useEffect(() => {
        (async () => {
            if (localStorage.getItem('access_token') && !user) {
                try {
                    await refreshMe()
                } catch {
                    logout()
                }
            }
        })()
        // eslint-disable-next-line
    }, [])

    return (
        <AuthContext.Provider value={{ isAuthenticated, user, login, register, refreshMe, logout }}>
            {children}
        </AuthContext.Provider>
    )
}

// eslint-disable-next-line react-refresh/only-export-components
export const useAuth = () => useContext(AuthContext)