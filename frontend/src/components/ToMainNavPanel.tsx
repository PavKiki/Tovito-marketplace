import { Link } from 'react-router-dom';
import horizontal_img from "../data/images/horizontal_logo.png";
import '../css_components/navpanel.css';

export function ToMainNavPanel() {
    return (
      <nav className='sticky top-0 w-full'>
        <ul className="navigation-panel table-row w-max">
          <li className="navigation-panel-item text-white table-cell w-36"><Link className="h-full" to={'/'}>На главную</Link></li>
          <li className="navigation-panel-item-space table-cell" onClick={() => window.scroll(0,0)}>
            <img alt='' className="logo-navpanel" src={horizontal_img}></img>
          </li>
        </ul>
        <div className="navpanel-divider"></div>
      </nav>
    )
}