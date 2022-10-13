import { singleCategory } from "../models"

interface IDropdownItem {
	cats: singleCategory[]; 
}

export function DropdownMenu(props: IDropdownItem) {
	return(
	<li className="navigation-panel-item"><a className="text-white" href="#" aria-haspopup="true">Категории</a>
      <ul className="navigation-panel-dropdown bg-orange hidden opacity-0 absolute left-0" aria-label="submenu">
        {props.cats.map(c => <li className="navigation-panel-item clear-both w-full"><a className="text-white" href={"#"+c.id}>{c.name}</a></li>)}
      </ul>
    </li>	
)}