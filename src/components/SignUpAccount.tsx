import React from 'react';
import { ToMainNavPanel } from "./ToMainNavPanel"
import { Link } from 'react-router-dom';
import axios from 'axios';

export function SignUpAccount() {

    const [passCoincidence, setPassCoincidence] = React.useState(false);
    const [emailExistance, setEmailExistance] = React.useState(false);

    function checkEntered(): void {
        setEmailExistance(false);
        let pass: string = (document.getElementById("newPass") as HTMLInputElement).value;
        let rPass: string = (document.getElementById("newPassRepeat") as HTMLInputElement).value;
        if (pass !== rPass) {
            setPassCoincidence(true);
            return;
        }
        else setPassCoincidence(false);

        let email: string = (document.getElementById("email") as HTMLInputElement).value;
        axios.post('https://api.escuelajs.co/api/v1/users/is-available', {
            email: `${email}`
          })
          .then((response) => {
                if (!response.data.isAvailable) setEmailExistance(true);
                else setEmailExistance(false);
            })
          .catch(function (error) {
            console.log(error);
          });
    }

    return (
        <div className='sign-up-page'>
            <ToMainNavPanel></ToMainNavPanel>
            <div className='entrance-window'>
                <div id="wrapper">
                    <form id="signin" method="" action="" onSubmit={(e) => e.preventDefault()}>
                        <input type="text" id="email" name="email" placeholder="Электронная почта" />
                        <input type="text" id="user" name="user" placeholder="Имя пользователя" />
                        <input type="password" id="newPass" name="newPass" placeholder="Придумайте пароль" />
                        <input type="password" id="newPassRepeat" name="newPassRepeat" placeholder="Повторите пароль" />
                        {passCoincidence && <p>Введенные пароли не совпадают!</p>}
                        {emailExistance && <p>Пользователь с данной электронной почтой уже существует!</p>}
                        <p><Link to="/account">У меня уже есть аккаунт</Link></p>
                        <button type="submit" onClick={() => checkEntered()}>&#9998;</button>
                    </form>
                </div>
            </div>
        </div>
    )
}