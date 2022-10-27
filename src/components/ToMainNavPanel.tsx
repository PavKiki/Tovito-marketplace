import { Link } from 'react-router-dom';
import horizontal_img from "../data/images/horizontal_logo.png";

export function ToMainNavPanel() {
    return (
      <nav className='sticky top-0 w-full'>
        <ul className="navigation-panel table-row w-max">
          <li className="navigation-panel-item text-white table-cell w-36 h-12"><Link className="h-full" to={'/'}>На главную</Link></li>
          <li className="navigation-panel-item-space table-cell w-full" onClick={() => window.scroll(0,0)}>
            <img className="logo-navpanel" src={horizontal_img}></img>
          </li>
        </ul>
      </nav>
    )
}