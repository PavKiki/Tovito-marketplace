import React from 'react';
import { ToMainNavPanel } from "./ToMainNavPanel";
import { Link, Navigate } from 'react-router-dom';
import axios from 'axios';
import '../css_components/sign-inup-form.css';

export function SignUpAccount() {

    const [passCoincidence, setPassCoincidence] = React.useState(false);
    const [emailExistance, setEmailExistance] = React.useState(false);
    const [canRedirect, setCanRedirect] = React.useState(false);

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
            password: `${pass}`,
            avatar: "https://google.ru"
          })
          .catch((error) => {
            console.log(error);
        });
        console.log(response);
    }

    function checkEntered(): void {
        setEmailExistance(false);
        setCanRedirect(false);
        
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
        registerUser(name, email, pass);
        setCanRedirect(true);
    }

    return (
        <div className='sign-up-page'>
            <ToMainNavPanel></ToMainNavPanel>
            <div className='entrance-window'>
                <div id="wrapper">
                    <form className='before:top-22' id="signin" method="" action="" onSubmit={(e) => e.preventDefault()}>
                        <input type="text" id="email" name="email" placeholder="Электронная почта" />
                        <input type="text" id="user" name="user" placeholder="Имя пользователя" />
                        <input type="password" id="newPass" name="newPass" placeholder="Придумайте пароль" />
                        <input type="password" id="newPassRepeat" name="newPassRepeat" placeholder="Повторите пароль" />
                        {passCoincidence && <p className='sign-error'>Введенные пароли не совпадают!</p>}
                        {emailExistance && <p className='sign-error'>Пользователь с данной электронной почтой уже существует!</p>}
                        <p className='sign-text'><Link className='sign-link' to="/account">У меня уже есть аккаунт</Link></p>
                        <button className='top-15' type="submit" onClick={() => {checkEntered();}}>&#9998;</button>
                        {canRedirect && <Navigate replace to="/account"/>}
                    </form>
                </div>
            </div>
        </div>
    )
}