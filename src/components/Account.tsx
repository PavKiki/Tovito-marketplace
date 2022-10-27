import { ToMainNavPanel } from "./ToMainNavPanel"

export function Account() {
    return (
        <div className='profile-page'>
            <ToMainNavPanel></ToMainNavPanel>
            <div className='entrance-window'>
                <div id="wrapper">
                    <form id="signin" method="" action="">
                        <input type="text" id="user" name="user" placeholder="Имя пользователя" />
                        <input type="password" id="pass" name="pass" placeholder="Пароль" />
                        <button type="submit">&#9998;</button>
                        <p>Забыли пароль?<a href="#"> тык</a></p>
                    </form>
                </div>
            </div>
        </div>
    )
}