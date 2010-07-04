package hangman.domain.config;

import hangman.constants.CategoryItemProperty;

import java.util.HashMap;

public class CategoryItem extends ConfigurationItem {

	/**
	 * Stores property key value for corresponding category in properties files.
	 */
	private String name;

	public CategoryItem() {
		super();
	}

	public CategoryItem(int id, String description, String name) {
		super(id, description);
		this.name = name;
	}

	public CategoryItem(HashMap<String, String> categoryProperties) {
		if (categoryProperties.size() > 0) {
			CategoryItemProperty[] categoryPropertyNames = CategoryItemProperty
					.values();
			String categoryPropValue = null;
			for(CategoryItemProperty categoryPropName : categoryPropertyNames) {
				categoryPropValue = categoryProperties.get(categoryPropName.toString());
				if(categoryPropValue != null) {
					setProperty(categoryPropName, categoryPropValue);
				}
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("%s, Name: %s", super.toString(), this.name);
	}

	private void setProperty(CategoryItemProperty categoryProp, String propValue) {
		switch (categoryProp) {
		case ID:
			try {
				setId(Integer.parseInt(propValue));
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
			break;
		case DESCRIPTION:
			setDescription(propValue);
			break;
		case NAME:
			setName(propValue);
			break;
		}
	}
}
