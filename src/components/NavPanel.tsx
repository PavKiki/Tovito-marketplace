import React from 'react';
import { singleCategory } from '../models';
import { DropdownMenu } from './Dropdown';
import { Link } from 'react-router-dom';

interface navProps {
    categories: singleCategory[];
}

export function NavPanel(props: navProps) {
    return (
      <nav className='sticky top-0 w-full'>
        <ul className="navigation-panel">
          <li className="navigation-panel-item text-white"><Link to={'/about'}>О нас</Link></li>
          <DropdownMenu cats={props.categories}></DropdownMenu>
          <li className="navigation-panel-item text-white"><Link to={'/account'}>Профиль</Link></li>
          <div className="navigation-panel-item-space" onClick={() => window.scroll(0,0)}><p className="npis-text">Tovito marketplace</p></div>
        </ul>
      </nav>
    )
}