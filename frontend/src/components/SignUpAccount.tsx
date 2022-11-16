import React from 'react';
import { ToMainNavPanel } from "./ToMainNavPanel";
import { Link, Navigate } from 'react-router-dom';
import axios from 'axios';
import '../css_components/sign-inup-form.css';

export function SignUpAccount() {

    const [passCoincidence, setPassCoincidence] = React.useState(false);
    const [canRedirect, setCanRedirect] = React.useState(false);

    let isRedirectAvailable: boolean = false;

    React.useEffect(() => {
        const tmpRedirect = isRedirectAvailable;
        console.log(tmpRedirect);
        setCanRedirect(tmpRedirect);
    }, [isRedirectAvailable]);

    async function registerUser(name: string, email: string, pass: string) {
        await axios.post('http://localhost:8080/user/register', 
          {
            name: `${name}`,
            email: `${email}`,
            password: `${pass}`,
          })
          .then((response) => {
            console.log(response);
            setCanRedirect(true);
          })
          .catch((error) => {
            console.log(error);
            setCanRedirect(false);
        });
    }

    async function checkEntered() {
        isRedirectAvailable = false;
        
        let pass: string = (document.getElementById("newPass") as HTMLInputElement).value;
        let rPass: string = (document.getElementById("newPassRepeat") as HTMLInputElement).value;
        
        if (pass !== rPass) {
            setPassCoincidence(true);
            return;
        }
        else setPassCoincidence(false);

        let email: string = (document.getElementById("email") as HTMLInputElement).value;
        let name: string = (document.getElementById("user") as HTMLInputElement).value;
        registerUser(name, email, pass);
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
                        {/* {emailExistance && <p className='sign-error'>Пользователь с данной электронной почтой уже существует!</p>} */}
                        <p className='sign-text'><Link className='sign-link' to="/account">У меня уже есть аккаунт</Link></p>
                        <button className='top-15' type="submit" onClick={() => {checkEntered();}}>&#9998;</button>
                        {canRedirect && <Navigate replace to="/account"/>}
                    </form>
                </div>
            </div>
        </div>
    )
}