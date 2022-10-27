import { ToMainNavPanel } from "./ToMainNavPanel"

export function About() {
    return(
        <div className="about-page">
            <ToMainNavPanel/>
            <div className="about-info-container text-center pt-6">
                <h1 className="text-5xl">About</h1>
                <p className="text-3xl pt-4">Contact number: +7 (937) 372 81-91</p>
                <p className="text-3xl">Email: abdulkin2002@gmail.com</p>
            </div>
        </div>
    )
}