import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";
import { Category } from './components/Category';
import { About } from './components/About';
import { SignInAccount } from './components/SignInAccount';
import { SignUpAccount } from './components/SignUpAccount';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

root.render(
  <BrowserRouter>
  <Routes>
    <Route path="/" element={<App />}></Route>
    <Route path="/about" element={<About/>}/>
    <Route path="/account" element={<SignInAccount/>}></Route>
    <Route path="/account/sign-up" element={<SignUpAccount/>}></Route>
    <Route path="/category/:categoryId" element={ <Category/> }></Route>
  </Routes>
</BrowserRouter>
);
  