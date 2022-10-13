import React from 'react';
import { singleCategory } from '../models';
import { DropdownMenu } from './Dropdown';

interface navProps {
    categories: singleCategory[];
}

export function NavPanel(props: navProps) {
    const options = [
      'one', 'two', 'three'
    ];
    const defaultOption = options[0];

    return (
    <nav className="fixed">
    <ul className="navigation-panel">
    <li className="navigation-panel-item"><a className="text-white" href="#">Профиль</a></li>
    <DropdownMenu cats={props.categories}></DropdownMenu>
    <li className="navigation-panel-item"><a className="text-white" href="#">О нас</a></li>
    </ul>
    </nav>
    )
}