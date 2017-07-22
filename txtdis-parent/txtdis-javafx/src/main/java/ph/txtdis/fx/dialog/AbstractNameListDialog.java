package ph.txtdis.fx.dialog;

import static java.util.Arrays.asList;
import static ph.txtdis.type.Type.TEXT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ph.txtdis.dto.Keyed;
import ph.txtdis.fx.control.InputNode;
import ph.txtdis.fx.control.LabeledField;
import ph.txtdis.info.Information;
import ph.txtdis.service.SavedByName;
import ph.txtdis.service.UniqueNamedService;

public abstract class AbstractNameListDialog<T extends Keyed<Long>, S extends UniqueNamedService<T>> //
		extends AbstractFieldDialog<T> {

	@Autowired
	protected LabeledField<String> nameField;

	@Autowired
	protected S service;

	private void findDuplicate(String name) throws Exception {
		if (!name.isEmpty())
			service.confirmUniqueness(name);
	}

	private void verifyNameIsUnique() {
		try {
			findDuplicate(nameField.getValue());
		} catch (Exception e) {
			dialog.show(e).addParent(this).start();
			refresh();
		}
	}

	@Override
	protected List<InputNode<?>> addNodes() {
		nameField.name("Name").build(TEXT);
		nameField.onAction(e -> verifyNameIsUnique());
		return asList(nameField);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected T createEntity() {
		try {
			return ((SavedByName<T>) service).save(nameField.getValue());
		} catch (Exception | Information e) {
			resetNodesOnError(e);
			return null;
		}
	}
}
