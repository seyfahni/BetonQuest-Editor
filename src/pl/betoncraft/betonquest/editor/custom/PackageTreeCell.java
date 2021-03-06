/**
 * BetonQuest Editor - advanced quest creating tool for BetonQuest
 * Copyright (C) 2015  Jakub "Co0sh" Sapalski
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pl.betoncraft.betonquest.editor.custom;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import pl.betoncraft.betonquest.editor.BetonQuestEditor;
import pl.betoncraft.betonquest.editor.controller.RootController;
import pl.betoncraft.betonquest.editor.custom.PackageTreeItem.Type;

/**
 * Custom TreeCell with dynamic context menu and background color based on selected package.
 * 
 * @author Jakub Sapalski
 */
public class PackageTreeCell extends TreeCell<String> {
	
	@Override
	protected void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		if (empty || item == null) {
			setText(null);
			setGraphic(null);
			setContextMenu(null);
			setStyle(null);
		} else {
			setText(item);
			if (getTreeItem() instanceof PackageTreeItem) {
				PackageTreeItem pti = (PackageTreeItem) getTreeItem();
				if (pti.getType() == Type.SET) {
					if (BetonQuestEditor.getInstance().getDisplayedPackage().getSet().getName().get().equals(item)) {
						setStyle("-fx-background-color: -fx-medium-light;");
					} else {
						setStyle(null);
					}
				}
				if (pti.getType() == Type.PACKAGE) {
					if (BetonQuestEditor.getInstance().getDisplayedPackage().getName().get().equals(item)) {
						setStyle("-fx-background-color: -fx-medium-light;");
					} else {
						setStyle(null);
					}
				}
				switch (pti.getType()) {
				case ROOT:
				case TYPE:
					setContextMenu(null);
					break;
				case SET:
				case PACKAGE:
					ContextMenu menu = new ContextMenu();
					MenuItem edit = new MenuItem(BetonQuestEditor.getInstance().getLanguage().getString("edit"));
					edit.setOnAction(event -> RootController.getInstance().edit((PackageTreeItem) getTreeItem()));
					MenuItem del = new MenuItem(BetonQuestEditor.getInstance().getLanguage().getString("del"));
					del.setOnAction(event -> RootController.getInstance().delete((PackageTreeItem) getTreeItem()));
					menu.getItems().addAll(edit, del);
					setContextMenu(menu);
					break;
				}
			}
		}
	}

}
