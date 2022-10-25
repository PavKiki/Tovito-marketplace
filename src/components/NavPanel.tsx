import React from 'react';
import { singleCategory } from '../models';
import { DropdownMenu } from './Dropdown';
import { Link } from 'react-router-dom';

interface navProps {
    categories: singleCategory[];
}

export function NavPanel(props: navProps) {
    return (
    <nav className='sticky top-0'>
    <ul className="navigation-panel">
    <li className="navigation-panel-item text-white"><Link to={'/'}>О нас</Link></li>
    <DropdownMenu cats={props.categories}></DropdownMenu>
    <li className="navigation-panel-item text-white"><Link to={'/'}>Профиль</Link></li>
    </ul>
    </nav>
    )
}