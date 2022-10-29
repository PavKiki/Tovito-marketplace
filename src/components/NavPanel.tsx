import React from 'react';
import { singleCategory } from '../models';
import { DropdownMenu } from './Dropdown';
import { Link } from 'react-router-dom';
import '../css_components/navpanel.css';

interface navProps {
    categories: singleCategory[];
}

export function NavPanel(props: navProps) {
    return (
      <nav className='navpanel-main sticky top-0 w-full'>
        <ul className="navigation-panel table-row">
          <li className="navigation-panel-item table-cell w-24 text-white"><Link to={'/about'}>О нас</Link></li>
          <DropdownMenu cats={props.categories}></DropdownMenu>
          <li className="navigation-panel-item table-cell text-white"><Link to={'/account'}>Профиль</Link></li>
          <div className="navigation-panel-item-space" onClick={() => window.scroll(0,0)}><p className="npis-text">Tovito marketplace</p></div>
        </ul>
        <div className="navpanel-divider"></div>
      </nav>
    )
}