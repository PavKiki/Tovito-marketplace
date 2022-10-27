import { Link } from "react-router-dom"
import { ToMainNavPanel } from "./ToMainNavPanel"

export function SignInAccount() {
    return (
        <div className='sign-in-page'>
            <ToMainNavPanel></ToMainNavPanel>
            <div className='entrance-window'>
                <div id="wrapper">
                    <form id="signin" method="" action="">
                        <input type="text" id="user" name="user" placeholder="Имя пользователя" />
                        <input type="password" id="pass" name="pass" placeholder="Пароль" />
                        <button type="submit">&#9998;</button>
                        <p><Link to="/account/sign-up">Регистрация</Link></p>
                    </form>
                </div>
            </div>
        </div>
    )
}