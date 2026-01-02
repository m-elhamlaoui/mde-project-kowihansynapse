/**
 */
package APIMetamodel.provider;


import APIMetamodel.APIMetamodelFactory;
import APIMetamodel.APIMetamodelPackage;
import APIMetamodel.ApplicationModel;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link APIMetamodel.ApplicationModel} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ApplicationModelItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationModelItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addProjectNamePropertyDescriptor(object);
			addFrameworkPropertyDescriptor(object);
			addPythonVersionPropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Project Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addProjectNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ApplicationModel_projectName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ApplicationModel_projectName_feature", "_UI_ApplicationModel_type"),
				 APIMetamodelPackage.Literals.APPLICATION_MODEL__PROJECT_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Framework feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFrameworkPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ApplicationModel_framework_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ApplicationModel_framework_feature", "_UI_ApplicationModel_type"),
				 APIMetamodelPackage.Literals.APPLICATION_MODEL__FRAMEWORK,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Python Version feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPythonVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ApplicationModel_pythonVersion_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ApplicationModel_pythonVersion_feature", "_UI_ApplicationModel_type"),
				 APIMetamodelPackage.Literals.APPLICATION_MODEL__PYTHON_VERSION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ApplicationModel_description_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ApplicationModel_description_feature", "_UI_ApplicationModel_type"),
				 APIMetamodelPackage.Literals.APPLICATION_MODEL__DESCRIPTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(APIMetamodelPackage.Literals.APPLICATION_MODEL__ENTITIES);
			childrenFeatures.add(APIMetamodelPackage.Literals.APPLICATION_MODEL__INTERACTIONS);
			childrenFeatures.add(APIMetamodelPackage.Literals.APPLICATION_MODEL__DATABASE);
			childrenFeatures.add(APIMetamodelPackage.Literals.APPLICATION_MODEL__AUTHENTICATION);
			childrenFeatures.add(APIMetamodelPackage.Literals.APPLICATION_MODEL__API_FEATURES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns ApplicationModel.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ApplicationModel"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ApplicationModel)object).getProjectName();
		return label == null || label.length() == 0 ?
			getString("_UI_ApplicationModel_type") :
			getString("_UI_ApplicationModel_type") + " " + label;
	}


	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ApplicationModel.class)) {
			case APIMetamodelPackage.APPLICATION_MODEL__PROJECT_NAME:
			case APIMetamodelPackage.APPLICATION_MODEL__FRAMEWORK:
			case APIMetamodelPackage.APPLICATION_MODEL__PYTHON_VERSION:
			case APIMetamodelPackage.APPLICATION_MODEL__DESCRIPTION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__ENTITIES:
			case APIMetamodelPackage.APPLICATION_MODEL__INTERACTIONS:
			case APIMetamodelPackage.APPLICATION_MODEL__DATABASE:
			case APIMetamodelPackage.APPLICATION_MODEL__AUTHENTICATION:
			case APIMetamodelPackage.APPLICATION_MODEL__API_FEATURES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(APIMetamodelPackage.Literals.APPLICATION_MODEL__ENTITIES,
				 APIMetamodelFactory.eINSTANCE.createEntity()));

		newChildDescriptors.add
			(createChildParameter
				(APIMetamodelPackage.Literals.APPLICATION_MODEL__INTERACTIONS,
				 APIMetamodelFactory.eINSTANCE.createInteraction()));

		newChildDescriptors.add
			(createChildParameter
				(APIMetamodelPackage.Literals.APPLICATION_MODEL__DATABASE,
				 APIMetamodelFactory.eINSTANCE.createDatabaseConfig()));

		newChildDescriptors.add
			(createChildParameter
				(APIMetamodelPackage.Literals.APPLICATION_MODEL__AUTHENTICATION,
				 APIMetamodelFactory.eINSTANCE.createAuthenticationConfig()));

		newChildDescriptors.add
			(createChildParameter
				(APIMetamodelPackage.Literals.APPLICATION_MODEL__API_FEATURES,
				 APIMetamodelFactory.eINSTANCE.createAPIFeatures()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return APIMetamodelEditPlugin.INSTANCE;
	}

}
