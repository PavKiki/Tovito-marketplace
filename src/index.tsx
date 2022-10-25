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
import { Account } from './components/Account';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

root.render(
  <BrowserRouter>
  <Routes>
    <Route path="/" element={<App />}></Route>
    <Route path="/about" element={<About/>}/>
    <Route path="/account" element={<Account/>}/>
    <Route path="/category/:categoryId" element={ <Category/> }></Route>
  </Routes>
</BrowserRouter>
);
  