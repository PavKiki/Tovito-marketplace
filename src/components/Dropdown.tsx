import { Link } from "react-router-dom";
import { singleCategory } from "../models"

interface IDropdownItem {
	cats: singleCategory[]; 
}

export function DropdownMenu(props: IDropdownItem) {
  let key: number = 0;

	return(
	<li className="navigation-panel-item text-white "><Link to={"/"} aria-haspopup="true">Категории</Link>
      <ul className="navigation-panel-dropdown bg-orange hidden opacity-0 absolute left-0" aria-label="submenu">
        {props.cats.map(c => <Link to={`/category/${c.id}`} className="navigation-panel-item clear-both w-full text-white" key={key++}>{c.name}</Link>)}
      </ul>
    </li>	
)}