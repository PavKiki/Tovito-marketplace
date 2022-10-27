import axios from "axios";
import React from "react";
import { Link } from "react-router-dom";
import { ToMainNavPanel } from "./ToMainNavPanel";

export function SignInAccount() {

    const [unauthorized, setUnauthorized] = React.useState(false);

    async function sendUserData(email: string, pass: string) {
        let response: any = await axios.post('https://api.escuelajs.co/api/v1/auth/login',
        {
            email: `${email}`,
            password: `${pass}`
        })
        .catch(
            (error) => {
                console.log(error);
                if (error.response.status === 401) {
                    setUnauthorized(true);                    
                }
            }
        )
        console.log(response);
        if (response) setUnauthorized(false);
    }

    function enterProfile(): void {
        let email: string = (document.getElementById("user") as HTMLInputElement).value;
        let pass: string = (document.getElementById("pass") as HTMLInputElement).value;
        sendUserData(email, pass);
    }

    return (
        <div className='sign-in-page'>
            <ToMainNavPanel></ToMainNavPanel>
            <div className='entrance-window'>
                <div id="wrapper">
                    <form id="signin" method="" action="" onSubmit={(e) => e.preventDefault()}>
                        <input type="text" id="user" name="user" placeholder="Имя пользователя" />
                        <input type="password" id="pass" name="pass" placeholder="Пароль" />
                        <button type="submit" onClick={() => enterProfile()}>&#9998;</button>
                        {unauthorized && <p>Пароль/электронная почта введены неверно!</p>}
                        <p><Link to="/account/sign-up">Регистрация</Link></p>
                    </form>
                </div>
            </div>
        </div>
    )
}