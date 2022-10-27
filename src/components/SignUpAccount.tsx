import React from 'react';
import { ToMainNavPanel } from "./ToMainNavPanel";
import { Link } from 'react-router-dom';
import axios from 'axios';

export function SignUpAccount() {

    const [passCoincidence, setPassCoincidence] = React.useState(false);
    const [emailExistance, setEmailExistance] = React.useState(false);

    async function checkEmail(email: string) {
        let response: any = await axios.post('https://api.escuelajs.co/api/v1/users/is-available', 
          {
            email: `${email}`
          })
          .catch((error) => {
            console.log(error);
        });
        if (!response.data.isAvailable) setEmailExistance(true);
        else setEmailExistance(false);
    }

    async function registerUser(name: string, email: string, pass: string) {
        let response: any = await axios.post('https://api.escuelajs.co/api/v1/users/', 
          {
            name: `${name}`,
            email: `${email}`,
            password: `${pass}`
          })
          .catch((error) => {
            console.log(error);
        });
        console.log(response);
    }

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
        checkEmail(email);
        if (emailExistance) return;

        let name: string = (document.getElementById("user") as HTMLInputElement).value;
        // registerUser(name, email, pass);
        // redirect to /account
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
                        {passCoincidence && <p className='text-red'>Введенные пароли не совпадают!</p>}
                        {emailExistance && <p className='text-red'>Пользователь с данной электронной почтой уже существует!</p>}
                        <p><Link to="/account">У меня уже есть аккаунт</Link></p>
                        <button type="submit" onClick={() => checkEntered()}>&#9998;</button>
                    </form>
                </div>
            </div>
        </div>
    )
}