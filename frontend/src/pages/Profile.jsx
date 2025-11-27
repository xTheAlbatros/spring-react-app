import React, { useEffect, useState } from 'react'
import { Card, Form, Button, Alert } from 'react-bootstrap'
import { useAuth } from '../contexts/AuthContext.jsx'
import api from '../api/axios'

export default function Profile() {
    const { user, refreshMe, logout } = useAuth()

    const [form, setForm] = useState({ name: '', surname: '' })
    const [profileOk, setProfileOk] = useState(null)
    const [profileErr, setProfileErr] = useState(null)

    const [pw, setPw] = useState({
        currentPassword: '',
        newPassword: '',
        confirmPassword: '',
    })
    const [pwOk, setPwOk] = useState(null)
    const [pwErr, setPwErr] = useState(null)

    useEffect(() => {
        if (user) {
            setForm({
                name: user.name || '',
                surname: user.surname || '',
            })
        }
    }, [user])

    const chProfile = (k) => (e) => {
        setForm(s => ({ ...s, [k]: e.target.value }))
        if (profileErr) setProfileErr(null)
        if (profileOk) setProfileOk(null)
    }

    const chPw = (k) => (e) => {
        setPw(s => ({ ...s, [k]: e.target.value }))
        if (pwErr) setPwErr(null)
        if (pwOk) setPwOk(null)
    }

    const saveProfile = async (e) => {
        e.preventDefault()
        setProfileOk(null)
        setProfileErr(null)

        try {
            await api.put('/api/user/profile', {
                name: form.name,
                surname: form.surname,
            })
            await refreshMe()
            setProfileOk('Zapisano dane profilu.')
        } catch {
            setProfileErr('Nie udało się zapisać profilu.')
        }
    }

    const changePw = async (e) => {
        e.preventDefault()
        setPwOk(null)
        setPwErr(null)

        if (!pw.newPassword || !pw.confirmPassword) {
            setPwErr('Nowe hasło i jego potwierdzenie są wymagane.')
            return
        }
        if (pw.newPassword.length < 5 || !/\d/.test(pw.newPassword)) {
            setPwErr('Nowe hasło musi mieć co najmniej 5 znaków i zawierać co najmniej jedną cyfrę.')
            return
        }
        if (pw.newPassword !== pw.confirmPassword) {
            setPwErr('Nowe hasła muszą być takie same.')
            return
        }

        try {
            await api.patch('/api/user/new-password', {
                currentPassword: pw.currentPassword,
                newPassword: pw.newPassword,
                confirmationPassword: pw.confirmPassword,
            })
            setPwOk('Hasło zmienione. Zaloguj się ponownie.')
            logout()
        } catch {
            setPwErr('Nie udało się zmienić hasła.')
        }
    }

    return (
        <div className="row g-3">
            <div className="col-md-6">
                <Card className="p-3">
                    <h5>Dane profilu</h5>
                    {profileOk && <Alert variant="success">{profileOk}</Alert>}
                    {profileErr && <Alert variant="danger">{profileErr}</Alert>}
                    <Form onSubmit={saveProfile}>
                        <Form.Group className="mb-2">
                            <Form.Label>Imię</Form.Label>
                            <Form.Control
                                value={form.name}
                                onChange={chProfile('name')}
                            />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Nazwisko</Form.Label>
                            <Form.Control
                                value={form.surname}
                                onChange={chProfile('surname')}
                            />
                        </Form.Group>
                        <Button type="submit">Zapisz</Button>
                    </Form>
                </Card>
            </div>

            <div className="col-md-6">
                <Card className="p-3">
                    <h5>Zmiana hasła</h5>
                    {pwOk && <Alert variant="success">{pwOk}</Alert>}
                    {pwErr && <Alert variant="danger">{pwErr}</Alert>}
                    <Form onSubmit={changePw}>
                        <Form.Group className="mb-2">
                            <Form.Label>Stare hasło</Form.Label>
                            <Form.Control
                                type="password"
                                value={pw.currentPassword}
                                onChange={chPw('currentPassword')}
                            />
                        </Form.Group>
                        <Form.Group className="mb-2">
                            <Form.Label>Nowe hasło</Form.Label>
                            <Form.Control
                                type="password"
                                value={pw.newPassword}
                                onChange={chPw('newPassword')}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Powtórz nowe hasło</Form.Label>
                            <Form.Control
                                type="password"
                                value={pw.confirmPassword}
                                onChange={chPw('confirmPassword')}
                            />
                        </Form.Group>
                        <Button type="submit">Zmień hasło</Button>
                    </Form>
                </Card>
            </div>
        </div>
    )
}