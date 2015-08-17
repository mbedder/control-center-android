package io.mbedder.controlcenter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class CreateComponentDialog extends DialogFragment {

    public CreateComponentDialog() {

    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View createComponentView = inflater.inflate(R.layout.dialog_create_component, null);

        builder.setView(createComponentView)
                .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText nameField = (EditText) createComponentView.findViewById(R.id.createComponentFieldName);

                        try {
                            Editable nameFieldEditable;
                            try {
                                nameFieldEditable = nameField.getText();
                            } catch(NullPointerException e) {
                                throw new Exception("Unable to read name text");
                            }

                            ComponentDAO.addComponent(new Component(nameFieldEditable.toString(), false, 0, 1), getActivity());
                        } catch (Exception e) {
                            ErrorDialog errorDialog = new ErrorDialog();
                            errorDialog.setErrorMessage(e.toString());

                            errorDialog.show(getActivity().getSupportFragmentManager(), "error");
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        CreateComponentDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

}
